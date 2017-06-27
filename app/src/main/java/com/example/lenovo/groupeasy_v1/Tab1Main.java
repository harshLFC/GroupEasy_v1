package com.example.lenovo.groupeasy_v1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by lenovo on 26-06-2017.
 */

public class Tab1Main extends Fragment{

    private Context mContext;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private TextView mDetailTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View rootView = inflater.inflate(R.layout.tab1_main, container, false);

//        // Views
//        mStatusTextView = (TextView) rootView.findViewById(R.id.status);
//        mDetailTextView = (TextView) rootView.findViewById(R.id.detail);
//
//        // Button listeners
//       rootView.findViewById(R.id.sign_in_button).setOnClickListener((View.OnClickListener) this);
//       rootView.findViewById(R.id.go_to_chat).setOnClickListener((View.OnClickListener) this);
//       rootView.findViewById(R.id.disconnect_button).setOnClickListener((View.OnClickListener) this);

        return rootView;
    }
}
