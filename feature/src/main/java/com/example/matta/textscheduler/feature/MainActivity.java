package com.example.matta.textscheduler.feature;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private boolean onMainLayout;
    public static boolean hasPermission = false;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onMainLayout = true;
        TimerHandler.startTimer();
        requestPermission();


        //update list of texts
        Timer updateTextsTimer = new Timer();
        TimerTask countSeconds = new TimerTask() {
            @Override
            public void run() {
                try {
                    upDateList();
                    requestPermission();
                } catch (Exception e) {

                }
            }
        };
        updateTextsTimer.scheduleAtFixedRate(countSeconds, 0, 5000);


    }

    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                hasPermission = true;
            } else {
                hasPermission = false;
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();

                    hasPermission = true;

                    //smsManager.sendTextMessage(phoneNo, null, message, null, null);
                   // Toast.makeText(getApplicationContext(), "SMS sent.",
                          //  Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();

                    hasPermission = false;
                    return;
                }
            }
        }

    }







    public void upDateList() {
        TextView messageDisplay = (TextView) findViewById(R.id.messages);
        String allMessages = " ";
        for (TextMessage message : TextMessage.toSend) {
            allMessages += message.toString() + "\n";
        }
        allMessages += "\n\nYou have " + TextMessage.toSend.size() + " messages scheduled";
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
    private TextView errorMessage;





    public void scheduleMessage(View view)
    {
        setContentView(R.layout.add_textmessage);
        onMainLayout = false;
        dateInput = findViewById(R.id.editDate);
        numberInput = findViewById(R.id.editNumber);
        messageInput = findViewById(R.id.editMessage);
        hourInput = findViewById(R.id.editHour);
        minuteInput = findViewById(R.id.editMinute);
        amORpm = findViewById(R.id.switch1);
        errorMessage = findViewById(R.id.errorMessage);


    }
    public void saveMessage(View view) {

        boolean error = false;

        //message
        message = messageInput.getText().toString();
        if (message.length() == 0) {
            errorMessage.setText("Message is invalid");
            error = true;
        }

        //time
        try {
            hour = Integer.valueOf(hourInput.getText().toString());
            minute = Integer.valueOf(minuteInput.getText().toString());
            isPM = amORpm.isChecked();
            if (isPM) {
                hour += 12;
            }
        } catch (Exception e) {
            errorMessage.setText("Time is invalid");
            error = true;
        }

        //date
        dateGiven = dateInput.getText().toString();
        try {
            day = Integer.valueOf(dateGiven.substring(0,2));
            month = Integer.valueOf(dateGiven.substring(3,5));
            year = Integer.valueOf(dateGiven.substring(6));
        } catch (Exception e) {
            errorMessage.setText("Date is invalid");
            error = true;
        }

        //number
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

        if (!error) {
            //make it into a text message
            if (!TextMessage.create(year, month, day, hour, minute, number, message)) {
                errorMessage.setText("Date is already past");
            } else {
                TextMessage.create(year, month, day, hour, minute, number, message);
                setContentView(R.layout.activity_main);
                onMainLayout = true;
                upDateList();
            }
        }

    }
    public void cancelMessage(View view) {
        setContentView(R.layout.activity_main);
        upDateList();
        onMainLayout = true;
    }


}