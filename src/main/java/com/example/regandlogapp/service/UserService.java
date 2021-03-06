package com.example.regandlogapp.service;

import com.example.regandlogapp.model.User;

public interface UserService {

    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);

}
