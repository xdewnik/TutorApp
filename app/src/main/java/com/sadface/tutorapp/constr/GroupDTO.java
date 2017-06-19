package com.sadface.tutorapp.constr;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SADFACE on 11.06.2017.
 */

public class GroupDTO implements Serializable {

    private int id_group;

    private String groupName;

    private List<StudentDTO> student;

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<StudentDTO> getStudent() {
        return student;
    }

    public void setStudent(List<StudentDTO> student) {
        this.student = student;
    }

    public GroupDTO(){


    }

}
