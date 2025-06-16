package com.vikash.Journal.controller;


import com.vikash.Journal.entities.User;
import com.vikash.Journal.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserServices userServices;


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){

        try{
            userServices.saveUser(user);

            return new ResponseEntity<>(user,HttpStatus.CREATED);

        }catch(Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }



    }

}
