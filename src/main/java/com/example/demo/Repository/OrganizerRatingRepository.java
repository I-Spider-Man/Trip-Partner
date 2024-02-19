package com.example.demo.Repository;

import com.example.demo.Model.OrganizerRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizerRatingRepository extends MongoRepository<OrganizerRating,String> {
    Optional<OrganizerRating> findByOrganizerId(Integer organizerId);
}
