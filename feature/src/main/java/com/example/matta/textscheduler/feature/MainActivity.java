package com.example.matta.textscheduler.feature;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
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
        TextView messageDisplay = findViewById(R.id.messages);
        String allMessages = " ";
        for (TextMessage message : TextMessage.toSend) {
            allMessages += message.toString() + "\n";
        }
        allMessages += "\n\nYou have " + TextMessage.toSend.size() + " messages scheduled";
        messageDisplay.setText(allMessages);

        Button delete1 = findViewById(R.id.delete1);
        Button delete2 = findViewById(R.id.delete2);
        Button delete3 = findViewById(R.id.delete3);
        Button delete4 = findViewById(R.id.delete4);
        Button delete5 = findViewById(R.id.delete5);
        Button delete6 = findViewById(R.id.delete6);
        TextView deleteMessage = findViewById(R.id.deleteMessages);

        if (TextMessage.toSend.size() >= 6) {
            delete1.setVisibility(View.VISIBLE);
            delete2.setVisibility(View.VISIBLE);
            delete3.setVisibility(View.VISIBLE);
            delete4.setVisibility(View.VISIBLE);
            delete5.setVisibility(View.VISIBLE);
            delete6.setVisibility(View.VISIBLE);
            deleteMessage.setVisibility(View.VISIBLE);
        } else if (TextMessage.toSend.size() >= 5) {
            delete1.setVisibility(View.VISIBLE);
            delete2.setVisibility(View.VISIBLE);
            delete3.setVisibility(View.VISIBLE);
            delete4.setVisibility(View.VISIBLE);
            delete5.setVisibility(View.VISIBLE);
            delete6.setVisibility(View.INVISIBLE);
            deleteMessage.setVisibility(View.VISIBLE);
        } else if (TextMessage.toSend.size() >= 4) {
            delete1.setVisibility(View.VISIBLE);
            delete2.setVisibility(View.VISIBLE);
            delete3.setVisibility(View.VISIBLE);
            delete4.setVisibility(View.VISIBLE);
            delete5.setVisibility(View.INVISIBLE);
            delete6.setVisibility(View.INVISIBLE);
            deleteMessage.setVisibility(View.VISIBLE);
        } else if (TextMessage.toSend.size() >= 3) {
            delete1.setVisibility(View.VISIBLE);
            delete2.setVisibility(View.VISIBLE);
            delete3.setVisibility(View.VISIBLE);
            delete4.setVisibility(View.INVISIBLE);
            delete5.setVisibility(View.INVISIBLE);
            delete6.setVisibility(View.INVISIBLE);
            deleteMessage.setVisibility(View.VISIBLE);
        } else if (TextMessage.toSend.size() >= 2) {
            delete1.setVisibility(View.VISIBLE);
            delete2.setVisibility(View.VISIBLE);
            delete3.setVisibility(View.INVISIBLE);
            delete4.setVisibility(View.INVISIBLE);
            delete5.setVisibility(View.INVISIBLE);
            delete6.setVisibility(View.INVISIBLE);
            deleteMessage.setVisibility(View.VISIBLE);
        } else if (TextMessage.toSend.size() >= 1) {
            delete1.setVisibility(View.VISIBLE);
            delete2.setVisibility(View.INVISIBLE);
            delete3.setVisibility(View.INVISIBLE);
            delete4.setVisibility(View.INVISIBLE);
            delete5.setVisibility(View.INVISIBLE);
            delete6.setVisibility(View.INVISIBLE);
            deleteMessage.setVisibility(View.VISIBLE);
        } else {
            delete1.setVisibility(View.INVISIBLE);
            delete2.setVisibility(View.INVISIBLE);
            delete3.setVisibility(View.INVISIBLE);
            delete4.setVisibility(View.INVISIBLE);
            delete5.setVisibility(View.INVISIBLE);
            delete6.setVisibility(View.INVISIBLE);
            deleteMessage.setVisibility(View.INVISIBLE);
        }

    }

    private int day, month, year, hour, minute;
    private Switch amORpm;
    private EditText dateInput;
    private EditText numberInput;
    private EditText messageInput;
    private EditText hourInput;
    private EditText minuteInput;
    private TextView errorMessage;


    public void deleteFirst(View view) {
        if (TextMessage.toSend.size() >= 1) {
            TextMessage.toSend.remove(0);
        }
        try {
            upDateList();
        } catch (Exception e) {

        }
    }
    public void deleteSecond(View view) {
        if (TextMessage.toSend.size() >= 2) {
            TextMessage.toSend.remove(1);
        }
        try {
            upDateList();
        } catch (Exception e) {

        }
    }
    public void deleteThird(View view) {
        if (TextMessage.toSend.size() >= 3) {
            TextMessage.toSend.remove(2);
        }
        try {
            upDateList();
        } catch (Exception e) {

        }
    }
    public void deleteFourth(View view) {
        if (TextMessage.toSend.size() >= 4) {
            TextMessage.toSend.remove(3);
        }
        try {
            upDateList();
        } catch (Exception e) {

        }
    }
    public void deleteFifth(View view) {
        if (TextMessage.toSend.size() >= 5) {
            TextMessage.toSend.remove(4);
        }
        try {
            upDateList();
        } catch (Exception e) {

        }
    }
    public void deleteSixth(View view) {
        if (TextMessage.toSend.size() >= 6) {
            TextMessage.toSend.remove(5);
        }
        try {
            upDateList();
        } catch (Exception e) {

        }
    }




    //on schedule button from main_activity
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

    //on save button from add_textMessage
    public void saveMessage(View view) {

        boolean error = false;

        //get message input
        String message = messageInput.getText().toString();
        if (message.length() == 0) {
            errorMessage.setText(R.string.errorMessage);
            error = true;
        }

        //get time input
        try {
            hour = Integer.valueOf(hourInput.getText().toString());
            minute = Integer.valueOf(minuteInput.getText().toString());
            boolean isPM = amORpm.isChecked();
            if (isPM) {
                hour += 12;
            }
        } catch (Exception e) {
            errorMessage.setText(R.string.errorTime);
            error = true;
        }

        //get date input
        String dateGiven = dateInput.getText().toString();
        try {
            day = Integer.valueOf(dateGiven.substring(0,2));
            month = Integer.valueOf(dateGiven.substring(3,5));
            year = Integer.valueOf(dateGiven.substring(6));
        } catch (Exception e) {
            errorMessage.setText(R.string.errorDate);
            error = true;
        }


        //date is in past
        Calendar setDate = Calendar.getInstance();
        setDate.set(year, month - 1, day, hour, minute, 0);
        Calendar current = Calendar.getInstance();
        if (setDate.compareTo(current) < 0) {
            errorMessage.setText(R.string.errorPast);
            error = true;
        }

        //get number input
        String number = numberInput.getText().toString();
        if (number.length() != 10) {
            errorMessage.setText(R.string.errorNumberDigits);
            error = true;
        }
        try {
            long numeric = Long.parseLong(numberInput.getText().toString());
        } catch(Exception e) {
            errorMessage.setText(R.string.errorNumberNumber);
            error = true;
        }

        if (!error) {
            //make it into a text message
            TextMessage.create(setDate, number, message);
            setContentView(R.layout.activity_main);
            onMainLayout = true;
            upDateList();
        }

    }

    //on cancel button from add_textMessage
    public void cancelMessage(View view) {
        //return to main_activity layout
        setContentView(R.layout.activity_main);
        upDateList();
        onMainLayout = true;
    }


}