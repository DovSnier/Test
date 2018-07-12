package com.dvsnier.testRecycleView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dvsnier.R;
import com.dvsnier.base.IBaseOnClickListener;
import com.dvsnier.base.adapter.BaseRecyclerViewAdapter;
import com.dvsnier.base.holder.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/10/28.
 */

public class RecyclerViewAdapter extends BaseRecyclerViewAdapter<String, RecyclerViewAdapter.ViewHolder> {

    public RecyclerViewAdapter(@NonNull Context context) {
        super(context);
    }

    public RecyclerViewAdapter(@NonNull Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.layout_recycler_view_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (null != holder) {
            ((ViewHolder) holder).setOnClickListener(getOnClickListener());
            ((ViewHolder) holder).onBindViewHolder(context, position, getItem(position));
        }
    }

    public static class ViewHolder extends BaseViewHolder<String> {

        TextView content;
        protected OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content);
        }

        @Override
        public void onBindViewHolder(Context context, final int position, final String bean) {
            super.onBindViewHolder(context, position, bean);
            content.setText(bean);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onItemClickListener) {
                        onItemClickListener.onItemClick(v, position, bean);
                    }
                }
            });
        }

        @Override
        public void setOnClickListener(IBaseOnClickListener onClickListener) {
            if (null != onClickListener && onClickListener instanceof OnItemClickListener)
                this.onItemClickListener = (OnItemClickListener) onClickListener;
        }
    }
}
