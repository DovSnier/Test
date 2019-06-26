package com.dvsnier.base.flavor.sql;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dvsnier.base.flavor.R;
import com.dvsnier.base.flavor.R2;
import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.utils.DBManager;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestSQLActivity extends BaseActivity {

    @BindView(R2.id.btn_coordinate_sql)
    Button btnCoordinateSql;
    @BindView(R2.id.btn_notes_sql)
    Button btnNotesSql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sql);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.btn_coordinate_sql, R2.id.btn_notes_sql})
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_coordinate_sql) {
            DBManager.getInstance().createDatabase(new DBManager.Builder(this).setDatabaseName("coordinateSQL").setVersion(1).builder());
            SQLiteDatabase database = DBManager.getInstance().getDatabase();
            boolean tableIsExist = DBManager.getInstance().tableIsExist("coordinate", database);
            if (!tableIsExist) {
                try {
                    InputStream inputStream = getAssets().open("coordinate.sql");
                    DBManager.getInstance().executeAssetsSQL(inputStream, database);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (viewId == R.id.btn_notes_sql) {
            DBManager.getInstance().createDatabase(new DBManager.Builder(this).setDatabaseName("notesSQL").setVersion(1).builder());
            SQLiteDatabase notesDatabase = DBManager.getInstance().getDatabase();
            boolean notesTableIsExist = DBManager.getInstance().tableIsExist("notes", notesDatabase);
            if (!notesTableIsExist) {
                try {
                    InputStream inputStream = getAssets().open("notes.sql");
                    DBManager.getInstance().executeAssetsSQL(inputStream, notesDatabase);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DBManager.getInstance().closeDatabase();
    }

}
