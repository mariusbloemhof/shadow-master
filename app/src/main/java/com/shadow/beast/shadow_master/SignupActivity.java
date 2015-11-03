package com.shadow.beast.shadow_master;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.ArrayRes;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    protected EditText edtFirstName; //edtSignup_first_name
    protected EditText edtLastName; //edtSignup_first_name
    protected EditText edtMobileNo; //edtSignup_first_name

    private void InitializeSpinner(@IdRes int spinnerid, @ArrayRes int arrayid, String defaultText){
        List<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(getResources().getStringArray(arrayid)));
        list.add(defaultText);
        final int listsize = list.size() - 1;

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.styles_spinner_layout, list) {
            @Override
            public int getCount() {
                return(listsize); // Truncate the list
            }
        };

        Spinner spinner = (Spinner)findViewById(spinnerid);

        dataAdapter.setDropDownViewResource(R.layout.styles_spinner_dropdown);
        spinner.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(listsize); // Hidden item to appear in the spinner
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        InitializeSpinner(R.id.spnMedical_aid_provider, R.array.medical_aid_provider_arrays, "Medical Aid Provider");
        InitializeSpinner(R.id.spnSecurity_provider, R.array.security_provider_arrays, "Security Company");

        ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser != null) {
            edtFirstName = (EditText)findViewById(R.id.edtSignup_first_name);
            edtFirstName.setText((String) parseUser.get("first_name"));
            edtLastName = (EditText)findViewById(R.id.edtSignup_last_name);
            edtLastName.setText((String) parseUser.get("last_name"));
        }
        edtMobileNo = (EditText)findViewById(R.id.edtSignup_mobile_no);
        edtMobileNo.setText(getMy10DigitPhoneNumber());

        Button btnsignupBack = (Button)findViewById(R.id.btn_back_signup);
        btnsignupBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private String getMyPhoneNumber(){
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getLine1Number();
    }

    private String getMy10DigitPhoneNumber(){
        String s = getMyPhoneNumber();
        return s != null && s.length() > 2 ? s.substring(2) : null;
    }
}
