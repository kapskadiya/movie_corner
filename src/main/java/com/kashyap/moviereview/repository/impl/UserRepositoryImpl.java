package com.kashyap.moviereview.repository.impl;

import com.kashyap.moviereview.model.User;
import com.kashyap.moviereview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

public class UserRepositoryImpl {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

//    @Override
//    public boolean save(User user) {
//
//        if (user == null) {
//            return false;
//        }
//
//        final int result = jdbcTemplate.update("insert into users (firstname, lastname, username, password, email, mobileNo, role_id) values (?, ?, ?, ?, ?, ?, ?)",
//                user.getFirstname(),
//                user.getLastname(),
//                user.getUsername(),
//                user.getPassword(),
//                user.getEmail(),
//                user.getMobileNo(),
//                user.getRole());
//
//        if (result <= 0) {
//            return false;
//        }
//
//        return true;
//    }
}
