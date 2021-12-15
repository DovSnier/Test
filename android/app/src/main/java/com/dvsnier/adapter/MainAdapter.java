package com.dvsnier.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.dvsnier.R;
import com.dvsnier.base.IBaseOnClickListener;
import com.dvsnier.bean.CategoryBean;
import com.dvsnier.bean.ComponentBean;
import com.dvsnier.viewholder.CategoryViewHolder;
import com.dvsnier.viewholder.ComponentViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * MainAdapter
 * Created by machine code on 2019/6/13.
 */
public class MainAdapter extends BaseExpandableListAdapter {

    protected Context context;
    protected List<CategoryBean> dataSet = new ArrayList<>();
    protected IBaseOnClickListener onClickListener;

    public MainAdapter() {
    }

    public MainAdapter(Context context) {
        this.context = context;
    }

    public MainAdapter(Context context, List<CategoryBean> dataSet) {
        this(context);
        if (null != dataSet && !dataSet.isEmpty()) {
            clear();
            this.dataSet.addAll(dataSet);
        }
    }

    public MainAdapter(Context context, List<CategoryBean> dataSet, IBaseOnClickListener onClickListener) {
        this(context, dataSet);
        this.onClickListener = onClickListener;
    }

    protected void clear() {
        if (!dataSet.isEmpty()) {
            dataSet.clear();
        }
    }

    @Override
    public int getGroupCount() {
        return null != getDataSet() ? getDataSet().size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (!getDataSet().isEmpty()) {
            CategoryBean categoryBean = getDataSet().get(groupPosition);
            if (null != categoryBean && null != categoryBean.getChild()) {
                return categoryBean.getChild().size();
            }
        }
        return 0;
    }

    @Override
    public CategoryBean getGroup(int groupPosition) {
        if (!getDataSet().isEmpty()) {
            return getDataSet().get(groupPosition);
        }
        return null;
    }

    @Override
    public ComponentBean getChild(int groupPosition, int childPosition) {
        if (!getDataSet().isEmpty()) {
            CategoryBean categoryBean = getDataSet().get(groupPosition);
            if (null != categoryBean && null != categoryBean.getChild()) {
                return categoryBean.getChild().get(childPosition);
            }
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        if (!getDataSet().isEmpty() && null != getDataSet().get(groupPosition)) {
            return getDataSet().get(groupPosition).getId();
        }
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        if (!getDataSet().isEmpty()) {
            CategoryBean categoryBean = getDataSet().get(groupPosition);
            if (null != categoryBean && null != categoryBean.getChild() && null != categoryBean.getChild().get(childPosition)) {
                return categoryBean.getChild().get(childPosition).getId();
            }
        }
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CategoryViewHolder categoryViewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_expandable_list_item_2, null, false);
            categoryViewHolder = new CategoryViewHolder(getContext(), convertView);
            convertView.setTag(categoryViewHolder);
        } else {
            categoryViewHolder = (CategoryViewHolder) convertView.getTag();
        }
        categoryViewHolder.setOnClickListener(getOnClickListener());
        categoryViewHolder.onBindViewHolder(getContext(), groupPosition, getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ComponentViewHolder componentViewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_view_item, null, false);
            componentViewHolder = new ComponentViewHolder(getContext(), convertView);
            convertView.setTag(componentViewHolder);
        } else {
            componentViewHolder = (ComponentViewHolder) convertView.getTag();
        }
        componentViewHolder.setOnClickListener(getOnClickListener());
        componentViewHolder.onBindViewHolder(getContext(), childPosition, getChild(groupPosition, childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getGroupType(int groupPosition) {
        return super.getGroupType(groupPosition);
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<CategoryBean> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<CategoryBean> dataSet) {
        if (null != dataSet && !dataSet.isEmpty()) {
            clear();
            this.dataSet.addAll(dataSet);
        }
    }

    public IBaseOnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(IBaseOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
