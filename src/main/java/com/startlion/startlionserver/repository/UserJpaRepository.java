package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.User;
import com.startlion.startlionserver.global.exception.UnauthorizedException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    default User findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new UnauthorizedException("인증되지 않은 유저입니다."));
    }

}
