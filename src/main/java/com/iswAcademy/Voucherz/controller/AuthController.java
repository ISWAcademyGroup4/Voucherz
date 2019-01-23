package com.iswAcademy.Voucherz.controller;

import com.iswAcademy.Voucherz.controller.model.JwtAuthenticationResponse;
import com.iswAcademy.Voucherz.controller.model.LoginInRequest;
import com.iswAcademy.Voucherz.controller.model.Response;
import com.iswAcademy.Voucherz.controller.model.UserRegistrationRequest;
import com.iswAcademy.Voucherz.dao.util.JwtTokenProvider;
import com.iswAcademy.Voucherz.domain.RoleName;
import com.iswAcademy.Voucherz.domain.User;
import com.iswAcademy.Voucherz.service.RoleService;
import com.iswAcademy.Voucherz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    protected Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;


    @Autowired
    RoleService roleService;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginInRequest loginInRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginInRequest.getEmail(),
                        loginInRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        logger.info(String.format("signin.authenticateUser(%s)", jwt));
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request){
        User user = new User();

        if(userService.findUser(user.getEmail()) != null){
            return new ResponseEntity(new Response("400", "Email Already in use"),
                    HttpStatus.BAD_REQUEST);
        }
//Creating User's account
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword( passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCompanySize(request.getCompanySize());
        user.setRole(RoleName.ROLE_USER.toString());
        User  result= userService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/auth/{email}")
                .buildAndExpand(result.getEmail()).toUri();
        logger.info(String.format("Signup.registerUser(%s)", user));

        return ResponseEntity.created(location).body(new Response("201", "created"));

    }



}