package com.example.demo.Service.ParticipantServices;

import com.amazonaws.Response;
import com.amazonaws.services.apigateway.model.Op;
import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.Service.Scheduling;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService{
    @Autowired
    private ParticipantRepository participantRepo;
    @Autowired
    private SMTP_mailService mailService;
    @Autowired
    private UserExtraDetailsRepostiory userExtraDetailsRepostiory;
    @Autowired
    private Scheduling scheduling;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository grpRepo;

    @Override
    public List<Group> getAllParticipatedGroups(Integer userId) {
        List<Group> groupList = new ArrayList<>();
        Optional<UserExtraDetails> userExtraDetails = userExtraDetailsRepostiory.findByUserId(userId);

        // Check if userExtraDetails is present
        if (userExtraDetails.isPresent()) {
            UserExtraDetails userDetails = userExtraDetails.get();
            List<Integer> participatedGroupIds = userDetails.getParticipatedList().getParticipatedGroupId();

            // Map groupIds to Optional<Group> and collect them into a list of Group
            groupList = participatedGroupIds.stream()
                    .map(groupId -> grpRepo.findById(groupId))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }

        return groupList;
    }


    @Override
    public List<Participant> getAllParticipants() {

        return (List<Participant>) participantRepo.findAll();
    }

    @Override
    public Participant getParticipantById(Integer participantId) {
        Optional<Participant> participant=participantRepo.findById(participantId);
        return participant.orElse(null);
    }

    @Override
    public List<Participant> getAllParticipantsByGroupId(Integer groupId) {
        return participantRepo.findAllByGroupId(groupId);
    }

    @Override
    public ResponseEntity<String> addParticipant(Participant newParticipant) {

        Optional<Participant> participant = participantRepo.findByUserId(newParticipant.getUserId());
        Optional<User> user=userRepository.findById(newParticipant.getUserId());
        Optional<UserExtraDetails> userExtraDetails=userExtraDetailsRepostiory.findByUserId(newParticipant.getUserId());
        String participantEmail=user.get().getUserEmail();
        Optional<Group> grp=grpRepo.findById(newParticipant.getGroupId());

        if(scheduling.getActiveGrpId().contains(newParticipant.getGroupId()) && (grp.get().getGroupStatus() != GroupStatus.Full)) {
            if(!scheduling.getActiveOrganizerId().contains(newParticipant.getUserId())) {
                if (participant.isPresent()) {
                    if (participant.get().getParticipantStatus() == UserStatus.Free) {
                        newParticipant.setParticipantId(participant.get().getParticipantId());
                        newParticipant.increaseParticipationCount(participant.get().getParticipationCount());
                        newParticipant.setParticipantStatus(UserStatus.Busy);

                        participantRepo.save(newParticipant);
                        Optional<User> organizer=userRepository.findById(organizerRepository.findById(grp.get().getOrganizerId()).get().getUserId());
                        grp.get().participantAdded(grp.get().getParticipantsCount());
                        userExtraDetails.get().getParticipatedList().setParticipatedGroupId(grp.get().getGroupId());
                        userExtraDetailsRepostiory.save(userExtraDetails.get());
                        grpRepo.save(grp.get());
                        try {

                            String Subject="Group Joining";
                            String Content="Hi "+user.get().getUserName()+",\n you have been successfully joined in "+grp.get().getGroupName();
                            mailService.sendMailService(participantEmail,Subject,Content);
                            if(organizer.isPresent()) {
                                String Subject1 = "New participant joined";
                                String Content1 = "Hi " + organizer.get().getUserName() + ", \n A new Participant have been added to your group. Check your Group page on our website for more details.\n\n\n\n\nThank you, Trip partner.";
                                mailService.sendMailService(organizer.get().getUserEmail(), Subject1, Content1);
                            }
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                        return new ResponseEntity<>("Participant added to group " + grp.get().getGroupName(), HttpStatus.OK) ;
                    } else {
                        return new ResponseEntity<>("Participant is already joined in a grp : " + grpRepo.findById(participant.get().getGroupId()).get().getGroupName(),HttpStatus.CONFLICT) ;
                    }
                } else {
                    grp.get().participantAdded(grp.get().getParticipantsCount());
                    userExtraDetails.get().getParticipatedList().setParticipatedGroupId(grp.get().getGroupId());
                    userExtraDetailsRepostiory.save(userExtraDetails.get());
                    grpRepo.save(grp.get());
                    Optional<User> organizer=userRepository.findById(organizerRepository.findById(grp.get().getOrganizerId()).get().getUserId());
                    newParticipant.increaseParticipationCount(newParticipant.getParticipationCount());
                    participantRepo.save(newParticipant);
                    try {
                        String Subject="Group Joining";
                        String Content="Hi "+user.get().getUserName()+",\n you have been successfully joined in "+grp.get().getGroupName();
                        mailService.sendMailService(participantEmail,Subject,Content);
                        if(organizer.isPresent()) {
                            String Subject1 = "New participant joined";
                            String Content1 = "Hi " + organizer.get().getUserName() + ", \n A new Participant have been added to your group. Check your Group page on our website for more details.\n\n\n\n\nThank you, Trip partner.";
                            mailService.sendMailService(organizer.get().getUserEmail(), Subject1, Content1);
                        }
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    return new ResponseEntity<>("participant added successfully to the group " + grp.get().getGroupName(),HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<>("you already organizing one group so you cannot join here",HttpStatus.CONFLICT) ;
            }
        }
        else{
             return new ResponseEntity<>("check Group details",HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public String removeParticipantById(Integer participantId) {
        Optional<Participant> participant=participantRepo.findById(participantId);
        if(participant.isPresent()){
            Optional<Group> grp=grpRepo.findById(participant.get().getGroupId());
            if(grp.isPresent()) {
                grp.get().participantRemoved(grp.get().getParticipantsCount());
                grpRepo.save(grp.get());

            }
            participantRepo.deleteById(participantId);
            return "participant with id: "+participantId+" removed successfully";
        }
        else{
            return "participant with id: "+participantId+" is not found";
        }
    }

    @Override
    public ResponseEntity<String> leaveGroupByParticipantId(Integer participantId, Integer groupId) {
        Optional<Participant> participant=participantRepo.findById(participantId);
        Optional<Group> group=grpRepo.findAllByGroupStatus(GroupStatus.Active).stream().filter(group1 -> group1.getGroupId().equals(groupId)).findAny();
        if(participant.isPresent() && group.isPresent()){
            Optional<User> user=userRepository.findById(participant.get().getUserId());
            String participantEmail=user.get().getUserEmail();
            Optional<User> organizer=userRepository.findById(organizerRepository.findById(group.get().getOrganizerId()).get().getUserId());
            participant.get().setGroupId(null);
            participant.get().setParticipantStatus(UserStatus.Free);
            participant.get().decreaseParticipationCount(participant.get().getParticipationCount());
            participantRepo.save(participant.get());
            try {
                String Subject="Group Leaving";
                String Content="Hi "+user.get().getUserName()+",\n you have been successfully removed from "+group.get().getGroupName();
                mailService.sendMailService(participantEmail,Subject,Content);
                if(organizer.isPresent()) {
                    String Subject1 = "Participant left";
                    String Content1 = "Hi " + organizer.get().getUserName() + ", \n We are sorry to inform you that a participant has left your group. Please check your Group page on our website for more details./n/n/nThank you,\nTrip Partner.";
                    mailService.sendMailService(organizer.get().getUserEmail(), Subject1, Content1);
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            group.get().participantRemoved(group.get().getParticipantsCount());
            grpRepo.save(group.get());
            return new ResponseEntity<>("Participant left the Group successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Check Group Id, Status and Participant Id",HttpStatus.CONFLICT);
    }

    @Override
    public boolean requestJoin() {
        return false;
    }
}
