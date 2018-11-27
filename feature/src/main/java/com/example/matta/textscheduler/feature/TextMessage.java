package com.example.matta.textscheduler.feature;
import java.util.Date;
import java.util.Calendar;
import android.telephony.SmsManager;

public class TextMessage {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private Date sendDate;
    private String phoneNumber;
    private String message;

    public static void sendMessage(TextMessage source) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(source.phoneNumber, null, source.message, null, null);
    }
}
