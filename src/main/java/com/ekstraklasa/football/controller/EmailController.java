package com.ekstraklasa.football.controller;

import com.ekstraklasa.football.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class EmailController {

    private final EmailSender emailSender;

    public EmailController() {
        emailSender = null;
    }


    @RequestMapping("/asd")
    public String send() {

        emailSender.sendEmail("damianok3@gmail.com", "CodeCouple Newsletter", "dsfsdf");
        return "dashboard";
    }
}
