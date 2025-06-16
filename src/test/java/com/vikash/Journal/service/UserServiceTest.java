package com.vikash.Journal.service;


import com.vikash.Journal.entities.User;
import com.vikash.Journal.repository.UserRepository;
import com.vikash.Journal.services.UserServices;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvSources;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    @Disabled
    @ParameterizedTest
    @ValueSource(strings={

            "vikash",
            "ram",
            "vivek"

    })
    public void testFindByUsername(String name){

        //assertEquals(4,2+2);

        User user=userRepository.findByUsername(name);
       // assertNotNull(userRepository.findByUsername(name));

        assertTrue(!user.getJournalEntries().isEmpty());

    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b,int excepted){

        assertEquals(excepted, a+b);
    }


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){


        assertTrue(userServices.saveUser(user));
    }
}
