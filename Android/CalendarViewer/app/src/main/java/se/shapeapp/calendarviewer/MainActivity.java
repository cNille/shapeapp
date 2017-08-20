package se.shapeapp.calendarviewer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.Format;
import java.util.Date;


public class MainActivity extends ActionBarActivity {
    private Cursor cursor;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.event);
        retrieveCalendarEvents(null);
    }

    public void retrieveCalendarEvents(View v) {
        /* Defines which columns (variables of the event we are interested in retrieving.
         * In this case we retrieve the title, datestart and description
        */
        String[] column = new String[]{
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DESCRIPTION
        };

        /* The filter for the query. The ?s will be replaced with the arguments */
        String filter = CalendarContract.Events.DTSTART + " > " + Long.toString((new Date()).getTime());

        /* Defining the arguments dtstart and the current time in millis
         * These will be as compered in the filter => dtstart > time */
        String event_date = CalendarContract.Events.DTSTART;
        String[] arguments = new String[]{};

        /* Defining our preferred order. Here we want the events
         * ordered by dtstart in ascending order */
        String order = CalendarContract.Events.DTSTART + " ASC";

        /* Adding the query with our preferences to the cursor */
        cursor = getContentResolver().query(
                CalendarContract.Events.CONTENT_URI,
                column,
                filter,
                arguments,
                order
        );
        cursor.moveToFirst();
    }

    /**
     * When next butten is pressed this method moves the event-curser to the next one
     * @param v
     */
    public void next_event(View v){
        if(!cursor.isLast()){
            cursor.moveToNext();
        }
        updateText();
    }
    /**
     * When next butten is pressed this method moves the event-curser to the previous one.
     * @param v
     */
    public void previous_event(View v) {
        if (!cursor.isFirst()) {
            cursor.moveToPrevious();
        }
        updateText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int a = 2;
        int b = 1 + a;

    }

    /**
     * Updates the event-text to display the new event
     * after the curser has been moved.
     */
    private void updateText(){
        String title = "N/A", description = "";
        Long start = 0L;
        Format df = DateFormat.getDateFormat(this);
        Format tf = DateFormat.getTimeFormat(this);
        try {
            title = cursor.getString(0);
            start = cursor.getLong(1);
            description = cursor.getString(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv.setText(title + " on " + df.format(start) + " at " + tf.format(start) + "\n\n" + description);
    }
}
