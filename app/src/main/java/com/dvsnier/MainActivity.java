package com.dvsnier;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dvsnier.testAIDL.TestAIDLActivity;
import com.dvsnier.testAffinity.AffinityActivity;
import com.dvsnier.testAnimator.TestAnimator;
import com.dvsnier.testCache.TestCacheActivity;
import com.dvsnier.testCoordinatorLayout.TestCoordinatorLayoutActivity;
import com.dvsnier.testCrash.TestCrashHandleActivity;
import com.dvsnier.testGreenDao.TestGreenDaoActivity;
import com.dvsnier.testImage.TestImageActivity;
import com.dvsnier.testOkhttp.TestOkhttpActivity;
import com.dvsnier.testRecycleView.TestRecyclerActivity;
import com.dvsnier.testSQL.TestSQLActivity;
import com.dvsnier.testScroll.TestScrollActivity;
import com.dvsnier.testSpeechRecognition.TestSpeechRecognitionActivity;
import com.dvsnier.testSurface.TestSurfaceActivity;
import com.dvsnier.testTheme.TestThemeActivity;
import com.dvsnier.testXUtils.TestXUtilsActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * the test main activity
 *
 * @author dvsnier
 * @since JDK 1.8
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.testContainer)
    ListView container;
    protected boolean DEBUG;
    private List<String> dataSet = new ArrayList<>();
    private ArrayAdapter adapter;

    protected void hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            hideSystemUI();
    }

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
        DEBUG = getResources().getBoolean(R.bool.debug);
        initializeData();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, dataSet);
        container.setAdapter(adapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            container.setAlpha(0.6f);
        }
        container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                if (DEBUG)
                    Log.d(TAG, "the current position is " + position + " and id is " + id);
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
                    case 15: // TODO test okhttp
                        intent = new Intent(MainActivity.this, TestOkhttpActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initializeData() {
        dataSet.clear();
        dataSet.add("测试 Exception");
        dataSet.add("测试 Affinity");
        dataSet.add("测试 Cache");
        dataSet.add("测试 SQL");
        dataSet.add("测试 Theme");
        dataSet.add("测试 SurfaceView");
        dataSet.add("测试 AIDL");
        dataSet.add("测试 Scroll");
        dataSet.add("测试 Glide");
        dataSet.add("测试 RecyclerView");
        dataSet.add("测试 XUtils3");
        dataSet.add("测试 Animator");
        dataSet.add("测试 GreenDao");
        dataSet.add("测试 语音识别");
        dataSet.add("测试 CoordinatorLayout");
        dataSet.add("测试 Okhttp");
    }


}
