package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.TouristSpot;

public interface TouristSpotRepository extends JpaRepository<TouristSpot, Integer> {
	Optional<TouristSpot> findBySpotName(String spotName);
	List<TouristSpot> findAllByLocationPostalCode(String postalCode);
	List<TouristSpot> findAllByLocationState(String state);
	List<TouristSpot> findAllByLocationCity(String city);
	List<TouristSpot> findAllByLocationCountry(String country);

}
