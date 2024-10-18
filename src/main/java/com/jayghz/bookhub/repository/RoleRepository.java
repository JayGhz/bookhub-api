package com.jayghz.bookhub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayghz.bookhub.model.entity.Role;
import com.jayghz.bookhub.model.enums.ERole;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);

}
