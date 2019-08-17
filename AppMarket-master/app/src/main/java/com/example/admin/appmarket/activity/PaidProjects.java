package com.example.admin.appmarket.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.appmarket.R;
import com.example.admin.appmarket.base.BaseFragment;
import com.example.admin.appmarket.factory.PaidProjectsFragmentFactory;
import com.example.admin.appmarket.util.UIUtils;

public class PaidProjects extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_projects);

        initPagerTab();
    }

    private void initPagerTab() {

        mViewPager = (ViewPager) findViewById(R.id.paid_project_viewpager);
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        //设置fragment监听切换不同的fragment
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Fragment工厂类获取Fragment对象
                BaseFragment fragment = PaidProjectsFragmentFactory.createFragment(position);
                //根据不同的网络状态显示不同的界面
                fragment.baseShow();
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /*内部类，Fragment适配器*/
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] notificationsName;

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);

            notificationsName = UIUtils.getStringArray(R.array.notification_name);
        }

        @Override
        public Fragment getItem(int position) {
            // 根据不同的索引生成不同的Fragment
            return PaidProjectsFragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return notificationsName.length;
        }

        //指定指针说明文字方法
        @Override
        public CharSequence getPageTitle(int position) {
            return notificationsName[position];
        }
    }

}
