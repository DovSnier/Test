package com.dvsnier.test.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dvsnier.common.compat.ICompatBaseView;
import com.dvsnier.common.view.BaseCompatFragment;
import com.dvsnier.test.view.R;
import com.dvsnier.test.view.R2;
import com.dvsnier.test.view.affinity.AffinityActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * CFragment
 * Created by dovsnier on 2019-11-11.
 */
public class CFragment extends BaseCompatFragment implements ICompatBaseView {

    @BindView(R2.id.tv_click)
    TextView tvClick;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);
        initView();
        unbinder = ButterKnife.bind(this, view);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R2.id.tv_click)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), AffinityActivity.class);
        startActivity(intent);

    }
}
