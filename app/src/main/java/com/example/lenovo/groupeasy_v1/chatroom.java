package com.example.lenovo.groupeasy_v1;

// Code for instant messaging

import android.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.groupeasy_v1.R;
import com.example.lenovo.groupeasy_v1.chatMessage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class chatroom extends AppCompatActivity
{
    private String room_name;
    private String user_name;
    private String temp_key;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //set layout for activity
        setContentView(R.layout.activity_main);

// Initialize gui elements
        Button sndBtn = (Button) findViewById(R.id.sendButton);
        final EditText msgText = (EditText) findViewById(R.id.messageText);
        ListView msgList = (ListView) findViewById(R.id.messagesList);

// get room name from last intent and override the chatroom title
        room_name = getIntent().getExtras().get("room_name").toString();
        setTitle(room_name);


// Initialize the Firebase database reference in 'myRef'
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("allgroups").child("0").child("messages");
        final DatabaseReference root = database.getReference("allgroups").child("0").child("messages");

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        user_name = database.getReference("allgroups").child("messages").child("phonenumber").toString();
        System.out.println("this is the username"+user_name);

// On click listener for 'send' button
        sndBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
// if entered string is null a tost will prompt
                if(msgText.getText().toString().isEmpty()){
                    Toast.makeText(chatroom.this, "You have entered nothing", Toast.LENGTH_SHORT).show();
                }
// else the entered string will be pushed to the firebase database reference
                else {
                    Map<String,Object> map1 = new HashMap<String,Object>();
                    temp_key = root.push().getKey();
                    root.updateChildren(map1);

                    DatabaseReference message_root = root.child(temp_key);
                    Map<String,Object> map2 = new HashMap<String,Object>();
                    map2.put("phonenumber",user_name);
                    map2.put("message",msgText.getText().toString());
                    message_root.updateChildren(map2);


//                    todo change name
//                    chatMessage chat = new chatMessage("harsh", msgText.getText().toString());
//                    myRef.push().setValue(chat);
                    msgText.setText("");

                }
            }
        });

// Initialize a Linked list called 'messages' and assign it to chatMessage class
        final List<chatMessage> messages = new LinkedList<>();
        for (int i = 0;i<messages.size();i++)
        {
            System.out.println(" name "+messages.get(i).getMessage());
        }
// then add the ArrayAdapter 'adapter' to chatMessage
        final ArrayAdapter<chatMessage> adapter = new ArrayAdapter<chatMessage>
                (this, android.R.layout.two_line_list_item, messages)
        {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = getLayoutInflater().inflate(android.R.layout.two_line_list_item, parent, false);
                }
                chatMessage chat = messages.get(position);
                ((TextView)convertView.findViewById(android.R.id.text1)).setText(chat.getName());
                ((TextView)convertView.findViewById(android.R.id.text2)).setText(chat.getMessage());
                return convertView;
            }
        };

        msgList.setAdapter(adapter);

        myRef.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                chatMessage chat = dataSnapshot.getValue(chatMessage.class);
                messages.add(chat);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {
                chatMessage chat = dataSnapshot.getValue(chatMessage.class);
//                        messages.add(chat);
                messages.remove(chat);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {

            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }
}