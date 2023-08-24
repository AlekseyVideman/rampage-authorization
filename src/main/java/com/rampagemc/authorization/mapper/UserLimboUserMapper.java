package com.rampagemc.authorization.mapper;

import com.rampagemc.authorization.domain.entity.LimboAuthUser;
import com.rampagemc.authorization.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
//@Mapper
public class UserLimboUserMapper {
//    @Mapping(target = "lowercaseNickname", expression = "java(user.getUsername().toLowerCase(Locale.ROOT))")
//    @Mapping(target = "nickname", source = "username")
//    @Mapping(target = "hash", source = "password")
    public LimboAuthUser toLimboUser(User user) {
        return new LimboAuthUser(user.getUsername(), user.getPassword());
    }

//    @Mapping(target = "username", source = "nickname")
//    @Mapping(target = "password", source = "hash")
    public User toUser(LimboAuthUser limboAuthUser) {
       return new User(limboAuthUser.getNickname(), limboAuthUser.getHash());
    }
}
