package com.vikash.Journal.controller;


import com.vikash.Journal.api.response.WeatherResponse;
import com.vikash.Journal.entities.User;
import com.vikash.Journal.repository.UserRepository;
import com.vikash.Journal.services.UserServices;
import com.vikash.Journal.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private WeatherService weatherService;




    @PutMapping()
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


    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){

       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

       userRepository.deleteByUsername(authentication.getName());

       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{city}")
    public ResponseEntity<?> greeting(@PathVariable String city){

       Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        WeatherResponse weatherResponse=weatherService.getWeather(city);

        String greeting="";

        if(weatherResponse!=null){

            greeting=", "+city+ " Temperature : "+weatherResponse.getCurrent().getTemperature();
        }

       return new ResponseEntity<>("Hi "+authentication.getName()+greeting ,HttpStatus.OK);
    }
}
