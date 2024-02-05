package com.example.demo.Service.UserServices;

import java.io.IOException;
import java.util.List;

import com.example.demo.Model.Organizer;
import com.example.demo.Model.Participant;
import com.example.demo.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
	List<User> getAllUser();
	ResponseEntity<String> updateUserProfile(Integer userId, MultipartFile file) throws IOException;
	User getUserById(Integer userId);
	ResponseEntity<User> updateUser(Integer userId, User updateUser);
	ResponseEntity<Organizer> getOrganizerData(Integer userId);
	ResponseEntity<Participant> getParticipantData(Integer userId);
	ResponseEntity<String> addUser(User user);
	String removeUserById(Integer userId);
	List<User> getAllByUserName(String userName);
	User getByUserEmail(String userEmail);
	ResponseEntity<String> forgotPassword(String userEmail);
}
