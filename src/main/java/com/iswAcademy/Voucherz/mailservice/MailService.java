package com.iswAcademy.Voucherz.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class MailService implements IMailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Override
    @Async
    public void sendEmail(Mail mail) {
        try {

            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = springTemplateEngine.process("email/email-template", context);

            messageHelper.setTo(mail.getTo());
            messageHelper.setText(html, true);
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setFrom(mail.getFrom());

            emailSender.send(mimeMessage);
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
