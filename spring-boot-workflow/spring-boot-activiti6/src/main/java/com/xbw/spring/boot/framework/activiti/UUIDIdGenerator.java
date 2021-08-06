package com.xbw.spring.boot.framework.activiti;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * UUID
 */
public class UUIDIdGenerator implements IdGenerator {

    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static SecureRandom random = new SecureRandom();

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    public static String randomBase62(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return encodeBase62(randomBytes);
    }

    public static void main(String[] args) {
        String uuid = UUIDIdGenerator.uuid();
        System.out.printf("UUID: %s, Length: %s%n", uuid, uuid.length());
        System.out.printf("NextId: %s%n", new UUIDIdGenerator().getNextId());
        for (int i = 0; i < 10; i++) {
            System.out.println(UUIDIdGenerator.randomLong() + "  " + UUIDIdGenerator.randomBase62(5));
        }
    }

    public static String encodeBase62(byte[] input) {
        char[] chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
        }
        return new String(chars);
    }

    @Override
    public String getNextId() {
        return UUIDIdGenerator.uuid();
    }
}
