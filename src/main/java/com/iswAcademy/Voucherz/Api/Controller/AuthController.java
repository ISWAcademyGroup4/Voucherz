package com.iswAcademy.Voucherz.Api.Controller;

import com.iswAcademy.Voucherz.Api.Controller.Model.JwtAuthenticationResponse;
import com.iswAcademy.Voucherz.Api.Controller.Model.LoginInRequest;
import com.iswAcademy.Voucherz.Api.Controller.Model.Response;
import com.iswAcademy.Voucherz.Api.Controller.Model.UserRegistrationRequest;
import com.iswAcademy.Voucherz.Dao.RoleDao;
import com.iswAcademy.Voucherz.Dao.util.JwtTokenProvider;
import com.iswAcademy.Voucherz.Model.Role;
import com.iswAcademy.Voucherz.Model.RoleName;
import com.iswAcademy.Voucherz.Model.User;
import com.iswAcademy.Voucherz.Service.RoleService;
import com.iswAcademy.Voucherz.Service.UserService;
import com.iswAcademy.Voucherz.exception.AppException;
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

import javax.management.relation.RoleInfoNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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

//        Role userRole = new Role();
//
//        if (roleService.findRole(RoleName.ROLE_USER) == null) {
//            throw new AppException(" User Role not set");
//        }
//        System.out.println("successful");
//        ArrayList<Role> roles = new ArrayList<>();
//        Role roleUser = new Role();
//        roleUser.setName(RoleName.ROLE_USER);

//        Role roleAdmin = new Role();
//        roleAdmin.setName(RoleName.ROLE_ADMIN);

//        roles.add(roleUser);
//        roles.add(roleAdmin);

        user.setRole(RoleName.ROLE_USER.toString());
        User  result= userService.createUser(user);


        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/auth/{email}")
                .buildAndExpand(result.getEmail()).toUri();
        logger.info(String.format("Signup.registerUser(%s)", user));

        return ResponseEntity.created(location).body(new Response("201", "created"));

    }

    @GetMapping("/notsecured")
    public Response testing (){
        System.out.println("hello world");
        return new Response("200","Successful");
    }


}