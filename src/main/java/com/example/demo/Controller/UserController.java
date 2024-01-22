package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Service.OtpMailService.SMTP_mailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.User;
import com.example.demo.Service.UserServices.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
	private SMTP_mailService mailService;
	@Autowired
	private UserService userServ;
	
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
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User newUser) {
		return userServ.addUser(newUser);
	}
	@PostMapping("/updateProfile/{userId}")
	public ResponseEntity<String> updateProfile(@PathVariable Integer userId ,@RequestParam(value = "profile")MultipartFile profilePicture){
		return userServ.updateUserProfile(userId,profilePicture);
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