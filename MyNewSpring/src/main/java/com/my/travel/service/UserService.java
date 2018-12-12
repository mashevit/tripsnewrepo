/*package com.my.travel.service;


import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.my.travel.dao.RoleRepository;
import com.my.travel.dao.User1Repository;
import com.my.travel.model2.Role1;
import com.my.travel.model2.User1;

@Service("userService")
public class UserService {

    private User1Repository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(User1Repository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User1 findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User1 user) {
    	System.out.println("ssdsdsdsds");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role1 userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role1>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

}*/