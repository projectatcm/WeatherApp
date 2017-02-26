package com.codemagos.webservicedemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.codemagos.webservicedemo.Adapter.LocationsAdapter;
import com.codemagos.webservicedemo.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText txt_city;
    Button btn_find;
    ListView list_cities;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_city = (EditText) findViewById(R.id.txt_city);
        btn_find = (Button) findViewById(R.id.btn_find);
        list_cities = (ListView) findViewById(R.id.list_cities);

/*String[] city_list = {"kochi","banglore","Chennai","Delhi"};
String[] state_list = {"kerala","karnataka","Thamilnadu","Delhi"};
        LocationsAdapter locationsAdapter = new LocationsAdapter(MainActivity.this,city_list,state_list);
list_cities.setAdapter(locationsAdapter);*/
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city = txt_city.getText().toString();
                BackTask backTask = new BackTask();
                backTask.execute(city);
            }
        });
    }


    protected class BackTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String[] params) {

            String respose = WebService.getLocation(params[0]);
            return respose;
        }

        @Override
        protected void onPostExecute(String respose) {
            try {
                JSONArray jsonArray = new JSONArray(respose);
                int result_length = jsonArray.length();
               final String[] city_list = new String[result_length];
               final String[] state_list = new String[result_length];
               final String[] key = new String[result_length];
                for(int i=0;i<result_length;i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String city_name = jsonObject.getString("EnglishName");
                    String key_name = jsonObject.getString("Key");
                    JSONObject state_data = jsonObject.getJSONObject("AdministrativeArea");
                    String state_name = state_data.getString("EnglishName");
                    city_list[i] = city_name;
                    state_list[i] = state_name;
                    key[i] = key_name;

                }
                LocationsAdapter locationsAdapter = new LocationsAdapter(MainActivity.this,city_list,state_list);
                list_cities.setAdapter(locationsAdapter);
                list_cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(getApplicationContext(),WeatherActivity.class);
                        i.putExtra("key",key[position]);
                        startActivity(i);
                       // finish();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
