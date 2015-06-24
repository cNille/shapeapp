package se.shapeapp.weatherapp;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView my_city;
    private EditText new_city;
    private TextView resp;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up the views.
        my_city = (TextView) findViewById(R.id.my_city);
        new_city = (EditText) findViewById(R.id.new_city);
        resp = (TextView) findViewById(R.id.response);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void updateCity(View v){
        my_city.setText(new_city.getText().toString());
        new_city.setText("(Change city)");
    }

    public void getWeather(View v){
        //Starts the magic! Sends in the text from my_city.
        new WeatherTask().execute(my_city.getText().toString());
    }

    // A custom AsyncTask made for fetching the weather. Gives a result of a JSONObject.
    class WeatherTask extends AsyncTask<String, Integer, JSONObject>{

        private static final String TAG = "HttpGetTask"; // For the log.
        private static final String url_string =
                "http://api.openweathermap.org/data/2.5/weather?APPID=3042883333ea15b779ec35e37f51a544&units=metric&q=";

        @Override
        protected void onPreExecute() {
            //Shows the progressbar.
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jObject = new JSONObject();
            HttpURLConnection httpUrlConnection = null;

            try {
                //Connects to the url. Internet permission required. Params(0) is the city.
                httpUrlConnection = (HttpURLConnection) new URL(url_string + params[0]).openConnection();
                InputStream in = new BufferedInputStream(
                        httpUrlConnection.getInputStream());

                //Reads the response and converts it to a JSONObject.
                jObject = readStream(in);
            } catch (MalformedURLException exception) { Log.e(TAG, "MalformedURLException");
            } catch (IOException exception) { Log.e(TAG, "IOException");
            } catch (JSONException e) { Log.e(TAG, "JSONException");
            } finally {
                if (null != httpUrlConnection)
                    httpUrlConnection.disconnect();
            }
            return jObject;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                //Parse the JSON-result to get out the values.
                JSONObject m = result.getJSONObject("main");
                JSONArray weather = result.getJSONArray("weather");
                JSONObject arr = weather.getJSONObject(0);

                //Save the values in variables.
                int temp = m.getInt("temp");
                String main = arr.getString("main");
                String description = arr.getString("description");

                //Display the resulting string in the response-textview
                resp.setText( "Temp: " + temp + "\nMain: " + main + "\nDescription: " + description);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Hide the progressbar once again.
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        }

        private JSONObject readStream(InputStream in) throws JSONException{
            BufferedReader reader = null;
            StringBuffer data = new StringBuffer("");

            try {
                //Reads in every line from the InputStream.
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //Creates a JSONObjct from the string. Throws JSONException if malformat.
            return new JSONObject(data.toString());
        }
    }
}
