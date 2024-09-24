package com.example.smartcities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.GridView;

import com.example.BaseadapterRepeat.HomeGrid;
import com.example.Bean.Row;
import com.example.Bean.ServeBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Home extends AppCompatActivity {

    HomeGrid homeGrid;
    Context mcontext = this;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeGrid = new HomeGrid(Home.this);
        gridView = findViewById(R.id.Allserve);

        getserve();

    }

    public void getserve(){

        Request request = new Request.Builder()
                .get()
                .url("http://10.35.249.105:10011/prod-api/api/service/list")
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: " + e.getMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                ServeBean AllServe = gson.fromJson(response.body().string(),ServeBean.class);
                List<Row> rows = AllServe.getRows();
                runOnUiThread(() ->{
                    homeGrid = new HomeGrid(mcontext,rows);
                    gridView.setAdapter(homeGrid);
                });
                Log.e("TAG", "onFailure: " + AllServe );
            }
        });
    }



}