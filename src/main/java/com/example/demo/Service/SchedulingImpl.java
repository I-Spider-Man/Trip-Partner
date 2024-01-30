package com.example.demo.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.Organizer.OrganizerService;
import com.example.demo.Service.ParticipantServices.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import javax.swing.text.html.Option;

@Service
public class SchedulingImpl implements Scheduling{
    @Autowired
    private GroupRepository grpRepo;
    @Autowired
    private TouristSpotRepository spotRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private EventRepository eventRepository;
    private final List<Integer> activeGrpId =new ArrayList<>();

    private final List<Integer> activeOrganizers=new ArrayList<>();
    private final List<Integer> activeParticipants=new ArrayList<>();
    private final List<Integer> activeEvents=new ArrayList<>();
    private List<Event> allActiveEvent=new ArrayList<>();
    private List<Group> allActiveGroup=new ArrayList<>();
    private final List<Group> allNotActiveGroup=new ArrayList<>();
    private List<Participant> allActiveParticipants=new ArrayList<>();
    private List<Organizer> allActiveOrganizers=new ArrayList<>();
    @PostConstruct
    private void initializeActiveAndNotActiveLists() {
        // Initialize the lists based on the initial state in the database
        allActiveGroup=grpRepo.findAllByGroupStatus(GroupStatus.Active);
        allActiveGroup.forEach(grp->addActiveGrpId(grp.getGroupId()));

        allActiveEvent=eventRepository.findAllByEventStatus(EventStatus.Active);
        allActiveEvent.forEach(event -> addActiveEventId(event.getEventId()));

        allActiveParticipants=participantRepository.findAllByParticipantStatus(UserStatus.Busy);
        allActiveParticipants.forEach(participant -> addActiveParticipantUserId(participant.getUserId()));

        allActiveOrganizers=organizerRepository.findAllByOrganizerStatus(UserStatus.Busy);
        allActiveOrganizers.forEach(organizer -> addActiveOrganizerUserId(organizer.getUserId()));
    }
    @Override
    public void addActiveGrpId(Integer activeGrpId) {
        if(!this.activeGrpId.contains(activeGrpId)) {
            this.activeGrpId.add(activeGrpId);
        }
    }
    @Override
    public void addActiveEventId(Integer eventId){
        if(!this.activeEvents.contains(eventId)){
            this.activeEvents.add(eventId);
        }
    }
    @Override
    public void addActiveOrganizerUserId(Integer activeOrganizerUserId){
        if(!this.activeOrganizers.contains(activeOrganizerUserId)) {
            this.activeOrganizers.add(activeOrganizerUserId);
        }
    }
    @Override
    public void addActiveParticipantUserId(Integer activeParticipantUserId){
        if(!this.activeParticipants.contains(activeParticipantUserId)){
            this.activeParticipants.add(activeParticipantUserId);
        }
    }
    @Override
    public List<Integer> getActiveOrganizerId() {
        return activeOrganizers;
    }
    @Override
    public List<Integer> getActiveParticipantId() {
        return activeParticipants;
    }


    @Override
    @Scheduled(cron="0 0 0 * * *")
    public void checkGroupStatus() {
        LocalDate currentDate = LocalDate.now();
        allActiveEvent=eventRepository.findAllByEventStatus(EventStatus.Active);
        allActiveEvent.forEach(event -> addActiveEventId(event.getEventId()));
        allActiveEvent.forEach(event->{
            if(event.getEndDate() != null && currentDate.isAfter(event.getEndDate())){
                activeEvents.remove(event.getEventId());
                event.setEventStatus(EventStatus.InActive);
                eventRepository.save(event);
            }
        });

        allActiveGroup=grpRepo.findAllByGroupStatus(GroupStatus.Active);
        allActiveGroup.forEach(grp->addActiveGrpId(grp.getGroupId()));
        allActiveGroup.forEach(grp -> {
            if (currentDate.isAfter(grp.getDateTo())) {
                if(grp.getEventName()!=null){
                    Optional<Event> event=eventRepository.findByEventName(grp.getEventName());
                    event.get().decreasePeopleCount(grp.getParticipantsLimit());
                    eventRepository.save(event.get());
                }else{
                    Optional<TouristSpot> spot=spotRepository.findBySpotName(grp.getSpotName());
                    spot.get().decreasePeopleCount(grp.getParticipantsLimit());
                    spotRepository.save(spot.get());
                }
                grp.setGroupStatus(GroupStatus.InActive);

                Optional<Organizer> organizer=organizerRepository.findById(grp.getOrganizerId());
                organizer.get().setOrganizerStatus(UserStatus.Free);
                organizerRepository.save(organizer.get());
                System.out.println("organized updated");

                activeGrpId.remove(grp.getGroupId());
                activeOrganizers.remove(organizer.get().getUserId());


                List<Participant> allParticipants= participantRepository.findAllByGroupId(grp.getGroupId());
                allParticipants.forEach(participant -> {
                    participant.setParticipantStatus(UserStatus.Free);
                    System.out.println("participant updated");
                    activeParticipants.remove(participant.getUserId());
                });
                participantRepository.saveAll(allParticipants);
            };

        });
        grpRepo.saveAll(allActiveGroup);
        System.out.println("active group id "+activeGrpId);
        System.out.println("active organizer id "+activeOrganizers);
        System.out.println("active participant id "+activeParticipants);
        System.out.println("active event id "+activeEvents);

    }

    public List<Integer> getActiveOrganizers() {
        return activeOrganizers;
    }

    public List<Integer> getActiveParticipants() {
        return activeParticipants;
    }

    @Override
    public List<Integer> getActiveGrpId() {
        return activeGrpId;
    }
}
