package com.cpxiao.idleballz.mode.extra;

import com.cpxiao.AppConfig;

import java.text.DecimalFormat;

/**
 * @author cpxiao on 2017/09/27.
 */

public final class GameExtra {
    private static final boolean DEBUG = AppConfig.DEBUG;
    private static final String TAG = GameExtra.class.getSimpleName();

    /**
     * Kilo     K   1K字节 = 1，024字节
     * Meg      M   1M字节 = 1，048，576字节
     * Giga     G   1G字节 = 1，073，741，824字节
     * Tera     T   1T字节 = 1，099，511，627，776字节
     * Peta     P   1P字节 = 1，125，899，906，842，624字节
     * Exa      E   1E字节 = 1，152，921，504，606，846，976字节
     * Zetta    Z   1Z字节 = 1，180，591，620，717，411，303，424字节
     * Yotta    Y   1Y字节 = 1，208，925，819，614，629，174，706，176字节
     */
    private static final float K = 1000.0F;
    private static final float M = 1000000.0F;
    private static final float G = 1000000000.0F;
    private static final float T = 1000000000000.0F;
    private static final float P = 1000000000000000.0F;
    private static final float E = 1000000000000000000.0F;
    private static final float Z = 1000000000000000000000.0F;
    private static final float Y = 1000000000000000000000000.0F;

    public static String format1(float price) {
        if (DEBUG) {
//            Log.d(TAG, "format1: ");
        }
        DecimalFormat df = new DecimalFormat("######0.0");
        return format(price, df);
    }

    public static String format2(float power) {
        if (DEBUG) {
//            Log.d(TAG, "format2: ");
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        return format(power, df);
    }

    private static String format(float value, DecimalFormat df) {
        if (value < 0) {
            return "0";
        }
        if (value < K) {
            return Math.round(value) + "";
        } else if (value < M) {
            return df.format(value / K) + "K";
        } else if (value < G) {
            return df.format(value / M) + "M";
        } else if (value < T) {
            return df.format(value / G) + "G";
        } else if (value < P) {
            return df.format(value / T) + "T";
        } else if (value < E) {
            return df.format(value / P) + "P";
        } else if (value < Z) {
            return df.format(value / E) + "E";
        } else if (value < Y) {
            return df.format(value / Z) + "Z";
        } else {
            return df.format(value / Y) + "Y";
        }


    }

}
