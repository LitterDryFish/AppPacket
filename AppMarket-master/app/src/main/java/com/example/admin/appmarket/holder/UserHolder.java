package com.example.admin.appmarket.holder;

import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.appmarket.R;
import com.example.admin.appmarket.base.BaseHolder;
import com.example.admin.appmarket.entity.UserInfo;
import com.example.admin.appmarket.http.HttpHelper;
import com.example.admin.appmarket.util.BitmapHelpr;
import com.example.admin.appmarket.util.UIUtils;
import com.lidroid.xutils.BitmapUtils;

public class UserHolder extends BaseHolder {

    @Override
    public void refreshView() {
        UserInfo data= (UserInfo) getData();

        NavigationView mNavigationView= (NavigationView) getRootView().findViewById(R.id.nav_drawer);
        TextView username= (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.username);
        ImageView profile_photo= (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.profile_photo);

        username.setText(data.getUname());
        profile_photo.setImageURI(Uri.parse(data.getPhotoUri()));

        //加载一张网络图片(三级缓存(1,内存(维护了LRU算法的Map<imgUrl,图片Bitmap对象>)2,本地文件缓存(文件名称唯一性url地址))3,网络)
        //XUtils中BitmapUtils维护了一个三级缓存的对象
        //因为每new一个XUtils对象就申请了一个100M的内存空间,所以XUtils对象要设置成单例
        BitmapUtils bitmapUtils = BitmapHelpr.getBitmapUtils(UIUtils.getContext());
        bitmapUtils.display(profile_photo, HttpHelper.URL + "media/" + data.getPhotoUri());
        //http://127.0.0.1:8090/image?name=app/com.youyuan.yyhl/icon.jpg
    }

    @Override
    public View initView() {
        return UIUtils.inflate(R.layout.activity_main);
    }
}
