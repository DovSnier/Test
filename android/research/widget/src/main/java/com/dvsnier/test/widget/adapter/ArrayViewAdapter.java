package com.dvsnier.test.widget.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dvsnier.base.adapter.BaseRecyclerViewAdapter;
import com.dvsnier.base.holder.BaseViewHolder;
import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.viewholder.ContentViewHolder;

import java.util.List;

/**
 * ArrayViewAdapter
 * Created by Administrator on 2016/10/28.
 */
public class ArrayViewAdapter extends BaseRecyclerViewAdapter<String, BaseViewHolder<String>> {

    public ArrayViewAdapter(@NonNull Context context) {
        super(context);
    }

    public ArrayViewAdapter(@NonNull Context context, List<String> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContentViewHolder(context, R.layout.layout_content_view_item, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (null != holder) {
            if (holder instanceof ContentViewHolder) {
                ((ContentViewHolder) holder).setOnClickListener(getOnClickListener());
                ((ContentViewHolder) holder).onBindViewHolder(getContext(), position, getItem(position));
            }
        }
    }
}
