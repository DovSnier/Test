package com.dvsnier.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dvsnier.base.adapter.BaseRecyclerViewAdapter;
import com.dvsnier.test.service.R;
import com.dvsnier.test.viewholder.MessageViewHolder;

import java.util.List;

/**
 * MessageAdapter
 * Created by dovsnier on 2020/5/14.
 */
public class MessageAdapter extends BaseRecyclerViewAdapter<String, RecyclerView.ViewHolder> {

    public MessageAdapter() {
    }

    public MessageAdapter(@NonNull Context context) {
        super(context);
    }

    public MessageAdapter(@NonNull Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(getContext(), R.layout.layout_view_holder_message, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (null != holder) {
            String item = getItem(position);
            if (holder instanceof MessageViewHolder) {
                ((MessageViewHolder) holder).setOnClickListener(getOnClickListener());
                ((MessageViewHolder) holder).onBindViewHolder(getContext(), position, item);
            } else {
                // nothing to do
            }
        }
    }
}
