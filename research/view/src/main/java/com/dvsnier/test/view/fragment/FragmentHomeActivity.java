package com.dvsnier.test.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.view.R;
import com.dvsnier.test.view.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * FragmentHomeActivity
 * Created by dovsnier on 2019-11-11.
 */
public class FragmentHomeActivity extends BaseActivity {

    @BindView(R2.id.fl_fragment)
    FrameLayout flFragment;
    @BindView(R2.id.tv_1)
    TextView tv1;
    @BindView(R2.id.tv_2)
    TextView tv2;
    @BindView(R2.id.tv_3)
    TextView tv3;
    @BindView(R2.id.tv_4)
    TextView tv4;
    @BindView(R2.id.ll_tab_container)
    LinearLayout llTabContainer;
    @BindView(R2.id.rl_container)
    RelativeLayout rlContainer;
    @BindView(R2.id.tb_btn)
    ToggleButton tbBtn;
    @BindView(R2.id.ll_status_container)
    LinearLayout llStatusContainer;
    protected AFragment aFragment;
    protected BFragment bFragment;
    protected CFragment cFragment;
    protected DFragment dFragment;
    protected int currentPosition; // 逻辑索引不操作，只设置不同状态
    private FragmentManager fragmentManager;
    private boolean isChecked; // true 开启 false 关闭

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        if (null != savedInstanceState) {
            removeByTag("A");
            removeByTag("B");
            removeByTag("C");
            removeByTag("D");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_home);
        ButterKnife.bind(this);
        performScheduledInternal();
        currentPosition = 0; // 默认不保存,默认重建为0索引位置fragment

        tbBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FragmentHomeActivity.this.isChecked = isChecked;
                if (isChecked) { // 关闭 -> 开启
                    initView();
                } else { // 开启 -> 关闭
                    removeByTag("C");
                }

                currentPosition = 0;
                setTabAndFragmentStatus(currentPosition);
            }
        });
    }

    @Override
    public void initView() {
        if (null == aFragment)
            aFragment = new AFragment();
        if (null == bFragment)
            bFragment = new BFragment();
        if (null == cFragment)
            cFragment = new CFragment();
        if (null == dFragment)
            dFragment = new DFragment();

        if (!aFragment.isAdded()) {
            fragmentManager.beginTransaction().add(R.id.fl_fragment, aFragment, "A").hide(aFragment).commit();
        }
        if (!bFragment.isAdded()) {
            fragmentManager.beginTransaction().add(R.id.fl_fragment, bFragment, "B").hide(bFragment).commit();
        }
        if (isChecked()) {
            if (!cFragment.isAdded()) {
                fragmentManager.beginTransaction().add(R.id.fl_fragment, cFragment, "C").hide(cFragment).commit();
            }
        }
        if (!dFragment.isAdded()) {
            fragmentManager.beginTransaction().add(R.id.fl_fragment, dFragment, "D").hide(dFragment).commit();
        }

    }

    @Override
    public void initData() {
        setTabAndFragmentStatus(currentPosition);
    }

    protected void removeByTag(String tag) {
        if (null == fragmentManager) return;
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);
        if (null != fragmentByTag) {
            fragmentManager.beginTransaction().remove(fragmentByTag).commit();
        }
    }

    protected void hideFragment() {
        fragmentManager.beginTransaction().hide(aFragment).commit();
        fragmentManager.beginTransaction().hide(bFragment).commit();
        if (isChecked()) {
            fragmentManager.beginTransaction().hide(cFragment).commit();
        }
        fragmentManager.beginTransaction().hide(dFragment).commit();
    }

    protected void switchFragment(int currentPosition) {
        hideFragment();
        if (0 == currentPosition) { // tv_1
            fragmentManager.beginTransaction().show(aFragment).commit();
        } else if (1 == currentPosition) { // tv_2
            fragmentManager.beginTransaction().show(bFragment).commit();
        } else if (2 == currentPosition) { // tv_3 or tv_4
            if (isChecked()) {
                fragmentManager.beginTransaction().show(cFragment).commit();
            } else {
                fragmentManager.beginTransaction().show(dFragment).commit();
            }
        } else if (3 == currentPosition) { // tv_4
            fragmentManager.beginTransaction().show(dFragment).commit();
        } else {
            fragmentManager.beginTransaction().show(aFragment).commit();
        }
    }

    protected void defaultTabStatus() {
        tv1.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
        tv2.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
        if (isChecked()) {
            tv3.setVisibility(View.VISIBLE);
            tv3.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
        } else {
            tv3.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
            tv3.setVisibility(View.GONE);
        }
        tv4.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
    }

    protected void setTabStatus(View view) {
        if (null == view) return;
        view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
    }

    protected void switchTabStatus(int currentPosition) {
        defaultTabStatus();
        if (0 == currentPosition) { // tv_1
            setTabStatus(tv1);
        } else if (1 == currentPosition) { // tv_2
            setTabStatus(tv2);
        } else if (2 == currentPosition) { // tv_3 or tv_4
            if (isChecked()) {
                setTabStatus(tv3);
            } else {
                setTabStatus(tv4);
            }
        } else if (3 == currentPosition) { // tv_4
            setTabStatus(tv4);
        } else {
            setTabStatus(tv1);
        }
    }

    protected void setTabAndFragmentStatus(int position) {
        switchTabStatus(position);
        switchFragment(position);
    }

    public boolean isChecked() {
        return isChecked;
    }


    @OnClick({R2.id.tv_1, R2.id.tv_2, R2.id.tv_3, R2.id.tv_4})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_1) {
            currentPosition = 0;
        } else if (id == R.id.tv_2) {
            currentPosition = 1;
        } else if (id == R.id.tv_3) {
            currentPosition = 2;
        } else if (id == R.id.tv_4) {
            currentPosition = 3;
        } else {
            // nothing to do
        }

        setTabAndFragmentStatus(currentPosition);
    }
}