package com.example.lenovo.groupeasy_v1;

/**
 * Created by lenovo on 08-07-2017.
 */

public class chatBubble {

    private String content;
    private boolean myMessage;

    public chatBubble(String content, boolean myMessage) {
        this.content = content;
        this.myMessage = myMessage;
    }

    public String getContent() {
        return content;
    }

    public boolean myMessage() {
        return myMessage;
    }
}
