package com.kashyap.moviereview.service.impl;

import com.kashyap.moviereview.dto.UserDto;
import com.kashyap.moviereview.model.Role;
import com.kashyap.moviereview.model.User;
import com.kashyap.moviereview.repository.UserRepository;
import com.kashyap.moviereview.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean save(final User user) {
        // put validations here
        final User result = userRepo.save(user);
        return result != null;
    }

    @Override
    public List<User> getAll() {
        final List<User> users = userRepo.findAll();

        if (CollectionUtils.isEmpty(users)) {
            return new ArrayList<>();
        }

        return users;
    }

    @Override
    public User getById(final Long id) {
        final Optional<User> user = userRepo.findById(id);
        return user.orElse(null);
    }

    @Override
    public User getByUsername(final String username) {
        if (checkEmpty(username)) {
           return null;
        }

        final Optional<User> user =userRepo.findByUsername(username);
        return  user.orElse(null);
    }

    @Override
    public boolean update(final String username, final UserDto dto) {
        if(checkEmpty(username)) {
            return false;
        }

        final User user = this.getByUsername(username);
        fillUser(dto, user);
        return this.save(user);
    }

    @Override
    public boolean remove(final String username) {
        if(checkEmpty(username)) {
            return false;
        }

        return userRepo.deleteByUsername(username);
    }

    private void fillUser(final UserDto dto,  final User user) {
        if (dto == null) {
            return;
        }

        if (StringUtils.isNotBlank(dto.getFirstname())) user.setFirstname(dto.getFirstname());
        if (StringUtils.isNotBlank(dto.getLastname())) user.setLastname(dto.getLastname());
        if (StringUtils.isNotBlank(dto.getEmail())) user.setEmail(dto.getEmail());
        if (dto.getMobileNo() != 0L) user.setMobileNo(dto.getMobileNo());

        if (StringUtils.isNotBlank(dto.getRole())) {
            final String role = dto.getRole().toLowerCase();
            switch (role) {
                case "admin":
                    user.setRole(Role.ADMIN);
                    break;
                default:
                    user.setRole(Role.SUBSCRIBER);
            }
        }
    }

    private boolean checkEmpty(String value) {
        if(StringUtils.isBlank(value)) {
            return true;
        }
        return false;
    }

}
