package com.vikash.Journal.scheduler;

import com.vikash.Journal.entities.JournalEntry;
import com.vikash.Journal.entities.User;
import com.vikash.Journal.repository.UserRepositoryImpl;
import com.vikash.Journal.services.EmailService;
import com.vikash.Journal.services.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class UserScheduler {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;


    @Scheduled(cron="0 0 0/1 1/1 * ? *")
    public void fetchUsersAndSendMail(){

        List<User> users=userRepository.getUserForSA();

        for(User user:users){

            List<JournalEntry> journalEntries=user.getJournalEntries();

            List<String> filteredEntries=journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());

            

           String entry= String.join("",filteredEntries);

           String sentiment=sentimentAnalysisService.getSentiment(entry);

           emailService.sendMail(user.getEmail(),"Sentiment for last 7 days", sentiment);

        }





    }
}
