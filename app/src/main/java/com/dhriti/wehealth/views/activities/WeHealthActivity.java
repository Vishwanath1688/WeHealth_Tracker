package com.dhriti.wehealth.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.dhriti.wehealth.R;
import com.dhriti.wehealth.databinding.WeHealthDashboardBinding;
import com.dhriti.wehealth.listeners.WeHealthBottomNavigationListener;
import com.dhriti.wehealth.utils.WeHealthUtils;
import com.dhriti.wehealth.views.fragments.DashBoardFragment;
import com.dhriti.wehealth.views.fragments.SourceFragment;

public class WeHealthActivity extends WeHealthBaseActivity {

    private WeHealthDashboardBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.we_health_dashboard);
        mBinding = DataBindingUtil.setContentView(this, R.layout.we_health_dashboard);
        init();
    }

    private void init() {
        WeHealthUtils.loadFragment(DashBoardFragment.newInstance(), mBinding.weHealthContainer.getId(), this);
        mBinding.weHealthNavigation.setSelectedItemId(R.id.navigation_dashboard);
        mBinding.weHealthNavigation.setOnNavigationItemSelectedListener(
                new WeHealthBottomNavigationListener(this, mBinding.weHealthContainer.getId()));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
