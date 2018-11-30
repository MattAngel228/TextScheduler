package com.example.matta.textscheduler.feature;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
    private String dateGiven;
    private String timeFrame;
    private String number;
    private String message;
    private EditText dateInput;
    private EditText numberInput;
    private EditText messageInput;
    private EditText hourInput;
    private EditText minuteInput;
    private EditText timeFrameInput;


    public void scheduleMessage(View view)
    {
        setContentView(R.layout.add_textmessage);
        dateInput = (EditText) findViewById(R.id.editDate);
        numberInput = (EditText) findViewById(R.id.editNumber);
        messageInput = (EditText) findViewById(R.id.editMessage);
        hourInput = (EditText) findViewById(R.id.editHour);
        minuteInput = (EditText) findViewById(R.id.editMinute);
        timeFrameInput = (EditText) findViewById(R.id.editTimeZone);

    }
    public void saveMessage(View view) {

        boolean error = false;
        dateGiven = dateInput.getText().toString();
        try {
            day = Integer.valueOf(dateGiven.substring(0,2));
            month = Integer.valueOf(dateGiven.substring(3,5));
            year = Integer.valueOf(dateGiven.substring(6));
        } catch (Exception e) {
            //date is invalid
            error = true;
        }
        try {
            hour = Integer.valueOf(hourInput.getText().toString());
            minute = Integer.valueOf(hourInput.getText().toString());
            timeFrame = timeFrameInput.getText().toString().toLowerCase().replaceAll("\\s","");
            if (timeFrame.equals("pm")) {
                hour += 12;
            }
        } catch (Exception e) {
            //time is invalid
            error = true;
        }

        try {
            number = numberInput.getText().toString();
        } catch (Exception e) {
            //number is invalid
            error = true;
        }
        try {
            message = messageInput.getText().toString();
        } catch (Exception e) {
            //message is invalid
            error = true;
        }

        if (!error) {
            //make it into a text message


            //return to main screen
            setContentView(R.layout.activity_main);
        } else {
            //display error
        }

    }
    public void cancelMessage(View view) {
        setContentView(R.layout.activity_main);
    }


}
