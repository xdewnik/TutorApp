package com.sadface.tutorapp.constr;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by SADFACE on 23.05.2017.
 */

public class StudentDTO implements Serializable {

    private int id_student;

    private String studentName;

    private String formOfAducation;

    private String studentPhone;

    private Date birthday;

    private String studentHome;

    private String studentLive;

    private String motherName;

    private String motherPhone;

    private String motherWork;

    private String fatherName;

    private String fatherPhone;

    private String fatherWork;

    private GroupDTO group;


    public StudentDTO(){

    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFormOfAducation() {
        return formOfAducation;
    }

    public void setFormOfAducation(String formOfAducation) {
        this.formOfAducation = formOfAducation;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getStudentHome() {
        return studentHome;
    }

    public void setStudentHome(String studentHome) {
        this.studentHome = studentHome;
    }

    public String getStudentLive() {
        return studentLive;
    }

    public void setStudentLive(String studentLive) {
        this.studentLive = studentLive;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherPhone() {
        return motherPhone;
    }

    public void setMotherPhone(String motherPhone) {
        this.motherPhone = motherPhone;
    }

    public String getMotherWork() {
        return motherWork;
    }

    public void setMotherWork(String motherWork) {
        this.motherWork = motherWork;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getFatherWork() {
        return fatherWork;
    }

    public void setFatherWork(String fatherWork) {
        this.fatherWork = fatherWork;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }
}
