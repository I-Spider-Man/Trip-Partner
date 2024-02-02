package com.example.demo.Repository;

import com.example.demo.Model.EventPicture;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventImageRepository extends MongoRepository<EventPicture,String> {
    Optional<EventPicture> findByEventId(Integer eventId);
}
