package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document("userExtraDetails")
public class UserExtraDetails {
    @Id
    private String id;
    private Integer userId;
    private FollowersList followersList;
    private BlockedList blockedList;
    private FollowingList followingList;

    @Override
    public String toString() {
        return "UserExtraDetails{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", followersList=" + followersList +
                ", blockedList=" + blockedList +
                ", followingList=" + followingList +
                '}';
    }

    public FollowingList getFollowingList() {
        return followingList;
    }

    public void setFollowingList(FollowingList followingList) {
        this.followingList = followingList;
    }

    public UserExtraDetails(String id, Integer userId, FollowersList followersList, BlockedList blockedList, FollowingList followingList) {
        this.id = id;
        this.userId = userId;
        this.followersList = followersList;
        this.blockedList = blockedList;
        this.followingList = followingList;
    }

    public static  class FollowingList{
       private List<Integer> following;

       public FollowingList() {
       }

       @Override
       public String toString() {
           return "FollowingList{" +
                   "following=" + following +
                   '}';
       }

       public List<Integer> getFollowing() {
           return following;
       }
        public void setFollowing(Integer following){
           this.following.add(following);
        }
       public void setFollowing(List<Integer> following) {
           this.following = following;
       }

       public FollowingList(List<Integer> following) {
           this.following = following;
       }
   }

    public UserExtraDetails() {
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

    public FollowersList getFollowersList() {
        return followersList;
    }

    public void setFollowersList(FollowersList followersList) {
        this.followersList = followersList;
    }

    public BlockedList getBlockedList() {
        return blockedList;
    }

    public void setBlockedList(BlockedList blockedList) {
        this.blockedList = blockedList;
    }

    public static class FollowersList{
        private List<Integer> follower;

        public FollowersList() {
        }

        @Override
        public String toString() {
            return "FollowersList{" +
                    "follower=" + follower +
                    '}';
        }

        public List<Integer> getFollower() {
            return follower;
        }
        public void setFollower(Integer follower){
            this.follower.add(follower);
        }
        public void setFollower(List<Integer> follower) {
            this.follower = follower;
        }

        public FollowersList(List<Integer> follower) {
            this.follower = follower;
        }
    }
    public static class BlockedList{
        private List<Integer> blocked;

        public BlockedList() {
        }

        @Override
        public String toString() {
            return "BlockedList{" +
                    "blocked=" + blocked +
                    '}';
        }

        public List<Integer> getBlocked() {
            return blocked;
        }
        public void setBlocked(Integer blocked){
            this.blocked.add(blocked);
        }
        public void setBlocked(List<Integer> blocked) {
            this.blocked = blocked;
        }

        public BlockedList(List<Integer> blocked) {
            this.blocked = blocked;
        }
    }
}
