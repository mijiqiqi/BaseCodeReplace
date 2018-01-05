package com.http;

import android.content.Context;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class HttpSender {
	private static CMHttpSender instance;
  public static CMHttpSender getInstance(Context context){
	  SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());	//用于https
	  if(instance==null){
		  instance=new CMHttpSender(context);
	  }
	  return instance;
  }
}
