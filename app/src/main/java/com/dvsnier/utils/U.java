package com.dvsnier.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by DovSnier on 2016/5/27.
 */
public class U {

    public static File getCacheFile(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir();
        }
        return context.getCacheDir();
    }

    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static void writeToFile(OutputStream outputStream, String value) {
        try {
            Writer writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
            writer.write(value);
            writer.flush();
            outputStream.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(InputStream inputStream) {
        try {
            Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            CharBuffer charBuffer = CharBuffer.allocate(1024 * 10);
            while (reader.ready()) {
                reader.read(charBuffer);
            }
            reader.close();
            return String.valueOf(charBuffer.array()).trim();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
