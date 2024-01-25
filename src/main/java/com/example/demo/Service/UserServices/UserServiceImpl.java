package com.example.demo.Service.UserServices;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.OtpMailService.SMTP_mailService;
import com.example.demo.config.CustomUserDetailsService;
import com.example.demo.config.JwtProvider;
import com.example.demo.Service.StorageService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private StorageService storageService;

    @Autowired
    private SMTP_mailService mailService;
    @Autowired
    private CustomUserDetailsService customerUserDetails;

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    public ResponseEntity<String> updateUserProfile(Integer userId, MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + userId;
        if (storageService.uploadFile(fileName, file)) {
            User user = getUserById(userId);
            user.setUserProfile(fileName);
            userRepo.save(user);
            return new ResponseEntity<>("User profile updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Problem in updating userProfile", HttpStatus.NOT_MODIFIED);
    }

    @Override
    public User getUserById(Integer userId) {
        Optional<User> user = userRepo.findById(userId);
        return user.orElse(null);
    }

    @Override
    public AuthResponse addUser(User newUser) throws Exception {
        Optional<User> user = userRepo.findByUserEmail(newUser.getUserEmail());
        if (user.isPresent()) {
           throw new Exception("User is already exit");
        } else {
        	String temp = passwordEncoder.encode(newUser.getUserPassword());
			newUser.setUserPassword(temp);
            newUser.setRole(Role.ROLE_USER);
            User saveduser = userRepo.save(newUser);
            Authentication authentication= new UsernamePasswordAuthenticationToken(saveduser.getUserEmail(), saveduser.getUserPassword());
            String token = JwtProvider.generateToken(authentication);
            try {
                String mail = newUser.getUserEmail();
                String subject = "Registration";
                String content = "Hi " + newUser.getUserName() + "\n We are happy to welcome you to be a part of Torry Harris Trip Partner family.";
                mailService.sendMailService(mail, subject, content);
                AuthResponse res=new AuthResponse(token, "Register Success");
                return res;
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            
        }
    }

    @Override
    public String removeUserById(Integer userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            userRepo.deleteById(userId);
            return "User with id: " + userId + " is removed successfully";
        } else {
            return "User with id: " + userId + " is not found";
        }
    }

    @Override
    public User getByUserEmail(String userEmail) {
        Optional<User> user = userRepo.findByUserEmail(userEmail);
        return user.orElse(null);
    }

    @Override
    public ResponseEntity<String> forgotPassword(String userEmail) {
        Optional<User> user = userRepo.findByUserEmail(userEmail);
        if (user.isPresent()) {
            String password = PasswordGenerator();
            user.get().setUserPassword(password);
            try {
                mailService.sendMailService(userEmail, "Password Changed", "Your new Password is : " + user.get().getUserPassword());
                userRepo.save(user.get());
                return new ResponseEntity<>("New password has been sent to the user's email", HttpStatus.ACCEPTED);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ResponseEntity<>("User with this email not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<User> getAllByUserName(String userName) {
        return userRepo.findAllByUserName(userName);
    }

//    public ResponseEntity<String> loginUser(String userEmail, String userPassword) {
//        Optional<User> user = userRepo.findByUserEmailAndUserPassword(userEmail, userPassword);
//        if (user.isPresent() && user.get().getRole() == Role.ROLE_ADMIN) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
    @Override
    public AuthResponse sigin(LoginRequest LoginRequest ) {
    	Authentication authentication = authencate(LoginRequest.getEmail(), LoginRequest.getPassword());
    	String token = JwtProvider.generateToken(authentication);
    	AuthResponse res=new AuthResponse(token, "Login Sucess");
    	
    	return res;
    }

    private Authentication authencate(String email, String password) {
    	UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
    	if(userDetails==null) {
    		throw new BadCredentialsException("Invalid Username");
    	
    	}
    	if(!passwordEncoder.matches(password,  userDetails.getPassword())) {
    		throw new BadCredentialsException("password not match");
    	}
	// TODO Auto-generated method stub
	return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
}

	public String PasswordGenerator() {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "1234567890";
        String characters = alpha + numbers;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

	@Override
	public User findUserByJwt(String jwt) {
		String email= JwtProvider.getEmailFromJwtToken(jwt);
		Optional<User> user=userRepo.findByUserEmail(email);
		return user.orElse(null);
		}


}
