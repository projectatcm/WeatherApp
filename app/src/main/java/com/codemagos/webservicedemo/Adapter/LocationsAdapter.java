package com.codemagos.webservicedemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codemagos.webservicedemo.R;

/**
 * Created by prasanth on 19/2/17.
 */

public class LocationsAdapter extends ArrayAdapter {
    final Activity activity;
    final String[] city;
    final String[] state;
    public LocationsAdapter(Activity activity, String[] city, String[] state) {
        super(activity, R.layout.listview_row,city);
        this.activity = activity;
        this.city  =city;
        this.state = state;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View v = inflater.inflate(R.layout.listview_row,null);
        TextView txt_city_name = (TextView) v.findViewById(R.id.txt_city_name);
        TextView txt_city_state = (TextView) v.findViewById(R.id.txt_city_state);
        txt_city_name.setText(city[position]);
        txt_city_state.setText(state[position]);

        return v;

    }
}
