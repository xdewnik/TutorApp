package com.sadface.tutorapp.services;

/**
 * Created by SADFACE on 08.06.2017.
 */

public  class URL {
    private static final String HOST="http://192.168.0.102:8080/";

    public static final String GET_ALL_TUTORS =HOST+"tutors";
    public static final String GET_STUDENT_BY_ID =HOST+"student/";
    public static final String GET_STUDENT_BY_GRUOUPID =HOST+"students/";
    public static final String SAVE_STUDENT =HOST+"student";
    public static final String GET_GROUP_BY_ID = HOST + "groups/";
    public static final String GET_EVENTS = HOST + "event/";
    public static final String GET_DUTIES = HOST + "duty/";
    public static final String GET_EVENT_INFO = HOST+"eventstudent/";
    public static final String GET_ORDERS = HOST+"order/";
}

