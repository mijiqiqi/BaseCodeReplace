package com.http;

import android.util.Log;


import com.bean.ApiBase;
import com.bean.UpYunBean;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public abstract class CMJsonHandler<T> extends AsyncHttpResponseHandler {
	private static final String TAG = "LYKJJsonHandler";
	private Class<?> mTargetCls;

	public CMJsonHandler(Class<?> cls) {
		mTargetCls = cls;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void onSuccess(final int statusCode, final Header[] headers, final byte[] responseBytes) {
		if (statusCode == HttpStatus.SC_NO_CONTENT) {
			SuccessResponseTask task = new SuccessResponseTask(null, headers);
			postRunnable(task);
			return;
		}

		String jsonString =new String(responseBytes);
		Log.d(TAG, "jsonString="+jsonString);
		Log.d(TAG, "target class="+mTargetCls.getName());
		if(mTargetCls.getName().equals(UpYunBean.class.getName())){
			try {
				Gson gson=new Gson();
				UpYunBean baApiBase = (UpYunBean)gson.fromJson(jsonString, mTargetCls);
				if(baApiBase==null ){
					onFailure(statusCode, headers, responseBytes, new Exception("result of json parse is null"));
					return;
				}else if(baApiBase.getCode()!=200){
					onErrorToSub(baApiBase.getCode(), baApiBase.getMessage());
					return;
				}else{
					T t=(T) baApiBase;
					SuccessResponseTask task = new SuccessResponseTask(t, headers);
					postRunnable(task);
				}
			} catch (Throwable e) {
				Log.e(TAG, "onSuccess()", e);
				onFailure(statusCode, headers, responseBytes, e);
				return;
			}
		}else{
			try {
				Gson gson=new Gson();
				ApiBase<T> baApiBase = (ApiBase<T>)gson.fromJson(jsonString, mTargetCls);
				if(baApiBase==null ){
					onFailure(statusCode, headers, responseBytes, new Exception("result of json parse is null"));
					return;
				}else if(baApiBase.getCode()!=0){
					onErrorToSub(baApiBase.getCode(), baApiBase.getMsg());
					return;
				}else{
					SuccessResponseTask task = new SuccessResponseTask(baApiBase.getData(), headers);
					postRunnable(task);
				}
			} catch (Throwable e) {
				Log.e(TAG, "onSuccess()", e);
				onFailure(statusCode, headers, responseBytes, e);
				return;
			}
		}

		if (null == mTargetCls) {
			onFailure(statusCode, headers, responseBytes, new Exception("result of json parse is null"));
			return;
		}
	}

	@Override
	public final void onFailure(final int statusCode, final Header[] headers, final byte[] responseBytes, final Throwable throwable) {		
		FailureResponseTask task = new FailureResponseTask(statusCode, throwable, headers);
        postRunnable(task);
	}
	
	/**
     * Returns when request succeeds
     *
     */
    public void onSuccessToSub(T t, Header[] headers) {
    	Log.d(TAG, "onSuccessToSub(Object) has not @Override");
    }
    
    /**
     * Returns when request failed
     *
     * @param statusCode    http response status line
     * @param throwable     throwable describing the way request failed
     */
    public void onFailureToSub(int statusCode, Throwable throwable, Header[] headers) {
    	Log.d(TAG, "onFailureToSub(int, Throwable) has not @Override");
    }
    /**
     * Returns when request failed
     *
     * @param statusCode    http response status line
     */
    public void onErrorToSub(int statusCode,String msg) {
    	Log.d(TAG, "onFailureToSub(int, Throwable) has not @Override");
    }

    /**
     * Attempts to encode response bytes as string of set encoding
     *
     * @param charset     charset to create string with
     * @param stringBytes response bytes
     * @return String of set encoding or null
     */
    /*public static String getResponseString(byte[] stringBytes, String charset) {
        try {
            String toReturn = (stringBytes == null) ? null : new String(stringBytes, charset);
            if (toReturn != null && toReturn.startsWith(UTF8_BOM)) {
                return toReturn.substring(1);
            }
            return toReturn;
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Encoding response into string failed", e);
            return null;
        }
    }*/
    
    /**
     * Returns Object of type {@link JSONObject}, {@link JSONArray}, String, Boolean, Integer, Long,
     * Double or {@link JSONObject#NULL}, see {@link JSONTokener#nextValue()}
     *
     * @param responseBody response bytes to be assembled in String and parsed as JSON
     * @return Object parsedResponse
     * @throws JSONException exception if thrown while parsing JSON
     */
    protected Object parseResponse(byte[] responseBody) throws JSONException {
        if (null == responseBody){
        	return null;
        }
        
        //trim the string to prevent start with blank, and test if the string is valid JSON, because the parser don't do this :(. If JSON is not valid this will return null
        String jsonString = new String (responseBody);
        if (null == jsonString) {
			return null;
		}
        
        Object result = null;
        jsonString = jsonString.trim();
        // Check if the string is an JSONObject style {} or JSONArray style []
        // If not we consider this as a string
        if ((jsonString.startsWith("{") && jsonString.endsWith("}"))
                || jsonString.startsWith("[") && jsonString.endsWith("]")) {
            result = new JSONTokener(jsonString).nextValue();
        }
        // Check if this is a String "my String value" and remove quote
        // Other value type (numerical, boolean) should be without quote
        else if (jsonString.startsWith("\"") && jsonString.endsWith("\"")) {
            result = jsonString.substring(1, jsonString.length() - 1);
        }
        
        if (result == null) {
            result = jsonString;
        }
        return result;
    }
    
    class SuccessResponseTask implements Runnable {
    	private T t;
    	Header[] headers;
    	
    	public SuccessResponseTask(T t, Header[] headers) {
    		this.t = t;
    		this.headers = headers;
    	}

		@Override
		public void run() {
			onSuccessToSub(t, headers);
		}
    	
    }
    
    class FailureResponseTask implements Runnable {
    	private int mStatusCode;
    	private Throwable mErr;
    	private Header[] headers;
    	
    	public FailureResponseTask(int statusCode, Throwable tr, Header[] headers) {
    		mStatusCode = statusCode;
    		mErr = tr;
    		this.headers = headers;
    	}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			onFailureToSub(mStatusCode, mErr, headers);
		}
    	
    }
    
}