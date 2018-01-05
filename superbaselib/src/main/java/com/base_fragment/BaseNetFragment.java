package com.base_fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.presenter.BaseNetView;
import com.presenter.StateContract;


public abstract class BaseNetFragment extends BaseFragment {
	private StateContract.StateView mView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView=new BaseNetView(getActivity()) {
			
			@Override
			public View getChildRootView() {
				return View.inflate(getActivity(),getContentResId(), null);
			}
			@Override
			public void showLoadStatus(int state) {
				 super.showLoadStatus(state);
				 showLoadState(state);
			}
		};
		initView(mView.getRootView());
	    return mView.getRootView();
	}
	protected final StateContract.StateView getStateView() {
        return mView;
    }

	@Override
	protected abstract void initView(View v);

	@Override
	protected abstract int getContentResId();


	protected void showLoadState(int state){}
	
}
