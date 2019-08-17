package com.example.admin.appmarket.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.admin.appmarket.R;

public class CommunityNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_notification);

        //给toolBar添加监听
        Toolbar toolbar = (Toolbar) findViewById(R.id.notification_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunityNotification.this.setResult(RESULT_CANCELED);
                CommunityNotification.this.finish();
            }
        });

        NavigationView notification_nav_view=(NavigationView) findViewById(R.id.notification_nav_view);
        notification_nav_view.setItemIconTintList(null);
        notification_nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.answer_me_item:
                        Toast.makeText(CommunityNotification.this, "回复我的", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.star_me_item:
                        Toast.makeText(CommunityNotification.this, "点赞我的", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.attention_me_item:
                        Toast.makeText(CommunityNotification.this, "关注我的", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.important_message_item:
                        Toast.makeText(CommunityNotification.this, "重要事件", Toast.LENGTH_LONG).show();
                        break;
                }
                        return false;

            }
        });

    }
}
