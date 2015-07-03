package se.shapeapp.activitylifecycle;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private String my_tag = "Activity_life_circle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Activity about to be created
        // Typical actions;
        // 1. Restore any saved data
        super.onCreate(savedInstanceState);

        // 2. Set content view
        setContentView(R.layout.activity_main);

        // 3. Initialize UI-elements

        // 4. Link UI elements to actions.
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(my_tag, "The Activity is about to start.");

        // Activity is about to become visible

        // Typical actions;
        // Start when visible only behavior.
        // Things like updating emails in a list.
        // Loading location of user.
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(my_tag, "The Activity is about to be resumed.");

        // Activity is now visible.

        // Typical actions;
        // Start foreground behaviors like;
        // Animations, playing sound track.
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(my_tag, "The Activity is about to be paused.");

        // Activity is about to loose focus.

        // Typical actions;
        // Shutdown foreground-behaviors like animations
        // Also, save persistant state data.
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(my_tag, "The Activity is about to be stopped.");

        // Activity no longer visible

        // Typical actions;
        // Cache state if it happens that the activity is resumed later.
        // Not only called, so therefore save persistant data in onpause.
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(my_tag, "The Activity is about to be destroyed.");

        // Activity about to be destroyed.

        // Typical actions;
        // Release activity resources.
    }
}
