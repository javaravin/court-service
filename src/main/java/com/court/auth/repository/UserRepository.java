package com.court.auth.repository;

import com.court.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMobileNo(String mobileNo);

    Optional<User> findByUserName(String userName);
    // You can define additional methods for custom queries or operations

}
