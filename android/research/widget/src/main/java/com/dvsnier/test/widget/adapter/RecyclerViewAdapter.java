package com.dvsnier.test.widget.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dvsnier.base.adapter.BaseRecyclerViewAdapter;
import com.dvsnier.base.holder.BaseViewHolder;
import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.bean.BaseRecycleBean;
import com.dvsnier.test.widget.recycleview.IRecycleType;
import com.dvsnier.test.widget.viewholder.LayoutMultiViewHolder;
import com.dvsnier.test.widget.viewholder.RecycleViewHolder;

import java.util.List;

/**
 * RecyclerViewAdapter
 * Created by Administrator on 2016/10/28.
 */
public class RecyclerViewAdapter extends BaseRecyclerViewAdapter<BaseRecycleBean, BaseViewHolder<BaseRecycleBean>>
        implements IRecycleType {

    public RecyclerViewAdapter(@NonNull Context context) {
        super(context);
    }

    public RecyclerViewAdapter(@NonNull Context context, List<BaseRecycleBean> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_DEFAULT) {
            return new RecycleViewHolder(context, R.layout.layout_recycler_view_item, parent);
        } else if (viewType == TYPE_LEFT_CONTENT_RIGHT_PICTURE) {
            return new LayoutMultiViewHolder(context, R.layout.layout_multi_view_item, parent);
        } else {
            return new RecycleViewHolder(context, R.layout.layout_recycler_view_item, parent);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (null != holder) {
            BaseRecycleBean item = getItem(position);
            if (holder instanceof RecycleViewHolder) {
                ((RecycleViewHolder) holder).setOnClickListener(getOnClickListener());
                ((RecycleViewHolder) holder).onBindViewHolder(getContext(), position, item);
            } else if (holder instanceof LayoutMultiViewHolder) {
                ((LayoutMultiViewHolder) holder).setOnClickListener(getOnClickListener());
                ((LayoutMultiViewHolder) holder).onBindViewHolder(getContext(), position, item);
            } else {
                // nothing to do
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        BaseRecycleBean item = getItem(position);
        if (null != item) {
            return item.getItemType();
        }
        return super.getItemViewType(position);
    }
}
