package com.my.travel.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.my.travel.model2.User1;

@Repository("user1Repository")
public interface User1Repository extends JpaRepository<User1, Long> {
   User1 findByEmail(String email);

}
