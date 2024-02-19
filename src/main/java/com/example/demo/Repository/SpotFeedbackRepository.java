package com.example.demo.Repository;

import com.example.demo.Model.TouristSpotFeedback;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpotFeedbackRepository extends MongoRepository<TouristSpotFeedback,String> {
    Optional<TouristSpotFeedback> findBySpotId(Integer spotId);
    void deleteBySpotId(Integer spotId);
}
