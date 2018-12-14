package com.example.matta.textscheduler.feature;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.telephony.SmsManager;

/**
 * Class to manage the creation of Text Messages being sent.
 */
public class TextMessage {
    private Calendar sendCalendar;
    private String phoneNumber;
    private String message;
    public static List<TextMessage> toSend = new ArrayList<TextMessage>();

    /**
     * Constructor for text message.
     * @param setDate the calendar date the message is to be sent at
     * @param phoneNumber Phone number receiving the text (##########).
     * @param message Message being sent.
     */
    public TextMessage(Calendar setDate, String phoneNumber, String message){
        this.sendCalendar = setDate;
        this.phoneNumber = phoneNumber;
        this.message = message;
        TextHandler.addToListCalendar(this,toSend);
    }

    /**
     * Tests provided parameters. Creates a new Text Message object if parameters are valid.
     * @param setDate the calendar date when the message is to be sent
     * @param phoneNumber Provided phone number "##########".
     * @param message Message.
     */
    public static void create(Calendar setDate, String phoneNumber, String message) {
        new TextMessage(setDate, phoneNumber, message);
    }

    /**
     * Sends message.
     * @param source Text Message object being sent.
     */
    public static void sendMessage(TextMessage source) {

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(source.phoneNumber, null, source.message, null, null);

    }



    public Calendar getSendCalendar() {return sendCalendar;}

    /**
     * Gets a message from a Text Message object
     * @return message
     */
    public String getMessage() {return message;}

    /**
     * Creates the display for text message objects in the list.
     * @return String output
     */
    public String toString() {
        return "Number: " + parseNum(phoneNumber) + "\n" + "Message: " + message + "\n" + "Date to Send: " + sendCalendar.getTime().toString() + "\n" + "   ";
    }

    /**
     * Takes a phone number string (##########) and returns it in a phone number format ((###) ###-####)).
     * @param phoneNumber String of numbers
     * @return Formatted string
     */
    public String parseNum(String phoneNumber) {
        String first = phoneNumber.substring(0,3);
        String second = phoneNumber.substring(3,6);
        String third = phoneNumber.substring(6);
        return "(" + first + ") " + second + "-" + third;
    }
}
