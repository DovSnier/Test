package com.dvsnier.wrapper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.dvsnier.R;
import com.dvsnier.base.flavor.cache.TestCacheActivity;
import com.dvsnier.base.flavor.crash.TestCrashHandleActivity;
import com.dvsnier.base.flavor.sql.TestSQLActivity;
import com.dvsnier.bean.ComponentBean;
import com.dvsnier.constant.IAdapterType;
import com.dvsnier.test.common.aidl.TestAIDLActivity;
import com.dvsnier.test.common.permission.TestPermissionActivity;
import com.dvsnier.test.common.touch.TestTouchEventActivity;
import com.dvsnier.test.tpl.image.TestImageActivity;
import com.dvsnier.test.tpl.xutils.TestXUtilsActivity;
import com.dvsnier.test.view.affinity.AffinityActivity;
import com.dvsnier.test.view.fragment.FragmentHomeActivity;
import com.dvsnier.test.view.fragment.FragmentHomeNoStateActivity;
import com.dvsnier.test.view.fragment.FragmentHomeStateActivity;
import com.dvsnier.test.widget.animator.TestAnimator;
import com.dvsnier.test.widget.coordinator.TestCoordinatorLayout2Activity;
import com.dvsnier.test.widget.coordinator.TestCoordinatorLayout3Activity;
import com.dvsnier.test.widget.coordinator.TestCoordinatorLayoutActivity;
import com.dvsnier.test.widget.recycleview.TestRecyclerActivity;
import com.dvsnier.test.widget.screen.TestScreenInfoActivity;
import com.dvsnier.test.widget.scroll.TestScrollActivity;
import com.dvsnier.test.widget.surfaceview.TestSurfaceActivity;
import com.dvsnier.test.widget.theme.TestThemeActivity;
import com.dvsnier.test.tpl.eventbus.TestEventBusActivity;
import com.dvsnier.test.tpl.green.TestGreenDaoActivity;
import com.dvsnier.test.tpl.okhttp.TestOkhttpActivity;
import com.dvsnier.test.tpl.speech.TestSpeechRecognitionActivity;
import com.orhanobut.logger.Logger;

/**
 * TransferStationWrapper
 * Created by dovsnier on 2019/6/14.
 */
public class TransferStationWrapper implements IAdapterType {

    public static final String TAG = "TransferStationWrapper";
    protected Context context;
    protected boolean DEBUG;

    private TransferStationWrapper() {
    }

    public TransferStationWrapper(Context context) {
        this.context = context;
        DEBUG = context.getResources().getBoolean(R.bool.debug);
    }

    public void onItemClick(AdapterView<?> parent, View view, int groupPosition, int childPosition, ComponentBean bean) {
        if (null == bean) return;
        if (DEBUG)
            Logger.wtf(TAG, "the current groupPosition is " + groupPosition + " and childPosition is " + childPosition + " ,then id is " + bean.getId());
        switch (groupPosition) {
            case TYPE_WIDGET:
                onItemWidgetClick(parent, view, groupPosition, childPosition, bean);
                break;
            case TYPE_VIEW:
                onItemViewClick(parent, view, groupPosition, childPosition, bean);
                break;
            case TYPE_SERVICE:
                onItemServiceClick(parent, view, groupPosition, childPosition, bean);
                break;
            case TYPE_PROVIDER:
                onItemProviderClick(parent, view, groupPosition, childPosition, bean);
                break;
            case TYPE_BASE:
                onItemBaseClick(parent, view, groupPosition, childPosition, bean);
                break;
            case TYPE_COMMON:
                onItemCommonClick(parent, view, groupPosition, childPosition, bean);
                break;
            case TYPE_TPL:
                onItemTplClick(parent, view, groupPosition, childPosition, bean);
                break;
        }
    }

