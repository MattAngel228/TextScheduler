package com.example.matta.textscheduler.feature;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
//Change
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public String test = "Test";

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();

            /*
            mTextMessage.setText(R.string.title_schedule);
            return true;
            *///
            /*
            if (id == R.id.navigation_home) {
                mTextMessage.setText(R.string.title_home);
                return true;
            } else if (id == R.id.navigation_dashboard) {
                mTextMessage.setText(R.string.title_dashboard);
                return true;
            } else if (id == R.id.navigation_notifications) {
                mTextMessage.setText(R.string.title_notifications);
                return true;
            }
            */
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private int day, month, year, hour, minute;
    private boolean isPM;
    private String dateGiven;
    private Switch amORpm;
    private String number;
    private String message;
    private EditText dateInput;
    private EditText numberInput;
    private EditText messageInput;
    private EditText hourInput;
    private EditText minuteInput;
    private EditText timeFrameInput;
    private TextView errorMessage;


    public void scheduleMessage(View view)
    {
        setContentView(R.layout.add_textmessage);
        dateInput = (EditText) findViewById(R.id.editDate);
        numberInput = (EditText) findViewById(R.id.editNumber);
        messageInput = (EditText) findViewById(R.id.editMessage);
        hourInput = (EditText) findViewById(R.id.editHour);
        minuteInput = (EditText) findViewById(R.id.editMinute);
        amORpm = (Switch) findViewById(R.id.switch1);
        errorMessage = (TextView) findViewById(R.id.errorMessage);

    }
    public void saveMessage(View view) {

        boolean error = false;

        dateGiven = dateInput.getText().toString();
        try {
            day = Integer.valueOf(dateGiven.substring(0,2));
            month = Integer.valueOf(dateGiven.substring(3,5));
            year = Integer.valueOf(dateGiven.substring(6));
        } catch (Exception e) {
            errorMessage.setText("Date is invalid");
            error = true;
        }
        try {
            hour = Integer.valueOf(hourInput.getText().toString());
            minute = Integer.valueOf(hourInput.getText().toString());
            isPM = (boolean) amORpm.isChecked();
            if (isPM) {
                hour += 12;
            }
        } catch (Exception e) {
            //time is invalid
            errorMessage.setText("Time is invalid");
            error = true;
        }

        number = numberInput.getText().toString();
        if (number.length() == 0) {
            errorMessage.setText("Number is invalid");
            error = true;
        }

        message = messageInput.getText().toString();
        if (message.length() == 0) {
            errorMessage.setText("Message is invalid");
            error = true;
        }



        if (!error) {
            //make it into a text message
            errorMessage.setVisibility(View.INVISIBLE);

            //return to main screen
            setContentView(R.layout.activity_main);
        } else {
            //display error
            errorMessage.setVisibility(View.VISIBLE);
        }

    }
    public void cancelMessage(View view) {
        setContentView(R.layout.activity_main);
    }


}