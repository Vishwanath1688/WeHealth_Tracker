package com.dhriti.wehealth.views.fragments;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhriti.wehealth.R;
import com.dhriti.wehealth.WeHealthApplication;
import com.dhriti.wehealth.adapters.WeHealthDashBoardAdapter;
import com.dhriti.wehealth.adapters.WeHealthSourceAdapter;
import com.dhriti.wehealth.databinding.FragmentHealthVitalsSourceBinding;
import com.dhriti.wehealth.model.VitalsSourceResponseVO;
import com.dhriti.wehealth.network.WeHealthServiceClient;
import com.dhriti.wehealth.utils.WeHealthConstants;
import com.dhriti.wehealth.viewmodel.VitalsSourcesListViewModel;

import java.util.ArrayList;

public class SourceFragment extends Fragment implements Observer<VitalsSourceResponseVO> {

    private FragmentHealthVitalsSourceBinding mBinding;
    private VitalsSourcesListViewModel mVitalsSourcesListViewModel;

    public static SourceFragment newInstance() {
        SourceFragment fragment = new SourceFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_health_vitals_source,
                container, false);
        init();
        return mBinding.getRoot();
    }

    private void init(){
        mVitalsSourcesListViewModel = new VitalsSourcesListViewModel(
                WeHealthServiceClient.getInstance().getClient(),
                WeHealthServiceClient.getInstance().getRequest
                        (WeHealthConstants.WE_HEALTH_GET_VITALS_SOURCES_LIST));
        mVitalsSourcesListViewModel.getVitalsSource();
        mVitalsSourcesListViewModel.loadVitalsSource().observe(getActivity(), this);;
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
    public void onChanged(@Nullable VitalsSourceResponseVO vitalsSourceResponseVO) {
        if(vitalsSourceResponseVO.getVitalsSources().length > 0) {
            WeHealthSourceAdapter mWeHealthSourceAdapter = new WeHealthSourceAdapter(vitalsSourceResponseVO,
                    WeHealthApplication.getAppContext());
            mBinding.vitalsSourceContainerRecyclerView.setHasFixedSize(true);
            mBinding.vitalsSourceContainerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.VERTICAL, false));
            mBinding.vitalsSourceContainerRecyclerView.setAdapter(mWeHealthSourceAdapter);
        }
    }
}
