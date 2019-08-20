package com.spring.boot.common.util;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class IdGenerator {

    public static Long generateId(){
        long second = Instant.now().toEpochMilli();
        return Long.parseLong(second + random());
    }

    public static String generateUUID(){
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    private static String random(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++){
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            System.out.println(generateId());
        }
    }

}
