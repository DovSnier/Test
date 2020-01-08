package com.dvsnier.test.widget.screen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.widget.R;
import com.dvsnier.test.widget.R2;
import com.dvsnier.test.widget.presenter.TestScreenInfoPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TestScreenInfoActivity
 * Created by dovsnier on 2020-01-08.
 */
public class TestScreenInfoActivity extends BaseActivity<TestScreenInfoPresenter> {

    @BindView(R2.id.content)
    TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_screen_info);
        ButterKnife.bind(this);
        performScheduledInternal();
    }


    @SuppressLint("LongLogTag")
    @Override
    public void initData() {
        StringBuilder stringBuilder = new StringBuilder();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.w("density - 计算公式:", "1 px = 1 dp * density, densityDpi = 160 * density");
        String TAG_FORMAT = "%s: %s";
        stringBuilder.append(String.format(TAG_FORMAT, "density - 计算公式", "1 px = 1 dp * density, densityDpi = 160 * density"))
                .append("\n");
        Log.w("density - 计算公式:", "(dpi | ppi * inch)^2 = (w^2 + h^2)");
        stringBuilder.append(String.format(TAG_FORMAT, "density - 计算公式", "(dpi | ppi * inch)^2 = (w^2 + h^2)"))
                .append("\n");
        Log.w("density - densityDpi", String.valueOf(displayMetrics.densityDpi));
        stringBuilder.append(String.format(TAG_FORMAT, "density - densityDpi", String.valueOf(displayMetrics.densityDpi)))
                .append("\n");
        Log.w("density - xdpi", String.valueOf(displayMetrics.xdpi));
        stringBuilder.append(String.format(TAG_FORMAT, "density - xdpi", String.valueOf(displayMetrics.xdpi)))
                .append("\n");
        Log.w("density - ydpi", String.valueOf(displayMetrics.ydpi));
        stringBuilder.append(String.format(TAG_FORMAT, "density - ydpi", String.valueOf(displayMetrics.ydpi)))
                .append("\n");
        Log.w("density - density", String.valueOf(displayMetrics.density));
        stringBuilder.append(String.format(TAG_FORMAT, "density - density", String.valueOf(displayMetrics.density)))
                .append("\n");
        Log.w("density - widthPixels", String.valueOf(displayMetrics.widthPixels));
        stringBuilder.append(String.format(TAG_FORMAT, "density - widthPixels", String.valueOf(displayMetrics.widthPixels)))
                .append("\n");
        Log.w("density - heightPixels", String.valueOf(displayMetrics.heightPixels));
        stringBuilder.append(String.format(TAG_FORMAT, "density - heightPixels", String.valueOf(displayMetrics.heightPixels)))
                .append("\n");
        //noinspection UnnecessaryBoxing
        Log.w("density - (w/h)", String.valueOf(Double.valueOf(displayMetrics.widthPixels) / Double.valueOf(displayMetrics.heightPixels)));
        //noinspection UnnecessaryBoxing
        stringBuilder.append(String.format(TAG_FORMAT, "density - (w/h)", String.valueOf(Double.valueOf(displayMetrics.widthPixels) / Double.valueOf(displayMetrics.heightPixels))))
                .append("\n");
        Log.w("density - scaledDensity", String.valueOf(displayMetrics.scaledDensity));
        stringBuilder.append(String.format(TAG_FORMAT, "density - scaledDensity", String.valueOf(displayMetrics.scaledDensity)))
                .append("\n");
        double sqrt = Math.sqrt((displayMetrics.widthPixels * displayMetrics.widthPixels + displayMetrics.heightPixels * displayMetrics.heightPixels));
        Log.w("density - sqrt(densityDpi * inch)", String.format("%f", sqrt));
        Log.w("density - inch", String.format("%f", (sqrt / displayMetrics.densityDpi)));
        stringBuilder.append(String.format(TAG_FORMAT, "density - sqrt(densityDpi * inch)", String.format(Locale.getDefault(), "%f", sqrt)))
                .append("\n");
        stringBuilder.append(String.format(TAG_FORMAT, "density - inch", String.format(Locale.getDefault(), "%f", (sqrt / displayMetrics.densityDpi))))
                .append("\n");

        content.setText(stringBuilder.toString());
    }
}