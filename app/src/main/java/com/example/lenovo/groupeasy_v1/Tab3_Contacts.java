package com.example.lenovo.groupeasy_v1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

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

/**
 * Created by lenovo on 26-06-2017.
 */

public class Tab3_Contacts extends Fragment {

    private final List<String> rooms = new ArrayList<>();
    private Button btn ;
    private ListView msgList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context mcontext = getActivity();
        final View rootView = inflater.inflate(R.layout.chat_rooms, container, false);

        btn = (Button) rootView.findViewById(R.id.buttonhide);
        msgList = (ListView) rootView.findViewById(R.id.lvToDoList);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Groups");

       FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText edittext = (EditText) rootView.findViewById(R.id.edittext);

                btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put(edittext.getText().toString(),"");
                        myRef.updateChildren(map);
                    }
                });

                    if (edittext.getVisibility()==View.GONE)
                {
                    edittext.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);
                }
                    else {
                        edittext.setVisibility(View.GONE);
                        btn.setVisibility(View.GONE);
                    }
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<String>(mcontext,android.R.layout.simple_list_item_1,rooms);
//        final ListAdapter adapter = null;
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

            }
        });

        msgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(mcontext,chatroom.class);
                startActivity(i);
            }
        });

        return rootView;
    }
}