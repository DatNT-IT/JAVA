package com.example.duanlon.utili;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptUtil {
    public static String encrypt(String origin) {
        String encrypted = BCrypt.hashpw(origin, BCrypt.gensalt());
        return encrypted;
    }

    public static boolean check(String origin, String encrypted) {

        return BCrypt.checkpw(origin, encrypted);
    }
}
