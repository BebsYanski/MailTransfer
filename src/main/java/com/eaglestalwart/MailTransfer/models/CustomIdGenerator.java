package com.eaglestalwart.MailTransfer.models;

import java.util.Random;

public class CustomIdGenerator {
    public String generateTrackingId(){
        StringBuilder builder = new StringBuilder("TRACK-");
        builder.append(System.currentTimeMillis());
        builder.append("-");
        builder.append(getRandomString(8));
        return builder.toString();
    }

    private String getRandomString(int length){
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }
}
