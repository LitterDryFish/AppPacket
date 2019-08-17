package com.example.admin.appmarket.protocol;

import com.example.admin.appmarket.base.BaseProtocol;
import com.example.admin.appmarket.entity.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProtocol extends BaseProtocol {
    UserInfo userInfo;
    @Override
    public String getKey() {
        return "user";
    }

    @Override
    public String getParams() {
        return userInfo.getUname()+userInfo.getUpwd();
    }

    @Override
    public UserInfo parseJson(String result) {
        if (result == null) {
            return null;
        }
        try{
            JSONObject jsonObject = new JSONObject(result);
            UserInfo userInfo=new UserInfo();
            userInfo.setLevel(jsonObject.getString("level"));
            userInfo.setNickName(jsonObject.getString("nike_name"));
            userInfo.setUname(jsonObject.getString("username"));
            userInfo.setEmail(jsonObject.getString("Email"));
            userInfo.setUpwd(jsonObject.getString("password"));
            userInfo.setSexId(jsonObject.getString("sex"));
            userInfo.setPhotoUri(jsonObject.getString("profile_photo"));
            userInfo.setRgdtDate(jsonObject.getString("register_time"));
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

}
