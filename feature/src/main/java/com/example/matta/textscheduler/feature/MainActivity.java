package com.example.matta.textscheduler.feature;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ListView;
//Change
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private TextView messageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimerHandler.startTimer();




        //setting up list of text
         String allMessages = "";
         for (TextMessage message : TextMessage.toSend) {
             allMessages += message.toString() + "\n";
         }
         messageDisplay = (TextView) findViewById(R.id.messages);
         messageDisplay.setText(allMessages);



        //mTextMessage = (TextView) findViewById(R.id.message);
    }


    public void upDateList() {
        String allMessages = "";
        for (TextMessage message : TextMessage.toSend) {
            allMessages += message.toString() + "\n";
        }
        messageDisplay = (TextView) findViewById(R.id.messages);
        messageDisplay.setText(allMessages);
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
        year = year - 1900;
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
        hour = hour - 1;
        minute = minute - 1;

        number = numberInput.getText().toString();
        if (number.length() != 10) {
            errorMessage.setText("Number must be ten digits");
            error = true;
        }
        try {
            long numeric = Long.parseLong(numberInput.getText().toString());
        } catch(Exception e) {
            errorMessage.setText("Number is not a number");
            error = true;
        }

        message = messageInput.getText().toString();
        if (message.length() == 0) {
            errorMessage.setText("Message is invalid");
            error = true;
        }



        //af


        if (!error) {
            //make it into a text message
            if (!TextMessage.create(year, month, day, hour, minute, number, message)) {
                errorMessage.setText("Invalid input");
            } else {
                TextMessage.create(year, month, day, hour, minute, number, message);
                setContentView(R.layout.activity_main);
                upDateList();
            }


        }

    }
    public void cancelMessage(View view) {
        setContentView(R.layout.activity_main);
        upDateList();
    }


}