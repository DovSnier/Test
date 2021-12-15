package com.dvsnier.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dvsnier.R;
import com.dvsnier.base.IBaseOnClickListener;
import com.dvsnier.bean.ComponentBean;

/**
 * ComponentViewHolder
 * Created by machine code on 2019/6/13.
 */
public class ComponentViewHolder extends BaseViewHolder<ComponentBean> {

    protected TextView content;

    public ComponentViewHolder() {
    }

    public ComponentViewHolder(Context context) {
        super(context);
    }

    public ComponentViewHolder(Context context, View convertView) {
        super(context, convertView);
        content = (TextView) convertView.findViewById(R.id.text1);
//        ViewGroup.LayoutParams layoutParams = content.getLayoutParams();
//        if (null != layoutParams) {
//            layoutParams.height = Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getContext().getResources().getDisplayMetrics())).intValue();
//            content.setLayoutParams(layoutParams);
//        }
    }

    public ComponentViewHolder(Context context, IBaseOnClickListener onClickListener) {
        super(context, onClickListener);
    }

    @Override
    public void onBindViewHolder(Context context, final int position, final ComponentBean bean) {
        content.setText(String.format("%s", bean.getName()));
//        setItemOnClickListener(position, bean);
    }
}
