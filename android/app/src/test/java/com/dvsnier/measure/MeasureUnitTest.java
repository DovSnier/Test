package com.dvsnier.measure;

import org.junit.Test;

/**
 * MeasureUnitTest
 */
public class MeasureUnitTest {
    @Test
    public void measure() {
        int MODE_SHIFT = 30;
        int MODE_MASK = 0x3 << MODE_SHIFT;
        int UNSPECIFIED = 0 << MODE_SHIFT;
        int EXACTLY = 1 << MODE_SHIFT;
        int AT_MOST = 2 << MODE_SHIFT;

        int size = 100;
        int mode = UNSPECIFIED;
        System.out.println("0 << MODE_SHIFT(30): " + (0 << MODE_SHIFT));
        System.out.println("1 << MODE_SHIFT(30): " + (1 << MODE_SHIFT));
        System.out.println("2 << MODE_SHIFT(30): " + (2 << MODE_SHIFT));
        System.out.println("0x03 << MODE_SHIFT(30): " + (0x03 << MODE_SHIFT));
        int measureSpec = (size & ~MODE_MASK) | (mode & MODE_MASK);
        int getSize = measureSpec & ~MODE_MASK;
        int getMode = measureSpec & MODE_MASK;
        System.out.println("size: " + size + " , " + "mode: " + mode);
        System.out.println("measureSpec: " + measureSpec + " , "
                + "getSize: " + getSize + " , "
                + "getMode: " + getMode);
    }
}