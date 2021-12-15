package com.dvsnier.test.tpl.green;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.dvsnier.bean.DaoSession;
import com.dvsnier.bean.Student;
import com.dvsnier.test.tpl.R;
import com.dvsnier.test.tpl.R2;
import com.dvsnier.test.tpl.green.common.DaoSessionWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestGreenDaoActivity extends AppCompatActivity {

    @BindView(R2.id.add)
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_green_dao);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.add)
    public void onViewClicked() {
        DaoSession daoSession = DaoSessionWrapper.getInstance().with(getApplicationContext()).getDaoSession();
        Student student = new Student();
        double value = Math.random() * 100;
        String number = String.format("%s", System.currentTimeMillis());
        student.setNumber(number);
        String name = String.format("%1$s%2$s", "test", value);
        student.setName(name);
        if (value > 50.0d) {
            student.setSex("女");
        } else {
            student.setSex("男");
        }
        daoSession.getStudentDao().insert(student);
        Toast.makeText(this, String.format("%1$s %2$s", number, name), Toast.LENGTH_SHORT).show();
    }
}
