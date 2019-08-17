package com.example.admin.appmarket.protocol;

import com.example.admin.appmarket.base.BaseProtocol;
import com.example.admin.appmarket.entity.AppInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/3/31.
 */
public class HomeProtocol extends BaseProtocol<List<AppInfo>> {

    private List<AppInfo> mAppInfoList = new ArrayList<AppInfo>();
    private List<String> mPicList = new ArrayList<String>();

    public List<String> getPicList() {
        return mPicList;
    }

    @Override
    public String getKey() {
        return "home";
    }

    @Override
    public String getParams() {
        return "";
    }

    @Override
    public List<AppInfo> parseJson(final String result) {
        if (result == null) {
            return null;
        }

        try {
                JSONArray jsonArray=new JSONArray(result);
            //JSONObject jsonObject = new JSONObject(result);
            //if (jsonObject.has("list")) {
                mAppInfoList.clear();
                //JSONArray jsonArray = jsonObject.getJSONArray("list");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    JSONObject jsonObject3 = jsonObject2.getJSONObject("fields");
                    AppInfo appInfo = new AppInfo();
                    appInfo.setDes(jsonObject3.getString("des"));
                    appInfo.setDownloadUrl(jsonObject3.getString("downloadUrl"));
                    appInfo.setIconUrl(jsonObject3.getString("iconUrl"));
                    appInfo.setId(jsonObject2.getInt("pk"));
                    appInfo.setName(jsonObject3.getString("name"));
                    appInfo.setPackageName(jsonObject3.getString("packetName"));
                    appInfo.setSize(jsonObject3.getInt("size"));
                    appInfo.setStars((float) jsonObject3.getDouble("stars"));
                    mAppInfoList.add(appInfo);

                    if (jsonObject3.has("picture")) {
                        mPicList.clear();
                        //JSONArray jsonArray1 = jsonObject2.getJSONArray("picture");
                        //for (int j = 0; j < jsonArray1.length(); j++) {
                            mPicList.add(jsonObject3.getString("picture"));
                       // }
                    }
                }




            return mAppInfoList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
