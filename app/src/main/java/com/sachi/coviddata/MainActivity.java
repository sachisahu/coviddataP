package com.sachi.coviddata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
        ArrayList<CovidDataClass> covidDataClassList = new ArrayList<>();



        ListView listView;
    CovidCustomList covidCustomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.covidList);



        loadData();



    }

    private void loadData() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://covidtracking.com/api/states")
                .method("GET",null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("Api Fail",e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d("Api Success","Success");

                try {
                    String resp = response.body().string();
                    JSONArray jsonArray = new JSONArray(resp);
                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String state = obj.getString("state");
                        int positive = obj.getInt("positive");
                        covidDataClassList.add(new CovidDataClass(state,positive));
                        Log.d("StateData",state);
                        Log.d("adapterData", String.valueOf(covidDataClassList));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }

        });
    }
}