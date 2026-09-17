package com.neat.otpgenerator.controller;

import com.neat.otpgenerator.service.GmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GmailController {
    private GmailService gmailService;
    public GmailController(GmailService gmailService){
        this.gmailService=gmailService;
    }
    @GetMapping("/sendOTP")
    public ResponseEntity<String> sendOTP(){
        try{
            gmailService.sendOtpAdmin();
            return ResponseEntity.ok("otp send to reciver");
        }
        catch (Exception ex){
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }
}
