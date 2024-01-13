package com.example.jwtsecurity.repository;

import com.example.jwtsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(String roleName);
}
