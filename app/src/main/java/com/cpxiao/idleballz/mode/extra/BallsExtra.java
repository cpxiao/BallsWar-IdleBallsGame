package com.cpxiao.idleballz.mode.extra;

import android.content.Context;
import android.util.Log;

import com.cpxiao.AppConfig;
import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.idleballz.mode.ItemData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cpxiao on 2017/09/27.
 */

public final class BallsExtra {
    private static final boolean DEBUG = AppConfig.DEBUG;
    private static final String TAG = BallsExtra.class.getSimpleName();


    private static final int[] ball0 = {R.string.ball0, R.drawable.ball0tap};
    private static final int[] ball1 = {R.string.ball1, R.drawable.ball1};
    private static final int[] ball2 = {R.string.ball2, R.drawable.ball2};
    private static final int[] ball3 = {R.string.ball3, R.drawable.ball3};
    private static final int[] ball4 = {R.string.ball4, R.drawable.ball4};
    private static final int[] ball5 = {R.string.ball5, R.drawable.ball5};
    private static final int[] ball6 = {R.string.ball6, R.drawable.ball6};
    private static final int[] ball7 = {R.string.ball7, R.drawable.ball7};
    private static final int[] ball8 = {R.string.ball8, R.drawable.ball8};
    private static final int[] ball9 = {R.string.ball9, R.drawable.ball9};
    private static final int[] ball10 = {R.string.ball10, R.drawable.ball10};
    private static final int[] ball11 = {R.string.ball11, R.drawable.ball11};
    private static final int[] ball12 = {R.string.ball12, R.drawable.ball12};
    private static final int[] ball13 = {R.string.ball13, R.drawable.ball13};
    private static final int[] ball14 = {R.string.ball14, R.drawable.ball14};
    private static final int[] ball15 = {R.string.ball15, R.drawable.ball15};
    private static final int[] ball16 = {R.string.ball16, R.drawable.ball16};
    private static final int[] ball17 = {R.string.ball17, R.drawable.ball17};
    private static final int[] ball18 = {R.string.ball18, R.drawable.ball18};
    private static final int[] ball19 = {R.string.ball19, R.drawable.ball19};
    private static final int[] ball20 = {R.string.ball20, R.drawable.ball20};
    private static final int[] ball21 = {R.string.ball21, R.drawable.ball21};
    private static final int[] ball22 = {R.string.ball22, R.drawable.ball22};
    private static final int[] ball23 = {R.string.ball23, R.drawable.ball23};
    private static final int[] ball24 = {R.string.ball24, R.drawable.ball24};
    private static final int[] ball25 = {R.string.ball25, R.drawable.ball25};
    private static final int[] ball26 = {R.string.ball26, R.drawable.ball26};

    private static final int[][] balls = {
            ball1, ball2, ball3, ball4, ball5, ball6, ball7, ball8, ball9, ball10,
            ball11, ball12, ball13, ball14, ball15, ball16, ball17, ball18, ball19, ball20,
            ball21, ball22, ball23, ball24, ball25
    };

    public static List<ItemData> parseList(float mScore, List<ItemData> mDataList) {
        for (int i = 0; i < mDataList.size(); i++) {
            ItemData data = mDataList.get(i);
            data.isCanBeUpdated = mScore >= data.updatePrice;
        }
        return mDataList;
    }

    public static List<ItemData> getDataList(Context context) {
        List<ItemData> list = new ArrayList<>();
        ItemData tap = new ItemData();
        tap.title = context.getString(ball0[0]);
        tap.level = PreferencesUtils.getInt(context, Extra.Key.getBallLevelKey(-1), 0);
        tap.resId = ball0[1];
        tap.power = (float) Math.max(tap.level, 1 * Math.pow(1.1, tap.level - 1));
        tap.updatePrice = (float) Math.max(tap.level, 10 * Math.pow(1.15, tap.level));
        list.add(tap);


        for (int i = 0; i < balls.length; i++) {
            double basePower = 1;
            double basePrice = 1;
            if (i > 0) {
                basePower = 10 * Math.pow(3, i);
                basePrice = 300 * Math.pow(6, i - 1);
            }
            int[] ball = balls[i];
            ItemData itemData = new ItemData();
            itemData.title = context.getString(ball[0]);
            itemData.level = PreferencesUtils.getInt(context, Extra.Key.getBallLevelKey(i), 0);
            itemData.resId = ball[1];
            itemData.power = (float) Math.max(itemData.level, basePower * Math.pow(1.07, itemData.level - 1));
            itemData.updatePrice = (float) Math.max(itemData.level, basePrice * Math.pow(1.1, itemData.level - 1));
            list.add(itemData);
        }

        if (DEBUG) {
            Log.d(TAG, "getDataList: list.size() = " + list.size());
        }
        return list;
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
