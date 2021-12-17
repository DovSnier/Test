package com.dvsnier.test.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dvsnier.base.holder.BaseViewHolder;
import com.dvsnier.test.service.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MessageViewHolder
 * Created by dovsnier on 2020/5/14.
 */
public class MessageViewHolder extends BaseViewHolder<String> {

    @BindView(R2.id.tv_content)
    TextView tvContent;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public MessageViewHolder(@NonNull Context context, int LayoutId, ViewGroup parent) {
        super(context, LayoutId, parent);
//        super(context, R.layout.layout_view_holder_message, parent);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindViewHolder(Context context, int position, String bean) {
        super.onBindViewHolder(context, position, bean);
        tvContent.setText(bean);
    }
}
