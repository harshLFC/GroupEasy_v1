package com.example.lenovo.groupeasy_v1;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Tab3_Contacts extends Fragment {
    private Context mContext;
    private View rootView;
    ListView contactList;
    Button clickMe;
    ArrayAdapter<String> arrayAdapter;
    TextView textView;

    //on create
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        rootView = inflater.inflate(R.layout.contacts, container, false);

//       Referancing ui elements
        arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.support_simple_spinner_dropdown_item);
        //contactList = (ListView) rootView.findViewById(R.id.contacts_list);
        //contactList.setAdapter(arrayAdapter);

        textView = (TextView) rootView.findViewById(R.id.editTextContax) ;
        clickMe = (Button) rootView.findViewById(R.id.click_to_load);
//
        clickMe.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Cursor phones = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
                while (phones.moveToNext())
                {
                    //String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                    //arrayAdapter.add(phoneNumber);
                    textView.setText(phoneNumber);

                }
                phones.close();


            }
        });
        return rootView;
    }


}