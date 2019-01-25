package com.iswAcademy.Voucherz.controller;

import com.iswAcademy.Voucherz.dao.IUserDao;
import com.iswAcademy.Voucherz.domain.PasswordResetToken;
import com.iswAcademy.Voucherz.domain.User;
import com.iswAcademy.Voucherz.mailservice.IMailService;
import com.iswAcademy.Voucherz.controller.service.Response;
import com.iswAcademy.Voucherz.mailservice.Mail;
import com.iswAcademy.Voucherz.service.ITokenService;
import com.iswAcademy.Voucherz.service.IUserService;
import com.iswAcademy.Voucherz.util.ITokenGenerator;
import com.iswAcademy.Voucherz.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/password")
public class PasswordController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IMailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    IUserDao userDao;

    @RequestMapping(value="/forgot", method = RequestMethod.POST)
    public Response forgot(@RequestParam("email") String email, HttpServletRequest request) {
        User user = userDao.findByEmail(email);
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
        return new Response("200","Kindly check your mail ");
    }

    @RequestMapping(value="reset", method = RequestMethod.POST)
    public Response reset() {

        return new Response("200","Kindly check your mailservice ");
    }
}
