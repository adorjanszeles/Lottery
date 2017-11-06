package com.lottery.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class UserDTO {

    private String username;
    private String password;
    private String role;

    @NotNull(message = "missing username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "missing password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "missing role")
    @ApiModelProperty(value = "allowed values:", allowableValues = "user,admin")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
