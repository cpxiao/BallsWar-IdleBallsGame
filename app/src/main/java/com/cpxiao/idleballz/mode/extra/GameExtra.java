package com.cpxiao.idleballz.mode.extra;

import android.util.Log;

import com.cpxiao.AppConfig;

import java.text.DecimalFormat;

/**
 * @author cpxiao on 2017/09/27.
 */

public final class GameExtra {
    private static final boolean DEBUG = AppConfig.DEBUG;
    private static final String TAG = GameExtra.class.getSimpleName();


    /**
     * power:   1,  1,  30,     90,     270,    810     ...
     * price:   11, 1,  300,    1.8k,   10.8k,  64.8k   ...
     */
    public static float getPower(int itemIndex, int itemLevel) {
        float basePower = 0;
        float rate = 1.07F;
        if (itemIndex < 0) {
            if (DEBUG) {
                Log.d(TAG, "getPower: ");
                throw new IllegalArgumentException("itemIndex < 0");
            }
        } else if (itemIndex == 0) {
            basePower = 1;
            rate = 1.1F;
        } else if (itemIndex == 1) {
            basePower = 1;
        } else {
            basePower = (float) (10 * Math.pow(3, itemIndex - 1));
        }
        if (itemLevel <= 0) {
            itemLevel = 1;
        }
        return (float) Math.max(itemLevel, basePower * Math.pow(rate, itemLevel - 1));
    }

    public static float getUpdatePrice(int itemIndex, int itemLevel) {
        float rate = 1.1F;
        float basePrice = 0;
        if (itemIndex < 0) {
            if (DEBUG) {
                Log.d(TAG, "getUpdatePrice: ");
                throw new IllegalArgumentException("itemIndex < 0");
            }
        } else if (itemIndex == 0) {
            basePrice = 11;
            rate = 1.15F;
        } else if (itemIndex == 1) {
            if (itemLevel == 0) {
                basePrice = 1;
            } else {
                basePrice = 10;
            }
        } else {
            basePrice = (float) (300 * Math.pow(6, itemIndex - 2));
        }
        return (float) Math.max(itemLevel, basePrice * Math.pow(rate, itemLevel));
    }



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
    private static final double K = 1000.0;
    private static final double M = 1000000.0;
    private static final double G = 1000000000.0;
    private static final double T = 1000000000000.0;
    private static final double P = 1000000000000000.0;
    private static final double E = 1000000000000000000.0;
    private static final double Z = 1000000000000000000000.0;
    private static final double Y = 1000000000000000000000000.0;

    public static String format1(double price) {
        DecimalFormat df = new DecimalFormat("######0.0");
        return format(price, df);
    }

    public static String format2(double power) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return format(power, df);
    }

    private static String format(double value, DecimalFormat df) {
        if (value < 0) {
            if (DEBUG) {
                Log.d(TAG, "format: value = " + value);
                throw new IllegalArgumentException("value < 0, value = " + value);
            }
            return "";
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
