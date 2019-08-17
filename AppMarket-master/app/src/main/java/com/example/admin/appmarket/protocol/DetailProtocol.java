package com.example.admin.appmarket.protocol;

import com.example.admin.appmarket.base.BaseProtocol;
import com.example.admin.appmarket.entity.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2016/4/7.
 */
public class DetailProtocol extends BaseProtocol<AppInfo> {

    private String packageName;

    @Override
    public String getKey() {
        return "detail";
    }

    @Override
    public String getParams() {
        return packageName;
    }

    @Override
    public AppInfo parseJson(String result) {
        if(result==null){
            return null;
        }

        try {
            JSONArray jsonArray=new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                JSONObject jsonObject2=jsonObject1.getJSONObject("fields");
                AppInfo appInfo = new AppInfo();
                appInfo.setDes(jsonObject2.getString("des"));
                appInfo.setDownloadUrl(jsonObject2.getString("downloadUrl"));
                appInfo.setIconUrl(jsonObject2.getString("iconUrl"));
                appInfo.setId(jsonObject1.getInt("pk"));
                appInfo.setName(jsonObject2.getString("name"));
                appInfo.setPackageName(jsonObject2.getString("packetName"));
                appInfo.setSize(jsonObject2.getInt("size"));
                appInfo.setStars((float) jsonObject2.getDouble("stars"));

                appInfo.setAuthor(jsonObject2.getString("author"));
                appInfo.setDownloadNum(jsonObject2.getString("downloadNum"));
                appInfo.setVersion(jsonObject2.getString("version"));
                appInfo.setDate(jsonObject2.getString("date"));

                appInfo.getScreenList().clear();
                if (jsonObject2.has("picture")) {
                    //JSONArray jsonArray1 = jsonObject2.getJSONArray("picture");
                    //for (int j = 0; j < jsonArray.length(); j++) {
                      //  appInfo.getScreenList().add(jsonArray1.getString(i));
                    //}
                    appInfo.getScreenList().add(jsonObject2.getString("picture"));
                }

                appInfo.getSafeUrlList().clear();
                appInfo.getSafeDesUrlList().clear();
                appInfo.getSafeDesList().clear();
                if (jsonObject1.has("safe")) {
                    JSONArray jsonArray2 = jsonObject1.getJSONArray("safe");
                    for (int k = 0; k < jsonArray.length(); k++) {
                        JSONObject jsonObject3 = jsonArray.getJSONObject(i);

                        appInfo.getSafeUrlList().add(jsonObject2.getString("safeUrl"));
                        appInfo.getSafeDesUrlList().add(jsonObject2.getString("safeDesUrl"));
                        appInfo.getSafeDesList().add(jsonObject2.getString("safeDes"));
                    }
                }
                return appInfo;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
