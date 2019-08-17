package com.example.admin.appmarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.appmarket.R;
import com.example.admin.appmarket.base.BaseActivity;
import com.example.admin.appmarket.base.BaseFragment;
import com.example.admin.appmarket.factory.FragmentFactory;
import com.example.admin.appmarket.util.UIUtils;
import com.example.admin.appmarket.widget.PagerTab;

public class MainActivity extends BaseActivity {

    private PagerTab mTab;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private ImageView profile_photo;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPagerTab();
        initActionBar();
    }



    private void initActionBar() {
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        ActionBar supportActionBar = getSupportActionBar();
        //设置按钮右侧的文字说明
        supportActionBar.setTitle(UIUtils.getString(R.string.app_name));
        //设置actionbar左上角按钮可以去点击
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        //设置actionbar左上角按钮可以去设置图片
        supportActionBar.setHomeButtonEnabled(true);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerlayout,
                R.string.drawer_open,
                R.string.drawer_close
        );
        mDrawerlayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        });
        //将按钮的点击状态和侧拉栏目的扩展或者收缩做同步
        mActionBarDrawerToggle.syncState();


        /*为侧边栏menu添加事件监听*/
        mNavigationView=(NavigationView) findViewById(R.id.nav_drawer);
        mNavigationView.setItemIconTintList(null);//此处是设置menu图标的颜色为图标本身的颜色  /*设置侧栏头部监听*/
        mNavigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_photo=(ImageView) findViewById(R.id.profile_photo);
                Login=(Button) findViewById(R.id.log_in);

                profile_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"头像",Toast.LENGTH_LONG).show();
                    }
                });

                Login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"登录",Toast.LENGTH_LONG).show();
                        Intent LoginIntent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivityForResult(LoginIntent,1);

                    }
                });
            }
        });
        //为Item设置监听
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.community_notification_item:
                        Toast.makeText(MainActivity.this,"社区通知",Toast.LENGTH_LONG).show();
                        Intent notification=new Intent(MainActivity.this,CommunityNotification.class);
                        startActivityForResult(notification,2);
                        break;
                    case R.id.project_paid_item:
                        Toast.makeText(MainActivity.this,"已购项目",Toast.LENGTH_LONG).show();
                        Intent paid_projects=new Intent(MainActivity.this,PaidProjects.class);
                        startActivityForResult(paid_projects,3);
                        break;
                    case R.id.my_app_packet_item:
                        Toast.makeText(MainActivity.this,"我的软件迁移包",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.my_page_item:
                        Toast.makeText(MainActivity.this,"我的主页",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.setting_item:
                        Toast.makeText(MainActivity.this,"设置",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.help_item:
                        Toast.makeText(MainActivity.this,"建议与帮助",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.update_item:
                        Toast.makeText(MainActivity.this,"检查更新",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.about_us_item:
                        Toast.makeText(MainActivity.this,"关于我们",Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });



    }

      /*点击滑动事件绑定,便于侧边栏的滑出和滑动隐藏，或者点击隐藏及点击滑出*/
    private void initPagerTab() {
        mTab = (PagerTab) findViewById(R.id.tab);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        //指针控件绑定ViewPager
        mTab.setViewPager(mViewPager);
        mTab.setOnPageChangeListener(new MyOnPageChangeListener());
    }

        /*内部类，用于获取产生不同的Fragment方便Fragment切换*/
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            //Fragment工厂类获取Fragment对象
            BaseFragment fragment = FragmentFactory.createFragment(position);
            //根据不同的网络状态显示不同的界面
            fragment.baseShow();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
    /*内部类，Fragment适配器*/
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] tabNames;

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            tabNames = UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public Fragment getItem(int position) {
            // 根据不同的索引生成不同的Fragment
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        //指定指针说明文字方法
        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }

    /*创建侧边栏弹出按钮*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    /*为侧边栏弹出按钮设置事件监听*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item)|| mActionBarDrawerToggle.onOptionsItemSelected(item);
    }
}
