package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Service.OtpMailService.SMTP_mailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.User;
import com.example.demo.Service.UserServices.UserService;

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
	
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User newUser) {
		return userServ.addUser(newUser);
	}

	@GetMapping("/otp/{email}")
	public String sendEmail(@PathVariable String email){
		User user=userServ.getByUserEmail(email);
		if(user==null){
			return mailService.sendOTPService(email);
		}
		else {
			return null;
		}
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