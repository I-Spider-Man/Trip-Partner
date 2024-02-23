package com.example.demo.Repository;

import com.example.demo.Model.AdminFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdminFeedBackRepository extends MongoRepository<AdminFeedback,String> {
    List<AdminFeedback> findAllByUserId(Integer userId);
}
