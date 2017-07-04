package com.example.lenovo.groupeasy_v1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by lenovo on 26-06-2017.
 */

public class Tab1Main extends Fragment {

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


//        //attempt to paint something on canvas
//
//        Paint paint = new Paint();
//        paint.setColor(Color.parseColor("#D3D3D3"));
//        Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
//
//            Canvas canvas = new Canvas(bg);
//            RectF rectF = new RectF(50,20,100,80);
//            canvas.drawArc(rectF,90,45,true,paint);
////        LinearLayout ll = (LinearLayout) findViewById(R.id.draw_area);
//        LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.draw_area);
////        return new BitmapDrawable(mContext.getResources(), bg);
//        ll.setBackground(new BitmapDrawable(bg));
//
////        Failed attempt

        return rootView;
    }
}


