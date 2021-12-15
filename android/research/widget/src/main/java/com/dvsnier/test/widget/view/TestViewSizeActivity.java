package com.dvsnier.test.widget.view;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.R2;
import com.dvsnier.test.widget.presenter.TestViewSizePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TestViewSizeActivity
 * Created by machine code on 2020/2/5.
 */
public class TestViewSizeActivity extends BaseActivity<TestViewSizePresenter> {

    @BindView(R2.id.container)
    LinearLayout container;
    @BindView(R2.id.content)
    TextView content;
    @BindView(R2.id.tv_one)
    TextView tvOne;
    @BindView(R2.id.tv_two)
    TextView tvTwo;
    @BindView(R2.id.tv_three)
    TextView tvThree;
    @BindView(R2.id.tv_four)
    TextView tvFour;
    protected VelocityTracker velocityTracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_size);
        ButterKnife.bind(this);
        performScheduledInternal();
    }


    @Override
    public void initView() {
        super.initView();
        addOnTouch();
        tvOne.setText("职能赋能里正式");
        tvTwo.setText("管理员");
        tvThree.setText("我来自于earth,我是地球人，我喜欢china.中国崛起，天佑中华");
        tvFour.setText("我来自于earth,我是地球人，我喜欢china.中国崛起，天佑中华");
    }

    @Override
    public void initData() {
        super.initData();
        float measureTextOne = tvOne.getPaint().measureText(tvOne.getText().toString());
        float measureTextTwo = tvTwo.getPaint().measureText(tvTwo.getText().toString());
        float measureTextThree = tvThree.getPaint().measureText(tvThree.getText().toString());
        float measureTextFour = tvFour.getPaint().measureText(tvFour.getText().toString());
        Rect measuredSizeOne = getMeasuredSize(tvOne);
        Rect measuredSizeTwo = getMeasuredSize(tvTwo);
        Rect measuredSizeThree = getMeasuredSize(tvThree);
        Rect measuredSizeFour = getMeasuredSize(tvFour);
        int widthOne = measuredSizeOne.width();
        int widthTwo = measuredSizeTwo.width();
        int widthThree = measuredSizeThree.width();
        int widthFour = measuredSizeFour.width();
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                int lineCountThree = tvThree.getLayout().getLineCount();
                for (int i = 0; i < lineCountThree; i++) {
                    int lineAscentThree = tvThree.getLayout().getLineAscent(i);
                    int lineBaselineThree = tvThree.getLayout().getLineBaseline(i);
                    int lineBottomThree = tvThree.getLayout().getLineBottom(i);
                    Rect boundsThree = new Rect();
                    int lineBoundsThree = tvThree.getLayout().getLineBounds(i, boundsThree);
                    boolean lineContainsTabThree = tvThree.getLayout().getLineContainsTab(i);
                    int lineDescentThree = tvThree.getLayout().getLineDescent(i);
                    Layout.Directions lineDirectionsThree = tvThree.getLayout().getLineDirections(i);
                    int lineEndThree = tvThree.getLayout().getLineEnd(i);
                    int lineForOffsetThree = tvThree.getLayout().getLineForOffset(i);
                    int lineForVerticalThree = tvThree.getLayout().getLineForVertical(i);
                    float lineLeftThree = tvThree.getLayout().getLineLeft(i);
                    float lineMaxThree = tvThree.getLayout().getLineMax(i);
                    float lineRightThree = tvThree.getLayout().getLineRight(i);
                    int lineStartThree = tvThree.getLayout().getLineStart(i);
                    int lineTopThree = tvThree.getLayout().getLineTop(i);
                    int lineVisibleEndThree = tvThree.getLayout().getLineVisibleEnd(i);
                    float lineWidthThree = tvThree.getLayout().getLineWidth(i);
                    int ellipsisCountThree = tvThree.getLayout().getEllipsisCount(i);
                    int ellipsisStartThree = tvThree.getLayout().getEllipsisStart(i);
                    int ellipsizedWidthThree = tvThree.getLayout().getEllipsizedWidth();
                }
                int lineCountFour = tvFour.getLayout().getLineCount();
                for (int i = 0; i < lineCountFour; i++) {
                    // 上坡度,为负值
                    int lineAscentFour = tvFour.getLayout().getLineAscent(i);
                    // 文本行基线位置
                    int lineBaselineFour = tvFour.getLayout().getLineBaseline(i);
                    // 文本行距离底边
                    int lineBottomFour = tvFour.getLayout().getLineBottom(i);
                    Rect boundsFour = new Rect();
                    // 文本行边界信息
                    int lineBoundsFour = tvFour.getLayout().getLineBounds(i, boundsFour);
                    // 文本行中是否有制表符信息
                    boolean lineContainsTabFour = tvFour.getLayout().getLineContainsTab(i);
                    // 下坡度,为正值
                    int lineDescentFour = tvFour.getLayout().getLineDescent(i);
                    // 文本行方向
                    Layout.Directions lineDirectionsFour = tvFour.getLayout().getLineDirections(i);
                    // 文本行末尾索引
                    int lineEndFour = tvFour.getLayout().getLineEnd(i);
                    // 指定偏移量为止字符所在的行号,垂直方向相对位置
                    int lineForOffsetFour = tvFour.getLayout().getLineForOffset(i);
                    // 垂直方向的文本行数值.垂直方向相对位置
                    int lineForVerticalFour = tvFour.getLayout().getLineForVertical(i);
                    // 文本行最左边
                    float lineLeftFour = tvFour.getLayout().getLineLeft(i);
                    // 文本行水平方向范围
                    float lineMaxFour = tvFour.getLayout().getLineMax(i);
                    // 文本行右间距
                    float lineRightFour = tvFour.getLayout().getLineRight(i);
                    // 文本行起始左间距
                    int lineStartFour = tvFour.getLayout().getLineStart(i);
                    // 文本行上间距
                    int lineTopFour = tvFour.getLayout().getLineTop(i);
                    // 文本行最后一个可见字符的偏移量
                    int lineVisibleEndFour = tvFour.getLayout().getLineVisibleEnd(i);
                    // 文本行宽
                    float lineWidthFour = tvFour.getLayout().getLineWidth(i);
                    // 文本行,省略的字数
                    int ellipsisCountFour = tvFour.getLayout().getEllipsisCount(i);
                    // 文本行,省略的开始位置
                    int ellipsisStartFour = tvFour.getLayout().getEllipsisStart(i);
                    // 文本宽
                    int ellipsizedWidthFour = tvFour.getLayout().getEllipsizedWidth();
                }
            }
        }, 200);
        String msg = "measureTextOne: " + measureTextOne + "\n"
                + "measureTextTwo: " + measureTextTwo + "\n"
                + "measureTextThree: " + measureTextThree + "\n"
                + "measureTextFour: " + measureTextFour + "\n"
                + "widthOne: " + widthOne + "\n"
                + "widthTwo: " + widthTwo + "\n"
                + "widthThree: " + widthThree + "\n"
                + "widthFour: " + widthFour + "\n";
        content.setText(msg);
        onTips("the test view size.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != velocityTracker) {
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    protected void addOnTouch() {
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (null == velocityTracker) {
                    velocityTracker = VelocityTracker.obtain();
                }
                velocityTracker.addMovement(event);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        onLog("ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        onLog("ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        onLog("ACTION_UP");
//                        velocityTracker.computeCurrentVelocity(1000);
                        velocityTracker.computeCurrentVelocity(100);
                        onLog("XVelocity: " + velocityTracker.getXVelocity());
                        onLog("YVelocity: " + velocityTracker.getYVelocity());
                        break;
                }
                return true;
            }
        });
    }

    public void onTips(@NonNull String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }


    public Rect getMeasuredSize(View view) {
        Rect rect = new Rect();
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        rect.right = width;
        rect.bottom = height;
        return rect;
    }

}