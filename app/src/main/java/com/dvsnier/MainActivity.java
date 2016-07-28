package com.dvsnier;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dvsnier.testAIDL.TestAIDLActivity;
import com.dvsnier.testAffinity.AffinityActivity;
import com.dvsnier.testCache.TestCacheActivity;
import com.dvsnier.testCrash.TestCrashHandleActivity;
import com.dvsnier.testImage.TestImageActivity;
import com.dvsnier.testSQL.TestSQLActivity;
import com.dvsnier.testScroll.TestScrollActivity;
import com.dvsnier.testSurface.TestSurfaceActivity;
import com.dvsnier.testTheme.TestThemeActivity;

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
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.testContainer)
    ListView container;
    private List<String> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeData();
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, dataset);
        container.setAdapter(adapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            container.setAlpha(0.6f);
        }
        container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
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
                }
            }
        });
    }

    private void initializeData() {
        dataset.clear();
        dataset.add("测试 Exception");
        dataset.add("测试 Affinity");
        dataset.add("测试 Cache");
        dataset.add("测试 SQL");
        dataset.add("测试 Theme");
        dataset.add("测试 SurfaceView");
        dataset.add("测试 AIDL");
        dataset.add("测试 Scroll");
        dataset.add("测试 Glide");
    }
}
