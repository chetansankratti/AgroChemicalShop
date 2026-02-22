package com.agrocare.controller;

import com.agrocare.service.AdminService;
import com.agrocare.service.OtpService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class ForgotPasswordController {

    private final OtpService otpService;
    private final AdminService adminService;

    public ForgotPasswordController(OtpService otpService,
                                    AdminService adminService) {
        this.otpService = otpService;
        this.adminService = adminService;
    }

    // STEP 1: Send OTP
    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam Long userId,
                          @RequestParam String phone) {

        otpService.generateOtp(userId, phone);
        return "OTP Sent Successfully";
    }

    // STEP 2: Verify OTP & Reset Password
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam Long userId,
                                @RequestParam String otp,
                                @RequestParam String newPassword) {

        boolean valid = otpService.verifyOtp(userId, otp);

        if (valid) {
            adminService.resetPasswordByUserId(userId, newPassword);
            return "Password Reset Successful";
        }

        return "Invalid or Expired OTP";
    }
}