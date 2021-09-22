package com.dvsnier.constant;

import com.dvsnier.viewholder.IType;

/**
 * IAdapterType
 * Created by dovsnier on 2019/6/14.
 */
public interface IAdapterType {

    int TYPE_WIDGET = 0; // widget
    int TYPE_VIEW = 1; // view
    int TYPE_SERVICE = 2; // service
    int TYPE_PROVIDER = 3; // provider
    int TYPE_BASE = 4; // base
    int TYPE_COMMON = 5; // common
    int TYPE_TPL = 6; // tpl
    int TYPE_TOOLS = 7; // tools


    interface WidgetType extends IType {
        int TYPE_WIDGET_THEME = 0;
        int TYPE_WIDGET_SCROLL = 1;
        int TYPE_WIDGET_RECYCLER_VIEW = 2;
        int TYPE_WIDGET_SURFACE_VIEW = 3;
        int TYPE_WIDGET_ANIMATOR = 4;
        int TYPE_WIDGET_COORDINATOR_LAYOUT = 5;
        int TYPE_WIDGET_COLLAPSING_TOOLBAR_LAYOUT = 6;
        int TYPE_WIDGET_APPBAR_LAYOUT = 7;
        int TYPE_WIDGET_SCREEN_INFO = 8;
        int TYPE_WIDGET_TEXT_VIEW_SIZE = 9;
        int TYPE_WIDGET_NESTED_SCROLLING_PARENT = 10;
        int TYPE_WIDGET_VIEW_GROUP = 11;
    }

    interface ViewType extends IType {
        int TYPE_VIEW_AFFINITY = 0;
        int TYPE_VIEW_FRAGMENT = 1;
        int TYPE_VIEW_FRAGMENT_AND_FRAGMENT_PAGER_NO_STATE_ADAPTER = 2;
        int TYPE_VIEW_FRAGMENT_AND_FRAGMENT_PAGER_STATE_ADAPTER = 3;
    }

    interface ServiceType extends IType {
        int TYPE_SERVICE_MESSENGER = 0;
    }

    interface ProviderType extends IType {
        int TYPE_PROVIDER_NONE = 0;
    }

    interface BaseType extends IType {
        int TYPE_BASE_EXCEPTION = 0;
        int TYPE_BASE_CACHE = 1;
        int TYPE_BASE_SQL = 2;
    }

    interface CommonType extends IType {
        int TYPE_COMMON_PERMISSION = 0;
        int TYPE_COMMON_AIDL = 1;
        int TYPE_COMMON_TOUCH_EVENT = 2;
        int TYPE_COMMON_NOTIFICATION = 3;
    }

    interface TplType extends IType {
        int TYPE_TPL_GLIDE = 0;
        int TYPE_TPL_X_UTILS = 1;
        int TYPE_TPL_GREEN_DAO = 2;
        int TYPE_TPL_SPEECH_RECOGNITION = 3;
        int TYPE_TPL_OK_HTTP = 4;
        int TYPE_TPL_EVENT_BUS = 5;
    }

    interface ToolType extends IType {
        int TYPE_TOOL_ANNUALIZED_RATE = 0;
    }
}
