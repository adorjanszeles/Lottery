package com.lottery.service;

import com.lottery.model.User;
import com.lottery.model.UserDTO;
import com.lottery.repository.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Userhez tarttozó interfész implementációja
 */
@Service
public class UserServiceImpl implements UserService {

    private UserJPARepository userJPARepository;
    private PasswordEncoder encoder;
    private UserDestinationMapper userDestinationMapper;

    @Autowired
    public UserServiceImpl(UserJPARepository userJPARepository,
                           PasswordEncoder encoder,
                           UserDestinationMapper userDestinationMapper) {
        this.userJPARepository = userJPARepository;
        this.encoder = encoder;
        this.userDestinationMapper = userDestinationMapper;
    }

    @Override
    public String saveUser(UserDTO userDTO) {
        String hashedPassword = encoder.encode(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);
        User user = this.userDestinationMapper.sourceToDestination(userDTO);
        this.userJPARepository.save(user);
        return "success";
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = this.userJPARepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            UserDTO userd = this.userDestinationMapper.destinationToSource(users.get(i));
            userDTOS.add(userd);
        }
        return userDTOS;
    }

}
