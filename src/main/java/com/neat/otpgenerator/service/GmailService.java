package com.neat.otpgenerator.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;
@Service
public class GmailService {
    private JavaMailSender mailSender;
    public GmailService(JavaMailSender mailSender){
        this.mailSender=mailSender;
    }
    private final String reciver_mail="hunterkavin13@gmail.com";
    private String last_otp;
    private Instant otpTimeStamp;
    public void sendOtpAdmin(){
        String otp=String.format("%05d",new SecureRandom().nextInt(100000));
        this.last_otp=otp;
        this.otpTimeStamp=Instant.now();
        SimpleMailMessage message=new SimpleMailMessage();
        //message.setFrom(); if you set the admin mail default
        message.setTo(reciver_mail);
        message.setText("Your OTP for verification: "+otp);
        message.setSubject("Otp Verification");
        mailSender.send(message);
    }
}
