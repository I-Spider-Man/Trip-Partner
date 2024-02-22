package com.example.demo.Repository;

import com.example.demo.Model.AdminFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminFeedBackRepository extends MongoRepository<AdminFeedback,String> {
    List<AdminFeedback> findAllByUserId(Integer userId);
}
