package com.agrocare.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    private final JdbcTemplate jdbcTemplate;

    public OtpService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ✅ Generate OTP
    public String generateOtp(Long userId, String phone) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

        jdbcTemplate.update("""
            INSERT INTO otp_verifications 
            (user_id, phone, otp_code, expiry_time, verified)
            VALUES (?, ?, ?, ?, false)
        """, userId, phone, otp, expiryTime);

        // TODO: Integrate SMS API here
        System.out.println("Generated OTP: " + otp);

        return otp;
    }

    // ✅ Verify OTP
    public boolean verifyOtp(Long userId, String otp) {

        Integer count = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM otp_verifications
            WHERE user_id = ?
            AND otp_code = ?
            AND verified = false
            AND expiry_time > NOW()
        """, Integer.class, userId, otp);

        if (count != null && count > 0) {

            // Mark OTP as verified
            jdbcTemplate.update("""
                UPDATE otp_verifications
                SET verified = true
                WHERE user_id = ? AND otp_code = ?
            """, userId, otp);

            return true;
        }

        return false;
    }
}