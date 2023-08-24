package com.rampagemc.authorization.repository;

import com.rampagemc.authorization.domain.entity.LimboAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimboUserRepository extends JpaRepository<LimboAuthUser, String> {
    Optional<LimboAuthUser> findByLowercaseNickname(String name);
}
