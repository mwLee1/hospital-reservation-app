package com.codelab.hospital.user.repository;

import com.codelab.hospital.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 사용자 정보를 DB에서 조회하기 위한 JPA 레포지토리입니다.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
