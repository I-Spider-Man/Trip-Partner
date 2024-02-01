package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.User;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Integer> {
	List<User> findAllByUserName(String userName);
	Optional<User> findByUserEmail(String userEmail);
}
