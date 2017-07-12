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

    //initilize elements
    private final List<String> rooms = new ArrayList<>();
    private Button btn ;
    private ListView msgList;
    private TextInputLayout bg;
    Animation fab_close,fab_open,fab_rotate,fab_rotate_rev;
    boolean isOpen = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context mcontext = getActivity();
        final View rootView = inflater.inflate(R.layout.chat_rooms, container, false);

//find view by id of gui elements
        btn = (Button) rootView.findViewById(R.id.buttonhide);
        msgList = (ListView) rootView.findViewById(R.id.lvToDoList);
        bg = (TextInputLayout) rootView.findViewById(R.id.bg);

//Create instance and connect to firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Groups");

//initialize animations and fab
        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab1);
        final FloatingActionButton fab2 = (FloatingActionButton) rootView.findViewById(R.id.fab2);
        final Animation fab_close = AnimationUtils.loadAnimation(mcontext.getApplicationContext(),R.anim.fab_close);
        final Animation fab_open = AnimationUtils.loadAnimation(mcontext.getApplicationContext(),R.anim.fab_open);
        final Animation fab_rotate = AnimationUtils.loadAnimation(mcontext.getApplicationContext(),R.anim.fab_rotate);
        final Animation fab_rotate_rev = AnimationUtils.loadAnimation(mcontext.getApplicationContext(),R.anim.fab_rotate_rev);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final EditText edittext = (EditText) rootView.findViewById(R.id.edittext);

//code for sending data to database on click
                btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put(edittext.getText().toString(),"");
                        myRef.updateChildren(map);
                    }
                });

//animation for fab
                if (isOpen)
                {

                    fab2.startAnimation(fab_close);
                    fab.startAnimation(fab_rotate_rev);
                    fab2.setClickable(false);
                    isOpen= false;
                    fab2.setVisibility(View.GONE);
                }
                else{
                    fab2.startAnimation(fab_open);
                    fab.startAnimation(fab_rotate);
                    fab2.setClickable(true);
                    isOpen= true;
                    fab2.setVisibility(View.VISIBLE);

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
                            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                            builder.setView(input);
// Set up the buttons
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String mText = input.getText().toString();
                                    Map<String,Object> map = new HashMap<>();
                                    map.put(input.getText().toString(),"");
                                    myRef.updateChildren(map);
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
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

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

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