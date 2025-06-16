package com.vikash.Journal.controller;


import com.vikash.Journal.entities.User;
import com.vikash.Journal.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {


    @Autowired
    private UserServices userServices;


    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){

        List<User> users=userServices.getAll();

        if(users!=null){

            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-Admin-Users")
    public User createAdminUser(@RequestBody User user){

        return userServices.saveAdmin(user);
    }
}
