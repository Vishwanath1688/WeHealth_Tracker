package com.dhriti.wehealth.views.fragments;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhriti.wehealth.R;
import com.dhriti.wehealth.WeHealthApplication;
import com.dhriti.wehealth.adapters.WeHealthDashBoardAdapter;
import com.dhriti.wehealth.databinding.FragmentDashBoardBinding;
import com.dhriti.wehealth.interfaces.IMQTTListener;
import com.dhriti.wehealth.listeners.WeHealthMQTTSubscriber;
import com.dhriti.wehealth.model.HealthVitalsResponseVO;
import com.dhriti.wehealth.network.WeHealthServiceClient;
import com.dhriti.wehealth.utils.WeHealthConstants;
import com.dhriti.wehealth.viewmodel.HealthVitalsListViewModel;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

public class DashBoardFragment extends Fragment implements Observer<HealthVitalsResponseVO>, IMQTTListener {

    private FragmentDashBoardBinding mBinding;
    private ArrayList<String> mVitalsList;
    private HealthVitalsListViewModel mHealthVitalsListViewModel;
    private TextView mVitalTitle, mVitalValue, mVitalDayAndTime, mVitalSource;
    private WeHealthMQTTSubscriber mWeHealthMQTTSubscriber;

    public static DashBoardFragment newInstance() {
        DashBoardFragment fragment = new DashBoardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dash_board,
                container, false);
        init();
        return mBinding.getRoot();
    }

    private void init(){
        mHealthVitalsListViewModel = new HealthVitalsListViewModel(
                WeHealthServiceClient.getInstance().getClient(),
                WeHealthServiceClient.getInstance().getRequest(WeHealthConstants.WE_HEALTH_GET_VITALS_LIST));
        mHealthVitalsListViewModel.getHealthVitals();
        mHealthVitalsListViewModel.loadHealthVitals().observe(getActivity(), this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onChanged(@Nullable HealthVitalsResponseVO healthVitalsResponseVO) {
        if(healthVitalsResponseVO.getHealthVitals().length > 0) {
            WeHealthDashBoardAdapter mWeHealthDashBoardAdapter = new WeHealthDashBoardAdapter(healthVitalsResponseVO,
                    WeHealthApplication.getAppContext());
            mBinding.dashboardContainerRecyclerView.setHasFixedSize(true);
            mBinding.dashboardContainerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.VERTICAL, false));
            mBinding.dashboardContainerRecyclerView.setAdapter(mWeHealthDashBoardAdapter);
            mWeHealthMQTTSubscriber = new WeHealthMQTTSubscriber(WeHealthApplication.getAppContext(),
                    this);
            mBinding.healthVitalsBodyTempView.healthVitalTitle.setText(getResources()
                    .getString(R.string.vital_body_temperature_title));
            mBinding.healthVitalsBodyTempView.healthVitalDayAndTime.setText(getResources()
                    .getString(R.string.vital_body_temperature_date));
            mBinding.healthVitalsBodyTempView.healthVitalSource.setText(getResources()
                    .getString(R.string.vital_body_temperature_type));
        }
    }

    @Override
    public void onMessageReceived(String topic, MqttMessage message) {
        mBinding.healthVitalsBodyTempView.healthVitalValue.setText(message.toString());
    }

    @Override
    public void onPause() {
        mWeHealthMQTTSubscriber.unSubscribeMQTTConnection();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mWeHealthMQTTSubscriber.disConnectMQTTConnection();
        super.onDestroy();
    }
}
