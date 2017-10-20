package com.lottery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String role;

    protected User(){}

    public User(String password, String username, String role) {
        this.password = password;
        this.username = username;
        this.role = role;
    }

}


