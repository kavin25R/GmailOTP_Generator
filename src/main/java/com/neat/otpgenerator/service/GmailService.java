package com.neat.otpgenerator.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
@Service
public class GmailService {
    private JavaMailSender mailSender;
    public GmailService(JavaMailSender mailSender){
        this.mailSender=mailSender;
    }
    private final String receiver_mail="hunterkavin13@gmail.com";
    private String last_otp;
    private Instant otpCreatedAt;
    public void sendOtpReceiver(){
        String otp=String.format("%05d",new SecureRandom().nextInt(100000));
        this.last_otp=otp;
        this.otpCreatedAt=Instant.now();
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(receiver_mail);
        message.setText("Your OTP for verification: "+otp);
        message.setSubject("Otp Verification");
        mailSender.send(message);
    }

    public boolean verifyOTP(String reeivedOTP){
        if(last_otp==null || otpCreatedAt==null){
            return false;
        }
        if (Instant.now().isAfter(otpCreatedAt.plusSeconds(60))) {
            return false;
        }
        if(!reeivedOTP.equals(last_otp)){
            return false;
        }
        return true;
    }
}
