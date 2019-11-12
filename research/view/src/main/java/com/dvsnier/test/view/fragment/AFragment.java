package com.dvsnier.test.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvsnier.common.compat.ICompatBaseView;
import com.dvsnier.common.view.BaseCompatFragment;
import com.dvsnier.test.view.R;

/**
 * AFragment
 * Created by dovsnier on 2019-11-11.
 */
public class AFragment extends BaseCompatFragment implements ICompatBaseView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        initView();
        return view;
    }

    @Override
    public void initView() {
        // TODO: 2019-11-11
    }

    @Override
    public void initData() {
        // TODO: 2019-11-11
    }
}
