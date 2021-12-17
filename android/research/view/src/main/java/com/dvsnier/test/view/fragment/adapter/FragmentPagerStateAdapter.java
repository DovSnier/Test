package com.dvsnier.test.view.fragment.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.dvsnier.base.adapter.CompatFragmentStatePagerAdapter;

import java.util.List;

/**
 * FragmentPagerStateAdapter
 * Created by dovsnier on 2019-11-26.
 */
public class FragmentPagerStateAdapter<BaseCompatFragment> extends CompatFragmentStatePagerAdapter {


    public FragmentPagerStateAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentPagerStateAdapter(@NonNull FragmentManager fm, @NonNull Context context, @NonNull List data) {
        super(fm, context, data);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return getItemPositionByObject(object);
    }
}
