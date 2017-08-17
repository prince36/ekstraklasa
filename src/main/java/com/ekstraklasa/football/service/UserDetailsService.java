package com.ekstraklasa.football.service;

import com.ekstraklasa.football.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserDetailsService {
    User findById(Long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    void deleteAllUsers();

    List<User> findAllUsers();

    boolean isUserExist(User user);
}
