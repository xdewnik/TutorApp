package com.sadface.tutorapp.constr;

import java.util.Date;

/**
 * Created by SADFACE on 14.06.2017.
 */

public class DutyRosterDTO {
    private int id_duty;


    private Date dutyDate;


    private TutorDTO tutor;

    public DutyRosterDTO(){

    }

    public int getId_duty() {
        return id_duty;
    }

    public void setId_duty(int id_duty) {
        this.id_duty = id_duty;
    }

    public Date getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    public TutorDTO getTutor() {
        return tutor;
    }

    public void setTutor(TutorDTO tutor) {
        this.tutor = tutor;
    }
}
