package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document("userImages")
public class UserImages {
    @Id
    private String id;
    private Integer userId;
    private Images userImages;

    public UserImages() {
    }

    @Override
    public String toString() {
        return "UserImages{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", userImages=" + userImages +
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

    public Images getUserImages() {
        return userImages;
    }

    public void setUserImages(Images userImages) {
        this.userImages = userImages;
    }

    public UserImages(String id, Integer userId, Images userImages) {
        this.id = id;
        this.userId = userId;
        this.userImages = userImages;
    }

    public static class Images{
        private byte[] profileImage;
        private List<Posts> postsList;

        public Images() {
        }

        @Override
        public String toString() {
            return "Images{" +
                    "profileImage=" + Arrays.toString(profileImage) +
                    ", postsList=" + postsList +
                    '}';
        }

        public byte[] getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(byte[] profileImage) {
            this.profileImage = profileImage;
        }

        public List<Posts> getPostsList() {
            return postsList;
        }

        public void setPostsList(List<Posts> postsList) {
            this.postsList = postsList;
        }

        public Images(byte[] profileImage, List<Posts> postsList) {
            this.profileImage = profileImage;
            this.postsList = postsList;
        }

        public static class Posts{
            private byte[] post;
            private String discription;

            public Posts() {
            }

            @Override
            public String toString() {
                return "Posts{" +
                        "post=" + Arrays.toString(post) +
                        ", distription='" + discription + '\'' +
                        '}';
            }

            public byte[] getPost() {
                return post;
            }

            public void setPost(byte[] post) {
                this.post = post;
            }

            public String getDiscription() {
                return discription;
            }

            public void setDiscription(String discription) {
                this.discription = discription;
            }

            public Posts(byte[] post, String discription) {
                this.post = post;
                this.discription = discription;
            }
        }
    }
}
