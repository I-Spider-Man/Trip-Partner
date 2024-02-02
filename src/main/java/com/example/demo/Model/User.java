package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.net.URL;
import java.time.LocalDate;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer userId;
	private String userName;
	private String userEmail;
	private String aboutUser;
	private String gender;
	private LocalDate dateOfBirth;
	private Role role=Role.User_Role;
	private String userPassword;
	private URL userProfile;
	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", userEmail='" + userEmail + '\'' +
				", aboutUser='" + aboutUser + '\'' +
				", gender='" + gender + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", role=" + role +
				", userPassword='" + userPassword + '\'' +
				", userProfile='" + userProfile + '\'' +
				'}';
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getAboutUser() {
		return aboutUser;
	}

	public void setAboutUser(String aboutUser) {
		this.aboutUser = aboutUser;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public URL getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(URL userProfile) {
		this.userProfile = userProfile;
	}

	public User(Integer userId, String userName, String userEmail, String aboutUser, String gender, LocalDate dateOfBirth, Role role, String userPassword, URL userProfile) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.aboutUser = aboutUser;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.userPassword = userPassword;
		this.userProfile = userProfile;
	}
}
