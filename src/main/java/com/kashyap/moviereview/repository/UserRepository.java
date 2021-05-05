package com.kashyap.moviereview.repository;

import com.kashyap.moviereview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);
}
