package com.rampagemc.authorization.service;

import java.util.UUID;

public interface GenerateBukkitUniqueIdService {
    UUID generateId(String playerName);
}
