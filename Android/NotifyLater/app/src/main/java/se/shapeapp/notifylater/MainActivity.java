package se.shapeapp.notifylater;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void notify(View v){
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long notification_time = System.currentTimeMillis() + 5000;
        int alarmId = 111;
        Intent intent = new Intent(this, AlarmReciever.class);
        Context context = this.getApplicationContext();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, notification_time, pendingIntent);
    }
}
