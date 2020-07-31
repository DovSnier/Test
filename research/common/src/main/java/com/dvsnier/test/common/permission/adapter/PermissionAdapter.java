package com.dvsnier.test.common.permission.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dvsnier.base.adapter.BaseRecyclerViewAdapter;
import com.dvsnier.test.common.R;
import com.dvsnier.test.common.permission.bean.PermissionBean;
import com.dvsnier.test.common.permission.holder.IType;
import com.dvsnier.test.common.permission.holder.PermissionViewHolder;

import java.util.List;

/**
 * PermissionAdapter
 * Created by dovsnier on 2020/7/27.
 */
public class PermissionAdapter extends BaseRecyclerViewAdapter<PermissionBean, RecyclerView.ViewHolder>
        implements IType {

    public PermissionAdapter() {
    }

    public PermissionAdapter(@NonNull Context context) {
        super(context);
    }

    public PermissionAdapter(@NonNull Context context, List<PermissionBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_DEFAULT) {
            return new PermissionViewHolder(getContext(), R.layout.layout_common_item_one, parent);
        } else {
            // nothing to do
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (null != holder) {
            PermissionBean item = getItem(position);
            if (holder instanceof PermissionViewHolder) {
                ((PermissionViewHolder) holder).setOnClickListener(getOnClickListener());
                ((PermissionViewHolder) holder).onBindViewHolder(getContext(), position, item);
            } else {
                // nothing to do
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        PermissionBean item = getItem(position);
        if (null != item) {
            return item.getItemType();
        }
        return super.getItemViewType(position);
    }
}
