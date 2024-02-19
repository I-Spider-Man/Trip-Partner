package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document("userImages")
public class UserImages {
    @Id
    private String id;
    private Integer userId;
    private byte[] profile;
    private List<Posts> postsList=new ArrayList<>(1);
    public static class Posts{
        private byte[] post;
        private String description;

        public Posts() {
        }

        @Override
        public String toString() {
            return "Posts{" +
                    "post=" + Arrays.toString(post) +
                    ", description='" + description + '\'' +
                    '}';
        }

        public byte[] getPost() {
            return post;
        }

        public void setPost(byte[] post) {
            this.post = post;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Posts(byte[] post, String description) {
            this.post = post;
            this.description = description;
        }
    }

    public UserImages() {
    }

    @Override
    public String toString() {
        return "UserImages{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", profile=" + Arrays.toString(profile) +
                ", postsList=" + postsList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }

    public List<Posts> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<Posts> postsList) {
        this.postsList = postsList;
    }

    public UserImages(String id, Integer userId, byte[] profile, List<Posts> postsList) {
        this.id = id;
        this.userId = userId;
        this.profile = profile;
        this.postsList = postsList;
    }
}
