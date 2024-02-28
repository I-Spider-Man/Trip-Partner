package com.example.demo.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import com.example.demo.Model.*;
import com.example.demo.Service.Admin.LoginRequest;
import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.Service.StorageService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.UserServices.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
	private SMTP_mailService mailService;
	@Autowired
	private UserService userServ;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private StorageService storageService;
	
	@GetMapping
	public List<User> getAllUser(){
		return userServ.getAllUser();
	}
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId){
		User user=userServ.getUserById(userId);
		if(user!=null)
			return new ResponseEntity<>(user,HttpStatus.OK);
		else
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}

	@PostMapping("/userFollowing")
	public ResponseEntity<String> userFollowingUser(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "followingId") Integer followingId){
		return userServ.addingFollower(userId,followingId);
	}
	@PostMapping("/userUnfollowing")
	public ResponseEntity<String> userUnFollowing(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "followingId") Integer followingId){
		return userServ.unFollowing(userId,followingId);
	}
	@PostMapping("/userBlocking")
	public ResponseEntity<String> userBlocking(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "blockingId") Integer blockingId){
		return userServ.blockingFollower(userId,blockingId);
	}

	@PostMapping("/userUnBlocking")
	public ResponseEntity<String> userUnBlocking(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "blockedUserId") Integer blockedUserId){
		return userServ.unBlockingUser(userId,blockedUserId);
	}

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User updatedUser){
		return userServ.updateUser(userId,updatedUser);
	}
	@GetMapping("/Organizer/{userId}")
	public ResponseEntity<Organizer> getOrganizerData(@PathVariable Integer userId){
		return userServ.getOrganizerData(userId);
	}
	@GetMapping("/followersId/{userId}")
	public List<Integer> getFollowersId(@PathVariable Integer userId){
		return userServ.getFollowersId(userId);
	}
	@GetMapping("/followingId/{userId}")
	public List<Integer> getFollowingId(@PathVariable Integer userId){
		return userServ.getFollowingId(userId);
	}
	@GetMapping("/blockedId/{userId}")
	public List<Integer> getBlockedId(@PathVariable Integer userId){
		return userServ.getBlockedId(userId);
	}
	@GetMapping("/getAllFollowers/{userId}")
	public List<User> getAllFollowers(@PathVariable Integer userId){
		return userServ.getAllFollowers(userId);
	}
	@GetMapping("/getAllFollowing/{userId}")
	public List<User> getAllFollowing(@PathVariable Integer userId){
		return userServ.getAllFollowing(userId);
	}
	@GetMapping("/getAllBlocked/{userId}")
	public List<User> getAllBlocked(@PathVariable Integer userId){
		return userServ.getAllBlocked(userId);
	}
	@GetMapping("/Participant/{userId}")
	public ResponseEntity<Participant> getParticipantData(@PathVariable Integer userId){
		return userServ.getParticipantData(userId);
	}
	@PostMapping("/adminFeedBack")
	public String postAdminFeedBack(@RequestBody AdminFeedback feedback){

		return userServ.postAdminFeedBack(feedback);
	}
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User newUser) {
		return userServ.addUser(newUser);
	}
	@PostMapping("/updateProfile/{userId}")
	public ResponseEntity<String> updateProfile(@PathVariable Integer userId ,@RequestParam(value = "profilePicture") MultipartFile profilePicture) throws IOException {
		return userServ.updateUserProfile(userId,profilePicture);
	}
	@PutMapping("/updateEmail/{userId}")
	public ResponseEntity<String> updateEmail(@PathVariable Integer userId,@RequestParam(value = "userEmail") String userEmail){
		return userServ.updateUserEmail(userId,userEmail);
	}
	@PutMapping("/changePassword/{userId}")
	public ResponseEntity<String> changePassword(@PathVariable Integer userId,@RequestParam(value = "oldPassword") String oldPassword,@RequestParam(value = "newPassword") String newPassword){
		return userServ.changePassword(userId,oldPassword,newPassword);
	}
	@PutMapping("/checkPassword/{userId}")
	public ResponseEntity<String> checkPassword(@PathVariable Integer userId, @RequestParam(value = "password") String password){
		return userServ.CheckUserPassword(userId,password);
	}
	@GetMapping("/userPost/{userId}")
	public List<UserImageResponse> getUserPosts(@PathVariable Integer userId){
		return storageService.getUserPosts(userId);
	}
	@GetMapping("/userProfile/{userId}")
	public String getUserProfile(@PathVariable Integer userId){
		return storageService.getUserProfile(userId);
	}
	@GetMapping("/otp/{email}")
	public String sendEmail(@PathVariable String email){
			return mailService.sendOTPService(email);
	}
	@PostMapping("/forgotPassword")
	public ResponseEntity<String> forgotPassword(@RequestParam(name = "userEmail",required = true) String userEmail){
		return userServ.forgotPassword(userEmail);
	}
	@PostMapping("/deletePost/{userId}")
	public String deletePost(@PathVariable Integer userId,@RequestParam(value = "post") String post){
		byte[] po= Base64.getDecoder().decode(post);
		return storageService.deletePost(userId,po);
	}
	@PostMapping("/userPost/{userId}")
	public ResponseEntity<String> postUserPosts(@PathVariable Integer userId,@RequestParam(value = "description") String description,@RequestParam(value = "post") MultipartFile post) throws IOException {
		return userServ.uploadPost(userId,description,post);
	}
	 @GetMapping("/email/{userEmail}")
	    public ResponseEntity<User> getUserByEmail(@PathVariable String userEmail) {
	        User user = userServ.getByUserEmail(userEmail);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
}
	 @GetMapping("/name/{userName}")
	 public List<User> getAllUserByUserName(@PathVariable String userName){
		 return userServ.getAllByUserName(userName);
	 }
	@PostMapping("/SignInUser")
	public ResponseEntity<?> signInUser(@RequestBody LoginRequest userLoginRequest) {
		return userServ.signinUser(userLoginRequest);
	}
}