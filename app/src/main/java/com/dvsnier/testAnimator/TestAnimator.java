package com.dvsnier.testAnimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;

import com.dvsnier.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lizw on 2017/6/22.
 */

public class TestAnimator extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();
    @Bind(R.id.test_1)
    Button test1;
    @Bind(R.id.test_2)
    Button test2;
    @Bind(R.id.test_3)
    Button test3;
    @Bind(R.id.test_4)
    Button test4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animator);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.test_1, R.id.test_2, R.id.test_3, R.id.test_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test_1:
                executeAnimator1();
                break;
            case R.id.test_2:
                executeAnimator2();
                break;
            case R.id.test_3:
                executeAnimator3();
                break;
            case R.id.test_4:
                executeAnimator4();
                break;
            default:
                break;
        }
    }

    protected void executeAnimator1() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_test);
        animator.setTarget(test1);
        animator.start();
    }

    protected void executeAnimator2() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 5f, 3f, 10f);
        valueAnimator.setDuration(5000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                Log.d(TAG, "the current value is " + currentValue);
            }
        });
    }

    protected void executeAnimator3() {
        ViewPropertyAnimator viewPropertyAnimator = test3.animate().rotation(180f).setDuration(2000);
        addUpdateListener(viewPropertyAnimator);
    }

    protected void executeAnimator4() {
        ViewPropertyAnimator viewPropertyAnimator = test4.animate().rotationBy(180f).setDuration(2000);
        addUpdateListener(viewPropertyAnimator);
    }

    protected void addUpdateListener(ViewPropertyAnimator viewPropertyAnimator) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            viewPropertyAnimator.setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float currentValue = (float) animation.getAnimatedValue();
                    Log.d(TAG, "the current value is " + currentValue);
                }
            });
        }
    }

}
