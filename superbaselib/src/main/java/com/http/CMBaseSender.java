package com.http;

import android.content.Context;


import com.loopj.android.http.AsyncHttpClient;
import com.utils.StringUtils;

import org.apache.http.Header;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class CMBaseSender {
	protected Context mCtx;
	protected AsyncHttpClient mClient;
	protected static HashMap<String, String> params; //store public params	
	public static void init() {
		params= new HashMap<String, String>();
		params.put("token","1.0");
	}
	public static void addParams(Object object){
		HashMap<String, String> hashMap=objToHash(object);
		params.putAll(hashMap);

	}
	public static void removeUid(){
		params.remove("uid");

	}
	public abstract <T> void get(String url,Class<?> cls,Object object, CMJsonCallback<T> callback) ;
	public abstract <T> void post(String url,Class<?> cls,Object object, CMJsonCallback<T> callback) ;
	public abstract <T> void postFile(Object object, CMJsonCallback<T> callback) ;
	public abstract void cancelAll();
	class ExtralCallback<T> extends CMJsonHandler<T>{
		private CMJsonCallback<T> callback;

		public ExtralCallback(Class<?> cls, String tag, CMJsonCallback<T> callback) {
			super(cls);
			this.callback = callback;
			
		}

		@Override
		public void onSuccessToSub(T t, Header[] headers) {
			if (null == callback) {
				return;
			}
			
			try {						
				callback.onSuccess(t);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
        @Override
        public void onErrorToSub(int statusCode, String msg) {
        	if (null == callback) {
				return;
			}
			
			try {						
				callback.onError(statusCode,msg);
			} catch (Throwable e) {
				//
			}
        }
		@Override
		public void onFailureToSub(int statusCode, Throwable throwable, Header[] headers) {
			if (null == callback) {
				return;
			}
			
			try {
				
				callback.onFail(statusCode, throwable.getMessage());
			} catch (Throwable e) {
				//
			}
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static HashMap<String, String> objToHash(Object obj) {
	     
	    LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
	    try {
			Class clazz = obj.getClass();
			List<Class> clazzs = new ArrayList<Class>();
			 
			do {
			    clazzs.add(clazz);
			    clazz = clazz.getSuperclass();
			} while (!clazz.equals(Object.class));
			 
			for (Class iClazz : clazzs) {
			    Field[] fields = iClazz.getDeclaredFields();
			    for (Field field : fields) {
			        String objVal = null;
			        field.setAccessible(true);
			        if(field.get(obj)!=null){
			        	objVal =String.valueOf(field.get(obj));			        	
			        	if(!StringUtils.isEmpty(objVal)){
			        		hashMap.put(field.getName(), objVal);
			        	}
			        }
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	    return hashMap;
	}
	public void addPublicParam(Object object) throws Exception{
		if (null == params || params.size() <= 0) {
			throw new Exception("it has not been initialized,please init network firstly!");
		}
		params.putAll(objToHash(object));
	}
}
