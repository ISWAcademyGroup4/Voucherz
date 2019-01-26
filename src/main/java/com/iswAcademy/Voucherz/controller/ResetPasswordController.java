package com.iswAcademy.Voucherz.controller;

import com.iswAcademy.Voucherz.dao.IUserDao;
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


@Controller
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
    IUserDao userDao;

    @ModelAttribute("passwordResetForm")
    public PasswordReset passwordReset() {
        return new PasswordReset();
    }

    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token, Model model) {
        PasswordResetToken passwordResetToken = tokenService.findByToken(token);
        if (passwordResetToken == null) {
            model.addAttribute("error", "Could not find password reset token.");
        } else if (passwordResetToken.isExpired()) {
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", passwordResetToken.getToken());
        }
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

//        PasswordResetToken passwordResetToken = tokenService.findByToken(resetrequest.getToken());
        User user = tokenService.findUserByToken(resetrequest.getToken());
        String updatePassword = passwordEncoder.encode(resetrequest.getPassword());
//        userService.updatePassword(user.getId(),updatePassword);
//        return "redirect:/login?resetSuccess";

        return "redirect:/api/auth/signin";
    }
}
