package com.dvsnier.testRecycleView;

import android.view.View;

import com.dvsnier.base.IBaseOnClickListener;

/**
 * Created by dovsnier on 2018/7/12.
 */
public interface OnItemClickListener extends IBaseOnClickListener {

    void onItemClick(View view, int position, String value);
}
