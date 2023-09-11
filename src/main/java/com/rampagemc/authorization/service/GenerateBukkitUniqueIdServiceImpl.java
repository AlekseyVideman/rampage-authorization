package com.rampagemc.authorization.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class GenerateBukkitUniqueIdServiceImpl implements GenerateBukkitUniqueIdService {
    @Override
    public UUID generateId(String playerName) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes(StandardCharsets.UTF_8));
    }
}
