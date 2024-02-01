package com.example.demo.Repository;

import com.example.demo.Model.GroupMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface MessagesRepository extends MongoRepository<GroupMessage,Integer> {
    Optional<GroupMessage> findByGroupId(Integer groupId);
}
