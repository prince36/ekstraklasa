package com.ekstraklasa.football.service;

public interface SecurityService {

    public void autologin(String username, String password);
    public String findLoggedInUsername();
}
