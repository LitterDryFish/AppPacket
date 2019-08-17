package com.example.admin.appmarket.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.appmarket.activity.HomeDetailActivity;
import com.example.admin.appmarket.base.BaseFragment;
import com.example.admin.appmarket.base.BaseHolder;
import com.example.admin.appmarket.base.MyBaseAdapter;
import com.example.admin.appmarket.entity.AppInfo;
import com.example.admin.appmarket.holder.AppHolder;
import com.example.admin.appmarket.protocol.AppProtocol;
import com.example.admin.appmarket.util.UIUtils;
import com.example.admin.appmarket.widget.LoadingPage;

import java.util.List;

/**
 * Created by admin on 2016/3/23.
 */
public class AppFragment extends BaseFragment {
    private List<AppInfo> mAppInfoList;
    private AppProtocol mHomeProtocol;

    @Override
    public View onSuccessedView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new AppFragment.MyAdapter(mAppInfoList, listView));
        return listView;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        AppProtocol appProtocol = new AppProtocol();
        mAppInfoList = appProtocol.getSoftWareDataFromNet(0);
        return check(mAppInfoList);
    }

    class MyAdapter extends MyBaseAdapter{

        public MyAdapter(final List<AppInfo> list, ListView listView) {
            super(list);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(UIUtils.getContext(), HomeDetailActivity.class);
                    String packageName = list.get(position).getPackageName();
                    intent.putExtra("packageName",packageName);
                    startActivity(intent);
                }
            });
        }

        @Override
        public List onLoadMore() {
            AppProtocol appProtocol = new AppProtocol();
            List<AppInfo> moreData = appProtocol.getSoftWareDataFromNet(0);
            return moreData;
        }

        @Override
        public BaseHolder getHolder() {
            return new AppHolder();
        }
    }
}
