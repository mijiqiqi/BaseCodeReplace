package com.presenter;

import android.content.Context;

import com.http.CMJsonCallback;
import com.http.HttpSender;
import com.utils.NetUtil;


/**
 * Created by chenmeng on 2016/6/22.
 */
public abstract class BaseNetPresenter<T> implements StateContract.StatePresenter{

    private StateContract.StateView mView;
    private Context mContext;
    private StateListener<T> mListener;
    private String url;
    private Object object;
    private Class<?> cls;
    public BaseNetPresenter(Context mContext, StateContract.StateView mView) {
        this.mContext = mContext;
        this.mView = mView;
        mView.setPresenter(this);
    }

    protected void updateData(StateListener<T> mListener,String url,Object object,Class<?> cls) {
        this.mListener = mListener;
        this.url=url;
        this.object=object;
        this.cls=cls;
        updateData();
    }

    @Override
    public void updateData(){
        if(mView==null||mListener==null)
            throw new NullPointerException("请先调用有参的updateData");
        if (NetUtil.checkNet(mContext)) {
            mView.showLoadStatus(BaseNetView.NetworkLoadStatus.LOAD_START);
            mListener.setParams(mView);
            try {
				HttpSender.getInstance(mContext).get(url,cls,object, new CMJsonCallback<T>() {

					@Override
					public void onSuccess(T t) {
						mView.showLoadStatus(BaseNetView.NetworkLoadStatus.LOAD_FINISH);
						mListener.finishUpdate(t);
					}

					@Override
					public void onError(int code, String message) {
						mView.showLoadStatus(BaseNetView.NetworkLoadStatus.LOAD_NETWORK_ERROR);
						mListener.onError(message);
					}

					@Override
					public void onFail(int code, String message) {
						mView.showLoadStatus(BaseNetView.NetworkLoadStatus.LOAD_NETWORK_ERROR);
						mListener.onError(message);
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            mView.showLoadStatus(BaseNetView.NetworkLoadStatus.LOAD_NETWORK_ERROR);
            mListener.onError("网络异常");
        }
    }

    public static abstract class StateListener<T> implements BaseMvpModel.UpdateListener<T>{
        private StateContract.StateView mView;

        public void loadingFinish() {
            mView.showLoadStatus(BaseNetView.NetworkLoadStatus.LOAD_FINISH);
        }

        @Override
        public void finishUpdate(T result) {
            mView.showLoadStatus(BaseNetView.NetworkLoadStatus.LOAD_FINISH);
        }

        public void setParams(StateContract.StateView mView) {
            this.mView = mView;
        }

        @Override
        public void onError(String msg) {
            if(mView!=null) {
                mView.showLoadStatus(BaseNetView.NetworkLoadStatus.LOAD_FAIL);
            }
        }
    }
}
