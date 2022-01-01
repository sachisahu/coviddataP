package com.sachi.coviddata;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;



public class CovidCustomList extends ArrayAdapter<CovidDataClass> {

    ArrayList<CovidDataClass> covidCustomLists;

    Activity context;
    public CovidCustomList(@NonNull Activity context, ArrayList<CovidDataClass> covidCustomLists) {
        super(context, R.layout.covidcustomlist,covidCustomLists);
        this.context = context;
        this.covidCustomLists = covidCustomLists;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =context.getLayoutInflater();
        View dataView = layoutInflater.inflate(R.layout.covidcustomlist,null,true);
        CovidDataClass covidDataClass = getItem(position);
        TextView txtState = dataView.findViewById(R.id.txtState);
        TextView txtPositive = dataView.findViewById(R.id.txtPositive);

        txtState.setText(covidDataClass.state);
        txtPositive.setText(covidDataClass.positiveCovid);

        return  dataView;
    }
}
