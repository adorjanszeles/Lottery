package com.lottery.authserver.repository;

import com.lottery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserJPARepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
