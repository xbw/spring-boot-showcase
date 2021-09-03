package com.xbw.spring.boot.secure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xbw
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class PasswordEncoderTests {
    private static final String rawPassword = "xbw";

    @Test
    void abs() {
        AbstractPasswordEncoder encoder = new AbsPasswordEncoder();
        test(encoder);
    }

    @Test
    void argon2() {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        test(encoder);
    }

    @Test
    void bCrypt() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        test(encoder);
    }

    @Test
    void delegating() {
        String encodingId = "bcrypt";
        String argon2 = "argon2";
        String pbkdf2 = "pbkdf2";
        String scrypt = "scrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder());
        encoders.put(pbkdf2, new Pbkdf2PasswordEncoder());
        encoders.put(scrypt, new SCryptPasswordEncoder());
        encoders.put(argon2, new Argon2PasswordEncoder());
        PasswordEncoder encoder = new DelegatingPasswordEncoder(encodingId, encoders);
        test(encoder);
    }

    @Test
    void delegatingDefault() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        test(encoder);
    }

    @Test
    @SuppressWarnings("deprecation")
    void noop() {
        PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        test(encoder);
    }

    @Test
    void pbkdf2() {
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
        test(encoder);
    }

    /**
     * Caused by: java.lang.ClassNotFoundException: org.bouncycastle.crypto.generators.SCrypt
     */
    @Test
    void sCrypt() {
        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder();
        test(encoder);
    }

    @Test
    @SuppressWarnings("deprecation")
    void standard() {
        PasswordEncoder encoder = new org.springframework.security.crypto.password.StandardPasswordEncoder();
        test(encoder);
    }

    @Test
    @SuppressWarnings("deprecation")
    void user() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("user")
                .build();
        System.out.println(user.getPassword());
    }

    private void test(PasswordEncoder encoder) {
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoder " + encoder.getClass() + "\nEncoded Password：\t" + encodedPassword + "\n");
        boolean result = encoder.matches(rawPassword, encodedPassword);
        Assertions.assertTrue(result);
    }

    private static class AbsPasswordEncoder extends AbstractPasswordEncoder {
        protected byte[] encode(CharSequence rawPassword, byte[] salt) {
            return new byte[0];
        }
    }
}
