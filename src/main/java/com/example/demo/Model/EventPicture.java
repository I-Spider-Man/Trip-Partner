package com.example.demo.Model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.util.List;

@Document("eventPicture")
public class EventPicture {
    @Id
    private String id;
    private Integer eventId;
    private List<EventPictures> eventPictures;
    public static class EventPictures{
        @Id
        private String id;
        private URL eventPicture;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public URL getEventPicture() {
            return eventPicture;
        }

        public void setEventPicture(URL eventPicture) {
            this.eventPicture = eventPicture;
        }

        public EventPictures(String id, URL eventPicture) {
            this.id = id;
            this.eventPicture = eventPicture;
        }

        public EventPictures() {
        }

        @Override
        public String toString() {
            return "EventPictures{" +
                    "id='" + id + '\'' +
                    ", eventPicture=" + eventPicture +
                    '}';
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

    public void setEventPictures(List<EventPictures> eventPictures) {
        this.eventPictures = eventPictures;
    }

    public EventPicture() {
    }

    public EventPicture(String id, Integer eventId, List<EventPictures> eventPictures) {
        this.id = id;
        this.eventId = eventId;
        this.eventPictures = eventPictures;
    }
}
