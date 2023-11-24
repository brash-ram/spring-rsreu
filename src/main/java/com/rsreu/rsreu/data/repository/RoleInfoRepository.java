package com.rsreu.rsreu.data.repository;

import com.rsreu.rsreu.data.entity.RoleInfo;
import com.rsreu.rsreu.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleInfoRepository extends JpaRepository<RoleInfo, Long> {

    Optional<RoleInfo> findByName(RoleEnum role);
}