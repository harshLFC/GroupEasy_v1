package com.example.lenovo.groupeasy_v1;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * A Pojo class is required to communicate with firebase
 */

public class chatPOJO {

    // inititalize variables

    String groupname;
    String members;
    Map<String, Object> map = new HashMap<>();

    public chatPOJO() {
// default empty constructor required for calls to DataSnapshot.getValue

    }

    public chatPOJO(String groupname, String members) {

        // setters
        this.groupname = groupname;
        this.members = members;
    }


    //getters

    public String getName() {
        return groupname;
    }

    public String getMembers() {
        return members;
    }

}

