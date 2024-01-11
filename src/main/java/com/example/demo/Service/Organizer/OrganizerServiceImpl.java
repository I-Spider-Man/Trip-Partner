package com.example.demo.Service.Organizer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.Scheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.GroupServices.GroupService;
import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.Service.UserServices.UserService;

import jakarta.mail.MessagingException;

@Service
public class OrganizerServiceImpl implements OrganizerService{
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private Scheduling scheduling;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TouristSpotRepository spotRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupService groupService;
    @Autowired
    private SMTP_mailService mailService;

    @Override
    public List<Organizer> getAllOrganizer() {
        return (List<Organizer>) organizerRepository.findAll();
    }

    @Override
    public Organizer getOrganizerById(Integer organizerId) {
        Optional<Organizer> organizer=organizerRepository.findById(organizerId);
        return organizer.orElse(null);
    }

    @Override
    public Organizer getOrganizerByUserId(Integer userId) {
        Optional<Organizer> organizer=organizerRepository.findByUserId(userId);
        return organizer.orElse(null);
    }
    
    @Override
    public Group addOrganizer(Organizer newOrganizer, Group newGroup) {
        Optional<Organizer> organizer=organizerRepository.findByUserId(newOrganizer.getUserId());
        User user=userService.getUserById(newOrganizer.getUserId());
        String organizerEmail=user.getUserEmail();
        if(!scheduling.getActiveParticipantId().contains(newOrganizer.getUserId())){
            if(organizer.isPresent()){
                if(organizer.get().getOrganizerStatus() == UserStatus.Free){
                    newOrganizer.setOrganizerId(organizer.get().getOrganizerId());
                    newOrganizer.setOrganizedCount(organizer.get().getOrganizedCount());
                    newOrganizer.setOrganizerStatus(UserStatus.Busy);
                    organizerRepository.save(newOrganizer);
                    scheduling.addActiveOrganizerUserId(newOrganizer.getUserId());
                    newGroup.setOrganizerId(newOrganizer.getOrganizerId());
                    groupService.addGroup(newGroup);
                    try {
                        String Subject="Group Creation";
                        String Content="Hii "+user.getUserName()+",\nyour group "+newGroup.getGroupName()+" is successfully created.";
                        mailService.sendMailService(organizerEmail,Subject,Content);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    return newGroup;
                }
                else {
                    return null;//"organizer already organizing "+groupService.getGroupByOrganizerId(organizer.get().getOrganizerId());
                }
            }
            else {

                //newOrganizer.increseOrganizedCount();
                organizerRepository.save(newOrganizer);
                newGroup.setOrganizerId(newOrganizer.getOrganizerId());
                groupService.addGroup(newGroup);

                try {
                    String Subject="Group Creation";
                    String Content="Hii "+user.getUserName()+" your group "+newGroup.getGroupName()+" creation is Successfully created.";
                    mailService.sendMailService(organizerEmail,Subject,Content);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                return  newGroup;
            }
        }else{
            return null;
        }
    }

    @Override
    public String removeOrganizerById(Integer organizerId) {
        Optional<Organizer> organizer=organizerRepository.findById(organizerId);
        if(organizer.isPresent()){
            User Organizeruser=userService.getUserById(organizer.get().getUserId());
            Optional<Group> group=groupRepository.findByOrganizerId(organizer.get().getOrganizerId());
            group.ifPresent(value -> {
                value.setGroupStatus(GroupStatus.InActive);
                if(value.getEventName()!=null){
                    Optional<Event> event=eventRepository.findByEventName(value.getEventName());
                    event.get().decreasePeopleCount(value.getParticipantsLimit());
                    eventRepository.save(event.get());
                }else{
                    Optional<TouristSpot> spot=spotRepository.findBySpotName(value.getSpotName());
                    spot.get().decreasePeopleCount(value.getParticipantsLimit());
                    spotRepository.save(spot.get());
                }
                List<Participant> participants=participantRepository.findAllByGroupId(value.getGroupId());
                participants.forEach(participant -> {
                    participant.setParticipantStatus(UserStatus.Free);
                    User participantUser=userService.getUserById(participant.getUserId());
                    try {
                        String participantEmail=participantUser.getUserEmail();
                        String Subject="Group Status turned InActive";
                        String Content="Hi "+participantUser.getUserName()+",\nThe group that you joined is turned inactive. Now you can join or organize groups.\nThank you for joining us.";
                        mailService.sendMailService(participantEmail,Subject,Content);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });
                groupRepository.save(value);
                participantRepository.saveAll(participants);
            });
            try {
                String organizerEmail=userService.getUserById(organizer.get().getUserId()).getUserEmail();
                String Subject="removed as organizer";
                String Content="Hi "+Organizeruser.getUserName()+",\nYou have been removed as the organizer. Don't worry you can still join or organize groups.\nThank you for joining us.";
                mailService.sendMailService(organizerEmail,Subject,Content);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            organizerRepository.deleteById(organizerId);
            return "Organizer with id: "+organizerId+" is removed successfully";
        }
        else{
            return "Organizer with id: "+organizerId+" is not found";
        }
    }
}
