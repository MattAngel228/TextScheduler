package com.example.matta.textscheduler.feature;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class to handle the timer checking the text messages.
 *
 * Note: MUST CALL startTimer() IN THE onCreate() METHOD.
 */
public class TimerHandler {
    /**
     * Initializes the timer and checks every minute.
     */
    public static void startTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                TextHandler.checkMessage();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 10000);
    }
}
