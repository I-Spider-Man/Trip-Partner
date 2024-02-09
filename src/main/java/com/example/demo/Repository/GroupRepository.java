package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.GroupStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Group;

public interface GroupRepository extends JpaRepository<Group,Integer>{
	Optional<Group> findByOrganizerId(Integer organizerId);
	List<Group> findAllBySpotName(String spotName);
	List<Group> findAllByEventName(String eventName);
	List<Group> findAllByGroupStatus(GroupStatus groupStatus);

}
