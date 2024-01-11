package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    default User findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("해당하는 유저가 없습니다."));
    }

}
