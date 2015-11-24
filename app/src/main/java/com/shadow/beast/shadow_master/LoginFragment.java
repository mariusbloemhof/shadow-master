package com.shadow.beast.shadow_master;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beast on 10/19/2015.
 */
public class LoginFragment extends Fragment {

    protected Button mBtnSignupFaceBook;
    protected ParseUser parseUser;
    protected String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_login_selection, container, false);

        Button btnsignup = (Button) rootView.findViewById(R.id.btn_login);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intentSignup = new Intent(view.getContext(), SignupActivity.class);
                startActivityForResult(intentSignup, 0);
            }

        });
        InitializeEventListeners(rootView);
        return rootView;
    }

    private void InitializeEventListeners(final View view) {
        mBtnSignupFaceBook = (Button) view.findViewById(R.id.btnfacebook_login);

        //listen to register button click
        FacebookDetails facebookDetails = new FacebookDetails();
        mBtnSignupFaceBook.setOnClickListener(facebookDetails.OnFacebookSignup(view, LoginFragment.this.getActivity()));

    }


}