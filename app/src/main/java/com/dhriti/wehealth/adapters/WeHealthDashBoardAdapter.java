package com.dhriti.wehealth.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhriti.wehealth.R;
import com.dhriti.wehealth.model.HealthVitalsResponseVO;
import com.dhriti.wehealth.utils.WeHealthConstants;

import java.util.ArrayList;

public class WeHealthDashBoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private HealthVitalsResponseVO mHealthVitalsResponseVO;

    public WeHealthDashBoardAdapter( HealthVitalsResponseVO healthVitalsResponseVO, Context context){
        this.mHealthVitalsResponseVO = healthVitalsResponseVO;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding mViewDataBinding;
        if(viewType == WeHealthConstants.VITAL_TYPE_HEADER) {
             mViewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.getContext()),
                    R.layout.health_vitals_header,
                    viewGroup, false);
            WeHealthDashBoardHeaderViewHolder mWeHealthDashBoardHeaderViewHolder = new
                    WeHealthDashBoardHeaderViewHolder(mViewDataBinding.getRoot());
            return mWeHealthDashBoardHeaderViewHolder;
        } else if(viewType == WeHealthConstants.VITAL_TYPE_ITEM) {
             mViewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.getContext()),
                    R.layout.health_vitals_items,
                    viewGroup, false);
            WeHealthDashBoardItemViewHolder mWeHealthDashBoardItemViewHolder = new
                    WeHealthDashBoardItemViewHolder(mViewDataBinding.getRoot());
            return mWeHealthDashBoardItemViewHolder;
        }
        throw new RuntimeException(mContext.getResources().getString(R.string.vitals_type_none));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof WeHealthDashBoardHeaderViewHolder) {
            ((WeHealthDashBoardHeaderViewHolder) viewHolder)
                    .mVitalHeader.setText(mHealthVitalsResponseVO.getHealthVitalsTitle());
        } else if(viewHolder instanceof WeHealthDashBoardItemViewHolder) {
            ((WeHealthDashBoardItemViewHolder) viewHolder)
                    .mVitalTitle.setText(mHealthVitalsResponseVO.getHealthVitals()[position].getVitalName());
            ((WeHealthDashBoardItemViewHolder) viewHolder)
                    .mVitalValue.setText(mHealthVitalsResponseVO.getHealthVitals()[position].getVitalValue());
            ((WeHealthDashBoardItemViewHolder) viewHolder)
                    .mVitalDayAndTime.setText(mHealthVitalsResponseVO.getHealthVitals()[position].getVitalDayAndTime());
            ((WeHealthDashBoardItemViewHolder) viewHolder)
                    .mVitalSource.setText(mHealthVitalsResponseVO.getHealthVitals()[position].getVitalSource());
        }
    }

    @Override
    public int getItemCount() {
        return mHealthVitalsResponseVO.getHealthVitals().length ;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return WeHealthConstants.VITAL_TYPE_HEADER;
        } else {
            return WeHealthConstants.VITAL_TYPE_ITEM;
        }
    }

    static class WeHealthDashBoardHeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView mVitalHeader;

        public WeHealthDashBoardHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            mVitalHeader = itemView.findViewById(R.id.health_vitals_header);
        }
    }

    static class WeHealthDashBoardItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mVitalTitle, mVitalValue, mVitalDayAndTime, mVitalSource;

        public WeHealthDashBoardItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mVitalTitle = itemView.findViewById(R.id.health_vital_title);
            mVitalValue = itemView.findViewById(R.id.health_vital_value);
            mVitalDayAndTime = itemView.findViewById(R.id.health_vital_day_and_time);
            mVitalSource = itemView.findViewById(R.id.health_vital_source);
        }
    }
}
