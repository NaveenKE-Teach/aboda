package com.Ptron;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class A {
    public static void main(String[] args) {
        PasswordEncoder e = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
        System.out.println(e.encode("testing"));
    }
}
