package com.example.demo.Service.GroupMessage;

import com.example.demo.Model.GroupMessage;
import com.example.demo.Repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessagesRepository messagesRepository;
    public List<GroupMessage.Message> getAllMessages(Integer grpId){
        System.out.println(messagesRepository.findByGroupId(grpId));
        Optional<GroupMessage> groupMessage=messagesRepository.findByGroupId(grpId);
        if(groupMessage.isPresent()){
            return groupMessage.get().getMessages();
        }else{
            return null;
        }
    }
}
