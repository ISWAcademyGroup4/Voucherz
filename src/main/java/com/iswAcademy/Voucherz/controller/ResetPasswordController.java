package com.iswAcademy.Voucherz.controller;

import com.iswAcademy.Voucherz.audit.CustomMessage;
import com.iswAcademy.Voucherz.audit.MessageSender;
import com.iswAcademy.Voucherz.dao.IUserDao;
import com.iswAcademy.Voucherz.domain.ApiPasswordReset;
import com.iswAcademy.Voucherz.domain.PasswordReset;
import com.iswAcademy.Voucherz.domain.PasswordResetToken;
import com.iswAcademy.Voucherz.domain.User;
import com.iswAcademy.Voucherz.mailservice.IMailService;
import com.iswAcademy.Voucherz.service.ITokenService;
import com.iswAcademy.Voucherz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


//@Controller
@RestController
@RequestMapping(value = "/reset-password")
public class ResetPasswordController {

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

    @ModelAttribute("passwordResetForm")
    public PasswordReset passwordReset() {
        return new PasswordReset();
    }

    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token, Model model) {
//        PasswordResetToken passwordResetToken = tokenService.findByToken(token);
//        if (passwordResetToken == null) {
//            model.addAttribute("error", "Could not find password reset token.");
//        } else if (passwordResetToken.isExpired()) {
//            model.addAttribute("error", "Token has expired, please request a new password reset.");
//        } else {
//            model.addAttribute("token", passwordResetToken.getActivationToken());
//        }
        return "reset-password";
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public String reset(@ModelAttribute("passwordResetForm") @Valid final PasswordReset resetrequest,
                        BindingResult result,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", resetrequest);
            return "redirect:/reset-password?token=" + resetrequest.getToken();
        }

//        PasswordResetToken passwordResetToken = tokenService.findByToken(resetrequest.getActivationToken());
//        User user = passwordResetToken.getUser();
//        String updatePassword = passwordEncoder.encode(resetrequest.getPassword());
//        userService.updatePassword(user.getId(),updatePassword);
//        return "redirect:/login?resetSuccess";

        return "redirect:/api/auth/signin";
    }


    @RequestMapping(value ="/point", method = RequestMethod.PATCH)
    @Transactional
    public String reset2(@RequestParam("token") String token, @RequestBody @Valid final ApiPasswordReset resetrequest,
                        BindingResult result,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", resetrequest);
            return "redirect:/reset-password?token=" + token;
        }
//        if(resetrequest.getPassword() != resetrequest.getConfirmPassword()) {
//            return "Password does not match! \n Please check password and try again!";
//        }
        User user = userService.findByToken(token);
        String updatedPassword = passwordEncoder.encode(resetrequest.getPassword());
        user.setPassword(updatedPassword);
        userService.updatePassword(user.getEmail(),user);
        CustomMessage message = new CustomMessage();
        message.setDescription("User with email Address " + user.getEmail() + " logged in");
        message.setRole(user.getRole());
        message.setEvent("Requested to reset there password");
        message.setEventdate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toString());
        messageSender.sendMessage(message);
        return "redirect:/api/auth/signin";
    }
}
