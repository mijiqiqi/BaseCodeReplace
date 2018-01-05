package com.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.App.MyApplication;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 常用工具
 * 
 * @author Tony
 *
 */
public class BaseTools extends Tools{
	/**
	 *
	 */
	private static Toast toast;

	private BaseTools() {
	}


	public static void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }
    
    public static void showToast(int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    public static void showToast(int resId, int duration) {
        final Context context = MyApplication.getInstance();
        showToast(context.getString(resId), duration);
    }
    
    public static void showToast(String content, int duration) {
        if (!StringUtils.isEmpty(content)) {
				try {
					if (toast==null)
					{
						toast =Toast.makeText(MyApplication.getInstance().getContext(), content, duration);
					}else {
						toast.setText(content);
					}
					toast.show();
				} catch (OutOfMemoryError e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

        }
    }
	
	/**
	 * ldpi : {density=0.75, width=240, height=320, scaledDensity=0.75, xdpi=120.0, ydpi=120.0}
	 * mdpi : {density=1.0, width=320, height=480, scaledDensity=1.0, xdpi=160.0, ydpi=160.0}
	 * hdpi : {density=1.5, width=480, height=800, scaledDensity=1.5, xdpi=240.0, ydpi=240.0}
	 * xdpi : {density=2, width=720, height=1280, scaledDensity=2, xdpi=320.0, ydpi=320.0}
	 */

	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 *
	 * @param pxValue
	 * @param scale（DisplayMetrics类中属性density）
	 * @return
	 */
	 public static int px2dp(float pxValue) {
	     final float fontScale = MyApplication
				 .getInstance().getResources().getDisplayMetrics().density;
		 return (int) (pxValue / fontScale + 0.5f);
	 }

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 *
	 * @param dipValue
	 * @param scale（DisplayMetrics类中属性density）
	 * @return
	 */
	 public static int dp2px(float dipValue) {
	     final float scale = MyApplication
				 .getInstance().getResources().getDisplayMetrics().density;
		 return (int) (dipValue * scale + 0.5f);
	 }

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue
	 * @return
	 */
	 public static int px2sp(float pxValue) {
		 final float fontScale = MyApplication
				 .getInstance().getResources().getDisplayMetrics().density;
		 return (int) (pxValue / fontScale + 0.5f);
	 }

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue
	 * @return
	 */
	 public static int sp2px(float spValue) {
		 final float fontScale = MyApplication
				 .getInstance().getResources().getDisplayMetrics().density;
		 return (int) (spValue * fontScale + 0.5f);
	 }


	/**
	 * 在给定的范围中随机取1个数<br>
	 *
	 *
	 * 如:getRandomInt(1, 9), 返回1到9之间的随机数
	 *
	 * @return
	 */
    public static int getRandomInt(final int start, final int end) {
        List<Integer> readomWords = new ArrayList<Integer>();
        for (int i = start; i <= end; i++) {
            readomWords.add(i);
        }
        return getRandom(readomWords);
    }

	/**
	 * 在给定的数组中随机取1个数
	 *
	 * readomWord = [18, 19, 20, 21];
	 * @return
	 */
    public static <T> T getRandom(final List<T> readomWords) {
        final int idx = (int) (Math.random() * readomWords.size());
        return readomWords.get(idx);
    }
    
    public static String getMeta(final String name) {
    	final Context context = MyApplication.getInstance().getContext();
        String str = "";

        ApplicationInfo ai = null;
        try {
            ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object v = ai.metaData.get(name);
            if (v != null) {
                str = v.toString();
            }
        } catch (NameNotFoundException e) {
           
        }
        return str;
    }
    public static boolean isTest(){
    	if(getMeta("api_host").equals("product")){
    		return false;
    	}
    	return true;
    }
    /**
     * 鏄剧ず闅愯棌杞敭鐩�
     *
     * @param keyWord 鑾峰彇鐒︾偣鐨凟ditText
     * @param type    1鏄剧ず 鍏朵粬闅愯棌锛堟殏鏈疄鐜帮級
     */
    public static void showAndHideKeybord(EditText keyWord, int type) {
        try {
			InputMethodManager inputManager = (InputMethodManager) keyWord.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			if (type == 1) {
				keyWord.setFocusableInTouchMode(true);
			    keyWord.requestFocus();
			    inputManager.showSoftInput(keyWord, 0);
			} else {
				 keyWord.setFocusableInTouchMode(false);
			    inputManager.hideSoftInputFromWindow(keyWord.getWindowToken(), 0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BaseTools.showToast(e.getMessage());
		}
    }
	//java 获取可变UUID
	 public static String getMyUUID(){
	  UUID uuid = UUID.randomUUID();
	     String uniqueId = uuid.toString();
	    return uniqueId;
	   }

	/**
	 * 判断对象是否为null或长度数量为0
	 */
	public static boolean isNotEmpty(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof String && (obj.toString().length() == 0||obj.toString().trim().length() == 0)) {
			return false;
		}
		if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
			return false;
		}
		if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			return false;
		}
		if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return false;
		}
		if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
			return false;
		}
		if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
			return false;
		}
		if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
			return false;
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
			if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
				return false;
			}
		}
		return true;
	}
}
