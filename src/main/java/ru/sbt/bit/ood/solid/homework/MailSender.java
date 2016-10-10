package ru.sbt.bit.ood.solid.homework;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private final String host;
    private final String subject;
    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    private final MimeMessage message;

    public MailSender(String host, String subject) {
        this.host = host;
        this.subject = subject;
        mailSender.setHost(host);
        message = mailSender.createMimeMessage();
    }

    public void send(String recipients, String resultingHtml) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            helper.setText(resultingHtml, true);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        mailSender.send(message);
    }
}
