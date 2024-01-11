package com.example.demo.Service.TouristSpot;

import com.example.demo.Model.TouristSpot;
import com.example.demo.Repository.TouristSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TouristSpotServiceImpl implements TouristSpotService{
    @Autowired
    private TouristSpotRepository touristSpotRepository;
    @Override
    public List<TouristSpot> getAllSpots() {
        return (List<TouristSpot>) touristSpotRepository.findAll();
    }

    @Override
    public TouristSpot getSpotById(Integer spotId) {
        Optional<TouristSpot> spot=touristSpotRepository.findById(spotId);
        return spot.orElse(null);
    }

    @Override
    public String addSpot(TouristSpot newSpot) {
        Optional<TouristSpot> touristSpot=touristSpotRepository.findBySpotName(newSpot.getSpotName());
        if(touristSpot.isPresent()){
            return "Spot Already Present";
        }else{
            touristSpotRepository.save(newSpot);
            return "Tourist spot "+newSpot.getSpotName()+" added Successfully";
        }
    }

    @Override
    public String addAllSpots(List<TouristSpot> spots) {
        touristSpotRepository.saveAll(spots);
        return "Success";
    }

    @Override
    public String removeSpotById(Integer spotId) {
        touristSpotRepository.deleteById(spotId);
        return "Tourist spot with id "+spotId+" is removed successfully.";
    }

    @Override
    public List<TouristSpot> getAllPopularTouristSpot() {
        List<TouristSpot> popularSpots = touristSpotRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(TouristSpot::getPeopleCount).reversed())
                .toList();

        return popularSpots.stream().limit(5).collect(Collectors.toList());
    }
}
