package com.sadface.tutorapp.constr;

/**
 * Created by SADFACE on 14.06.2017.
 */

public class EventStudent {

    private int id_eventStudent;

    private StudentDTO student;


    private EventDTO event;


    private Boolean checked;


    public EventStudent(){

    }


    public int getId_eventStudent() {
        return id_eventStudent;
    }

    public void setId_eventStudent(int id_eventStudent) {
        this.id_eventStudent = id_eventStudent;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
