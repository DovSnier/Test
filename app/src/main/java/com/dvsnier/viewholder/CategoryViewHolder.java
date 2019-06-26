package com.dvsnier.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dvsnier.base.IBaseOnClickListener;
import com.dvsnier.bean.CategoryBean;

/**
 * CategoryViewHolder
 * Created by machine code on 2019/6/13.
 */
public class CategoryViewHolder extends BaseViewHolder<CategoryBean> {

    protected TextView text1;
    protected TextView text2;


    public CategoryViewHolder() {
    }

    public CategoryViewHolder(Context context) {
        super(context);
    }

    public CategoryViewHolder(Context context, View convertView) {
        super(context, convertView);
        text1 = (TextView) convertView.findViewById(android.R.id.text1);
        text2 = (TextView) convertView.findViewById(android.R.id.text2);
    }

    public CategoryViewHolder(Context context, IBaseOnClickListener onClickListener) {
        super(context, onClickListener);
    }

    @Override
    public void onBindViewHolder(Context context, int position, CategoryBean bean) {
        text1.setText(String.format("%s", bean.getName()));
        text2.setText(String.format("%s", bean.getDescribe()));
//        setItemOnClickListener(position, bean);
    }
}
