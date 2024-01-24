package com.example.demo.Service.GroupServices;

import java.util.List;

import com.example.demo.Model.Group;

public interface GroupService {
	List<Group> getAllGroups();
	
	String addGroup(Group newGroup);
	String removeGroupById(Integer groupId);
	Group getGroupById(Integer grpId);
	Group getGroupByOrganizerId(Integer orgId);
	List<Group> getAllGroupBySpotName(String spotName);
	List<Group> getAllGroupByEventName(String eventName);
}
