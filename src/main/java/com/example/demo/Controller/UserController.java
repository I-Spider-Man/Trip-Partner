package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Service.OtpMailService.SMTP_mailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.User;
import com.example.demo.Service.UserServices.AuthResponse;
import com.example.demo.Service.UserServices.LoginRequest;
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
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId){
		User user=userServ.getUserById(userId);
		if(user!=null)
			return new ResponseEntity<>(user,HttpStatus.OK);
		else
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	@PostMapping
	public AuthResponse addUser(@RequestBody User newUser) throws Exception {
		return userServ.addUser(newUser);
	}
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest LoginRequest) {
		return userServ.sigin(LoginRequest);
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
	 @GetMapping("/api/profile")
	 public User getUserFromtoken(@RequestHeader("Authorization")String jwt) {
//		 System.out.println("jwt -----"+jwt);
		 User user=userServ.findUserByJwt(jwt);
		 user.setUserPassword(null);
		 return user;
	 }
}