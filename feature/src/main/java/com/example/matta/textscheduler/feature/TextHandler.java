package com.example.matta.textscheduler.feature;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.logging.*;
import java.util.logging.Handler;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Class to handle when to send messages and how to store Text Message objects.
 */
public class TextHandler {
    Handler handler;
    private long delay = 1000;
    /**
     * Adds and sorts a Text Message object in the list.
     * @param toAdd Text Message object being added and sorted
     * @param list List the Text Message object is being added to.
     */
    public static void addToListCalendar(TextMessage toAdd, List<TextMessage> list) {
        if (list.size() == 0) {
            list.add(toAdd);
            return;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSendCalendar().equals(toAdd.getSendCalendar()) && list.get(i).getMessage().equals(toAdd.getMessage())) {
                    return;
                }
                if (list.get(i).getSendCalendar().compareTo(toAdd.getSendCalendar()) >= 0) {
                    list.add(i, toAdd);
                    return;
                }
            }
            list.add(list.size(), toAdd);
        }
    }


    /**
     * Checks if the first Text Message object's date is equal to current date.
     * If so, sends text and removes it from list.
     * Checks for every Text Message object that has the current sendDate.
     */
    public static void checkMessageCalendar() {
        Calendar current = Calendar.getInstance();
        while (TextMessage.toSend.size() > 0 && current.compareTo(TextMessage.toSend.get(0).getSendCalendar()) >= 0) {
            TextMessage.sendMessage(TextMessage.toSend.get(0));
            TextMessage.toSend.remove(0);
        }
    }

}
