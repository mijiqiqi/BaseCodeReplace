package com.http;
public abstract interface CMJsonCallback<T> {

	public void onSuccess(T t);	
	public void onError(int code, String message);
	public void onFail(int code, String message);
}
