package com.example.demo.Service.UserServices;

import java.io.IOException;
import java.util.List;

import com.example.demo.Model.Organizer;
import com.example.demo.Model.Participant;
import com.example.demo.Model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

public interface UserService {
	List<User> getAllUser();
	ResponseEntity<String> updateUserEmail(Integer userId,String userEmail);
	ResponseEntity<String> changePassword(Integer userId,String userPassword);
	ResponseEntity<String> updateUserProfile(Integer userId, MultipartFile file) throws IOException;
	User getUserById(Integer userId);
	ResponseEntity<String> addingFollower(Integer userId,Integer followingId);
	ResponseEntity<String> blockingFollower(Integer userId,Integer blockingId);
	ResponseEntity<String> unBlockingUser(Integer userId,Integer blockedUserId);
	List<User> getAllFollowers(Integer userId);
	List<User> getAllFollowing(Integer userId);
	List<User> getAllBlocked(Integer userId);
	List<Integer> getFollowersId(Integer userId);
	List<Integer> getFollowingId(Integer userId);
	List<Integer> getBlockedId(Integer userId);
	ResponseEntity<String> unFollowing(Integer userId,Integer followingId);
	ResponseEntity<User> updateUser(Integer userId, User updateUser);
	ResponseEntity<Organizer> getOrganizerData(Integer userId);
	ResponseEntity<Participant> getParticipantData(Integer userId);
	ResponseEntity<String> addUser(User user);
	String removeUserById(Integer userId);
	List<User> getAllByUserName(String userName);
	User getByUserEmail(String userEmail);
	ResponseEntity<String> forgotPassword(String userEmail);
}
