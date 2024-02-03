package com.example.demo.Service.TouristSpot;

import com.amazonaws.services.apigateway.model.Op;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.example.demo.Model.Event;
import com.example.demo.Model.SpotPicture;
import com.example.demo.Model.TouristSpot;
import com.example.demo.Repository.SpotImageRepository;
import com.example.demo.Repository.TouristSpotRepository;
import com.example.demo.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TouristSpotServiceImpl implements TouristSpotService,SpotPicturesServices{
    @Autowired
    private TouristSpotRepository touristSpotRepository;
    @Autowired
    private SpotImageRepository spotImageRepository;
    @Autowired
    private StorageService storageService;
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
    public ResponseEntity<?> addSpot(TouristSpot newSpot,MultipartFile spotPicture) {
        Optional<TouristSpot> touristSpot=touristSpotRepository.findBySpotName(newSpot.getSpotName());
        if(touristSpot.isPresent()){
            return ResponseEntity.badRequest().body("spot already present");
        }else{
            touristSpotRepository.save(newSpot);
            String fileName=System.currentTimeMillis()+"_"+newSpot.getSpotId()+"_"+newSpot.getSpotName()+"_Spot";
            URL spotPicUrl=storageService.uploadFile(fileName,spotPicture);

            SpotPicture.SpotPictures spotPictures=new SpotPicture.SpotPictures();
            spotPictures.setSpotPicture(spotPicUrl);

            List<SpotPicture.SpotPictures> spotPicturesList=new ArrayList<>();
            spotPicturesList.add(spotPictures);

            SpotPicture spotPicture1=new SpotPicture();
            spotPicture1.setSpotId(newSpot.getSpotId());
            spotPicture1.setSpotPicturesList(spotPicturesList);
            spotImageRepository.save((spotPicture1));
            newSpot.setSpotPicture(spotPicture1.getId());
                touristSpotRepository.save(newSpot);
                return new ResponseEntity<>("New spot added",HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<TouristSpot> getSpotBySpotName(String spotName) {
        Optional<TouristSpot> spot=touristSpotRepository.findBySpotName(spotName);
        return new ResponseEntity<>(spot.orElse(null),HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<?> uploadSpotPicture(Integer SpotId, MultipartFile file) {
//        Optional<TouristSpot> spot=touristSpotRepository.findById(SpotId);
//        if(spot.isPresent()){
//            String fileName=System.currentTimeMillis()+"_"+SpotId+"_"+spot.get().getSpotName();
//            if(storageService.uploadFile(fileName,file)){
//                spot.get().setSpotPicture(fileName);
//                touristSpotRepository.save(spot.get());
//                return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
//            }
//            return new ResponseEntity<>("Conflict on uploading picture",HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity<>("Event not found in database",HttpStatus.NOT_FOUND);
//    }
    @Override
    public String addAllSpots(List<TouristSpot> spots) {
        touristSpotRepository.saveAll(spots);
        return "Success";
    }

    @Override
    public ResponseEntity<String> removeSpotById(Integer spotId) {
        Optional<TouristSpot> spot=touristSpotRepository.findById(spotId);
        if(spot.isPresent()){
            Optional<SpotPicture> spotPicture=spotImageRepository.findBySpotId(spotId);
            spotPicture.ifPresent(spotPicture1 -> storageService.deleteFile(spotPicture1));

            touristSpotRepository.deleteById(spotId);
            return ResponseEntity.ok().body("Tourist spot with id "+spotId+" is removed successfully.");
        }
        else{
            return new ResponseEntity<>("spot not found",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<TouristSpot> getAllPopularTouristSpot() {
        List<TouristSpot> popularSpots = touristSpotRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(TouristSpot::getPeopleCount).reversed())
                .toList();

        return popularSpots.stream().limit(5).collect(Collectors.toList());
    }

    @Override
    public void addSpotPictures(Integer spotId, MultipartFile file) {
        Optional<TouristSpot> spot=touristSpotRepository.findById(spotId);
        if(spot.isPresent()){
            Optional<SpotPicture> spotPicture=spotImageRepository.findBySpotId(spotId);
            String fileName=System.currentTimeMillis()+"_"+spotId+"_"+spot.get().getSpotName()+"_spot";
            if(spotPicture.isPresent() ){
                URL pictureUrl=storageService.uploadFile(fileName,file);
                SpotPicture.SpotPictures spotPictures=new SpotPicture.SpotPictures();
                spotPictures.setSpotPicture(pictureUrl);
                spotPicture.get().setSpotPicturesList(spotPictures);
                spotImageRepository.save(spotPicture.get());
            }
        }
    }

    @Override
    public List<SpotPicture.SpotPictures> getSpotPictureById(Integer spotId) {
        Optional<SpotPicture> spotPicture=spotImageRepository.findBySpotId(spotId);
        if(spotPicture.isPresent()){
            return spotPicture.get().getSpotPicturesList();
        }
        return null;
    }
}
