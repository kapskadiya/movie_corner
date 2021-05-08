package com.kashyap.moviereview.repository;

import com.kashyap.moviereview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    @Query("select * from users where username = :username")
    Optional<User> findByUsername(String username);

    @Query("delete from users where username = :username")
    boolean deleteByUsername(String username);
}
