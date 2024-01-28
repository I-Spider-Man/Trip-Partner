package com.example.demo.Service.ParticipantServices;

import com.example.demo.Model.Participant;
import org.springframework.http.ResponseEntity;

import java.util.*;
public interface ParticipantService {
	
	List<Participant> getAllParticipants();
	Participant getParticipantById(Integer participantId);
	List<Participant> getAllParticipantsByGroupId(Integer groupId);
	ResponseEntity<String> addParticipant(Participant newParticipant);
	String removeParticipantById(Integer participantId);
	boolean requestJoin();
	
}
