package com.vikash.Journal.repository;

import com.vikash.Journal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class UserRepositoryImpl {


    @Autowired
    private MongoTemplate mongoTemplate;



    public List<User> getUserForSA(){

        Query query=new Query();

        query.addCriteria(Criteria.where("email").exists(true));
       query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("SentimentAnalyysis").is(true));

     //   query.addCriteria(Criteria.where("username").is("vikash"));

//        Criteria criteria=new Criteria();
//
//        query.addCriteria(criteria.orOperator(
//                Criteria.where("email").exists(true),
//                Criteria.where("sentimentAnalysis").is(true))
//        );
           List<User> users=mongoTemplate.find(query,User.class);

           return users;

    }
}
