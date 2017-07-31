package com.example.lenovo.groupeasy_v1;

/**
 * Created by lenovo on 28-07-2017.
 */

public class getContacts {
    private String name;
    private Integer num;

    public getContacts(String name, Integer num){
        this.name = name;
        this.num = num;

    }
    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return num;
    }
}
