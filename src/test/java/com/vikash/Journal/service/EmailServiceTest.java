package com.vikash.Journal.service;


import com.vikash.Journal.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;



    @Test
    public void TestMail(){

        emailService.sendMail("vikashkr8766@gmail.com","java mail testing","Hi , How are you?");


    }
}
