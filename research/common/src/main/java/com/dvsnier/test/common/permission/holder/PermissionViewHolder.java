package com.dvsnier.test.common.permission.holder;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dvsnier.base.holder.BaseViewHolder;
import com.dvsnier.test.common.R2;
import com.dvsnier.test.common.permission.bean.PermissionBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * PermissionViewHolder
 * Created by dovsnier on 2020/7/27.
 */
public class PermissionViewHolder extends BaseViewHolder<PermissionBean> {

    @BindView(R2.id.tv_content)
    TextView tvContent;

    public PermissionViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public PermissionViewHolder(@NonNull Context context, int LayoutId, ViewGroup parent) {
        super(context, LayoutId, parent);
//        super(context, R.layout.layout_common_item_one, parent);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindViewHolder(Context context, final int position, final PermissionBean bean) {
        super.onBindViewHolder(context, position, bean);
        if (null != bean) {
            int itemType = bean.getItemType();
            String name = bean.getName();
            String msg = bean.getMsg();
            int flag = bean.getFlag();
            if (itemType == IType.TYPE_ITEM_DEFAULT) {
                tvContent.setText(String.format("%s: %s", position, bean.getPackageName()));
                tvContent.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                tvContent.setMaxLines(3);
                tvContent.setPadding(15, 20, 15, 20);
                if ((flag & ApplicationInfo.FLAG_SYSTEM) != 0) {
//                    itemView.setBackgroundColor(Color.parseColor("#69FF0000"));
                    itemView.setBackgroundColor(Color.parseColor("#FFFF4444"));
//                    itemView.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_red_light));
                } else {
                    itemView.setBackgroundColor(Color.WHITE);
                }
//                tvContent.setSingleLine(false);
//                tvContent.setInputType(EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE);
//                tvContent.setEllipsize(TextUtils.TruncateAt.END);
            } else {
                tvContent.setText(String.format("%s", name));
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != getOnClickListener() && getOnClickListener() instanceof IOnItemClickListener) {
                        //noinspection unchecked
                        ((IOnItemClickListener<PermissionBean>) getOnClickListener()).onItemClick(v, position, bean);
                    }
                }
            });
        }
    }

    public int getValue(float value) {
        return Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getContext().getResources().getDisplayMetrics())).intValue();
    }
}
