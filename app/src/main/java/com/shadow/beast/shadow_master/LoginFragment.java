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
    protected String lastname;
    protected String firstname;
    protected String email;

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
        mBtnSignupFaceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toast something

                final List<String> permissions = new ArrayList<String>();
                permissions.add("public_profile");
                permissions.add("user_status");
                permissions.add("user_friends");
                permissions.add("email");
                permissions.add("user_birthday");

                Toast.makeText(view.getContext(), "User trying to sign up through Facebook!", Toast.LENGTH_LONG).show();

                ParseFacebookUtils.logInWithReadPermissionsInBackground(LoginFragment.this.getActivity(), permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        if (user == null) {
                            Toast.makeText(view.getContext(), "Error: " + err, Toast.LENGTH_LONG).show();
                            Log.d("MyApp", "Error: " + err);
                        } else {
                            if (user.isNew()) {
                                getUserDetailsFromFB();
                                Toast.makeText(view.getContext(), "User signed up and logged in through Facebook!", Toast.LENGTH_LONG).show();
                            } else {
                                getUserDetailsFromFB();
                                Toast.makeText(view.getContext(), "User logged in through Facebook!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

            }
        });
    }

    private void getUserDetailsFromFB() {

        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            name = object.getString("name");
                            lastname = object.getString("last_name");
                            firstname = object.getString("first_name");
                            email = object.getString("email");
                            saveNewUser();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,last_name,link,email,first_name");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void saveNewUser() {
        parseUser = ParseUser.getCurrentUser();
        parseUser.put("first_name", firstname);
        parseUser.put("last_name", lastname);
        parseUser.setEmail(email);
        parseUser.saveInBackground();
    }

}