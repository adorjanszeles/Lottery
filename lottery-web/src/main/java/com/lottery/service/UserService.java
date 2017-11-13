package com.lottery.service;

import com.lottery.model.UserDTO;

import java.util.List;

/**
 * User-hez tartozó interfész
 */
public interface UserService {

    String saveUser(UserDTO userDTO);

    List<UserDTO> getUsers();

}
