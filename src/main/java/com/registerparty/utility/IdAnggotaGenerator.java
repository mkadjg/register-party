package com.registerparty.utility;

import java.util.UUID;

public class IdAnggotaGenerator {
    public static String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
