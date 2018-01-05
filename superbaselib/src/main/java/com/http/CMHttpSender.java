package com.http;

import android.content.Context;
import android.util.Log;


import com.bean.UpYunBean;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.upyun.UpYunConstants;
import com.upyun.UpYunUtils;
import com.utils.DLDateUtils;
import com.utils.DLFileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.HashMap;

public class CMHttpSender extends CMBaseSender {

	public CMHttpSender() {
		mClient = new AsyncHttpClient();
	}

	public CMHttpSender(Context ctx) {
		mClient = new AsyncHttpClient();
		this.mCtx = ctx;
	}

	@Override
	public synchronized void cancelAll() {
		mClient.cancelAllRequests(true);
		mCtx = null;
	}

	@Override
	public <T> void get(String url, Class<?> cls, Object object,
			CMJsonCallback<T> callback) {
		HashMap<String, String> hashMap=objToHash(object);
		hashMap.putAll(params);
		RequestParams requestParams = new RequestParams(hashMap);
		String ur=ConfigConstants.URL + url + "?" + requestParams;
		Log.e("url", ur);
		mClient.get(mCtx, ConfigConstants.URL + url, requestParams, new ExtralCallback<T>(cls,
				url, callback));
	}

	@Override
	public <T> void post(String url, Class<?> cls, Object object,
			CMJsonCallback<T> callback) {
		HashMap<String, String> hashMap=objToHash(object);
		hashMap.putAll(params);
		RequestParams requestParams = new RequestParams(hashMap);
		String allurl = ConfigConstants.URL + url + "?" + requestParams;
		Log.e("url", allurl);
		mClient.post(mCtx, ConfigConstants.URL + url, requestParams, new ExtralCallback<T>(
				cls, url, callback));
	}
	@Override
	public <T> void postFile(Object obj, CMJsonCallback<T> callback) {
		long EXPIRATION = System.currentTimeMillis() / 1000 + 1000 * 5 * 10; //过期时间，必须大于当前时间
		RequestParams params = null;
		try {
			String path = (String) obj;
			File file = new File(path);


			String tempExname = DLFileUtil.getExtensionNameFromPath(path);

			long temptime = DLDateUtils.getCurrentTimeInLong();
			int[] tempTime = DLDateUtils.getTimerSuffic(temptime);

			String SAVE_KEY = "/uploads" + File.separator + tempTime[0] + File.separator + tempTime[1] + File.separator + tempTime[2] + File.separator + temptime + tempExname;
			HashMap<String, Object> p = new HashMap<String, Object>();
			String policy = UpYunUtils.makePolicy(SAVE_KEY, EXPIRATION, UpYunConstants.BUCKET, p);
			String signature = UpYunUtils.signature(policy + "&" + UpYunConstants.UPYUN_UPLOAD_KEY);
			params = new RequestParams();
			params.put("policy", policy);
			params.put("signature", signature);
			params.put("file", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mClient.post(mCtx, UpYunConstants.url, params, new ExtralCallback<T>(UpYunBean.class, UpYunConstants.url, callback));
	}

	public static String getMd5ByFile(File file) throws FileNotFoundException {
		String value = null;
		FileInputStream in = new FileInputStream(file);
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
	
	

	
}
