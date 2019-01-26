package com.iswAcademy.Voucherz.controller;

import com.iswAcademy.Voucherz.dao.IUserDao;
import com.iswAcademy.Voucherz.domain.ForgotPassword;
import com.iswAcademy.Voucherz.domain.PasswordReset;
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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
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
    IUserDao userDao;

    @ModelAttribute("forgotPasswordForm")
    public ForgotPassword forgotPassword() {
        return new ForgotPassword();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String forgot(@ModelAttribute("forgotPasswordForm") @Valid ForgotPassword resetEmail,
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
        return "redirect:/forgot-password?success";
    }

}
