package com.vikash.Journal.services;


import com.vikash.Journal.entities.User;
import com.vikash.Journal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServices {


    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    private static final Logger logger= LoggerFactory.getLogger(UserServices.class);


    public boolean saveUser(User user){

        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            user.setRoles(Arrays.asList("USER"));




            userRepository.save(user);

            return true;

        }catch(Exception e){

            logger.info("Hahahhahhahahhhaha");

            return false;

        }


    }

    public List<User> getAll(){

        List<User> users=userRepository.findAll();

        return users;
    }


    public User findByUsername(String username) {


        return userRepository.findByUsername(username);
    }

    public User saveNewUser(User user) {

        return userRepository.save(user);
    }

    public User saveAdmin(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(Arrays.asList("USER","ADMIN"));

        return userRepository.save(user);

    }
}
