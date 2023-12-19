package com.example.demo.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Group;
import com.example.demo.Model.Organizer;
import com.example.demo.Model.Participant;
import com.example.demo.Repository.GroupRepository;
import com.example.demo.Repository.OrganizerRepository;
import com.example.demo.Repository.ParticipantRepository;

import jakarta.annotation.PostConstruct;
@Service
public class SchedulingImpl implements Scheduling{
    @Autowired
    private GroupRepository grpRepo;
    @Autowired
    private ParticipantRepository participantRepo;
    @Autowired
    private OrganizerRepository organizerRepo;

    public List<Integer> getActiveGrpId() {
        return activeGrpId;
    }

    public void addActiveGrpId(Integer activeGrpId) {
        this.activeGrpId.add(activeGrpId);
        this.notActiveGrpId.remove(activeGrpId);
    }

    public List<Integer> getNotActiveGrpId() {
        return notActiveGrpId;
    }

//    public void addNotActiveGrpId(Integer notActiveGrpId) {
//        this.notActiveGrpId.add(notActiveGrpId);
//        this.activeGrpId.remove(notActiveGrpId);
//    }

    private List<Integer> activeGrpId =new ArrayList<>();
    private List<Integer> notActiveGrpId=new ArrayList<>();


    @PostConstruct
    private void initializeActiveAndNotActiveLists() {
        // Initialize the lists based on the initial state in the database
        List<Group> allGroups = grpRepo.findAll();
        for (Group group : allGroups) {
            if (group.isGroupStatus()) {
                activeGrpId.add(group.getGroupId());
            } else {
                notActiveGrpId.add(group.getGroupId());
            }
        }
    }

    @Override
    @Scheduled(cron="0 0 0 * * *")//(fixedRate=10000)
    public void checkGroupStatus() {
    	
        List<Group> allActiveGrp = grpRepo.findAllById(activeGrpId);
        LocalDate currentDate = LocalDate.now();

        allActiveGrp.forEach(grp -> {
            if (currentDate.isAfter(grp.getDateTo()) || currentDate.isEqual(grp.getDateTo())) {
                grp.setGroupStatus(false);
                Optional<Organizer> organizer=organizerRepo.findById(grp.getOrganizerId());
                organizer.get().setOrganizerStatus(false);
                activeGrpId.remove(grp.getGroupId());
                notActiveGrpId.add(grp.getGroupId());
                List<Participant> allParticipants= participantRepo.findAllByGroupId(grp.getGroupId());
                allParticipants.forEach(participant -> participant.setStatus(false));
            }
        });

        grpRepo.saveAll(allActiveGrp);
        System.out.println("active group id "+activeGrpId);
        System.out.println("inactive group id "+notActiveGrpId);
        
    }


    @Override
    public void setParticipantStatus() {

    }

    @Override
    public void setOrganizerStatus() {

    }
}
