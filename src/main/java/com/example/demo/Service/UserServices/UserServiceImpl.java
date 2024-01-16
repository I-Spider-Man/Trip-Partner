package com.example.demo.Service.UserServices;

import java.util.List;
import java.util.Optional;

import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.Service.StorageService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private StorageService storageService;
	@Autowired
	private SMTP_mailService mailService;

	@Override
	public List<User> getAllUser() {
		return (List<User>) userRepo.findAll(); 
	}

	@Override
	public ResponseEntity<String> updateUserProfile(Integer userId, MultipartFile file) {
		String fileName = System.currentTimeMillis() + "_" + userId;
		if(storageService.uploadFile(fileName, file)){
			User user=getUserById(userId);
			user.setUserProfile(fileName);
			userRepo.save(user);
			return new ResponseEntity<>("User profile updated",HttpStatus.OK)  ;
		}
		return new ResponseEntity<>("problem in updating userProfile",HttpStatus.NOT_MODIFIED);
	}

	@Override
	public User getUserById(Integer userId) {
		Optional<User> user=userRepo.findById(userId);
		return user.orElse(null);
	}
	
	@Override
	public ResponseEntity<String> addUser(User newUser) {
		Optional<User> user = userRepo.findByUserEmail(newUser.getUserEmail());
		if (user.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User mail already exists");
		} else {
//			newUser.setUserPassword(passwordEncoder.encode(newUser.getUserPassword()));
			userRepo.save(newUser);
			try {
				String mail = newUser.getUserEmail();
				String subject = "Registration";
				String content = "Hi " + newUser.getUserName() + "\n We are happy to welcome you to be a part of Torry Harris Trip Partner family.";
				mailService.sendMailService(mail, subject, content);
				return ResponseEntity.status(HttpStatus.CREATED).body("User with id: " + newUser.getUserId() + " is registered");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public String removeUserById(Integer userId) {
		Optional<User> user=userRepo.findById(userId);
		if(user.isPresent()){
			userRepo.deleteById(userId);
			return "user with id: "+userId+" is removed successfully";
		}
		else {
			return "user with id: "+userId+" is not found";
		}
	}

	@Override
	public User getByUserEmail(String userEmail) {
		Optional<User> user=userRepo.findByUserEmail(userEmail);
		return user.orElse(null);
	}
	@Override
	public List<User> getAllByUserName(String userName) {
		return userRepo.findAllByUserName(userName);
	}

}
