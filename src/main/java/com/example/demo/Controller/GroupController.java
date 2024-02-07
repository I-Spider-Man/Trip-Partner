package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Model.GroupMessage;
import com.example.demo.Service.GroupMessage.GroupMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Group;
import com.example.demo.Service.GroupServices.GroupService;

@RestController
@RequestMapping("/Group")

public class GroupController {
	@Autowired
	private GroupService grpService;
	@Autowired
	private GroupMessageService groupMessageService;
	@GetMapping
	public List<Group> getAllGroup(){
		return grpService.getAllGroups();	
	}
	@PostMapping
	public String addGroup(@RequestBody Group newGrp) {
		System.out.println(newGrp);
		return grpService.addGroup(newGrp);
	}
	@GetMapping("/messages/{groupId}")
	public ResponseEntity<List<GroupMessage.Message>> getAllMessagesByGroupId(@PathVariable Integer groupId){
		return new ResponseEntity<>(groupMessageService.getAllMessageByGroupId(groupId),HttpStatus.OK);
	}
	@PostMapping("/messages/{groupId}")
	public void saveMessageToGroup(@PathVariable Integer groupId,@RequestBody GroupMessage.Message message){
		groupMessageService.saveMessageToGroupId(groupId,message);
	}

	@GetMapping("/groupId/{groupId}")
	public ResponseEntity<Group> getGroupById(@PathVariable Integer groupId){
		Group grp=grpService.getActiveGroupById(groupId);
		if(grp!=null){
			return new ResponseEntity<>(grp, HttpStatus.OK) ;
		}else{
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
}
