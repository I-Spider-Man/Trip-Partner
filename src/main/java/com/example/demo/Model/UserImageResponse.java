package com.example.demo.Model;

public class UserImageResponse{
	private String post;
	private String description;

	public UserImageResponse() {

	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserImageResponse(String post, String description) {
		this.post = post;
		this.description = description;
	}
}
