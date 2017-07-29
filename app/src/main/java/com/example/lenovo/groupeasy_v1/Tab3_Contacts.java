package com.example.lenovo.groupeasy_v1;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class Tab3_Contacts extends Fragment {
    private Context mContext;
    private View rootView;
    ListView contactList;
    Button clickMe;
    ArrayAdapter<String> arrayAdapter;
    TextView textView;
    private Cursor cursor;
    private List<String> list;
    //on create
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        list = new ArrayList<>();
        rootView = inflater.inflate(R.layout.contacts, container, false);
//      Referancing ui elements
        contactList = (ListView) rootView.findViewById(R.id.contacts_list);
        //contactList.setAdapter(arrayAdapter);
        textView = (TextView) rootView.findViewById(R.id.editTextContax) ;
        clickMe = (Button) rootView.findViewById(R.id.click_to_load);
        enableRuntimePermission();

       clickMe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);

                while (cursor.moveToNext()) {

                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                    String phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    System.out.println("NAME: "+name + "  "+phonenumber);
                    list.add(name);
//                    contactList.add(name + " "  + ":" + " " + phonenumber);
                }
//                contactList.setAdapter((ListAdapter) list);
                cursor.close();
                printArrayList();
            }
        });
        return rootView;
    }

    private void printArrayList() {
        arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item,list);
        for (String name :list) {
//            System.out.println("NAME : "+name);
            contactList.setAdapter(arrayAdapter);

        }
    }

    private void enableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.READ_CONTACTS))
        {
            Toast.makeText(getActivity(),"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.READ_CONTACTS}, 1);
        }
    }
}