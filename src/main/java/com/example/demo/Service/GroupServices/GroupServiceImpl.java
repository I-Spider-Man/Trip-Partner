package com.example.demo.Service.GroupServices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.Organizer.OrganizerService;
import com.example.demo.Service.Scheduling;
import com.example.demo.Service.SchedulingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import javax.swing.text.html.Option;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository grpRepo;
	@Autowired
	private OrganizerRepository organizerRepository;
	@Autowired
	private MessagesRepository messagesRepository;
	@Autowired
	private UserExtraDetailsRepostiory userExtraDetailsRepostiory;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private TouristSpotRepository spotRepository;
	@Autowired
	private ParticipantRepository participantRepository;
	@Autowired
	private Scheduling scheduling;
	
 	@Override
	public List<Group> getAllGroups() {
		return (List<Group>) grpRepo.findAll();
	}

	@Override
	public String addGroup(Group newGroup) {
		List<Group> activeGrp=grpRepo.findAllByGroupStatus(GroupStatus.Active);
		Optional<Group> grp=activeGrp.stream().filter(group -> group.getOrganizerId().equals(newGroup.getOrganizerId())).findAny();
		if(!newGroup.getEventName().isEmpty()){
			Optional<Event> event=eventRepository.findByEventName(newGroup.getEventName());
			event.get().increasePeopleCount(newGroup.getParticipantsLimit());
			eventRepository.save(event.get());
		}else{
			Optional<TouristSpot> spot=spotRepository.findBySpotName(newGroup.getSpotName());
			spot.get().increasePeopleCount(newGroup.getParticipantsLimit());
			spotRepository.save(spot.get());
		}
		if(grp.isEmpty()) {
			grpRepo.save(newGroup);
			Optional<Organizer> organizer = organizerRepository.findById(newGroup.getOrganizerId());
			Optional<UserExtraDetails> userExtraDetails = userExtraDetailsRepostiory.findByUserId(organizer.get().getUserId());
			userExtraDetails.get().getOrganizedList().setOrganizedGroupId(newGroup.getGroupId());
			organizer.get().increseOrganizedCount(organizer.get().getOrganizedCount());
			organizer.get().setGroupId(newGroup.getGroupId());
			GroupMessage.Message message = new GroupMessage.Message(0, "");
			List<GroupMessage.Message> ml = new ArrayList<>();
			ml.add(message);
			GroupMessage groupMessage = new GroupMessage(newGroup.getGroupId(), ml);
			messagesRepository.save(groupMessage);
			organizerRepository.save(organizer.get());
			userExtraDetailsRepostiory.save(userExtraDetails.get());
			scheduling.addActiveGrpId(newGroup.getGroupId());
			return "GROUP SUCCESSFULLY CREATED";
//			if(grp.get().getGroupStatus() == GroupStatus.InActive) {
//				Optional<Organizer> organizer=organizerRepository.findById(grp.get().getOrganizerId());
//				Optional<UserExtraDetails> userExtraDetails=userExtraDetailsRepostiory.findByUserId(organizer.get().getUserId());
//				organizer.get().increseOrganizedCount(organizer.get().getOrganizedCount());
//				grpRepo.save(newGroup);
//				userExtraDetails.get().getOrganizedList().setOrganizedGroupId(newGroup.getGroupId());
//
//				organizer.get().setGroupId(newGroup.getGroupId());
//				GroupMessage.Message message=new GroupMessage.Message(0,"");
//				List<GroupMessage.Message> ml=new ArrayList<>();
//				ml.add(message);
//				GroupMessage groupMessage=new GroupMessage(newGroup.getGroupId(),ml);
//				messagesRepository.save(groupMessage);
//				organizerRepository.save(organizer.get());
//				userExtraDetailsRepostiory.save(userExtraDetails.get());
//				scheduling.addActiveGrpId(newGroup.getGroupId());
//				return "GROUP SUCCESSFULLY CREATED";
//			}
		}
			else {
				return "YOU ARE ALREADY ORGANIZING ONE GROUP";
			}

	}
	@Override
	public String removeGroupById(Integer groupId) {
		Optional<Group> grp=grpRepo.findById(groupId);
		if(grp.isPresent()){
			if(!grp.get().getEventName().isEmpty()){
				Optional<Event> event=eventRepository.findByEventName(grp.get().getEventName());
				event.get().decreasePeopleCount(grp.get().getParticipantsLimit());
				eventRepository.save(event.get());
			}else{
				Optional<TouristSpot> spot=spotRepository.findBySpotName(grp.get().getSpotName());
				spot.get().decreasePeopleCount(grp.get().getParticipantsLimit());
				spotRepository.save(spot.get());
			}
			Optional<Organizer> organizer=organizerRepository.findById(grp.get().getOrganizerId());
			List<Participant> participants=participantRepository.findAllByGroupId(grp.get().getGroupId());
			participants.forEach(participant -> participant.setParticipantStatus(UserStatus.Free));
			participantRepository.saveAll(participants);
			organizer.get().setOrganizerStatus(UserStatus.Free);
			organizerRepository.save(organizer.get());
			scheduling.getActiveGrpId().remove(groupId);
			messagesRepository.deleteByGroupId(groupId);
			grpRepo.deleteById(groupId);
			return "Group with id: "+groupId+" is removed successfully";
		}
		else {
			return "Group with id: "+groupId+" is not found";
		}
	}
	@Override
	public Group getGroupById(Integer grpId) {
		Optional<Group> grp=grpRepo.findById(grpId);
        return grp.orElse(null);
	}

	@Override
	public Group getActiveGroupById(Integer grpId) {
		Optional<Group> group=grpRepo.findById(grpId);
		 return group.orElse(null);
	}

	@Override
	public Group getGroupByOrganizerId(Integer orgId) {
		 List<Group> activeGrp=grpRepo.findAllByGroupStatus(GroupStatus.Active);
		Optional<Group> grp=activeGrp.stream().filter(group -> group.getOrganizerId().equals(orgId)).findAny();
        return grp.orElse(null);
	}

	@Override
	public List<Group> getAllGroupBySpotName(String spotName) {
		List<Group> activeGroup=grpRepo.findAllByGroupStatus(GroupStatus.Active);
		return activeGroup.stream()
				.filter(group -> group.getSpotName().equals(spotName)).collect(Collectors.toList());
	}

	@Override
	public List<Group> getAllGroupByEventName(String eventName) {
		List<Group> activeGroup=grpRepo.findAllByGroupStatus(GroupStatus.Active);
		 return activeGroup.stream().filter(group -> group.getEventName().equals(eventName)).collect(Collectors.toList());
	}
}
