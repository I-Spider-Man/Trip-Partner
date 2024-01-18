package com.example.demo.Model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "Group")
public class Group {
	@Id
	@GeneratedValue
	private Integer groupId;
	private String groupName;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private Integer organizerId;
	private String eventName;
	private String spotName;
	private String about;
	private GroupStatus groupStatus=GroupStatus.Active;
	private Integer participantsLimit=0;
	private Integer participantsCount=0;

	@Override
	public String toString() {
		return "Group{" +
				"groupId=" + groupId +
				", groupName='" + groupName + '\'' +
				", dateFrom=" + dateFrom +
				", dateTo=" + dateTo +
				", organizerId=" + organizerId +
				", eventName='" + eventName + '\'' +
				", spotName='" + spotName + '\'' +
				", about='" + about + '\'' +
				", groupStatus=" + groupStatus +
				", participantsLimit=" + participantsLimit +
				", participantsCount=" + participantsCount +
				'}';
	}

	public Integer getParticipantsCount() {
		return participantsCount;
	}

	public void participantAdded(Integer participantsCount) {
		this.participantsCount = participantsCount + 1;
	}

	public void participantRemoved(Integer numberOfParticipants){

		this.participantsCount =getParticipantsCount() - numberOfParticipants ;
	}

	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getOrganizerId() {
		return organizerId;
	}
	public void setOrganizerId(Integer organizerId) {
		this.organizerId = organizerId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}

	public GroupStatus getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(GroupStatus groupStatus) {
		this.groupStatus = groupStatus;
		if(Objects.equals(this.participantsCount, this.participantsLimit)){
			this.groupStatus=GroupStatus.Full;
		}
	}

	public Integer getParticipantsLimit() {
		return participantsLimit;
	}
	public void setParticipantsLimit(Integer participantsLimit) {
		this.participantsLimit = participantsLimit;
	}
	public LocalDate getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}
	public LocalDate getDateTo() {
		return dateTo;
	}
	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public Group(Integer groupId, String groupName, LocalDate dateFrom, LocalDate dateTo, Integer organizerId, String eventName, String spotName, String about, GroupStatus groupStatus, Integer participantsLimit, Integer participantsCount) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.organizerId = organizerId;
		this.eventName = eventName;
		this.spotName = spotName;
		this.about = about;
		this.groupStatus = groupStatus;
		this.participantsLimit = participantsLimit;
		this.participantsCount = participantsCount;
	}

	public Group() {
		super();
	}
	


}
