package com.example.demo.Service.ParticipantServices;

import com.amazonaws.Response;
import com.example.demo.Model.*;
import com.example.demo.Repository.GroupRepository;
import com.example.demo.Repository.ParticipantRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.Service.Scheduling;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ParticipantServiceImpl implements ParticipantService{
    @Autowired
    private ParticipantRepository participantRepo;
    @Autowired
    private SMTP_mailService mailService;
    @Autowired
    private Scheduling scheduling;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository grpRepo;
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
        String participantEmail=user.get().getUserEmail();
        Optional<Group> grp=grpRepo.findById(newParticipant.getGroupId());

        if(scheduling.getActiveGrpId().contains(newParticipant.getGroupId()) && (grp.get().getGroupStatus() != GroupStatus.Full)) {
            if(!scheduling.getActiveOrganizerId().contains(newParticipant.getUserId())) {
                if (participant.isPresent()) {
                    if (participant.get().getParticipantStatus() == UserStatus.Free) {
                        newParticipant.setParticipantId(participant.get().getParticipantId());
                        newParticipant.setParticipationCount(participant.get().getParticipationCount());
                        newParticipant.increaseParticipationCount();
                        newParticipant.setParticipantStatus(UserStatus.Busy);
                        participantRepo.save(newParticipant);
                        grp.get().participantAdded(grp.get().getParticipantsCount());
                        grpRepo.save(grp.get());
                        try {

                            String Subject="Group Joining";
                            String Content="Hi "+user.get().getUserName()+",\n you have been successfully joined in "+grp.get().getGroupName();
                            mailService.sendMailService(participantEmail,Subject,Content);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                        return new ResponseEntity<>("Participant added to group " + grp.get().getGroupName(), HttpStatus.OK) ;
                    } else {
                        return new ResponseEntity<>("Participant is already joined in a grp : " + grpRepo.findById(participant.get().getGroupId()).get().getGroupName(),HttpStatus.CONFLICT) ;
                    }
                } else {
                    grp.get().participantAdded(grp.get().getParticipantsCount());
                    grpRepo.save(grp.get());
                    newParticipant.increaseParticipationCount();
                    participantRepo.save(newParticipant);
                    try {
                        String Subject="Group Joining";
                        String Content="Hi "+user.get().getUserName()+",\n you have been successfully joined in "+grp.get().getGroupName();
                        mailService.sendMailService(participantEmail,Subject,Content);
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
                grp.get().participantRemoved(1);
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
    public boolean requestJoin() {
        return false;
    }
}
