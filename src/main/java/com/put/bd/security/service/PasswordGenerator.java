package com.put.bd.security.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordGenerator {

    public String generatePassword(int len) {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String values = Capital_chars + Small_chars + numbers;

        Random rand = new Random();
        char[] password = new char[len];

        for (int i = 0; i < len; i++)
            password[i] = values.charAt(rand.nextInt(values.length()));

        System.out.println("NOWE HASLO: " + password.toString());
        return password.toString();
    }

}
