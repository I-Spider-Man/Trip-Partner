package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import com.example.demo.Model.*;
import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User updatedUser){
		return userServ.updateUser(userId,updatedUser);
	}
	@GetMapping("/Organizer/{userId}")
	public ResponseEntity<Organizer> getOrganizerData(@PathVariable Integer userId){
		return userServ.getOrganizerData(userId);
	}
	@GetMapping("/Participant/{userId}")
	public ResponseEntity<Participant> getParticipantData(@PathVariable Integer userId){
		return userServ.getParticipantData(userId);
	}
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User newUser) {
		return userServ.addUser(newUser);
	}
	@PostMapping("/updateProfile/{userId}")
	public ResponseEntity<String> updateProfile(@PathVariable Integer userId ,@RequestParam(value = "profile")MultipartFile profilePicture) throws IOException {
		return userServ.updateUserProfile(userId,profilePicture);
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
	 @GetMapping("email/{userEmail}")
	    public ResponseEntity<User> getUserByEmail(@PathVariable String userEmail) {
	        User user = userServ.getByUserEmail(userEmail);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
}
	 @GetMapping("name/{userName}")
	 public List<User> getAllUserByUserName(@PathVariable String userName){
		 return userServ.getAllByUserName(userName);
	 }
}