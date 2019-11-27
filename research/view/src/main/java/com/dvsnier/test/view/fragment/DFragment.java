package com.dvsnier.test.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvsnier.common.compat.ICompatBaseView;
import com.dvsnier.common.view.BaseCompatFragment;
import com.dvsnier.test.view.R;

/**
 * DFragment
 * Created by dovsnier on 2019-11-11.
 */
public class DFragment extends BaseCompatFragment implements ICompatBaseView {

    public static BaseCompatFragment newInstance() {
        BaseCompatFragment fragment = new DFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onLog("onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onLog("onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onLog("onCreateView()");
        View view = inflater.inflate(R.layout.fragment_d, container, false);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onLog("onViewCreated()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onLog("onActivityCreated()");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        onLog("onViewStateRestored()");
    }

    @Override
    public void onStart() {
        super.onStart();
        onLog("onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        onLog("onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        onLog("onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        onLog("onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onLog("onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onLog("onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onLog("onDetach()");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onLog("onActivityResult()");
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        onLog("onAttachFragment()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        onLog("onSaveInstanceState()");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        onLog(String.format("setUserVisibleHint(%s)", isVisibleToUser));
    }

    @Override
    public boolean getUserVisibleHint() {
        boolean userVisibleHint = super.getUserVisibleHint();
        onLog(String.format("getUserVisibleHint(%s)", userVisibleHint));
        return userVisibleHint;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        onLog(String.format("onHiddenChanged(%s)", hidden));
    }

    public void onLog(String message) {
        Log.i(TAG, String.format("%s", message));
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
