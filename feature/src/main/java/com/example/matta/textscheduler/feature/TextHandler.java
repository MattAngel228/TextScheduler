package com.example.matta.textscheduler.feature;
import java.util.List;
import java.util.Date;
import java.util.logging.Handler;

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
    public static void addToList(TextMessage toAdd, List<TextMessage> list) {
        if (list.size() == 0) {
            list.add(toAdd);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSendDate().compareTo(toAdd.getSendDate()) <= 0) {
                list.add(i, toAdd);
                break;
            }
        }
    }
    //This should be how we test the dates. Still a work in progress.
    private Runnable updateData = new Runnable(){
        public void run(){
            //call the service here
            ////// set the interval time here
            //handler.postDelayed(updateData,delay);
        }
    };
}
