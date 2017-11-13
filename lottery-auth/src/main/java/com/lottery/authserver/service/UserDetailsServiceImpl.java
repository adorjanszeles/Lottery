package com.lottery.authserver.service;

import com.lottery.authserver.repository.UserJPARepository;
import com.lottery.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * User service osztály a user adatbázisból való betöltéséhez
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private UserJPARepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserJPARepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsServiceImpl.LOGGER.debug("SimpleUserServiceImpl user betoltése elkezdődött");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            UserDetailsServiceImpl.LOGGER.error("Username not found: " + username);
            throw new UsernameNotFoundException("username not found:" + username);
        }

        UserDetailsServiceImpl.LOGGER.debug(
                "SimpleUserServiceImpl user betöltésének vége. username: " + user.getUsername());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                                                                      AuthorityUtils.createAuthorityList(
                                                                              "ROLE_" + user.getRole()));

    }

}