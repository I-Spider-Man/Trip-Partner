package com.example.demo.Repository;

import com.example.demo.Model.EventFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventFeedBackRepository extends MongoRepository<EventFeedback,String> {
    Optional<EventFeedback> findByEventId(Integer eventId);
    void deleteByEventId(Integer eventId);
}
