package com.example.demo.Repository;

import com.example.demo.Model.SpotPicture;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpotImageRepository extends MongoRepository<SpotPicture,String> {
    Optional<SpotPicture> findBySpotId(Integer spotId);
}
