package com.example.admin.appmarket.protocol;

import com.example.admin.appmarket.base.BaseProtocol;
import com.example.admin.appmarket.entity.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/4/5.
 */
public class AppProtocol extends BaseProtocol<List<AppInfo>> {
    @Override
    public String getKey() {
        return "app";
    }

    @Override
    public String getParams() {
        return "";
    }

    @Override
    public List<AppInfo> parseJson(String result) {
        if (result == null) {
            return null;
        }

        try {
            JSONArray jsonArray = new JSONArray(result);
            List<AppInfo> appInfoList = new ArrayList<AppInfo>();
            appInfoList.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AppInfo appInfo = new AppInfo();
                JSONObject jsonObject1 =jsonObject.getJSONObject("fields");
                        appInfo.setDes(jsonObject1.getString("des"));
                appInfo.setDownloadUrl(jsonObject1.getString("downloadUrl"));
                appInfo.setIconUrl(jsonObject1.getString("iconUrl"));
                appInfo.setId(jsonObject.getInt("pk"));
                appInfo.setName(jsonObject1.getString("name"));
                appInfo.setPackageName(jsonObject1.getString("packetName"));
                appInfo.setSize(jsonObject1.getInt("size"));
                appInfo.setStars((float) jsonObject1.getDouble("stars"));
                appInfoList.add(appInfo);
            }
            return appInfoList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
