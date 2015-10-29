package com.shadow.beast.shadow_master;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shadow.beast.apptourlibrary.AppTour;
import com.shadow.beast.apptourlibrary.MaterialSlide;

public class LoginSelectionActivity extends AppTour {
        @Override
        public void init(Bundle savedInstanceState) {

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
            ShowCustomBottomPanel(new LoginFragment());


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
    }
