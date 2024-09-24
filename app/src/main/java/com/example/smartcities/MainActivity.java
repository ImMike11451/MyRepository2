package com.example.smartcities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.BaseadapterRepeat.HomeGrid;
import com.example.Bean.LoginBean;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button loginbutton;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbutton = findViewById(R.id.login);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = username.getText().toString();
                String Pass = password.getText().toString();
                String jsonString = "";

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username","test01");
                    jsonObject.put("password","123456");
                    jsonString = jsonObject.toString();
                }
                catch (Exception e){
                    Log.e("TAG", "logsucc: " + e.getMessage() );
                }

                RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonString);
                OkHttpClient client = new OkHttpClient();
                Request request = new Request
                        .Builder()
                        .post(body)
                        .url("http://10.35.249.105:10011/prod-api/api/login")
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        runOnUiThread(() -> {
                            try {
                                Gson gson = new Gson();
                                LoginBean loginBean = gson.fromJson(response.body().string(),LoginBean.class);

                                if(loginBean.getCode() == 200){
                                    AlertDialog alertDialog1 = new AlertDialog.Builder(MainActivity.this)
                                            .setMessage("登陆成功")
                                            .create();
                                    alertDialog1.show();
                                    Intent intent = new Intent(MainActivity.this,Home.class);
                                    startActivity(intent);
                                    Log.e("TAG", "onFailure: " + loginBean.getToken() );
                                }
                                else {
                                    AlertDialog alertDialog02 = new AlertDialog.Builder(MainActivity.this)
                                            .setMessage(loginBean.getMsg())
                                            .create();
                                    alertDialog02.show();
                                    Log.e("TAG",  loginBean.getMsg() );
                                }
                            }
                            catch (Exception e){
                                Log.e("TAG", e.getMessage() );
                            }
                        });
                    }
                });
            }
        });
    }



}