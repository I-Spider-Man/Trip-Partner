package com.example.demo.Repository;

import com.example.demo.Model.UserExtraDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserExtraDetailsRepostiory extends MongoRepository<UserExtraDetails,String> {
    Optional<UserExtraDetails> findByUserId(Integer userId);
    void deleteByUserId(Integer userId);
}