    public void onItemWidgetClick(AdapterView<?> parent, View view, int groupPosition, int childPosition, ComponentBean bean) {
        Intent intent;
        switch (childPosition) {
            case WidgetType.TYPE_WIDGET_THEME:
                intent = new Intent(getContext(), TestThemeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case WidgetType.TYPE_WIDGET_SCROLL:
                intent = new Intent(getContext(), TestScrollActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case WidgetType.TYPE_WIDGET_RECYCLER_VIEW:
                intent = new Intent(getContext(), TestRecyclerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case WidgetType.TYPE_WIDGET_SURFACE_VIEW:
                intent = new Intent(getContext(), TestSurfaceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case WidgetType.TYPE_WIDGET_ANIMATOR:
                intent = new Intent(getContext(), TestAnimator.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case WidgetType.TYPE_WIDGET_COORDINATOR_LAYOUT:
                intent = new Intent(getContext(), TestCoordinatorLayoutActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case WidgetType.TYPE_WIDGET_COLLAPSING_TOOLBAR_LAYOUT:
                intent = new Intent(getContext(), TestCoordinatorLayout2Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case WidgetType.TYPE_WIDGET_APPBAR_LAYOUT:
                intent = new Intent(getContext(), TestCoordinatorLayout3Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            case WidgetType.TYPE_WIDGET_SCREEN_INFO:
                intent = new Intent(getContext(), TestScreenInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
        }
    }

    public void onItemViewClick(AdapterView<?> parent, View view, int groupPosition, int childPosition, ComponentBean bean) {
        Intent intent;
        switch (childPosition) {
            case ViewType.TYPE_VIEW_AFFINITY:
                intent = new Intent(getContext(), AffinityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case ViewType.TYPE_VIEW_FRAGMENT:
                intent = new Intent(getContext(), FragmentHomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case ViewType.TYPE_VIEW_FRAGMENT_AND_FRAGMENT_PAGER_NO_STATE_ADAPTER:
                intent = new Intent(getContext(), FragmentHomeNoStateActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case ViewType.TYPE_VIEW_FRAGMENT_AND_FRAGMENT_PAGER_STATE_ADAPTER:
                intent = new Intent(getContext(), FragmentHomeStateActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
        }
    }

    public void onItemServiceClick(AdapterView<?> parent, View view, int groupPosition, int childPosition, ComponentBean bean) {
        Intent intent;
        switch (childPosition) {
            case ServiceType.TYPE_SERVICE_NONE:
                break;
        }
    }

    public void onItemProviderClick(AdapterView<?> parent, View view, int groupPosition, int childPosition, ComponentBean bean) {
        Intent intent;
        switch (childPosition) {
            case ProviderType.TYPE_PROVIDER_NONE:
                break;
        }
    }

    public void onItemBaseClick(AdapterView<?> parent, View view, int groupPosition, int childPosition, ComponentBean bean) {
        Intent intent;
        switch (childPosition) {
            case BaseType.TYPE_BASE_EXCEPTION:
                intent = new Intent(getContext(), TestCrashHandleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case BaseType.TYPE_BASE_CACHE:
                intent = new Intent(getContext(), TestCacheActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case BaseType.TYPE_BASE_SQL:
                intent = new Intent(getContext(), TestSQLActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
        }
    }

    public void onItemCommonClick(AdapterView<?> parent, View view, int groupPosition, int childPosition, ComponentBean bean) {
        Intent intent;
        switch (childPosition) {
            case CommonType.TYPE_COMMON_PERMISSION:
                intent = new Intent(getContext(), TestPermissionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case CommonType.TYPE_COMMON_AIDL:
                intent = new Intent(getContext(), TestAIDLActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case CommonType.TYPE_COMMON_TOUCH_EVENT:
                intent = new Intent(getContext(), TestTouchEventActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
        }
    }

    public void onItemTplClick(AdapterView<?> parent, View view, int groupPosition, int childPosition, ComponentBean bean) {
        Intent intent;
        switch (childPosition) {
            case TplType.TYPE_TPL_GLIDE:
                intent = new Intent(getContext(), TestImageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case TplType.TYPE_TPL_X_UTILS:
                intent = new Intent(getContext(), TestXUtilsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case TplType.TYPE_TPL_GREEN_DAO:
                intent = new Intent(getContext(), TestGreenDaoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case TplType.TYPE_TPL_SPEECH_RECOGNITION:
                intent = new Intent(getContext(), TestSpeechRecognitionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case TplType.TYPE_TPL_OK_HTTP:
                intent = new Intent(getContext(), TestOkhttpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case TplType.TYPE_TPL_EVENT_BUS:
                intent = new Intent(getContext(), TestEventBusActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
        }
    }

    public void startActivity(Intent intent) {
        getContext().startActivity(intent);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
