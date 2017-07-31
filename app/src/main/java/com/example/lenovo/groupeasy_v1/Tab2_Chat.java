package com.example.lenovo.groupeasy_v1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.content.ContentValues.TAG;

/**
 * Created by lenovo on 26-06-2017.
 */

public class Tab2_Chat extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("allgroups");

    //initilize elements
    private final List<String> rooms = new ArrayList<>();
    private ListView msgList;
    Animation fab_close,fab_open,fab_rotate,fab_rotate_rev;
    boolean isOpen = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final Context mcontext = getActivity();
        final View rootView = inflater.inflate(R.layout.chat_rooms, container, false);

                /*Tried to solve the data retrival bug but this snippet keeps crashing
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database.setPersistenceEnabled(true);*/

//Initialize Ui elements
        msgList = (ListView) rootView.findViewById(R.id.lvToDoList);

//Create instance and connect to firebase database

//Declare and initialize animations and fab
        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab1);
        final FloatingActionButton fab2 = (FloatingActionButton) rootView.findViewById(R.id.fab2);
        final FloatingActionButton fab3 = (FloatingActionButton) rootView.findViewById(R.id.fab3);
        fab_close = AnimationUtils.loadAnimation(mcontext.getApplicationContext(),R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(mcontext.getApplicationContext(),R.anim.fab_open);
        fab_rotate = AnimationUtils.loadAnimation(mcontext.getApplicationContext(),R.anim.fab_rotate);
        fab_rotate_rev = AnimationUtils.loadAnimation(mcontext.getApplicationContext(),R.anim.fab_rotate_rev);

        Map<String,Object> map = new HashMap<>();
        myRef.updateChildren(map);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

//animation for fab
                if (isOpen)
                {
                    fab2.startAnimation(fab_close);
                    fab2.setClickable(false);
                    fab2.setVisibility(View.GONE);

                    fab3.startAnimation(fab_close);
                    fab3.setClickable(false);
                    fab3.setVisibility(View.GONE);

                    isOpen= false;
                    fab.startAnimation(fab_rotate_rev);
                }
                else{
                    fab2.startAnimation(fab_open);
                    fab2.setClickable(true);
                    fab2.setVisibility(View.VISIBLE);

                    fab3.startAnimation(fab_open);
                    fab3.setClickable(true);
                    fab3.setVisibility(View.VISIBLE);

                    isOpen= true;
                    fab.startAnimation(fab_rotate);

// Set on click listner for the second fab
                    fab2.setOnClickListener(new  View.OnClickListener(){
                        @Override
                        public void onClick(View v){
// Set up an Alert dialog builder
                            AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                            builder.setTitle("Create new Group");
// Set up the input
                            final EditText input = new EditText(mcontext);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                            input.setInputType(InputType.TYPE_CLASS_TEXT);
                            builder.setView(input);
// Set up the buttons
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
// Push data from inputText to firebase database ref via the 'put' method

                                    String mText = input.getText().toString();
//                                    Map<String,Object> map = new HashMap<>();
//                                    map.put(input.getText().toString(),"");

                                    chatRoomPOJO chatRoomPOJO = new chatRoomPOJO(mText, null, null, null);

                                    myRef.push().setValue(chatRoomPOJO);


//                                    myRef.updateChildren(map);

                                }});
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }});
                            builder.show();
                        }});
// on click listener for third fab
                    fab3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
// code for displaying dialogFragment created in external PollDialog class
                            // code for creating new poll event
                            FragmentManager manager = getFragmentManager();
                            PollDialog dialog = new PollDialog();
                            dialog.show(manager, "Message DIalog");

                            Log.i("TAG","djfhfhs");

                        }
                    });
                }
//Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//.setAction("Action", null).show();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<String>(mcontext,android.R.layout.simple_list_item_1,rooms);
//final ListAdapter adapter = null;
        msgList.setAdapter(adapter);

// code for updating list from firebase database

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // using temporary hashSet to avoid duplicates
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                rooms.clear();
                rooms.addAll(set);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }


        });

       /* myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


        msgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(mcontext,chatroom.class);
                i.putExtra("room_name",((TextView)view).getText().toString());
                startActivity(i);
            }
        });

        return rootView;
    }

}