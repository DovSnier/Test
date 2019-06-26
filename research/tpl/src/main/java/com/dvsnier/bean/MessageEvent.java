package com.dvsnier.bean;

import android.support.annotation.NonNull;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by lzw on 2017/9/7.
 */

public class MessageEvent {

  protected String message;

  public static void post(@NonNull MessageEvent event) {
    event.setMessage(event.getMessage() + " -> 发送完成");
    EventBus.getDefault().post(event);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
