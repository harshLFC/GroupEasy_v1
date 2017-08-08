package com.example.lenovo.groupeasy_v1;

/**
 * Created by lenovo on 01-08-2017.
 */

public class chatroomPOJO {

    String member, phone;


    public chatroomPOJO() {

    }

    public chatroomPOJO(String member, String phone) {
        this.member = member;
        this.phone = phone;
    }

    public String getMember() {
        return member;
    }

    public String getPhone() {
        return phone;
    }

}

