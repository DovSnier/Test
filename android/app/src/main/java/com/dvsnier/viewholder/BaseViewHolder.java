package com.dvsnier.viewholder;

import android.content.Context;
import android.view.View;

import com.dvsnier.base.IBaseOnClickListener;
import com.dvsnier.base.flavor.widget.IAccessiblity;
import com.dvsnier.base.holder.IBaseBindViewHolder;
import com.dvsnier.bean.CategoryBean;
import com.dvsnier.bean.ComponentBean;

/**
 * BaseViewHolder
 * Created by machine code on 2019/6/13.
 */
public abstract class BaseViewHolder<T> implements IBaseBindViewHolder<T>, IAccessiblity {

    protected Context context;
    protected View convertView;
    protected IBaseOnClickListener onClickListener;

    public BaseViewHolder() {
    }

    public BaseViewHolder(Context context) {
        this.context = context;
    }

    public BaseViewHolder(Context context, View convertView) {
        this.context = context;
        this.convertView = convertView;
    }

    public BaseViewHolder(Context context, IBaseOnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return this.getClass().getSimpleName();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public View getConvertView() {
        return convertView;
    }

    public void setConvertView(View convertView) {
        this.convertView = convertView;
    }

    public IBaseOnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(IBaseOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    protected void setItemOnClickListener(final int position, final T bean) {
        getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != getOnClickListener() && getOnClickListener() instanceof OnItemClickListener) {
                    if (null != bean) {
                        if (bean instanceof CategoryBean) {
                            //noinspection unchecked
                            ((OnItemClickListener) getOnClickListener()).onItemCategoryClick(v, position, bean);
                        } else if (bean instanceof ComponentBean) {
                            //noinspection unchecked
                            ((OnItemClickListener) getOnClickListener()).onItemComponentClick(v, position, bean);
                        } else {
                            // nothing to do
                        }
                    }
                }
            }
        });
    }
}
