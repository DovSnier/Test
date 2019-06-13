package com.dvsnier.viewholder;

import android.view.View;

import com.dvsnier.base.IBaseOnClickListener;

/**
 * OnItemClickListener
 * Created by machine code on 2019/6/13.
 */
public interface OnItemClickListener<K, V> extends IBaseOnClickListener {

    void onItemCategoryClick(View view, int position, K bean);

    void onItemComponentClick(View view, int position, V bean);
}
