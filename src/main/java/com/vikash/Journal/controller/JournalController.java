package com.vikash.Journal.controller;


import com.vikash.Journal.entities.JournalEntry;
import com.vikash.Journal.entities.User;
import com.vikash.Journal.repository.UserRepository;
import com.vikash.Journal.services.JournalServices;
import com.vikash.Journal.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalServices journalServices;


    @Autowired
    private UserServices userServices;


    @PostMapping()
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry myEntry){

        String username=SecurityContextHolder.getContext().getAuthentication().getName();

        journalServices.saveEntry(myEntry, username);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<JournalEntry>> getJournalEntriesOfUser(){


       String username= SecurityContextHolder.getContext().getAuthentication().getName();


        User user=userServices.findByUsername(username);

        List<JournalEntry> entries=user.getJournalEntries();


        return new ResponseEntity<>(entries,HttpStatus.OK);
    }


    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId){


        String username=SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userServices.findByUsername(username);

       List<JournalEntry> collect= user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());

       if(!collect.isEmpty()){

          JournalEntry entry=journalServices.getJournalById(myId);

          return new ResponseEntity<>(entry,HttpStatus.OK);
       }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteJournalById(@PathVariable ObjectId id){


        String username=SecurityContextHolder.getContext().getAuthentication().getName();

        journalServices.deleteJournalById(id, username);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable  ObjectId id, @RequestBody JournalEntry newEntry){


        String username=SecurityContextHolder.getContext().getAuthentication().getName();

        User user=userServices.findByUsername(username);


      List<JournalEntry> collect= user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());


      if(!collect.isEmpty()) {
          JournalEntry old = journalServices.getJournalById(id);

          if (old != null) {

              old.setTitle(newEntry != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
              old.setContent(newEntry != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());

              journalServices.save(old);

              return new ResponseEntity<>(old, HttpStatus.OK);


          }

      }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
