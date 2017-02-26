package com.codemagos.webservicedemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.codemagos.webservicedemo.Adapter.LocationsAdapter;
import com.codemagos.webservicedemo.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherActivity extends AppCompatActivity {
    Intent incomming_intent;
    String locationKey = "";
    TextView txt_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        txt_temp  = (TextView) findViewById(R.id.txt_temp);
        incomming_intent = getIntent();
        locationKey = incomming_intent.getStringExtra("key");
        BackTask backTask = new BackTask();
        backTask.execute(locationKey);
    }


    protected class BackTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String[] params) {

            String respose = WebService.getWeather(params[0]);
            return respose;
        }

        @Override
        protected void onPostExecute(String response) {
            Log.e("weather",response);
            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                JSONObject temperature = jsonObject.getJSONObject("Temperature");
                String value = temperature.getJSONObject("Metric").getString("Value");
                String unit = temperature.getJSONObject("Metric").getString("Unit");
                txt_temp.setText(value+" "+unit);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
