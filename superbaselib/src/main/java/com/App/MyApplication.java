package com.App;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Base64;

import com.base.AppInfo;
import com.bean.BaseRequest;
import com.http.CMBaseSender;
import com.model.UserInfo;
import com.umeng.analytics.MobclickAgent;
import com.utils.BaseTools;
import com.utils.SharedPreferencesUtils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class MyApplication extends Application {
    private Context mContext;
    private static MyApplication instance;

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;
        //友盟统计
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.setDebugMode(true);
        //网路初始化asynchttp
        CMBaseSender.init();
        BaseRequest request = new BaseRequest();
        request.setVersion("1.0");
        if (isLogin()) {
            request.setUid(getUser().getUid());
        }
        CMBaseSender.addParams(request);
        //网络初始化okutils
//        initOkHttp();
    }



    public String apiHost() {
        String ahiHost = "";
        try {
            ApplicationInfo appInfo = getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
            ahiHost = appInfo.metaData.getString("api_host");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ahiHost;
    }

    public void setIsFirst() {
        SharedPreferencesUtils.setParam(AppInfo.getVersionName(), false);
    }

    public boolean isFirst() {
        boolean isFirst = (boolean) SharedPreferencesUtils
                .getParam(AppInfo.getVersionName(), true);
        return isFirst;
    }

    public boolean isLogin() {
        return getUser() == null ? false : true;
    }

    /**
     * 注销登陆逻辑
     */
    public void clearUser() {
//        CMBaseSender.removeUid();
        clearData(UserInfo.class.getSimpleName());
    }

    public UserInfo getUser() {
        UserInfo userInfo = (UserInfo) getCacheData(UserInfo.class.getSimpleName());
        return userInfo;
    }

    /**
     * 清楚缓存
     *
     * @param data
     */
    public void clearData(String data) {
        try {
            SharedPreferencesUtils.setParam(data, "");
        } catch (Exception e) {
            String error = e.getMessage();
            BaseTools.showToast(error);
        }
    }

    public void saveUser(UserInfo userInfo) {
        saveCacheData(userInfo);
//        BaseRequest request=new BaseRequest();
//        request.setUid(userInfo.getUid());
//        CMBaseSender.addParams(request);
    }

    /**
     * 保存缓存数据
     */
    public void saveCacheData(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            String objString = new String(Base64.encode(
                    byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            SharedPreferencesUtils.setParam(obj.getClass().getSimpleName(), objString);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存数据
     */
    public Object getCacheData(String key) {
        Object obj = null;
        try {
            String str = SharedPreferencesUtils.getParam(key, "").toString();
            if (str.length() <= 0)
                return null;
            obj = null;
            byte[] mobileBytes = Base64.decode(str.toString().getBytes(),
                    Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    mobileBytes);
            ObjectInputStream objectInputStream;
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {

        }
        return obj;
    }


}
