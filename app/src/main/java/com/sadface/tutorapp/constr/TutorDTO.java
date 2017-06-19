package com.sadface.tutorapp.constr;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SADFACE on 11.06.2017.
 */

public class TutorDTO implements Serializable{


    private int id_tutor;

    private String tutorName;

    private String tutorLogin;

    private Map<String,String> logpas = new HashMap<>();;

    private String tutorPassworld;

    private GroupDTO group;

    public TutorDTO(){}

    public int getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorLogin() {
        return tutorLogin;
    }

    public void setTutorLogin(String tutorLogin) {
        this.tutorLogin = tutorLogin;
    }

    public String getTutorPassworld() {
        return tutorPassworld;
    }

    public void setTutorPassworld(String tutorPassworld) {
        this.tutorPassworld = tutorPassworld;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public Map<String, String> getLogpas() {
        return logpas;
    }

    public void setLogpas(String tutorLogin, String tutorPassworld) {
        logpas.put(tutorLogin, tutorPassworld);
    }
}
