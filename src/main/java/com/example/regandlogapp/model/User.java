package com.example.regandlogapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(nullable = false, unique = true)
    private String email;

}
