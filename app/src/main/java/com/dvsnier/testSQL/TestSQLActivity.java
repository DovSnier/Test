package com.dvsnier.testSQL;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dvsnier.R;
import com.dvsnier.utils.DBManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestSQLActivity extends AppCompatActivity {

    @Bind(R.id.btn_sql)
    Button btnSql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sql);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sql)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sql:
                DBManager.getInstance().createDatabase(new DBManager.Builder(this).setDatabaseName("testSQL").setVersion(1).builder());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DBManager.getInstance().closeDatabase();
    }
}
