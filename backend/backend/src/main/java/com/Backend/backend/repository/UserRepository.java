package com.Backend.backend.repository;

import java.util.Optional;

import com.Backend.backend.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


import com.Backend.backend.entity.UserEntity; 
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    Optional<UserEntity> findFirstByEmail(String username);

    Optional<UserEntity> findByUserRole(UserRole userRole);

}
