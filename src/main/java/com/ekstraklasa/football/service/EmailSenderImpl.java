package com.ekstraklasa.football.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

@Service
public class EmailSenderImpl implements EmailSender{

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendEmail(String to, String subject, String content) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();


        mail.setTo(to);
        mail.setFrom("app.dk.897@gmail.com");
        mail.setSubject(subject);
        mail.setText(content);

        javaMailSender.send(mail);
    }
}