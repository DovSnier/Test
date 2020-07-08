package com.dvsnier.test.widget.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dvsnier.base.holder.BaseViewHolder;
import com.dvsnier.test.widget.R2;
import com.dvsnier.test.widget.bean.BaseRecycleBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * LayoutMultiViewHolder
 * Created by dovsnier on 2020/7/8.
 */
public class LayoutMultiViewHolder extends BaseViewHolder<BaseRecycleBean> {

    @BindView(R2.id.rl_container)
    RelativeLayout rlContainer;
    @BindView(R2.id.tv_home2_news_title)
    TextView tvHome2NewsTitle;
    @BindView(R2.id.ll_bottom_container)
    LinearLayout llBottomContainer;
    @BindView(R2.id.tv_content)
    TextView tvContent;
    @BindView(R2.id.iv_home2_news_tupian)
    ImageView ivHome2NewsTupian;

    public LayoutMultiViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public LayoutMultiViewHolder(@NonNull Context context, int LayoutId, ViewGroup parent) {
        super(context, LayoutId, parent);
//        super(context, R.layout.layout_multi_view_item, parent);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindViewHolder(Context context, int position, BaseRecycleBean bean) {
        super.onBindViewHolder(context, position, bean);
        tvHome2NewsTitle.setText(String.format("%s-[%s],%s", position, bean.getContent(), System.currentTimeMillis()));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llBottomContainer.getLayoutParams();
        double value = Math.random() * 100;
        if (value > 60) {
            layoutParams.topMargin = getValue(45);
            tvContent.setBackgroundColor(Color.RED);
        } else if (value > 30) {
            layoutParams.topMargin = getValue(15);
            tvContent.setBackgroundColor(Color.BLUE);
        } else {
            layoutParams.topMargin = getValue(7);
            tvContent.setBackgroundColor(Color.WHITE);
        }
        llBottomContainer.setLayoutParams(layoutParams);
    }

    public int getValue(int value) {
        if (null != getContext()) {
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getContext().getResources().getDisplayMetrics()));
        } else {
            return value;
        }
    }
}
