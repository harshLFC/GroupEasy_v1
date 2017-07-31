package com.example.lenovo.groupeasy_v1;

/**
 * Created by lenovo on 19-06-2017.
 */

public class chatMessage {
    String phonenumber;
    String messagetxt;

    public chatMessage() {
// Default constructor required for calls to DataSnapshot.getValue(chatMessage.class)
    }

    public chatMessage(String phonenumber, String messagetxt) {
        this.phonenumber = phonenumber;
        this.messagetxt = messagetxt;
    }

    public String getName() {
        return phonenumber;
    }

    public String getMessage() {
        return messagetxt;
    }
}