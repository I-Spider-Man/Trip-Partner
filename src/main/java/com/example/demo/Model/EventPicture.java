package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document("eventPicture")
public class EventPicture {
    @Id
    private String id;
    private Integer eventId;
    private List<EventPictures> eventPictures=new ArrayList<>(1);
    public static class EventPictures{
        private byte[] eventPicture;

        public EventPictures() {
        }

        @Override
        public String toString() {
            return "EventPictures{" +
                    "eventPicture=" + Arrays.toString(eventPicture) +
                    '}';
        }

        public byte[] getEventPicture() {
            return eventPicture;
        }


        public void setEventPicture(byte[] eventPicture) {
            this.eventPicture = eventPicture;
        }

        public EventPictures(byte[] eventPicture) {
            this.eventPicture = eventPicture;
        }
    }

    @Override
    public String toString() {
        return "EventPicture{" +
                "id='" + id + '\'' +
                ", eventId=" + eventId +
                ", eventPictures=" + eventPictures +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public List<EventPictures> getEventPictures() {
        return eventPictures;
    }
    public void setEventPictures(EventPictures eventPictures) {
        this.eventPictures.add(eventPictures);
    }

    public EventPicture() {
    }

    public EventPicture(String id, Integer eventId, List<EventPictures> eventPictures) {
        this.id = id;
        this.eventId = eventId;
        this.eventPictures = eventPictures;
    }
}
