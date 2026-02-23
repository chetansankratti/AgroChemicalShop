package com.agrocare.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendOtp(String phone, String otp) {

        String url = "https://api.textlocal.in/send/?apikey=YOUR_API_KEY"
                + "&numbers=" + phone
                + "&message=Your OTP is " + otp
                + "&sender=AGRO";

        restTemplate.getForObject(url, String.class);
    }
}