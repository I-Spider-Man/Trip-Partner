package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document("userExtraDetails")
public class UserExtraDetails {
    @Id
    private String id;
    private Integer userId;
    private OrganizedList organizedList;
    private ParticipatedList participatedList;
    private FollowersList followersList;
    private BlockedList blockedList;
    private FollowingList followingList;

    public static class OrganizedList{
        private List<Integer> organizedGroupId;

        public OrganizedList() {
        }

        @Override
        public String toString() {
            return "OrganizedList{" +
                    "organizedGroupId=" + organizedGroupId +
                    '}';
        }

        public List<Integer> getOrganizedGroupId() {
            return organizedGroupId;
        }
        public void setOrganizedGroupId(Integer organizedGroupId){
            this.organizedGroupId.add(organizedGroupId);
        }
        public void setOrganizedGroupId(List<Integer> organizedGroupId) {
            this.organizedGroupId = organizedGroupId;
        }

        public OrganizedList(List<Integer> organizedGroupId) {
            this.organizedGroupId = organizedGroupId;
        }
    }
    public static class ParticipatedList{
        private List<Integer> participatedGroupId;

        public ParticipatedList() {
        }

        @Override
        public String toString() {
            return "ParticipatedList{" +
                    "participatedGroupId=" + participatedGroupId +
                    '}';
        }

        public List<Integer> getParticipatedGroupId() {
            return participatedGroupId;
        }
        public void setParticipatedGroupId(Integer participatedGroupId){
            this.participatedGroupId.add(participatedGroupId);
        }
        public void setParticipatedGroupId(List<Integer> participatedGroupId) {
            this.participatedGroupId = participatedGroupId;
        }

        public ParticipatedList(List<Integer> participatedGroupId) {
            this.participatedGroupId = participatedGroupId;
        }
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

    @Override
    public String toString() {
        return "UserExtraDetails{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", organizedList=" + organizedList +
                ", participatedList=" + participatedList +
                ", followersList=" + followersList +
                ", blockedList=" + blockedList +
                ", followingList=" + followingList +
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

    public OrganizedList getOrganizedList() {
        return organizedList;
    }

    public void setOrganizedList(OrganizedList organizedList) {
        this.organizedList = organizedList;
    }

    public ParticipatedList getParticipatedList() {
        return participatedList;
    }

    public void setParticipatedList(ParticipatedList participatedList) {
        this.participatedList = participatedList;
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

    public FollowingList getFollowingList() {
        return followingList;
    }

    public void setFollowingList(FollowingList followingList) {
        this.followingList = followingList;
    }

    public UserExtraDetails(String id, Integer userId, OrganizedList organizedList, ParticipatedList participatedList, FollowersList followersList, BlockedList blockedList, FollowingList followingList) {
        this.id = id;
        this.userId = userId;
        this.organizedList = organizedList;
        this.participatedList = participatedList;
        this.followersList = followersList;
        this.blockedList = blockedList;
        this.followingList = followingList;
    }
}
