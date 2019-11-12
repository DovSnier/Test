package com.dvsnier.test.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    protected AFragment aFragment;
    protected BFragment bFragment;
    protected CFragment cFragment;
    protected DFragment dFragment;
    protected int currentPosition;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        if (null != savedInstanceState) {
            Fragment a = fragmentManager.findFragmentByTag("A");
            if (null != a) {
                fragmentManager.beginTransaction().remove(a).commit();
            }
            Fragment b = fragmentManager.findFragmentByTag("B");
            if (null != b) {
                fragmentManager.beginTransaction().remove(b).commit();
            }
            Fragment c = fragmentManager.findFragmentByTag("C");
            if (null != c) {
                fragmentManager.beginTransaction().remove(c).commit();
            }
            Fragment d = fragmentManager.findFragmentByTag("D");
            if (null != d) {
                fragmentManager.beginTransaction().remove(d).commit();
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_home);
        ButterKnife.bind(this);
        performScheduledInternal();
        currentPosition = 0; // 默认不保存,默认重建为0索引位置fragment
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

        fragmentManager.beginTransaction().add(R.id.fl_fragment, aFragment, "A").hide(aFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fl_fragment, bFragment, "B").hide(bFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fl_fragment, cFragment, "C").hide(cFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fl_fragment, dFragment, "D").hide(dFragment).commit();
    }

    @Override
    public void initData() {
        setTabAndFragmentStatus(currentPosition);
    }

    protected void hideFragment() {
        fragmentManager.beginTransaction().hide(aFragment).commit();
        fragmentManager.beginTransaction().hide(bFragment).commit();
        fragmentManager.beginTransaction().hide(cFragment).commit();
        fragmentManager.beginTransaction().hide(dFragment).commit();
    }

    protected void switchFragment(int currentPosition) {
        hideFragment();
        if (0 == currentPosition) {
            fragmentManager.beginTransaction().show(aFragment).commit();
        } else if (1 == currentPosition) {
            fragmentManager.beginTransaction().show(bFragment).commit();
        } else if (2 == currentPosition) {
            fragmentManager.beginTransaction().show(cFragment).commit();
        } else if (3 == currentPosition) {
            fragmentManager.beginTransaction().show(dFragment).commit();
        } else {
            fragmentManager.beginTransaction().show(aFragment).commit();
        }
    }

    protected void defaultTabStatus() {
        tv1.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
        tv2.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
        tv3.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
        tv4.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
    }

    protected void setTabStatus(View view) {
        if (null == view) return;
        view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
    }

    protected void switchTabStatus(int currentPosition) {
        defaultTabStatus();
        if (0 == currentPosition) {
            setTabStatus(tv1);
        } else if (1 == currentPosition) {
            setTabStatus(tv2);
        } else if (2 == currentPosition) {
            setTabStatus(tv3);
        } else if (3 == currentPosition) {
            setTabStatus(tv4);
        } else {
            setTabStatus(tv1);
        }
    }

    protected void setTabAndFragmentStatus(int position) {
        switchTabStatus(position);
        switchFragment(position);
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