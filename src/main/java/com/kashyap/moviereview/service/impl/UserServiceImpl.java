package com.kashyap.moviereview.service.impl;

import com.kashyap.moviereview.model.User;
import com.kashyap.moviereview.repository.UserRepository;
import com.kashyap.moviereview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean save(User user) {
        // put validations here
        final User result = userRepo.save(user);
        return result != null;
    }

}
