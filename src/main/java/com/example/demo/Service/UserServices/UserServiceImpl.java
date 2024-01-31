package com.example.demo.Service.UserServices;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Group;
import com.example.demo.Model.Organizer;
import com.example.demo.Model.Participant;
import com.example.demo.Repository.OrganizerRepository;
import com.example.demo.Repository.ParticipantRepository;
import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.Service.StorageService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private OrganizerRepository organizerRepository;
	@Autowired
	private ParticipantRepository participantRepository;
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
	public ResponseEntity<User> updateUser(Integer userId, User updateUser) {
		Optional<User> user=userRepo.findById(userId);
		if(user.isPresent()){
			userRepo.save(updateUser);
			Optional<User> user1=userRepo.findById(userId);
			return new ResponseEntity<>(user1.get(),HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Organizer> getOrganizerData(Integer userId) {
		Optional<Organizer> organizer=organizerRepository.findByUserId(userId);
		return new ResponseEntity<>(organizer.orElse(null),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Participant> getParticipantData(Integer userId) {
		Optional<Participant> participant=participantRepository.findByUserId(userId);
		return new ResponseEntity<>(participant.orElse(null),HttpStatus.OK);
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
			Optional<Organizer> organizer=organizerRepository.findByUserId(userId);
			Optional<Participant> participant=participantRepository.findByUserId(userId);
			if(organizer.isPresent()){
				organizerRepository.delete(organizer.get());
			}
			if(participant.isPresent()) {
				participantRepository.delete((participant.get()));
			}
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
	public ResponseEntity<String> forgotPassword(String userEmail) {
		Optional<User> user=userRepo.findByUserEmail(userEmail);
		if(user.isPresent()){
			String Password=PasswordGenerator();
			user.get().setUserPassword(Password);
			try{
				mailService.sendMailService(userEmail,"Password Changed","Your new Password is : "+user.get().getUserPassword());
				userRepo.save(user.get());
				return new ResponseEntity<>("New password has been sent to the user's email",HttpStatus.ACCEPTED);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
        }
		else{
			return new ResponseEntity<>("User with this email not found",HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<User> getAllByUserName(String userName) {
		return userRepo.findAllByUserName(userName);
	}
	public String PasswordGenerator(){
		String Alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String Numbers="1234567890";
		String Characters=Alpha+Numbers;
		SecureRandom random=new SecureRandom();
		StringBuilder Password=new StringBuilder();
		for(int i=0;i<8;i++){
			int index=random.nextInt(Characters.length());
			Password.append(Characters.charAt(index));
		}
		return Password.toString();
	}
}
