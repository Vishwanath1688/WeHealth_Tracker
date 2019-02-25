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
import com.dhriti.wehealth.model.VitalsSourceResponseVO;
import com.dhriti.wehealth.utils.WeHealthConstants;

import java.util.ArrayList;

public class WeHealthSourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private VitalsSourceResponseVO mVitalsSourceResponseVO;

    public WeHealthSourceAdapter(VitalsSourceResponseVO vitalsSourceResponseVO, Context context){
        this.mVitalsSourceResponseVO = vitalsSourceResponseVO;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding mViewDataBinding;
        if(viewType == WeHealthConstants.VITAL_TYPE_HEADER) {
            mViewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.getContext()),
                    R.layout.vitals_source_header,
                    viewGroup, false);
            WeHealthSourceHeaderViewHolder mWeHealthSourceHeaderViewHolder = new
                    WeHealthSourceHeaderViewHolder(mViewDataBinding.getRoot());
            return mWeHealthSourceHeaderViewHolder;
        } else if(viewType == WeHealthConstants.VITAL_TYPE_ITEM) {
            mViewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.getContext()),
                    R.layout.vitals_source_items,
                    viewGroup, false);
            WeHealthSourceItemViewHolder mWeHealthSourceItemViewHolder = new
                    WeHealthSourceItemViewHolder(mViewDataBinding.getRoot());
            return mWeHealthSourceItemViewHolder;
        }
        throw new RuntimeException(mContext.getResources().getString(R.string.vitals_type_none));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof WeHealthSourceHeaderViewHolder) {
            ((WeHealthSourceHeaderViewHolder) viewHolder)
                    .mVitalSourceHeader.setText(mVitalsSourceResponseVO.getVitalsSourcesTitle());
        } else if(viewHolder instanceof WeHealthSourceItemViewHolder) {
            ((WeHealthSourceItemViewHolder) viewHolder)
                    .mSourceTitle.setText(mVitalsSourceResponseVO.getVitalsSources()[position].getVitalSourceName());
        }
    }

    @Override
    public int getItemCount() {
        return mVitalsSourceResponseVO.getVitalsSources().length;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return WeHealthConstants.VITAL_TYPE_HEADER;
        } else {
            return WeHealthConstants.VITAL_TYPE_ITEM;
        }
    }

    static class WeHealthSourceHeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView mVitalSourceHeader;

        public WeHealthSourceHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            mVitalSourceHeader = itemView.findViewById(R.id.vitals_source_header);
        }
    }

    static class WeHealthSourceItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mSourceTitle;

        public WeHealthSourceItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mSourceTitle = itemView.findViewById(R.id.vital_source_title);
        }
    }
}
