package com.example.admin.appmarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.admin.appmarket.R;
import com.example.admin.appmarket.base.BaseActivity;
import com.example.admin.appmarket.entity.UserInfo;
import com.example.admin.appmarket.protocol.UserProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends BaseActivity {

    private EditText etName,etPwd;
    private Button btLogin;
    private CheckBox cb1,cb2;
    private UserInfo mData;
    private UserProtocol userProtocol;
    private String TAG="Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //给toolBar添加监听
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.setResult(RESULT_CANCELED);
                LoginActivity.this.finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,1);
                return false;
            }
        });


        etName= (EditText) findViewById(R.id.etName);
        etPwd= (EditText) findViewById(R.id.etPwd);
        btLogin= (Button) findViewById(R.id.btLogin);
        cb1= (CheckBox) findViewById(R.id.cb1);
        cb2= (CheckBox) findViewById(R.id.cb2);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRequestWithHttpUrlConnection();
                Log.d(TAG, "onClick:  do it");
            }
        });
//        Login();
    }


    private void Login() {
       UserProtocol userProtocol=new UserProtocol();
       mData = new UserInfo();
       mData= (UserInfo) userProtocol.getData(0);
    }

    private void sendRequestWithHttpUrlConnection() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection httpURLConnection=null;
                        BufferedReader reader=null;

                        URL url= null;
                        try {
//                            String params = "name="+name+"&"+"pass="+pass
                            url = new URL("http://10.0.2.2:8000/appstore/user/0");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        try {
                            //设置连接器属性
                            httpURLConnection=(HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                            httpURLConnection.setConnectTimeout(5000);
                            httpURLConnection.setReadTimeout(5000);

                            //接收响应数据
                            InputStream inputStream=httpURLConnection.getInputStream();
                            reader=new BufferedReader(new InputStreamReader(inputStream));
                            StringBuilder responseSTR=new StringBuilder();
                            String LineSTR=null;
                            while((LineSTR = reader.readLine()) != null){
                                responseSTR.append(LineSTR);
                            }

                            //显示响应数据
                            show(responseSTR.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (reader!=null){
                                try {
                                    reader.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(httpURLConnection!=null){
                                httpURLConnection.disconnect();
                            }
                        }
                    }
                }
        ).start();
    }


    private void show(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                etName.setText(s);
            }
        });

    }


    //设置toolBar 的menu右上角加上一个注册按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_toolbar_to_register_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}

