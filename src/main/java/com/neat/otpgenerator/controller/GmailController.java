package com.neat.otpgenerator.controller;

import com.neat.otpgenerator.service.GmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            gmailService.sendOtpReceiver();
            return ResponseEntity.ok("otp send to reciver");
        }
        catch (Exception ex){
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

    @PostMapping("/verifyOTP")
    public ResponseEntity<String> verifyOTP(@RequestBody String otp){
        try {
            if (gmailService.verifyOTP(otp)) {
                return ResponseEntity.ok("Welcom our Hunter's Organisation");
            }
            else{
                return ResponseEntity.badRequest().body("Invalid or expired OTP"+otp);
            }
        }
        catch (Exception ex){
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }
}
