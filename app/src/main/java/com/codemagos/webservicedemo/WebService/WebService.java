package com.codemagos.webservicedemo.WebService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by prasanth on 19/2/17.
 */

public class WebService {

    public static String get(String siteurl) {
     String response = "";
        try {
            URL url = new URL(siteurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");


            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                response += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    public static String getLocation(String city) {
        String url = "http://apidev.accuweather.com/locations/v1/search?q=" + city + "&apikey=hoArfRosT1215";
        String siteData = get(url);
        return siteData;
    }

    public static String getWeather(String key){
        String url = "http://apidev.accuweather.com/currentconditions/v1/"+key+".json?language=en&apikey=hoArfRosT1215";
        String siteData = get(url);
        return siteData;
    }


}
