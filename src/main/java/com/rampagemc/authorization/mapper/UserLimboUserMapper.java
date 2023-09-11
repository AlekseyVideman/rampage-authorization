package com.rampagemc.authorization.mapper;

import com.rampagemc.authorization.domain.entity.LimboAuthUser;
import com.rampagemc.authorization.domain.entity.User;
import com.rampagemc.authorization.service.GenerateBukkitUniqueIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLimboUserMapper {

    private final GenerateBukkitUniqueIdService uuidService;

    public LimboAuthUser toLimboUser(User user) {
        return new LimboAuthUser(user.getUsername(), user.getPassword(), uuidService.generateId(user.getUsername()));
    }

    public User toUser(LimboAuthUser limboAuthUser) {
       return new User(limboAuthUser.getNickname(), limboAuthUser.getHash());
    }
}
