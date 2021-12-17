package com.dvsnier.test.widget.theme;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.dvsnier.base.flavor.activity.BaseActivity;
import com.dvsnier.test.widget.R;

public class TestThemeActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_theme);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("dvsnier");
        toolbar.setSubtitle("副标题");
        setSupportActionBar(toolbar);
        /* 这些通过ActionBar来设置也是一样的，注意要在setSupportActionBar(toolbar);之后，不然就报错了 */
        // getSupportActionBar().setTitle("标题");
        // getSupportActionBar().setSubtitle("副标题");
        // getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        /* 菜单的监听可以在toolbar里设置，也可以像ActionBar那样，通过Activity的onOptionsItemSelected回调方法来处理 */
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.action_settings:
//                        Toast.makeText(TestThemeActivity.this, "action_settings", 0).show();
//                        break;
//                    case R.id.action_share:
//                        Toast.makeText(TestThemeActivity.this, "action_share", 0).show();
//                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


}
