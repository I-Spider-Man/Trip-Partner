package com.example.demo.Service.ParticipantServices;

import com.example.demo.Model.Group;
import com.example.demo.Model.Participant;
import com.example.demo.Model.ParticipantRating;
import jakarta.servlet.http.Part;
import org.springframework.http.ResponseEntity;

import java.util.*;
public interface ParticipantService {
	List<Group> getAllParticipatedGroups(Integer userId);
	void setRating(Integer participantId, ParticipantRating.Ratings ratings);
	ParticipantRating getRatings(Integer participantId);
	List<Participant> getAllParticipants();
	Participant getParticipantByUserId(Integer userId);
	Participant getParticipantById(Integer participantId);
	List<Participant> getAllParticipantsByGroupId(Integer groupId);
	ResponseEntity<String> addParticipant(Participant newParticipant);
	String removeParticipantById(Integer participantId);
	ResponseEntity<String> leaveGroupByParticipantId(Integer participantId,Integer groupId);
	boolean requestJoin();
	
}
