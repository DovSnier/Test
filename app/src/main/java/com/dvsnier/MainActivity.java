package com.dvsnier;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.dvsnier.testAIDL.TestAIDLActivity;
import com.dvsnier.testAffinity.AffinityActivity;
import com.dvsnier.testCache.TestCacheActivity;
import com.dvsnier.testCrash.TestCrashHandleActivity;
import com.dvsnier.testImage.TestImageActivity;
import com.dvsnier.testRecycleView.TestRecyclerActivity;
import com.dvsnier.testSQL.TestSQLActivity;
import com.dvsnier.testScroll.TestScrollActivity;
import com.dvsnier.testSurface.TestSurfaceActivity;
import com.dvsnier.testTheme.TestThemeActivity;

import java.lang.ref.WeakReference;
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

    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.testContainer)
    ListView container;
    private List<String> dataset = new ArrayList<>();
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
        initializeData();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, dataset);
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
                    case 9: // TODO test recycler view
                        intent = new Intent(MainActivity.this, TestRecyclerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        break;
                }
            }
        });
        mGestureDetector = new GestureDetector(this, new DrapGestureListener());
        container.postDelayed(new Runnable() {
            @Override
            public void run() {
                final int count = adapter.getCount();
                for (int i = 0; i < count; i++) {
                    View childAt = container.getChildAt(i);
                    if (null != childAt) {
                        childAt.setOnTouchListener(onTouchListener);
                        childAt.setOnDragListener(onDragListener);
                    }
                }
            }
        }, 2 * 1000);
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
        dataset.add("测试 RecyclerView");
    }

    private GestureDetector mGestureDetector;
    private View dragView;
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            dragView = v;
            if (mGestureDetector.onTouchEvent(event))
                return true;
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };
    private View.OnDragListener onDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setAlpha(0.5F);
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setAlpha(1F);
                    break;
                case DragEvent.ACTION_DROP: // the changed to bean information
                    CheckedTextView dragView = (CheckedTextView) event.getLocalState();
                    CheckedTextView shadowView = (CheckedTextView) v;
                    int childCount = container.getChildCount();
                    final String valueOfDrag = dragView.getText().toString();
                    final String valueOfShadow = shadowView.getText().toString();
                    int positionOfDrag = -1;
                    int positionOfShadow = -1;
                    for (int i = 0; i < childCount; i++) {
                        View childAt = container.getChildAt(i);
                        if (childAt == dragView) {
                            positionOfDrag = i;
                        }
                        if (childAt == shadowView) {
                            positionOfShadow = i;
                        }
                    }
                    if (positionOfDrag != positionOfShadow && positionOfDrag >= 0 && positionOfShadow >= 0) {
                        dataset.set(positionOfDrag, valueOfShadow);
                        dataset.set(positionOfShadow, valueOfDrag);
                        adapter.notifyDataSetChanged();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setAlpha(1F);
                default:
                    break;
            }
            return true;
        }
    };

    private class DrapGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            ClipData data = ClipData.newPlainText("", "");
            MyDragShadowBuilder shadowBuilder = new MyDragShadowBuilder(dragView);
            dragView.startDrag(data, shadowBuilder, dragView, 0);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }

    private class MyDragShadowBuilder extends View.DragShadowBuilder {

        private final WeakReference<View> mView;

        public MyDragShadowBuilder(View view) {
            super(view);
            mView = new WeakReference<View>(view);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            canvas.scale(1.0F, 1.0F);
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
            // super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
            final View view = mView.get();
            if (view != null) {
                shadowSize.set((int) (view.getWidth() * 1.0F), (int) (view.getHeight() * 1.0F));
                shadowTouchPoint.set(shadowSize.x / 2, shadowSize.y / 2);
            } else {
                Log.e(TAG, "Asked for drag thumb metrics but no view");
            }
        }
    }
}
