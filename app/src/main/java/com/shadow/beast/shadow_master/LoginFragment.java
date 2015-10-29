package com.shadow.beast.shadow_master;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Beast on 10/19/2015.
 */
public class LoginFragment extends Fragment {

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




        return rootView;
    }
}