package com.base_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RelativeLayout;


import com.base_fragment.BaseFragment;

import java.io.Serializable;

import east.king.com.superbaselib.R;

public class SelectSharedFragmentActivity extends FragmentActivity {
	   public static final String INTENT_FRAGMENT_NAME = "intent_fragment_name";
	  public static void startFragmentActivity(Context context, Class<? extends BaseFragment> fragmentClass, Bundle extras) {
	        Intent intent = new Intent(context, SelectSharedFragmentActivity.class);
	        intent.putExtra(INTENT_FRAGMENT_NAME, fragmentClass);
	        if (null != extras)
	            intent.putExtras(extras);
	        context.startActivity(intent);
	    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common);
		initView();
	}


	protected void initView() {
		RelativeLayout rl_root=(RelativeLayout)findViewById(R.id.rl_root);
		rl_root.setBackgroundColor(getResources().getColor(R.color.nocolor));
		Serializable fragmentName = getIntent().getSerializableExtra(INTENT_FRAGMENT_NAME);
		if (fragmentName == null) {
			return;
		}
		String name = fragmentName instanceof Class ? ((Class) fragmentName).getName() : (String) fragmentName;
		setContentFragment(name, getIntent().getExtras());
	}
    protected void setContentFragment(String fragmentClassName, Bundle arguments) {
        Log.d("BaseActivity", fragmentClassName);
        if (isFinishing()) {
            return;
        }
        Fragment fragment = Fragment.instantiate(this, fragmentClassName, arguments);
        setContentFragment(fragment, R.id.fl_container);
    }

	   protected void setContentFragment(Class<? extends BaseFragment> fragmentClass, Bundle arguments) {
		   Fragment fragment = Fragment.instantiate(this, fragmentClass.getName(), arguments);
	        setContentFragment(fragment, R.id.fl_container);
	    }
	    protected void setContentFragment(Fragment fragment, int id) {
	        if (isFinishing()) {
	            return;
	        }
	        FragmentTransaction t =getSupportFragmentManager().beginTransaction();
	        t.replace(id, fragment);
	        t.commitAllowingStateLoss();
	    }
	    @Override
	    protected void onResume() {
	    	// TODO Auto-generated method stub
	    	super.onResume();

	    }
	    @Override
	    protected void onPause() {
	    	// TODO Auto-generated method stub
	    	super.onPause();

	    }
}
