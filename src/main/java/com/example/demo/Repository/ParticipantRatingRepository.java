package com.example.demo.Repository;

import com.example.demo.Model.OrganizerRating;
import com.example.demo.Model.ParticipantRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRatingRepository extends MongoRepository<ParticipantRating,String> {
    Optional<ParticipantRating> findByParticipantId(Integer participantId);
}
