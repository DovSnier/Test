package com.dvsnier.testSpeechRecognition;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dvsnier.R;
import com.dvsnier.test.utils.JsonParser;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class TestSpeechRecognitionActivity extends AppCompatActivity
    implements InitListener, RecognizerDialogListener {

  protected static final String TAG = TestSpeechRecognitionActivity.class.getSimpleName();

  @Bind(R.id.start) Button start;
  @Bind(R.id.stop) Button stop;
  @Bind(R.id.cancel) Button cancel;
  @Bind(R.id.content) EditText content;
  // 语音听写对象
  private SpeechRecognizer mIat;
  // 语音听写UI
  private RecognizerDialog mIatDialog;
  // 用HashMap存储听写结果
  private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
  // 引擎类型
  private String mEngineType = SpeechConstant.TYPE_CLOUD;
  boolean isShowDialog = true;
  private int ret = 0; // 函数调用返回值

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_speech_recognition);
    ButterKnife.bind(this);
    // 初始化识别无UI识别对象
    // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
    mIat = SpeechRecognizer.createRecognizer(this, this);

    // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
    // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
    mIatDialog = new RecognizerDialog(this, this);
  }

  @Override protected void onResume() {
    // 开放统计 移动数据统计分析
    FlowerCollector.onResume(TestSpeechRecognitionActivity.this);
    FlowerCollector.onPageStart(TAG);
    super.onResume();
  }

  @Override protected void onPause() {
    // 开放统计 移动数据统计分析
    FlowerCollector.onPageEnd(TAG);
    FlowerCollector.onPause(TestSpeechRecognitionActivity.this);
    super.onPause();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  @OnClick({ R.id.start, R.id.stop, R.id.cancel }) public void onViewClicked(View view) {
    if (null == mIat) {
      // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
      Toast.makeText(this,
          String.format("%s", "创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化"),
          Toast.LENGTH_SHORT).show();

      return;
    }
    switch (view.getId()) {
      case R.id.start: // 开始
        // 如何判断一次听写结束：OnResult isLast=true 或者 onError
        // 移动数据分析，收集开始听写事件
        FlowerCollector.onEvent(this, "iat_recognize");
        content.setText(null);// 清空显示内容
        mIatResults.clear();
        setParam();
        if (isShowDialog) {
          // 显示听写对话框
          mIatDialog.setListener(this);
          mIatDialog.show();
        } else {
          // 不显示听写对话框
          ret = mIat.startListening(mRecognizerListener);
          if (ret != ErrorCode.SUCCESS) {
            Toast.makeText(this, String.format("%1$s2$s", "听写失败,错误码：", ret), Toast.LENGTH_SHORT)
                .show();
          } else {
            Toast.makeText(this, String.format("%1$s", "请开始说话…"), Toast.LENGTH_SHORT).show();
          }
        }
        break;
      case R.id.stop: // 停止
        mIat.stopListening();
        Toast.makeText(this, String.format("%1$s", "停止听写"), Toast.LENGTH_SHORT).show();
        break;
      case R.id.cancel: // 取消
        mIat.cancel();
        Toast.makeText(this, String.format("%1$s", "取消听写"), Toast.LENGTH_SHORT).show();
        finish();
        break;
    }
  }

  @Override public void onInit(int code) {
    Log.d(TAG, "SpeechRecognizer init() code = " + code);
    if (code != ErrorCode.SUCCESS) {
      Toast.makeText(this, String.format("%1$s2$s", "初始化失败，错误码：", code), Toast.LENGTH_SHORT).show();
    }
  }

  @Override public void onResult(RecognizerResult recognizerResult, boolean b) {
    setContent(recognizerResult);
  }

  @Override public void onError(SpeechError speechError) {
    Toast.makeText(this, String.format("%1$s", speechError.getPlainDescription(true)),
        Toast.LENGTH_SHORT).show();
  }

  public void setParam() {
    // 清空参数
    mIat.setParameter(SpeechConstant.PARAMS, null);
    // 设置听写引擎
    mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
    // 设置返回结果格式
    mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

    //if (mTranslateEnable) {
    //  Log.i(TAG, "translate enable");
    //  mIat.setParameter(SpeechConstant.ASR_SCH, "1");
    //  mIat.setParameter(SpeechConstant.ADD_CAP, "translate");
    //  mIat.setParameter(SpeechConstant.TRS_SRC, "its");
    //}

    String lag = "mandarin"; // 普通话
    if (lag.equals("en_us")) {
      // 设置语言
      mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
      mIat.setParameter(SpeechConstant.ACCENT, null);

      //if (mTranslateEnable) {
      //  mIat.setParameter(SpeechConstant.ORI_LANG, "en");
      //  mIat.setParameter(SpeechConstant.TRANS_LANG, "cn");
      //}
    } else {
      // 设置语言
      mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
      // 设置语言区域
      mIat.setParameter(SpeechConstant.ACCENT, lag);

      //if (mTranslateEnable) {
      //  mIat.setParameter(SpeechConstant.ORI_LANG, "cn");
      //  mIat.setParameter(SpeechConstant.TRANS_LANG, "en");
      //}
    }

    // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
    mIat.setParameter(SpeechConstant.VAD_BOS, "4000");

    // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
    mIat.setParameter(SpeechConstant.VAD_EOS, "1000");

    // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
    mIat.setParameter(SpeechConstant.ASR_PTT, "1");

    // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE 权限
    // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
    mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
    mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH,
        Environment.getExternalStorageDirectory() + "/msc/iat.wav");
  }

  /**
   * 听写监听器 - 无UI 情况
   */
  private RecognizerListener mRecognizerListener = new RecognizerListener() {

    @Override public void onBeginOfSpeech() {
      // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
      Toast.makeText(TestSpeechRecognitionActivity.this, String.format("%1$s", ""),
          Toast.LENGTH_SHORT).show();
      Toast.makeText(TestSpeechRecognitionActivity.this, String.format("%1$s", "开始说话..."),
          Toast.LENGTH_SHORT).show();
    }

    @Override public void onError(SpeechError error) {
      // Tips：
      // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
      // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
      Toast.makeText(TestSpeechRecognitionActivity.this,
          String.format("%1$s", error.getPlainDescription(true)), Toast.LENGTH_SHORT).show();
    }

    @Override public void onEndOfSpeech() {
      // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
      Toast.makeText(TestSpeechRecognitionActivity.this, String.format("%1$s", "结束说话"),
          Toast.LENGTH_SHORT).show();
    }

    @Override public void onResult(RecognizerResult results, boolean isLast) {
      Log.d(TAG, results.getResultString());
      //Toast.makeText(TestSpeechRecognitionActivity.this,
      //    String.format("%1$s", results.getResultString()), Toast.LENGTH_SHORT).show();/
      setContent(results);
      if (isLast) {
        // TODO 最后的结果
      }
    }

    @Override public void onVolumeChanged(int volume, byte[] data) {
      Toast.makeText(TestSpeechRecognitionActivity.this,
          String.format("%1$s%2$s", "当前正在说话，音量大小：", volume), Toast.LENGTH_SHORT).show();
      Log.d(TAG, "返回音频数据：" + data.length);
    }

    @Override public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
      // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
      // 若使用本地能力，会话id为null
      //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
      //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
      //		Log.d(TAG, "session id =" + sid);
      //	}
    }
  };

  protected void setContent(RecognizerResult recognizerResult) {
    String text = JsonParser.parseIatResult(recognizerResult.getResultString());
    String sn = null;
    // 读取json结果中的sn字段
    try {
      JSONObject resultJson = new JSONObject(recognizerResult.getResultString());
      sn = resultJson.optString("sn");
    } catch (JSONException e) {
      e.printStackTrace();
    }
    mIatResults.put(sn, text);
    StringBuffer resultBuffer = new StringBuffer();
    for (String key : mIatResults.keySet()) {
      resultBuffer.append(mIatResults.get(key));
    }
    content.setText(resultBuffer.toString());
    content.setSelection(content.length());
  }
}
