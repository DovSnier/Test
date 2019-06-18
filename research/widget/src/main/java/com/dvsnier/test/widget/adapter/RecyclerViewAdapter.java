package com.dvsnier.test.widget.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dvsnier.base.adapter.BaseRecyclerViewAdapter;
import com.dvsnier.base.holder.BaseViewHolder;
import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.viewholder.RecycleViewHolder;

import java.util.List;

/**
 * RecyclerViewAdapter
 * Created by Administrator on 2016/10/28.
 */
public class RecyclerViewAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder<String>> {

    public RecyclerViewAdapter(@NonNull Context context) {
        super(context);
    }

    public RecyclerViewAdapter(@NonNull Context context, List<String> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecycleViewHolder(View.inflate(context, R.layout.layout_recycler_view_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (null != holder) {
            if (holder instanceof RecycleViewHolder) {
                ((RecycleViewHolder) holder).setOnClickListener(getOnClickListener());
                ((RecycleViewHolder) holder).onBindViewHolder(getContext(), position, getItem(position));
            }
        }
    }
}
