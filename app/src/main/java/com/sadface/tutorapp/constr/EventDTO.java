package com.sadface.tutorapp.constr;

import java.util.Date;
import java.util.List;

/**
 * Created by SADFACE on 14.06.2017.
 */

public class EventDTO {

    private int id_event;

    private String eventName;

    private String eventText;

    private Date eventDate;

    private List<EventStudent> eventStudent;

    public EventDTO(){
    }


    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    public List<EventStudent> getEventStudent() {
        return eventStudent;
    }

    public void setEventStudent(List<EventStudent> eventStudent) {
        this.eventStudent = eventStudent;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
