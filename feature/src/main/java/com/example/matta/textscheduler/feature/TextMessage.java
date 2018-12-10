package com.example.matta.textscheduler.feature;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import android.telephony.SmsManager;

/**
 * Class to manage the creation of Text Messages being sent.
 */
public class TextMessage {
    private final int yearMod = 1900;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private Date sendDate;
    private String phoneNumber;
    private String message;
    public static List<TextMessage> toSend = new ArrayList<TextMessage>();

    /**
     * Constructor for text message.
     * @param year Year to send text.
     * @param month Month to send text (0-11).
     * @param day Day to send text (1-31).
     * @param hour Hour to send text (0-23).
     * @param minute Minute to send text (0-59).
     * @param phoneNumber Phone number receiving the text (##########).
     * @param message Message being sent.
     */
    public TextMessage(int year, int month, int day, int hour, int minute, String phoneNumber, String message){
        this.sendDate = new Date(year - yearMod, month, day, hour, minute);
        this.phoneNumber = phoneNumber;
        this.message = message;
        TextHandler.addToList(this,toSend);
    }

    /**
     * Tests provided parameters. Creates a new Text Message object if parameters are valid.
     * @param year Provided year (year - 1900).
     * @param month Provided month (0-11).
     * @param day Provided day (1-31).
     * @param hour Provided hour (0-23).
     * @param minute Provided minute (0-59).
     * @param phoneNumber Provided phone number "##########".
     * @param message Message.
     * @return true if Text Message object was created, false if invalid parameter.
     */
    public static boolean create(int year, int month, int day, int hour, int minute, String phoneNumber, String message) {
        Date current = new Date();
        long numeric;
        //Invalid ranges
        try {
            Date testDate = new Date(year, month - 1, day, hour, minute);
            numeric = Long.parseLong(phoneNumber);
        } catch (Exception e) {
            return false;
        }
        Date setDate = new Date(year, month - 1, day, hour, minute);
        if (setDate.before(current)) {
            return false;
        }
        new TextMessage(year, month - 1, day, hour, minute, phoneNumber, message);
        return true;
    }

    /**
     * Sends message.
     * @param source Text Message object being sent.
     */
    public static void sendMessage(TextMessage source) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(source.phoneNumber, null, source.message, null, null);
    }

    /**
     * Retrieves the send date of a Text Message object
     * @return Intended send date.
     */
    public Date getSendDate() {
        return sendDate;
    }
}
