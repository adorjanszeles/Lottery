package com.lottery.controller;

import com.lottery.model.User;
import com.lottery.model.UserDTO;
import com.lottery.repository.UserJPARepository;
import com.lottery.service.UserDestinationMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * User-hez tartozó REST végpontokat tartalmazó Controller osztály
 */
@RequestMapping("/user")
@RestController
@Api(value = "/user", description = "User Controller")
public class UserController {

    private UserJPARepository userRepository;
    private PasswordEncoder encoder;
    private UserDestinationMapper userDestinationMapper;

    @Autowired
    public UserController(UserJPARepository userRepository, PasswordEncoder encoder, UserDestinationMapper userDestinationMapper) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userDestinationMapper = userDestinationMapper;
    }

    @PostMapping("/add-user")
    @ApiOperation(value = "POST new user to database", notes = "registering a new user")
    public User addUser(@ApiParam(value = "for roles use only 'admin' or 'user'", required = true) @Valid @RequestBody UserDTO userDTO) {
        String hashedPassword = encoder.encode(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);
        User user = this.userDestinationMapper.sourceToDestination(userDTO);
        return this.userRepository.save(user);
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/users")
    @ApiOperation(value = "GET all users from database", notes = "get all users from database")
    @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }
}
