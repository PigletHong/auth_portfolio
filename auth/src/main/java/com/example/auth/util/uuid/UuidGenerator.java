package com.example.auth.util.uuid;

import java.util.UUID;

public class UuidGenerator {
    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
