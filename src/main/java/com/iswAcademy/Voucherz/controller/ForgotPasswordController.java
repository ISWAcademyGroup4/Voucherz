package com.iswAcademy.Voucherz.controller;

import com.iswAcademy.Voucherz.audit.CustomMessage;
import com.iswAcademy.Voucherz.audit.MessageSender;
import com.iswAcademy.Voucherz.dao.IUserDao;
import com.iswAcademy.Voucherz.domain.ForgotPassword;
import com.iswAcademy.Voucherz.domain.PasswordResetToken;
import com.iswAcademy.Voucherz.domain.User;
import com.iswAcademy.Voucherz.mailservice.IMailService;
import com.iswAcademy.Voucherz.mailservice.Mail;
import com.iswAcademy.Voucherz.service.ITokenService;
import com.iswAcademy.Voucherz.service.IUserService;
import com.iswAcademy.Voucherz.util.TimeFormat;
import com.iswAcademy.Voucherz.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Controller
@RestController
@RequestMapping(value = "/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IMailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    MessageSender messageSender;

    @Autowired
    IUserDao userDao;

    @ModelAttribute("forgotPasswordForm")
    public ForgotPassword forgotPassword() {
        return new ForgotPassword();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @RequestMapping(value = "/end", method = RequestMethod.POST)
    public String forgot(@RequestBody @Valid ForgotPassword resetEmail,
                         BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()){
            return "forgot-password";
        }
        User user = userDao.findByEmail(resetEmail.getEmail());
        if(user == null) {
            throw new UsernameNotFoundException("This email does not Exist ");
        }
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(TokenGenerator.TokenGenerator());
        token.setExpiryDate(30);
        token.setEmail(user.getEmail());
        tokenService.createToken(token);

        Mail mail = new Mail();
        mail.setFrom("Voucherz@gmail.com");
        mail.setSubject("Password reset request");
        mail.setTo(user.getEmail());
        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://Voucherzng.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        mailService.sendEmail(mail);
//        sending a message to the queue
        CustomMessage message = new CustomMessage();
        message.setDescription("User with email Address " + user.getEmail() + " logged in");
        message.setRole(user.getRole());
        message.setEmail(user.getEmail());
        message.setEvent("Forgot Password request");
        message.setEventdate(TimeFormat.newtime());
        messageSender.sendMessage(message);
        return "redirect:/forgot-password?success";
    }

}
