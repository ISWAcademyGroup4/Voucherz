package com.iswAcademy.Voucherz.controller;

import com.iswAcademy.Voucherz.audit.CustomMessage;
import com.iswAcademy.Voucherz.audit.MessageSender;
import com.iswAcademy.Voucherz.controller.service.JwtAuthenticationResponse;
import com.iswAcademy.Voucherz.controller.model.LoginInRequest;
import com.iswAcademy.Voucherz.controller.service.Response;
import com.iswAcademy.Voucherz.controller.model.UserRegistrationRequest;
import com.iswAcademy.Voucherz.dao.IUserDao;
import com.iswAcademy.Voucherz.domain.ActivationToken;
import com.iswAcademy.Voucherz.mailservice.IMailService;
import com.iswAcademy.Voucherz.mailservice.Mail;
import com.iswAcademy.Voucherz.security.util.JwtTokenProvider;
import com.iswAcademy.Voucherz.domain.RoleName;
import com.iswAcademy.Voucherz.controller.model.UpdateUserRequest;
import com.iswAcademy.Voucherz.domain.User;
import com.iswAcademy.Voucherz.service.IActivationTokenService;
import com.iswAcademy.Voucherz.service.IUserService;
import com.iswAcademy.Voucherz.util.TokenGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Logger;

@Api(value = "User Authentication Resource")
@RestController
@RequestMapping("/auth")
public class AuthController {

    protected Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserService userService;

    @Autowired
    IUserDao userDao;

    @Autowired
    private IMailService mailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    MessageSender messageSender;

    @Autowired
    IActivationTokenService iActivationTokenService;

    @ApiOperation(value = "Returns JWT Token to an authenticated user")
    @ApiResponses(
            value={ @ApiResponse(code=201, message = "Created")}
    )
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginInRequest loginInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginInRequest.getEmail(),
                        loginInRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        logger.info(String.format("signin.authenticateUser(%s)", jwt));
        CustomMessage message = new CustomMessage();
        message.setDescription("User with email Address " + loginInRequest.getEmail() + " logged in.");
        message.setRole("Role_User");
        message.setEvent("logged into his account.");
        message.setEventdate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toString());
        messageSender.sendMessage(message);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
    @ApiOperation(value = "Users can signup via this endpoint")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRegistrationRequest request, HttpServletRequest httpServletRequest) {
        User user = new User();

        if (userService.findUser(user.getEmail()) != null) {
            return new ResponseEntity(new Response("400", "Email Already in use"),
                    HttpStatus.BAD_REQUEST);
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCompanySize(request.getCompanySize());
        user.setRole(RoleName.ROLE_USER.toString());
        user.setActive(false);
        user.setDateCreated(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        User result = userService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/auth/{email}")
                .buildAndExpand(result.getEmail()).toUri();
        logger.info(String.format("Signup.registerUser(%s)", user));

        //        sending a message to the Rabbitmq queue
        CustomMessage message = new CustomMessage();
        message.setDescription("User with email Address " + user.getEmail() + " logged in.");
        message.setRole(user.getRole());
        message.setEvent("Created an Account.");
        message.setEventdate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toString());
        messageSender.sendMessage(message);

        ActivationToken activationToken = new ActivationToken();
        activationToken.setActivationToken(TokenGenerator.TokenGenerator());
        activationToken.setExpiryDate(30);
        activationToken.setEmail(request.getEmail());
        iActivationTokenService.createActivationToken(activationToken);
        Mail mail = new Mail();
        mail.setFrom("Voucherz@gmail.com");
        mail.setSubject("Click the link below to activate your account");
        mail.setTo(user.getEmail());
        Map<String, Object> model = new HashMap<>();
        model.put("activationToken", activationToken);
        model.put("user", user);
        model.put("signature", "https://Voucherzng.com");
        String url = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort();
        model.put("resetUrl", url + "/login?token=" + activationToken.getActivationToken());
        mail.setModel(model);
        mailService.sendEmail(mail);
        return ResponseEntity.created(location).body(new Response("201", "created"));
    }

    @ApiOperation(value = "Admin sign up endpoint")
    @PostMapping("/admin")
    public ResponseEntity<?> adminsignup(@Valid @RequestBody UserRegistrationRequest request, HttpServletRequest httpServletRequest) {
        User user = new User();

        if (userService.findUser(user.getEmail()) != null) {
            return new ResponseEntity(new Response("400", "Email Already in use"),
                    HttpStatus.BAD_REQUEST);
        }
//Creating Admin's account
        user.setFirstName(request.getFirstName().toUpperCase());
        user.setLastName(request.getLastName().toUpperCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail().toLowerCase());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCompanySize(request.getCompanySize());
        user.setRole(RoleName.ROLE_ADMIN.toString());
        user.setActive(false);
        user.setDateCreated(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        User result = userService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/auth/{email}")
                .buildAndExpand(result.getEmail()).toUri();
        logger.info(String.format("admin.registerUser(%s)", user));

        //        sending a message to the queue
        CustomMessage message = new CustomMessage();
        message.setDescription("User with email Address " + user.getEmail() + " created an account.");
        message.setRole(user.getRole());
        message.setEvent("Created an Account");
        message.setEventdate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toString());
        messageSender.sendMessage(message);

//sending activation mail
        ActivationToken activationToken = new ActivationToken();
        activationToken.setActivationToken(TokenGenerator.TokenGenerator());
        activationToken.setExpiryDate(30);
        activationToken.setEmail(request.getEmail());
        iActivationTokenService.createActivationToken(activationToken);
        Mail mail = new Mail();
        mail.setFrom("Voucherz@gmail.com");
        mail.setSubject("Click the link below to activate your account");
        mail.setTo(user.getEmail());
        Map<String, Object> model = new HashMap<>();
        model.put("activationToken", activationToken);
        model.put("user", user);
        model.put("signature", "https://Voucherzng.com");
        String url = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort();
        model.put("resetUrl", url + "/login?token=" + activationToken.getActivationToken());
        mail.setModel(model);
        mailService.sendEmail(mail);
        return ResponseEntity.created(location).body(new Response("201", "created"));
    }

    @ApiOperation(value = "Users can update there info via this endpoint")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response updateUser(@PathVariable("id") long id, @RequestBody @Validated final UpdateUserRequest request) {
        User user = userDao.findById(id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCompanySize(request.getCompanySize());
        userService.updateUser(id, user);
        CustomMessage message = new CustomMessage();
        message.setDescription("User with email Address " + user.getEmail() + " logged in");
        message.setRole(user.getRole());
        message.setEvent("Updated Account");
        message.setEventdate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toString());
        messageSender.sendMessage(message);
        return new Response("200", "Updated");

    }

//    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
//    public Response resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail) {
//        User user = userService.findUser(userEmail);
//        if (user == null) {
//            throw new UsernameNotFoundException("Invalid Email Address");
//        }
//        String token = UUID.randomUUID().toString();
//        return null;
//    }

    @ApiOperation(value = "Returns a single user by id")
    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User findUser(@PathVariable("id") long id) {
        User user = userService.findUserById(id);
        return user;
    }

    @ApiOperation(value = "Returns a list of all users")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> findAll(@RequestParam(value="name") String name) {
        List<User> list = userService.findAll(name);
        return list;
    }

    @RequestMapping(value="/activate", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean activate(@RequestParam(value = "active") boolean active, @RequestParam(value="email") String email) {
        User user = userService.findUser(email);
        user.setActive(active);
        return true;
    }

}