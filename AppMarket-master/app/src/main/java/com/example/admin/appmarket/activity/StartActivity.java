package com.example.admin.appmarket.activity;

import android.content.Intent;
import android.os.Bundle;
import com.example.admin.appmarket.R;
import com.example.admin.appmarket.base.BaseActivity;

public class StartActivity extends BaseActivity {

    private  final int SPLASH_DISPLAY_LENGHT = 2000;//两秒后进入系统，时间可自行调整

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //在StartActivity停留2秒然后进入StartActivity
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(StartActivity.this,MainActivity.class);
                StartActivity.this.startActivity(mainIntent);
                StartActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGHT);

    }
}
