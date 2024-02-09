package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Group;
import com.example.demo.Model.Organizer;
import com.example.demo.Model.RequestWrapper;
import com.example.demo.Service.Organizer.OrganizerService;

import java.util.List;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
	@Autowired
	private OrganizerService organizerService;
	@PostMapping
	public ResponseEntity<?> addOrganizer(@RequestBody RequestWrapper wrapper) {
		Organizer organizer=wrapper.getOrganizer();
		Group group=wrapper.getGroup();
		return organizerService.addOrganizer(organizer,group);
	}
	@GetMapping("/allGroupsOrganizedByOrganizer/{userId}")
	public List<Group> allGroups(@PathVariable Integer userId){
		return organizerService.getAllGroupsOrganizedById(userId);
	}

}
