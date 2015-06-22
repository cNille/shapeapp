package se.shapeapp.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "MyPreferencesFileName";
    private TextView current_text;
    private EditText my_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load our textview and edittext.
        current_text = (TextView) findViewById(R.id.current_text);
        my_text = (EditText) findViewById(R.id.my_text);

        //Get the sharedpreferences for our file.
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        //Get the value of my_text.
        String text = prefs.getString("my_text", "Hello world!");

        //Update the current_text with the new text.
        current_text.setText(text);
    }

    public void saveClick(View v){
        //Get the text from our edittext.
        String text = my_text.getText().toString();

        //Load the editor for our sharedpref file.
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        //Update my_text with the new text.
        editor.putString("my_text", text);

        //Commit the changes.
        editor.commit();

        //Update the current_text with the new text.
        current_text.setText(text);
    }
}
