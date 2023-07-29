package com.rampagemc.authorization.application.repository;

import com.rampagemc.authorization.application.integration.limboauth.LimboAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationLimboUserRepository extends JpaRepository<LimboAuthUser, String> {
    Optional<LimboAuthUser> findByLowercaseNickname(String name);
    LimboAuthUser save(LimboAuthUser user);
}
