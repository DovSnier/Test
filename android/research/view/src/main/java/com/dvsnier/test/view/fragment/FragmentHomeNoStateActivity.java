package com.dvsnier.test.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.common.view.BaseCompatFragment;
import com.dvsnier.test.view.R;
import com.dvsnier.test.view.R2;
import com.dvsnier.test.view.fragment.adapter.FragmentPagerNoStateAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * FragmentHomeNoStateActivity
 * Created by dovsnier on 2019-11-26.
 */
public class FragmentHomeNoStateActivity extends BaseActivity {

    @BindView(R2.id.view_pager)
    ViewPager viewPager;
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
    protected BaseCompatFragment aFragment;
    protected BaseCompatFragment bFragment;
    protected BaseCompatFragment cFragment;
    protected BaseCompatFragment dFragment;
    protected int currentPosition; // 逻辑索引不操作，只设置不同状态
    private FragmentManager fragmentManager;
    private boolean isChecked; // true 开启 false 关闭
    private List<BaseCompatFragment> dataSet = new ArrayList<>(4);
    private FragmentPagerNoStateAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onLog("onCreate()");
        fragmentManager = getSupportFragmentManager();
//        if (null != savedInstanceState) {
//            removeByTag("A");
//            removeByTag("B");
//            removeByTag("C");
//            removeByTag("D");
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_home_no_state);
        ButterKnife.bind(this);
        currentPosition = 0; // 默认不保存,默认重建为0索引位置fragment
        performScheduledInternal();

//        tbBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                FragmentHomeNoStateActivity.this.isChecked = isChecked;
//                if (isChecked) { // 关闭 -> 开启
//                    initView();
//                } else { // 开启 -> 关闭
//                    removeByTag("C");
//                }
//
//                currentPosition = 0;
//                setTabAndFragmentStatus(currentPosition);
//            }
//        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onLog("onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        onLog("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        onLog("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        onLog("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        onLog("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onLog("onDestroy()");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        onLog("onAttachFragment()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onLog("onNewIntent()");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        onLog("onResumeFragments()");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        onLog("onAttachedToWindow()");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onLog("onDetachedFromWindow()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onLog("onRestoreInstanceState()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onLog("onSaveInstanceState()");
    }

    @Override
    public void initView() {
        dataSet.clear();
        if (null == aFragment)
            aFragment = AFragment.newInstance();
        if (null == bFragment)
            bFragment = BFragment.newInstance();
        if (null == cFragment)
            cFragment = CFragment.newInstance();
        if (null == dFragment)
            dFragment = DFragment.newInstance();
        if (!dataSet.contains(aFragment))
            dataSet.add(aFragment);
        if (!dataSet.contains(bFragment))
            dataSet.add(bFragment);
        if (!dataSet.contains(cFragment))
            dataSet.add(cFragment);
        if (!dataSet.contains(dFragment))
            dataSet.add(dFragment);

        adapter = new FragmentPagerNoStateAdapter(fragmentManager, this, dataSet);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                onLog(String.format("onPageScrolled(position: %s,positionOffset: %s,positionOffsetPixels: %s)", position, positionOffset, positionOffsetPixels));
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                onLog(String.format("onPageSelected(position: %s)", position));
                switchTabStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                onLog(String.format("onPageScrollStateChanged(state: %s)", state));
            }
        });
    }

    @Override
    public void initData() {
        setTabAndFragmentStatus(currentPosition);
    }

//    protected void removeByTag(String tag) {
//        if (null == fragmentManager) return;
//        Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);
//        if (null != fragmentByTag) {
//            fragmentManager.beginTransaction().remove(fragmentByTag).commit();
//        }
//    }

//    protected void hideFragment() {
//        fragmentManager.beginTransaction().hide(aFragment).commit();
//        fragmentManager.beginTransaction().hide(bFragment).commit();
//        if (isChecked()) {
//            fragmentManager.beginTransaction().hide(cFragment).commit();
//        }
//        fragmentManager.beginTransaction().hide(dFragment).commit();
//    }

//    protected void switchFragment(int currentPosition) {
//        hideFragment();
//        if (0 == currentPosition) { // tv_1
//            fragmentManager.beginTransaction().show(aFragment).commit();
//        } else if (1 == currentPosition) { // tv_2
//            fragmentManager.beginTransaction().show(bFragment).commit();
//        } else if (2 == currentPosition) { // tv_3 or tv_4
//            if (isChecked()) {
//                if (!cFragment.isAdded()) {
//                    fragmentManager.beginTransaction().add(R.id.fl_fragment, cFragment, "C").hide(cFragment).commit();
//                }
//                fragmentManager.beginTransaction().show(cFragment).commit();
//            } else {
//                fragmentManager.beginTransaction().show(dFragment).commit();
//            }
//        } else if (3 == currentPosition) { // tv_4
//            fragmentManager.beginTransaction().show(dFragment).commit();
//        } else {
//            fragmentManager.beginTransaction().show(aFragment).commit();
//        }
//    }

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
//        switchFragment(position);
        setCurrentItem(position);
    }

    public boolean isChecked() {
        return isChecked = true;
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

    protected void setCurrentItem(int position) {
        if (null != viewPager && null != adapter) {
            if (position >= 0 && position < adapter.getCount()) {
                viewPager.setCurrentItem(position);
            }
        }
    }
}