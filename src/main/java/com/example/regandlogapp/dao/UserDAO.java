package com.example.regandlogapp.dao;

import com.example.regandlogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);

}
