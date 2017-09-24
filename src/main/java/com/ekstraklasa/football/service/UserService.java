package com.ekstraklasa.football.service;

import com.ekstraklasa.football.model.User;

public interface UserService {

    public void save(User user);
    public User findByUsername(String username);
}
