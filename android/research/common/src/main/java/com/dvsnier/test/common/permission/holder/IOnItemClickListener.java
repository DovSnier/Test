package com.dvsnier.test.common.permission.holder;

import android.view.View;

import com.dvsnier.base.IBaseOnClickListener;

/**
 * IOnItemClickListener
 * Created by dovsnier on 2020/7/27.
 */
public interface IOnItemClickListener<T> extends IBaseOnClickListener {

    void onItemClick(View view, int position, T bean);
}
