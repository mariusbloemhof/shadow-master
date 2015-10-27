package com.shadow.beast.shadow_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        List<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(getResources().getStringArray(R.array.medical_aid_provider_arrays)));
        list.add("Medical Aid Provider");
        final int listsize = list.size() - 1;

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.styles_spinner_layout, list) {
            @Override
            public int getCount() {
                return(listsize); // Truncate the list
            }
        };

        Spinner spinner_med_aid_provider = (Spinner)findViewById(R.id.spnMedical_aid_provider);

        dataAdapter.setDropDownViewResource(R.layout.styles_spinner_dropdown);
        spinner_med_aid_provider.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        spinner_med_aid_provider.setAdapter(dataAdapter);
        spinner_med_aid_provider.setSelection(listsize); // Hidden item to appear in the spinner

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_screen, menu);
        return true;
    }
}
