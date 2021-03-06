package com.presenter;
import android.view.View;

/**
 * Created by chenmeng on 2016/5/25.
 */
public interface StateContract {
    interface StateView extends BaseView<StatePresenter>{
        void showLoadStatus(int state);
        View getChildRootView();
        View getRootView();
        void setOnInterceptDisplay(boolean OnInter);
    }

    interface StatePresenter extends BasePresenter{
        void updateData();
    }
}
