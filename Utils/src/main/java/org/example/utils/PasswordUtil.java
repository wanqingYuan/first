package org.example.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public static String getPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password="root";
        System.out.println(PasswordUtil.getPassword(password));
    }
}
