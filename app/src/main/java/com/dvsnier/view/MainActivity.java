package com.dvsnier.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dvsnier.R;
import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.base.task.ITask;
import com.dvsnier.common.compat.ICompatBaseView;
import com.dvsnier.presenter.MainPresenter;
import com.dvsnier.testAIDL.TestAIDLActivity;
import com.dvsnier.testAffinity.AffinityActivity;
import com.dvsnier.testAnimator.TestAnimator;
import com.dvsnier.testCache.TestCacheActivity;
import com.dvsnier.testCoordinatorLayout.TestCoordinatorLayout2Activity;
import com.dvsnier.testCoordinatorLayout.TestCoordinatorLayout3Activity;
import com.dvsnier.testCoordinatorLayout.TestCoordinatorLayoutActivity;
import com.dvsnier.testCrash.TestCrashHandleActivity;
import com.dvsnier.testEventBus.TestEventBusActivity;
import com.dvsnier.testGreenDao.TestGreenDaoActivity;
import com.dvsnier.testImage.TestImageActivity;
import com.dvsnier.testOkhttp.TestOkhttpActivity;
import com.dvsnier.testPermission.TestPermissionActivity;
import com.dvsnier.testRecycleView.TestRecyclerActivity;
import com.dvsnier.testSQL.TestSQLActivity;
import com.dvsnier.testScroll.TestScrollActivity;
import com.dvsnier.testSpeechRecognition.TestSpeechRecognitionActivity;
import com.dvsnier.testSurface.TestSurfaceActivity;
import com.dvsnier.testTheme.TestThemeActivity;
import com.dvsnier.testTouchEvent.TestTouchEventActivity;
import com.dvsnier.testXUtils.TestXUtilsActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * the test main activity
 *
 * @author dvsnier
 * @since JDK 1.8
 */
public class MainActivity extends BaseActivity<MainPresenter> implements ICompatBaseView, ITask, AdapterView.OnItemClickListener {

    @Bind(R.id.testContainer)
    ListView container;
    protected boolean DEBUG;
    private List<String> dataSet = new ArrayList<>();
    @SuppressWarnings("FieldCanBeLocal")
    private ArrayAdapter adapter;

    @SuppressLint("ObsoleteSdkInt")
    protected void hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            hideSystemUI();
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            hideSystemUI();
            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if (visibility == View.VISIBLE) {
                        hideSystemUI();
                    }
                }
            });
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void initView() {
        //noinspection Convert2Diamond
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, dataSet);
        container.setOnItemClickListener(this);
        container.setAdapter(adapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            container.setAlpha(0.6f);
        }
    }

    @Override
    public void initData() {
        DEBUG = getResources().getBoolean(R.bool.debug);
        execute();
    }

    @Override
    public void execute() {
        if (null != getPresenter()) {
            getPresenter().request();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        if (DEBUG)
            Logger.wtf(TAG, "the current position is " + position + " and id is " + id);
        switch (position) {
            case 0: // TODO test exception component
                intent = new Intent(MainActivity.this, TestCrashHandleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 1: // TODO test activity affinity component
                intent = new Intent(MainActivity.this, AffinityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 2: // TODO test activity cache component
                intent = new Intent(MainActivity.this, TestCacheActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 3: // TODO test activity sql component
                intent = new Intent(MainActivity.this, TestSQLActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 4: // TODO test activity theme component
                intent = new Intent(MainActivity.this, TestThemeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 5: // TODO test activity surface view component
                intent = new Intent(MainActivity.this, TestSurfaceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 6: // TODO test activity aidl component
                intent = new Intent(MainActivity.this, TestAIDLActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 7: // TODO test activity scroll component
                intent = new Intent(MainActivity.this, TestScrollActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 8: // TODO test glide component library
                intent = new Intent(MainActivity.this, TestImageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 9: // TODO test recycler view
                intent = new Intent(MainActivity.this, TestRecyclerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 10: // TODO test x utils view
                intent = new Intent(MainActivity.this, TestXUtilsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 11: // TODO test animator view
                intent = new Intent(MainActivity.this, TestAnimator.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 12: // TODO test green dao
                intent = new Intent(MainActivity.this, TestGreenDaoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 13: // TODO test speech recognition
                intent = new Intent(MainActivity.this, TestSpeechRecognitionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 14: // TODO test coordinator layout
                intent = new Intent(MainActivity.this, TestCoordinatorLayoutActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            //noinspection SpellCheckingInspection
            case 15: // TODO test okhttp
                intent = new Intent(MainActivity.this, TestOkhttpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            //noinspection SpellCheckingInspection
            case 16: // TODO test eventbus
                intent = new Intent(MainActivity.this, TestEventBusActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 17: // TODO test permission
                intent = new Intent(MainActivity.this, TestPermissionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 18: // TODO test CoordinatorLayout and AppBarLayout
                intent = new Intent(MainActivity.this, TestCoordinatorLayout2Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 19: // TODO test CoordinatorLayout and AppBarLayout
                intent = new Intent(MainActivity.this, TestCoordinatorLayout3Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case 20: // TODO test Touch Event
                intent = new Intent(MainActivity.this, TestTouchEventActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
        }
    }

    public List<String> getDataSet() {
        return dataSet;
    }
}
