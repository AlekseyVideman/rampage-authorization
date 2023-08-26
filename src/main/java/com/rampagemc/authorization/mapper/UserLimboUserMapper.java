package com.rampagemc.authorization.mapper;

import com.rampagemc.authorization.domain.entity.LimboAuthUser;
import com.rampagemc.authorization.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserLimboUserMapper {
    public LimboAuthUser toLimboUser(User user) {
        return new LimboAuthUser(user.getUsername(), user.getPassword());
    }

    public User toUser(LimboAuthUser limboAuthUser) {
       return new User(limboAuthUser.getNickname(), limboAuthUser.getHash());
    }
}
