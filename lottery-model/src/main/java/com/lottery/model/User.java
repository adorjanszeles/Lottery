package com.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * User objektum
 */
@Entity
@Table(name = "LOTTERY_USERS")
public class User {

    private Long id;
    private String username;
    private String password;
    private String role;

    public User() {
    }

    @Column(name = "password")
    @NotNull(message = "missing password")
//    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "username")
    @NotNull(message = "missing username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id()
    @GeneratedValue()
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "role")
    @NotNull(message = "missing role")
    @ApiModelProperty(value = "allowed values:", allowableValues = "user,admin")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}