package com.example.demo.Service.Event;

import com.example.demo.Model.EventPicture;

import java.net.URL;
import java.util.List;

public interface EventPictureService {
    void saveEventPicture(Integer eventId,URL eventPictureUrl);
    List<EventPicture.EventPictures> getAllPicturesByEventId(Integer eventId);
}
