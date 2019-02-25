package com.dhriti.wehealth.listeners;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.dhriti.wehealth.R;
import com.dhriti.wehealth.WeHealthApplication;
import com.dhriti.wehealth.utils.WeHealthUtils;
import com.dhriti.wehealth.views.fragments.DashBoardFragment;
import com.dhriti.wehealth.views.fragments.SourceFragment;

public class WeHealthBottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Activity mActivity;
    private int mContainerId;

    public WeHealthBottomNavigationListener(Activity activity, int containerId){
        this.mActivity = activity;
        this.mContainerId = containerId;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment mFragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_dashboard:
                mFragment = DashBoardFragment.newInstance();
                break;
            case R.id.navigation_source:
                mFragment = SourceFragment.newInstance();
                break;
        }
        return WeHealthUtils.loadFragment(mFragment, mContainerId, mActivity);
    }
}
