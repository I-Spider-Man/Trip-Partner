package com.example.demo.Repository;

import com.example.demo.Model.UserImages;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserImageRepository extends MongoRepository<UserImages,String> {
    Optional<UserImages> findByUserId(Integer userId);
}
