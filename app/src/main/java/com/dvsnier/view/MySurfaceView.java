package com.dvsnier.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dvsnier.R;

/**
 * Created by DovSnier on 2016/6/25.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    protected final String TAG = MySurfaceView.class.getSimpleName();
    protected SurfaceHolder surfaceHolder;
    private boolean ThreadFlag;
    private int counter;
    private Canvas canvas;

    private Thread mThread = new Thread(new Runnable() {

        @Override
        public void run() {
            while (ThreadFlag) {
                canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.GREEN);
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                paint.setTextSize(40);
                Rect rect = new Rect(100, 400, 400, 650);
                canvas.drawRect(rect, paint);
                canvas.drawText("时间 = " + (counter++) + " 秒", 150, 150, paint);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                canvas.drawBitmap(bitmap, 200, 250, paint);
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    });

    public MySurfaceView(Context context) {
        super(context);
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged: " + width + " & " + height);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated...");
        counter = 0;
        ThreadFlag = true;
        mThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "surfaceDestroyed");
        ThreadFlag = false;
    }

}

