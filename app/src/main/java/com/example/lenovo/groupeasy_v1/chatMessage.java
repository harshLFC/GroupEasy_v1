package com.example.lenovo.groupeasy_v1;

/**
 * Created by lenovo on 19-06-2017.
 */

public class chatMessage {
    String name;
    String message;

    public chatMessage() {

    }

    public chatMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
