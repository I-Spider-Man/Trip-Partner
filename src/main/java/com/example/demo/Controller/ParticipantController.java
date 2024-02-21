package com.example.demo.Controller;

import com.example.demo.Model.Group;
import com.example.demo.Model.Participant;
import com.example.demo.Model.ParticipantRating;
import com.example.demo.Service.ParticipantServices.ParticipantService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Participant")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;
    @GetMapping("/group/{groupId}")
    public List<Participant> getAllParticipantsInGroup(@PathVariable Integer groupId){
        return participantService.getAllParticipantsByGroupId(groupId);
    }
    @GetMapping("/allGroupsParticipated/{userId}")
    public List<Group> allGroups(@PathVariable Integer userId){

        return participantService.getAllParticipatedGroups(userId);
    }
    @PostMapping("/rating/{participantId}")
    public void addRating(@PathVariable Integer participantId, @RequestBody ParticipantRating.Ratings ratings){
        participantService.setRating(participantId,ratings);
    }
    @GetMapping("/rating/{participantId}")
    public ParticipantRating getRating(@PathVariable Integer participantId){
        return participantService.getRatings(participantId);
    }
    @GetMapping("/leaveGroupByParticipantId")
    public ResponseEntity<String> leaveGroupByParticipantId(@RequestParam(value = "participantId") Integer participantId,@RequestParam(value = "groupId") Integer groupId){
        return participantService.leaveGroupByParticipantId(participantId,groupId);
    }
    @GetMapping("/userId/{userId}")
    public Participant getParticipantByUserId(@PathVariable Integer userId){
        return participantService.getParticipantByUserId(userId);
    }
    @GetMapping("/participantId/{participantId}")
    public Participant getParticipantById(@PathVariable Integer participantId){
        return participantService.getParticipantById(participantId);
    }
    @PostMapping
    public ResponseEntity<String> addParticipant(@RequestBody Participant newParticipant){
        return participantService.addParticipant(newParticipant);
    }

}
