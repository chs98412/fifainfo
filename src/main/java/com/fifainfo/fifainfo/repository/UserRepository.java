package com.fifainfo.fifainfo.repository;

import com.fifainfo.fifainfo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(String email);

    Integer countByUserEmail(String userEmail);

    User findByNickname(String nickname);
}
