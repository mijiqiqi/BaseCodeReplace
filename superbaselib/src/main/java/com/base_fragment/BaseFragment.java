package com.base_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.App.AppManager;


/**
 * Created by Administrator on 2017/11/23.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener,AppManager.AppListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addListener(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(getContentResId(), container, false);
        initView(view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected abstract int getContentResId();
    protected abstract void initView(View v);

    @Override
    public void update(Object obj, String tag) {

    }
}
