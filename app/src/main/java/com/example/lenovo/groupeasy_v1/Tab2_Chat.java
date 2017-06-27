package com.example.lenovo.groupeasy_v1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by lenovo on 26-06-2017.
 */

public class Tab2_Chat extends Fragment {
    // variables
    private final List<chatMessage> messages = new LinkedList<>();
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        Button sndBtn = (Button) rootView.findViewById(R.id.sendButton);
        final EditText msgText = (EditText) rootView.findViewById(R.id.messageText);
        ListView msgList = (ListView) rootView.findViewById(R.id.messagesList);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");

        sndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                todo change name
                chatMessage chat = new chatMessage("harsh",msgText.getText().toString());
                myRef.push().setValue(chat);
                msgText.setText("");
            }
        });

        // adapter
        final ArrayAdapter<chatMessage> adapter = new ArrayAdapter<chatMessage>(
                mContext, android.R.layout.two_line_list_item, messages
        ){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (convertView == null){
                    convertView = inflater.inflate(android.R.layout.two_line_list_item, parent, false);
                }
                chatMessage chat = messages.get(position);
                ((TextView)convertView.findViewById(android.R.id.text1)).setText(chat.getName());
                ((TextView)convertView.findViewById(android.R.id.text2)).setText(chat.getMessage());
                return convertView;
            }
        };

        msgList.setAdapter(adapter);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                chatMessage chat = dataSnapshot.getValue(chatMessage.class);
                messages.add(chat);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                chatMessage chat = dataSnapshot.getValue(chatMessage.class);
//                        messages.add(chat);
                messages.remove(chat);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;
    }

}