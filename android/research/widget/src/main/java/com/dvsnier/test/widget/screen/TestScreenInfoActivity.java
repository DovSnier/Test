package com.dvsnier.test.widget.screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.base.task.ITask;
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
public class TestScreenInfoActivity extends BaseActivity<TestScreenInfoPresenter> implements ITask {

    @BindView(R2.id.content)
    TextView content;
    @BindView(R2.id.tv_concept)
    TextView tvConcept;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_screen_info);
        ButterKnife.bind(this);
        performScheduledInternal();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        onLog(String.format("onConfigurationChanged(%s)", newConfig.orientation));
        execute();
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            execute();
//        } else {
//            execute();
//        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onLog("onNewIntent()");
    }

    @Override
    public void execute() {
        initData();
    }

    @SuppressLint("LongLogTag")
    @Override
    public void initData() {
        StringBuilder stringBuilder = new StringBuilder();
        final String TAG_FORMAT = "%s: %s";
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        onLog("计算公式: ");
        stringBuilder.append("计算公式: ")
                .append("\n");
        Log.w("density - 计算公式:", "1 px = 1 dp * density, densityDpi = 160 * density");
        stringBuilder.append(String.format(TAG_FORMAT, "density - 计算公式", "1 px = 1 dp * density, densityDpi = 160 * density"))
                .append("\n");
        Log.w("density - 计算公式:", "(dpi | ppi * inch)^2 = (w^2 + h^2)");
        stringBuilder.append(String.format(TAG_FORMAT, "density - 计算公式", "(dpi | ppi * inch)^2 = (w^2 + h^2)"))
                .append("\n");
        Log.w("density - densityDpi", String.valueOf(displayMetrics.densityDpi) + " px/inch");
        stringBuilder.append(String.format(TAG_FORMAT, "density - densityDpi", String.valueOf(displayMetrics.densityDpi) + " px/inch"))
                .append("\n");
        Log.w("density - xdpi", String.valueOf(displayMetrics.xdpi) + " px/inch");
        stringBuilder.append(String.format(TAG_FORMAT, "density - xdpi", String.valueOf(displayMetrics.xdpi) + " px/inch"))
                .append("\n");
        Log.w("density - ydpi", String.valueOf(displayMetrics.ydpi) + " px/inch");
        stringBuilder.append(String.format(TAG_FORMAT, "density - ydpi", String.valueOf(displayMetrics.ydpi) + " px/inch"))
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

        //noinspection StringBufferReplaceableByString
        StringBuilder stringBuilderWithConcept = new StringBuilder();
        stringBuilderWithConcept.append("基本概念: ")
                .append("\n");
        stringBuilderWithConcept.append(String.format(TAG_FORMAT, "dip", "density independent pixels, 设备无关像素;"))
                .append("\n");
        stringBuilderWithConcept.append(String.format(TAG_FORMAT, "dp", "dp == dip;"))
                .append("\n");
        stringBuilderWithConcept.append(String.format(TAG_FORMAT, "px", "像素;"))
                .append("\n");
        stringBuilderWithConcept.append(String.format(TAG_FORMAT, "densityDpi", "densityDpi == dpi;"))
                .append("\n");
        stringBuilderWithConcept.append(String.format(TAG_FORMAT, "dpi", "dots per inch, 像素密度, 160 px/inch == 1 dip;"))
                .append("\n");
        stringBuilderWithConcept.append(String.format(TAG_FORMAT, "density", "密度, 其实是 DPI / (160像素/英寸) 后得到的值, 无单位;"))
                .append("\n");
        stringBuilderWithConcept.append(String.format(TAG_FORMAT, "分辨率", "横纵2个方向的像素点的数量;"))
                .append("\n");
        stringBuilderWithConcept.append(String.format(TAG_FORMAT, "屏幕尺寸", "屏幕对角线的长度;"))
                .append("\n");
        stringBuilderWithConcept.append(String.format(TAG_FORMAT, "屏幕比例", "只确定了对角线长, 2边长度还不一定;"))
                .append("\n");
        tvConcept.setText(stringBuilderWithConcept.toString());
    }
}