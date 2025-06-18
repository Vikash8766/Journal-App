package com.vikash.Journal.services;


import com.vikash.Journal.entities.JournalEntry;
import com.vikash.Journal.entities.User;
import com.vikash.Journal.exception.ResourceNotFoundException;
import com.vikash.Journal.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class JournalServices {


    @Autowired
    private JournalRepository journalRepository;


    @Autowired
    private UserServices userServices;







    public void saveEntry(JournalEntry myEntry, String username){


        try{

            User user=userServices.findByUsername(username);

            JournalEntry saved=journalRepository.save(myEntry);

            myEntry.setDate(LocalDateTime.now());

            user.getJournalEntries().add(saved);

            userServices.saveNewUser(user);



        }catch(Exception e){

            throw new RuntimeException("An error occured while saving the entry"+e);


        }



    }

    
    public List<JournalEntry> getAll(){

        return journalRepository.findAll();
    }

    public  JournalEntry getJournalById(ObjectId id){

            JournalEntry entry=journalRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("journal not found in server with given id"+id));


            return entry;
    }

    public void save(JournalEntry entry){

        journalRepository.save(entry);
    }

    public void deleteJournalById(ObjectId id, String username){

        User user=userServices.findByUsername(username);

        user.getJournalEntries().removeIf(x->x.getId().equals(id));

        userServices.saveNewUser(user);

        journalRepository.deleteById(id);



    }
}
