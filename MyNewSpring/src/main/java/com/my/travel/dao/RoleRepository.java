package com.my.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.travel.model2.Role1;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role1, Long> {
    Role1 findByRole(String role);


}