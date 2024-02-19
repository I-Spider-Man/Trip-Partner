package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("messages")
public class GroupMessage {
    @Id
    private String id;
    private Integer groupId;
    private List<Message> messages=new ArrayList<>(1);

    public GroupMessage() {
    }

    @Override
    public String toString() {
        return "GroupMessage{" +
                "groupId=" + groupId +
                ", messages=" + messages +
                '}';
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public GroupMessage(Integer groupId, List<Message> messages) {
        this.groupId = groupId;
        this.messages = messages;
    }

    public static class Message{
        private Integer userId;
        private String content;
        private Date time;

        @Override
        public String toString() {
            return "Message{" +
                    ", userId=" + userId +
                    ", content='" + content + '\'' +
                    ", time=" + time +
                    '}';
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getTime() {
            return time;
        }

        public void setTime() {
            this.time = new Date();
        }

        public Message(Integer userId, String content) {
            this.userId = userId;
            this.content = content;
            this.time = new Date();
        }
    }
}
