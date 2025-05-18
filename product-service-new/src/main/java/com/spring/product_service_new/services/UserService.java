package com.spring.product_service_new.services;

import com.spring.product_service_new.model.DatabaseSequence;
import com.spring.product_service_new.model.User;
import com.spring.product_service_new.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private MongoOperations mongoOperations;

    private final UserRepository userRepository;


    public Long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(Query.query(Criteria.where("id").is(seqName)),
                new Update().inc("sequence",1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSequence() : 1;
    }


    public User saveUser(String userName){

        User userData = User.builder()
                .id(generateSequence(User.USER_SEQUENCE_GENERATOR))
                .userName(userName)
                .build();

        User obj = userRepository.save(userData);
        log.info("product is saved in DB...");
        return obj;

    }
}
