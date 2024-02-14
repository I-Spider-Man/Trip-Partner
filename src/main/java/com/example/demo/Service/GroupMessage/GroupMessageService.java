package com.example.demo.Service.GroupMessage;

import com.example.demo.Model.GroupMessage;

import java.util.List;

public interface GroupMessageService {
    List<GroupMessage.Message> getAllMessageByGroupId(Integer groupId);
    void saveMessageToGroupId(Integer groupId, GroupMessage.Message message);
}
