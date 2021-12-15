package com.dvsnier.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dvsnier.common.view.BaseCompatFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * CompatFragmentStatePagerAdapter
 * Created by dovsnier on 2018/7/31.
 */
public class CompatFragmentStatePagerAdapter<T extends BaseCompatFragment> extends FragmentStatePagerAdapter {

    protected Context context;
    protected List<T> data = new ArrayList<>();

    public CompatFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
        internalApiClear();
    }

    public CompatFragmentStatePagerAdapter(FragmentManager fm, Context context) {
        this(fm);
        this.context = context;
    }

    public CompatFragmentStatePagerAdapter(@NonNull FragmentManager fm, @NonNull List<T> data) {
        this(fm);
        //noinspection ConstantConditions
        if (null != data && !data.isEmpty()) {
            this.data.addAll(data);
        }
    }

    public CompatFragmentStatePagerAdapter(@NonNull FragmentManager fm, @NonNull Context context, @NonNull List<T> data) {
        this(fm, context);
        //noinspection ConstantConditions
        if (null != data && !data.isEmpty()) {
            this.data.addAll(data);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size() > 0 ? data.size() : 0;
    }

    public int getItemPositionByObject(@NonNull Object object) {
        if (null != data && !data.isEmpty()) {
            //noinspection SuspiciousMethodCalls
            if (data.contains(object)) {
                int count = getCount();
                for (int i = 0; i < count; i++) {
                    T item = data.get(i);
                    //noinspection ConditionCoveredByFurtherCondition
                    if (null != item && item == object) {
                        return i;
                    }
                }
            }
        }
        return POSITION_NONE;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(@NonNull List<T> data) {
        //noinspection ConstantConditions
        if (null != data && !data.isEmpty()) {
            this.data.clear();
            this.data.addAll(data);
        }
    }

    protected final void internalApiClear() {
        if (null != data && !data.isEmpty()) {
            this.data.clear();
        }
    }
}
