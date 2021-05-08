package com.kashyap.moviereview.repository;

import com.kashyap.moviereview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    @Query(value = "select * from users where username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Modifying
    @Query(value = "delete from users where username = :username", nativeQuery = true)
    @Transactional(rollbackFor = Exception.class)
    int deleteByUsername(@Param("username") String username);
}
