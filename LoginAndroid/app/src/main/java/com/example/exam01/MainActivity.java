package com.example.exam01;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    Retrofit retrofit;

    EditText idEdit, pwEdit, nameEdit;
    Button loginBtn, registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://stac2016.applepi.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        idEdit = (EditText) findViewById(R.id.edit_id);
        pwEdit = (EditText) findViewById(R.id.edit_password);
        nameEdit = (EditText) findViewById(R.id.edit_name);
        loginBtn = (Button) findViewById(R.id.btn_login);
        registerBtn = (Button) findViewById(R.id.btn_register);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginTask().execute(idEdit.getText().toString(), pwEdit.getText().toString());
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RegisterTask().execute(idEdit.getText().toString(), pwEdit.getText().toString(), nameEdit.getText().toString());
            }
        });

    }

    class LoginTask extends AsyncTask<String, Void, ResultData> {
        AlertDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog.Builder(MainActivity.this)
                    .setTitle("로그인")
                    .setMessage("로그인 중입니다..")
                    .show();
        }
        @Override
        protected ResultData doInBackground(String... params) {
            try {
                return retrofit.create(UserService.class).login(params[0], params[1]).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ResultData data) {
            dialog.dismiss();
            if(data != null){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("로그인")
                        .setMessage(data.getMessage())
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
            else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("로그인")
                        .setMessage("서버 오류입니다.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        }


    }





    class RegisterTask extends AsyncTask<String, Void, ResultData> {
        AlertDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog.Builder(MainActivity.this)
                    .setTitle("회원가입")
                    .setMessage("회원가입 중입니다...")
                    .show();
        }
        @Override
        protected ResultData doInBackground(String... params) {
            try {
                return retrofit.create(UserService.class).register(params[0], params[1], params[2]).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ResultData data) {
            dialog.dismiss();
            if(data != null){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("회원가입")
                        .setMessage(data.getMessage())
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
            else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("회원가입")
                        .setMessage("서버 오류입니다.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        }


    }
}
