package com.example.demo.Service.GroupMessage;

import com.example.demo.Model.GroupMessage;
import com.example.demo.Repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements GroupMessageService{
    @Autowired
    private MessagesRepository messagesRepository;
    @Override
    public List<GroupMessage.Message> getAllMessageByGroupId(Integer groupId) {
        Optional<GroupMessage> groupMessage=messagesRepository.findByGroupId(groupId);
        if(groupMessage.isPresent()){
            return groupMessage.get().getMessages();
        }else{
            return null;
        }
    }
    @Override
    public void saveMessageToGroupId(Integer groupId, GroupMessage.Message message) {
        Optional<GroupMessage> groupMessage1=messagesRepository.findByGroupId(groupId);
        groupMessage1.ifPresent(groupMessage -> {
            List<GroupMessage.Message> messages = groupMessage.getMessages();
            messages.add(message);
            messagesRepository.save(groupMessage);
        });
    }
}
