package com.dvsnier.test.widget.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dvsnier.base.holder.BaseViewHolder;
import com.dvsnier.common.listener.IOnItemClickListener;
import com.dvsnier.test.widget.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecycleViewHolder
 * Created by machine code on 2019/6/18.
 */
public class ContentViewHolder extends BaseViewHolder<String> {

    @BindView(R2.id.ll_container)
    LinearLayout llContainer;
    @BindView(R2.id.content)
    TextView content;

    public ContentViewHolder(@NonNull View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public ContentViewHolder(@NonNull Context context, int LayoutId, ViewGroup parent) {
        super(context, LayoutId, parent);
//        super(context, R.layout.layout_content_view_item, parent);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindViewHolder(Context context, final int position, final String bean) {
        super.onBindViewHolder(context, position, bean);
        if (position % 2 == 0) {
            llContainer.setBackgroundColor(Color.parseColor("#4cb4e7"));
        } else {
            llContainer.setBackgroundColor(Color.parseColor("#9dd3fa"));
        }
        content.setText(bean);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != getOnClickListener() && getOnClickListener() instanceof IOnItemClickListener) {
                    //noinspection unchecked
                    ((IOnItemClickListener) getOnClickListener()).onItemClick(v, position, bean);
                }
            }
        });
    }
}
