package com.utils;

import android.content.Context;
import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 对字符串操作的工具类
 * 
 * @author Tony
 *
 */
public class StringUtils
{

    
    public static boolean isEmpty(CharSequence str) {
        if (TextUtils.isEmpty(str) || "null".equals(str) || "NULL".equals(str)) {
            return true;
        }
        //过滤空格
        if(str.equals(" ")){
            return true;
        }
        return false;
    }
    public static boolean isUserName(String str) {
    	 return match(str, "^[\\x{4e00}-\\x{9fa5}A-Za-z0-9_]{2,6}$");
    }
    public static boolean isPass(String str) {
   	 return match(str, "^[\\x{4e00}-\\x{9fa5}A-Za-z0-9_]{6,}$");
   }
    public static boolean isNum(String str) {
      	 return match(str, "^\\d+(\\.\\d+)?$");
      }
    public static String getTime(String str) {
    	 Long timestamp=Long.parseLong(str)*1000;
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			String datetime=format.format(new Date(timestamp));
   	 return datetime;
   }
    public static String getPhone(String phone) {
  	 return phone.substring(0,3)+"****"+phone.substring(7);
  }
    /** 
    * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
    */  
   public static int dip2px(Context context, float dpValue) {  
       final float scale = context.getResources().getDisplayMetrics().density;  
       return (int) (dpValue * scale + 0.5f);  
   }  
 
   /** 
    * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
    */  
   public static int px2dip(Context context, float pxValue) {  
       final float scale = context.getResources().getDisplayMetrics().density;  
       return (int) (pxValue / scale + 0.5f);  
   }  
   public static String GetDistance(double distance)
   {
	   String km="";
	   try {
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		km=df.format(distance/1000);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      return km+"km";
   }
   public static String GetDistance(String lat1, String lng1, double lat2, double lng2)
   {
	   String distance="";
	   try {
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		 double lat=Math.abs(Double.valueOf(lat1)-lat2); 
		 double lng=Math.abs(Double.valueOf(lng1)-lng2); 
		 distance=df.format(Math.sqrt(lat*lat+lng*lng)*100);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      return distance;
   }
   private final static boolean match(String text, String reg) {
       if (StringUtils.isEmpty(text) || StringUtils.isEmpty(reg))
           return false;
       return Pattern.compile(reg).matcher(text).matches();
   }
   public static String getPrice(Double price){
	   DecimalFormat    df   = new DecimalFormat("######0.00");   
      return df.format(price);
   }
   public static boolean isURL(String url){
   	if(!isEmpty(url)){
   		int pos = url.indexOf("http://");
   		if(pos == -1){
   			pos = url.indexOf("https://");
   			if(pos == -1){
   				return false;
   			}
   			return true;
   		}
   		return true;
   	}
   	return false;
   }
}
