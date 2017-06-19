package com.sadface.tutorapp.constr;

import java.util.Date;

/**
 * Created by SADFACE on 18.06.2017.
 */

public class OrderDTO {


    private int id_order;

    private Date orderDate;

    private String orderName;

    private String orderText;

    public OrderDTO(){

    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }
}
