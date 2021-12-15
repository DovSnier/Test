package com.dvsnier.tool.interestrate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.tool.R;
import com.dvsnier.tool.presenter.InterestRatePresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * InterestRateActivity
 * Created by dovsnier on 2021/9/14.
 */
public class InterestRateActivity extends BaseActivity<InterestRatePresenter> implements View.OnClickListener {

    protected Button btnCalculation;
    protected Button btnClear;
    protected TextView tvDescribe;
    protected EditText etPrincipal;
    protected EditText etAnnualInterestRate;
    protected EditText etMonthInterestRate;
    protected EditText etDayInterestRate;
    protected DatePicker dpTimeStart;
    protected DatePicker dpTimeEnd;
    protected EditText etInterest;
    protected EditText etTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_rate);
        performScheduledInternal();
    }

    @Override
    public void initView() {
        btnCalculation = findViewById(R.id.btn_calculation);
        btnClear = findViewById(R.id.btn_clear);
        tvDescribe = findViewById(R.id.tv_describe);
        etPrincipal = findViewById(R.id.et_principal);
        etAnnualInterestRate = findViewById(R.id.et_annual_interest_rate);
        etMonthInterestRate = findViewById(R.id.et_month_interest_rate);
        etDayInterestRate = findViewById(R.id.et_day_interest_rate);
        dpTimeStart = findViewById(R.id.dp_time_start);
        dpTimeEnd = findViewById(R.id.dp_time_end);
        etInterest = findViewById(R.id.et_interest);
        etTotal = findViewById(R.id.et_total);
    }

    @Override
    public void initData() {
        btnCalculation.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        etPrincipal.setText("");
        etAnnualInterestRate.setText("");
        etMonthInterestRate.setText("");
        etDayInterestRate.setText("");
        Calendar calendar = Calendar.getInstance();
        dpTimeStart.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy年MM月dd日");
                Toast.makeText(InterestRateActivity.this,
                        format.format(calendar.getTime()), Toast.LENGTH_SHORT)
                        .show();
            }
        });
        dpTimeEnd.init(calendar.get(Calendar.YEAR) + 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy年MM月dd日");
                Toast.makeText(InterestRateActivity.this,
                        format.format(calendar.getTime()), Toast.LENGTH_SHORT)
                        .show();
            }
        });
        etInterest.setText("");
        etTotal.setText("");
    }

    /**
     * 三要素:
     * 1. 本金
     * 2. 利率
     * 3. 时间
     * <p>
     * 本金 + 利息(利率[年,月,日] + 时间 + $1) = 总价
     * <p>
     * 计算分布:
     * 1. 本金 + 利息(利率 + 时间 + $1) = ？
     * 2. 本金 + ？ = 总价
     * 2.1 本金 + 利息(？ + 时间 + $1) = 总价
     * 2.2 本金 + 利息(利率[年,月,日] + ？ + $1) = 总价
     * 3. ？ + 利息(利率 + 时间 + $1) = 总价
     */
    public void onRecalculate() {

    }

    public void onClear() {
        initData();
    }

    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_calculation) { // 计算
            onRecalculate();
        } else if (viewId == R.id.btn_clear) { // 清理
            onClear();
        } else {
            // nothing to do
        }
    }
}