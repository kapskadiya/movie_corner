package com.kashyap.moviereview.service;

import com.kashyap.moviereview.dto.UserDto;
import com.kashyap.moviereview.model.User;

import java.util.List;

public interface UserService {

    boolean save(User user);

    List<User> getAll();

    User getById(Long id);

    User getByUsername(String username);

    boolean update(final String username, final UserDto dto);

    boolean remove(final String username);
}
