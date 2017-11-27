package com.lottery.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * User Data Transfer Object osztály. Az instance-aiból validált User Entity instance-ok lesznek.
 */
public class UserDTO {

    private String username;
    private String password;
    private String role;

    public @NotNull(message = "missing username") String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @NotNull(message = "missing password") String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ApiModelProperty(value = "allowed values:", allowableValues = "user,admin")
    public @NotNull(message = "missing role") String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
