package com.example.wallpaperservice.utils;

import java.security.SecureRandom;

public class Utils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static String findLengthInKbMb(String sizeInMb){
        if(sizeInMb.length() < 7)
            return sizeInMb+" MB";
        if(sizeInMb.startsWith("0.")){
            return sizeInMb.substring(2, 5)+" KB";
        }
        else{
            StringBuilder str1 = new StringBuilder();
            boolean found = false;
            for(char c : sizeInMb.toCharArray()){
                if(found){
                    str1.append(c);
                    break;
                }
                if(c != '.'){
                    str1.append(c);
                }
                else{
                    str1.append(c);
                    found = true;
                }
            }
            return str1+" MB";
        }
    }
}
