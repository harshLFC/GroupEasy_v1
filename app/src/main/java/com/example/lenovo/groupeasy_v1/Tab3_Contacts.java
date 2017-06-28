package com.example.lenovo.groupeasy_v1;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lenovo on 26-06-2017.
 */

public class Tab3_Contacts extends Fragment {

    private Context mcontext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.chat_rooms, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().getRoot();

        final Button btn = (Button) rootView.findViewById(R.id.buttonhide);


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button buttonhide = (Button) rootView.findViewById(R.id.buttonhide);
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
                    buttonhide.setVisibility(View.VISIBLE);
                }
                    else {
                        edittext.setVisibility(View.GONE);
                        buttonhide.setVisibility(View.GONE);
                    }


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        return rootView;
    }
}