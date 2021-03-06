package com.utils;



import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.App.MyApplication;
import com.inter.BaseConfig;
import com.inter.DialogListener;
import com.utils.math.DateUtils;
import com.view.TitleBar;

import java.io.Closeable;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import east.king.com.superbaselib.R;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class Tools {


    public static void e(String msg) {
        if (BaseConfig.log) {
            Log.e(BaseConfig.log_tag, msg);
        }
    }

    public static void printErrorMessage(Exception e) {
        if (BaseConfig.log) {
            e.printStackTrace();
        }
    }

//    public static void StartBgService() {
//        BgService.sShouldStopService = false;
//        DaemonEnv.startServiceMayBind(BgService.class);
//    }
//
//    public static void StartBgService(Activity warningActivity) {
//        IntentWrapper.whiteListMatters(warningActivity, "消息提醒!");
//        BgService.sShouldStopService = false;
//        DaemonEnv.startServiceMayBind(BgService.class);
//    }

    /**
     * 常用工具
     */
    public static int px2dp(float pxValue) {
        return MathUtils.getInstance().px2dp(pxValue);
    }

    public static int dp2px(float pxValue) {
        return MathUtils.getInstance().dp2px(pxValue);
    }

    public static int px2sp(float pxValue) {
        return MathUtils.getInstance().px2sp(pxValue);
    }

    public static int sp2px(float pxValue) {
        return MathUtils.getInstance().sp2px(pxValue);
    }

    public static <T> T getRandom(List<T> readomWords) {
        return MathUtils.getInstance().getRandom(readomWords);
    }

    public static int getRandomInt(int start, int end) {
        return MathUtils.getInstance().getRandomInt(start, end);
    }

    /**
     * 选择对话框：确定、取消
     */
    public static void showTwoChoiceDialog(Context context, String description, final DialogListener listener) {
        DialogUtils.getInstance().showTwoChoiceDialog(context, description, listener);
    }

    /**
     * 选择图片
     */
//    public static void showPictureSelector(Activity activity, int min, int max, boolean singleChoice, boolean showCamera) {
//        PictureSelector.create(activity)
//                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
//                .maxSelectNum(max)// 最大图片选择数量 int
//                .minSelectNum(min)// 最小选择数量 int
//                .imageSpanCount(4)// 每行显示个数 int
//                .selectionMode(singleChoice ? PictureConfig.SINGLE : PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
//                .previewImage(true)// 是否可预览图片 true or false
//                .isCamera(showCamera)// 是否显示拍照按钮 true or false
//                .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
//                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
//                .enableCrop(false)// 是否裁剪 true or false
//                .compress(true)// 是否压缩 true or false
//                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//    }

    /**
     * 选择图片
     */
//    public static void showPictureSelector(Activity activity) {
//        showPictureSelector(activity, 1, 1, true, true);
//    }

    /**
     * 获取Camera图片路径
     */
    public static String getCameraPicture(int resultCode, Intent data) {
        if (resultCode == 101 || resultCode == 102) {
            return data.getStringExtra("path");
        } else if (resultCode == 103) {
            showToast("请检查相机权限~");
        }
        return null;
    }

    /**
     * 获取图片路径
     */
//    public static List<LocalMedia> getPicturePath(Intent data) {
//        return PictureSelector.obtainMultipleResult(data);
//    }

    /**
     * 设置TextView图片
     */
    public static void setTextViewDrawable(int resourceId, TextView view) {
        setTextViewDrawable(resourceId, view, 1);
    }

    /**
     * 设置TitleBar
     */
    public static void setTitleBar(TitleBar titleBar, String title, View.OnClickListener leftClick, TitleBar.TextAction rightAction) {
        titleBar.setLeftImageResource(R.drawable.ic_back);
        titleBar.setLeftClickListener(leftClick);
        titleBar.setTitle(title);
        titleBar.setTitleColor(Color.parseColor("#333333"));
        titleBar.setActionTextColor(Color.WHITE);
        if (rightAction != null) {
            titleBar.addAction(rightAction);
        }
    }

    /**
     * 设置TitleBar
     */
    public static void setTitleBar(TitleBar titleBar, String title, View.OnClickListener leftClick) {
        setTitleBar(titleBar, title, leftClick, null);
    }

    /**
     * 设置TextView图片
     *
     * @param location 默认为1；1:左 2:右 3:上 4:下
     */
    public static void setTextViewDrawable(int resourceId, TextView view, int location) {
        Drawable drawable = MyApplication.getInstance().getContext().getResources().getDrawable(resourceId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (location == 1) {
            view.setCompoundDrawables(drawable, null, null, null);
        } else if (location == 2) {
            view.setCompoundDrawables(null, null, drawable, null);
        } else if (location == 3) {
            view.setCompoundDrawables(null, drawable, null, null);
        } else if (location == 4) {
            view.setCompoundDrawables(null, null, null, drawable);
        }
    }

    /**
     * 获取布局
     */
    public static View getView(Context context, int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    /**
     * 获取布局,包括父布局
     *
     * @param parent：父布局
     */
    public static View getView(Context context, int layoutId, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(layoutId, parent, false);
    }

    /**
     * 获取Data时间
     *
     * @param year：年
     * @param month：月
     * @param day：日
     */
    public static Date getData(int year, int month, int day) {
        return DateUtils.getInstance().getDateWithYearAndMonthForDay(year, month, day);
    }

    /**
     * 获取Data时间
     */
    public static Date getData(String str) {
        return getData(str, MyApplication.getInstance().getString(R.string.data_format));
    }

    public static Date getData(String str, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        Date parse = null;
        try {
            parse = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static Date getData(int year) {
        return DateUtils.getInstance().getDateWithYear(year);
    }

    /**
     * 设置文字
     *
     * @param textView:TextView
     * @param text:设置的文本，为空默认不设置
     */
    public static void setText(TextView textView, String text) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }

    /**
     * 设置文字
     *
     * @param textView:TextView
     * @param text:设置的文本
     * @param defaltText:默认文本;设置的文本为空则设置默认文本
     */
    public static void setText(TextView textView, String text, String defaltText) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        } else {
            textView.setText(defaltText);
        }
    }

    /**
     * 判断时间是否在范围时间内
     */
    public static boolean isMiddleDate(Date date, Date minCal, Date maxCal) {
        return DateUtils.getInstance().betweenDates(date, minCal, maxCal);
    }

    /**
     * Toast调用
     */
    public static void showToast(int resourceId) {
        if (isEmpty(MyApplication.getInstance().getString(resourceId))) return;
        showToast(MyApplication.getInstance().getString(resourceId));
    }

    /**
     * Toast调用
     */
    public static void showToast(String msg) {
        if (isEmpty(msg)) return;
        ToastUtils.getInstance().info(msg, Toast.LENGTH_SHORT, true, false);
    }

    public static void showSuccessToast(String msg) {
        if (isEmpty(msg)) return;
        ToastUtils.getInstance().success(msg, Toast.LENGTH_SHORT, true, false);
    }

    public static void showErrorToast(String msg) {
        if (isEmpty(msg)) return;
        ToastUtils.getInstance().error(msg, Toast.LENGTH_SHORT, true, false);
    }

    /**
     * 获取手机号码
     */
    public static String getPhone(String phone) {
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

    public static String getPhone(String phone, int startIndex, int endIndex, String replaceString) {
        return phone.substring(0, startIndex) + replaceString + phone.substring(endIndex);
    }

    /**
     * 时间戳转换成字符串类型的时间
     */

    public static String getTime(String str, String formatString) {
        Long timestamp = Long.parseLong(str) * 1000;
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        String datetime = format.format(new Date(timestamp));
        return datetime;
    }

    public static String getTime(String str) {
        return getTime(str, MyApplication.getInstance().getString(R.string.data_format));
    }

    /**
     * 时间戳转换成字符串类型的时间
     */
    public static String getTime(long time, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        String datetime = format.format(new Date(time));
        return datetime;
    }

    public static String getTime(long time) {
        return getTime(time, MyApplication.getInstance().getString(R.string.data_format));
    }

    /**
     * 时间戳转换成字符串类型的时间
     */
    public static String getTime(Date date, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        String datetime = format.format(date);
        return datetime;
    }

    public static String getTime(Date date) {
        return getTime(date, MyApplication.getInstance().getResources().getString(R.string.data_format));
    }

    /**
     * 获取星期
     */
    public static String getWeek(int type) {
        if (type == 1) {
            return "星期日";
        } else if (type == 2) {
            return "星期一";
        } else if (type == 3) {
            return "星期二";
        } else if (type == 4) {
            return "星期三";
        } else if (type == 5) {
            return "星期四";
        } else if (type == 6) {
            return "星期五";
        } else if (type == 7) {
            return "星期六";
        }
        return null;
    }

    /**
     * 获取当前时间,默认截止到当天
     */
    public static String getCurrentTime() {
        return DateUtils.getInstance().getCurrentDate();
    }

    /**
     * 非空检验
     */
    public static boolean isNotEmpty(Object obj) {
        return MathUtils.getInstance().isNotEmpty(obj);
    }

    /**
     * 空检验
     */
    public static boolean isEmpty(Object obj) {
        return !MathUtils.getInstance().isNotEmpty(obj);
    }

    /**
     * 获取距离
     */
    public static String getDistance(String lat1, String lng1, double lat2, double lng2) {
        String distance = "";
        try {
            DecimalFormat df = new DecimalFormat("######0.00");
            double lat = Math.abs(Double.valueOf(lat1) - lat2);
            double lng = Math.abs(Double.valueOf(lng1) - lng2);
            distance = df.format(Math.sqrt(lat * lat + lng * lng) * 100);
        } catch (Exception e) {
            printErrorMessage(e);
        }
        return distance;
    }

    /**
     * 获取mate信息
     */
    public static String getMeta(String name) {
        final Context context = MyApplication.getInstance().getContext();
        String str = "";

        ApplicationInfo ai = null;
        try {
            ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object v = ai.metaData.get(name);
            if (v != null) {
                str = v.toString();
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return str;
    }

    /**
     * 银行卡号，保留最后4位，其他星号替换
     */
    public static String getBankCard(String card) {
        return card.substring(0, 4) + "***********" + card.substring(15);
    }

    /**
     * 身份证号，中间10位星号替换
     */
    public static String getIdentity(String card) {
        return card.substring(0, 4) + "**********" + card.substring(14);
    }

    public static int getScreenHeight() {
        return ScreenUtils.getInstance().getScreenHeight();
    }

    public static int getScreenWidth() {
        return ScreenUtils.getInstance().getScreenWidth();
    }

    /**
     * 关闭流
     */
    public static boolean closeIo(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                printErrorMessage(e);
            }
        }
        return true;
    }

    //获取实例
    public static synchronized Fragment getFragment(Class<? extends Fragment> clazz) {
        Fragment fragment = null;
        try {
            fragment = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }
}
