package com.ekstraklasa.football.service;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
    void sendEmail2(String to, String subject, String content);

}
