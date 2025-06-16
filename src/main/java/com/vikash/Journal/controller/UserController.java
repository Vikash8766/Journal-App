package com.vikash.Journal.controller;


import com.vikash.Journal.entities.User;
import com.vikash.Journal.repository.UserRepository;
import com.vikash.Journal.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserServices userServices;

    @Autowired
    private UserRepository userRepository;




    @PutMapping("{username}")
    public ResponseEntity<User> updateUser( @RequestBody User newUser){

       String username= SecurityContextHolder.getContext().getAuthentication().getName();

       User userInDb=userServices.findByUsername(username);

        if(userInDb!=null){


            userInDb.setUsername(userInDb.getUsername());
            userInDb.setPassword(userInDb.getPassword());

            userServices.saveUser(userInDb);

            return new ResponseEntity<>(userInDb,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
