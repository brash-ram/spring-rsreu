package com.rsreu.rsreu.data.repository;

import com.rsreu.rsreu.data.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUsername(String username);

    Optional<UserInfo> findByUsernameAndPassword(String username, String password);
}
