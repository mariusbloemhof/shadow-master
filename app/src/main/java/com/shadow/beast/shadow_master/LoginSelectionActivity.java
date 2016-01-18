package com.shadow.beast.shadow_master;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.shadow.beast.apptourlibrary.AppTour;
import com.shadow.beast.apptourlibrary.MaterialSlide;

import java.util.ArrayList;
import java.util.List;

public class LoginSelectionActivity extends AppTour {

    protected LoginFragment loginFragment;

    @Override
    public void init(Bundle savedInstanceState) {

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "0cJSz075MogqVzprRn36GXO6m1ur547EN8fVhOF4", "nafYF4kwB41R4AyTGZY7j0oGPKMVh5DXNzxurWjt");
        ParseFacebookUtils.initialize(this);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();


        int firstColor = Color.parseColor("#0097A7");
        int secondColor = Color.parseColor("#FFA000");
        int thirdColor = Color.parseColor("#FF5722");
        int forthColor = Color.parseColor("#673AB7");
        int customSlideColor = Color.parseColor("#009866");

            //Create pre-created fragments
            Fragment firstSlide = MaterialSlide.newInstance(R.drawable.com_shadow_device, "Presentations on the go",
                    "Get stuff done with or without an internet connection.", Color.WHITE, Color.WHITE);

            Fragment secondSlide = MaterialSlide.newInstance(R.drawable.com_shadow_device, "Presentations on the go",
                    "Get stuff done with or without an internet connection.", Color.WHITE, Color.WHITE);
            //Add slides
            addSlide(firstSlide, getResources().getColor(R.color.com_shadow_login_activity_background_start));
            addSlide(secondSlide, getResources().getColor(R.color.com_shadow_login_activity_background_start));

            //Custom slide
//        addSlide(new CustomSlide(), customSlideColor);

            //Customize tour
            hideSkip();
            hideSkip();
            hideNext();
            hideDone();
            setSkipButtonTextColor(Color.WHITE);
            setNextButtonColorToWhite();
            setDoneButtonTextColor(Color.WHITE);
            loginFragment = new LoginFragment();
            ShowCustomBottomPanel(loginFragment);

            //Button mBtnSignupFaceBook = (Button)loginFragment.getActivity().findViewById(R.id.btnfacebook_login);
        }


        @Override
        public void onSkipPressed() {
            Toast.makeText(this, "Skip", Toast.LENGTH_SHORT).show();

            //Do something after clicking Skip button.
            //E.x: Go to the sign up slide.
            setCurrentSlide(4);
        }

        @Override
        public void onDonePressed() {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

            //Do something after clicking Done button.
            //E.x: Finish the intro.
            finish();
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }



    }
