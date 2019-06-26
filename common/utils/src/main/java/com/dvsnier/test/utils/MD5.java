package com.dvsnier.test.utils;

import android.util.Log;

/**
 * THE MD5 CODE
 */
public class MD5 {

    /**
     * the md5 encryption
     *
     * @param source the string encryption
     */
    public static String encode(String source) {
        return encode(source.getBytes());
    }

    /**
     * the md5 encryption
     *
     * @param source An byte array encryption
     */
    private static String encode(byte[] source) {
        String digest = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("MD5");
            messageDigest.update(source);
            byte bytes[] = messageDigest.digest();
            char chars[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = bytes[i];
                chars[k++] = hexDigits[byte0 >>> 4 & 0xf];
                chars[k++] = hexDigits[byte0 & 0xf];
            }
            digest = new String(chars);
        } catch (Exception e) {
            Log.e("MD5", e.toString());
        }
        return digest;
    }
}
