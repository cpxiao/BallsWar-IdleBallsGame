package com.cpxiao.idleballz.mode.extra;

import android.content.Context;
import android.util.Log;

import com.cpxiao.AppConfig;
import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.idleballz.mode.ItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cpxiao on 2017/09/27.
 */

public final class BallsExtra {
    private static final boolean DEBUG = AppConfig.DEBUG;
    private static final String TAG = BallsExtra.class.getSimpleName();


//    private static final int INDEX_STRING_ID = 0;
    public static final int INDEX_DRAWABLE_ID = 1;
    //    private static final int[] tap = {R.string.ball0, R.drawable._0tap};
    //    private static final int[] ball1 = {R.string._1ball, R.drawable._1ball};
    //    private static final int[] ball2 = {R.string._2tennis, R.drawable._2tennis};
    //    private static final int[] ball3 = {R.string._3basketball, R.drawable._3basketball};
    //    private static final int[] ball4 = {R.string._4football, R.drawable._4football};
    //    private static final int[] ball5 = {R.string._5baseball, R.drawable._5baseball};
    //    private static final int[] ball6 = {R.string._6starball, R.drawable._6starball};
    //    private static final int[] ball7 = {R.string._7orange, R.drawable._7orange};
    //    private static final int[] ball8 = {R.string._8watermelon, R.drawable._8watermelon};
    //    private static final int[] ball9 = {R.string._9egg, R.drawable._9egg};
    //    private static final int[] ball10 = {R.string._10cookie, R.drawable._10cookie};
    //    private static final int[] ball11 = {R.string._11donut, R.drawable._11donut};
    //    private static final int[] ball12 = {R.string._12burger, R.drawable._12burger};
    //    private static final int[] ball13 = {R.string._13maki, R.drawable._13maki};
    //    private static final int[] ball14 = {R.string._14vinyl, R.drawable._14vinyl};
    //    private static final int[] ball15 = {R.string._15coin, R.drawable._15coin};
    //    private static final int[] ball16 = {R.string._16eyeball, R.drawable._16eyeball};
    //    private static final int[] ball17 = {R.string._17wheel, R.drawable._17wheel};
    //    private static final int[] ball18 = {R.string._18pacman, R.drawable._18pacman};
    //    private static final int[] ball19 = {R.string._19mine, R.drawable._19mine};
    //    private static final int[] ball20 = {R.string._20radioactive, R.drawable._20radioactive};
    //    private static final int[] ball21 = {R.string._21moon, R.drawable._21moon};
    //    private static final int[] ball22 = {R.string._22earth, R.drawable._22earth};
    //    private static final int[] ball23 = {R.string._23neptune, R.drawable._23neptune};
    //    private static final int[] ball24 = {R.string._24jupiter, R.drawable._24jupiter};
    //    private static final int[] ball25 = {R.string._25atom, R.drawable._25atom};

    private static final int[] newBall0 = {R.string._newBall, R.drawable._0tap};
    private static final int[] newBall1 = {R.string._newBall, R.drawable._1};
    private static final int[] newBall2 = {R.string._newBall, R.drawable._2};
    private static final int[] newBall3 = {R.string._newBall, R.drawable._3};
    private static final int[] newBall4 = {R.string._newBall, R.drawable._4};
    private static final int[] newBall5 = {R.string._newBall, R.drawable._5};
    private static final int[] newBall6 = {R.string._newBall, R.drawable._6};
    private static final int[] newBall7 = {R.string._newBall, R.drawable._7};
    private static final int[] newBall8 = {R.string._newBall, R.drawable._8};
    private static final int[] newBall9 = {R.string._newBall, R.drawable._9};
    private static final int[] newBall10 = {R.string._newBall, R.drawable._10};
    private static final int[] newBall11 = {R.string._newBall, R.drawable._11};
    private static final int[] newBall12 = {R.string._newBall, R.drawable._12};
    private static final int[] newBall13 = {R.string._newBall, R.drawable._13};
    private static final int[] newBall14 = {R.string._newBall, R.drawable._14};
    private static final int[] newBall15 = {R.string._newBall, R.drawable._15};
    private static final int[] newBall16 = {R.string._newBall, R.drawable._16};
    private static final int[] newBall17 = {R.string._newBall, R.drawable._17};
    private static final int[] newBall18 = {R.string._newBall, R.drawable._18};
    private static final int[] newBall19 = {R.string._newBall, R.drawable._19};
    private static final int[] newBall20 = {R.string._newBall, R.drawable._20};
    private static final int[] newBall21 = {R.string._newBall, R.drawable._21};
    private static final int[] newBall22 = {R.string._newBall, R.drawable._22};
    private static final int[] newBall23 = {R.string._newBall, R.drawable._23};
    private static final int[] newBall24 = {R.string._newBall, R.drawable._24};
    private static final int[] newBall25 = {R.string._newBall, R.drawable._25};
    private static final int[] newBall26 = {R.string._newBall, R.drawable._26};
    private static final int[] newBall27 = {R.string._newBall, R.drawable._27};
    private static final int[] newBall28 = {R.string._newBall, R.drawable._28};
    private static final int[] newBall29 = {R.string._newBall, R.drawable._29};
    private static final int[] newBall30 = {R.string._newBall, R.drawable._30};

    private static final int[][] ITEM_ARRAY = {
            //            tap,
            //            ball1, ball2, ball3, ball4, ball5, ball6, ball7, ball8, ball9, ball10,
            //            ball11, ball12, ball13, ball14, ball15, ball16, ball17, ball18, ball19, ball20,
            //            ball21, ball22, ball23, ball24, ball25
            //
            newBall0,
            newBall1, newBall2, newBall3, newBall4, newBall5, newBall6, newBall7, newBall8, newBall9, newBall10,
            newBall11, newBall12, newBall13, newBall14, newBall15, newBall16, newBall17, newBall18, newBall19, newBall20,
            newBall21, newBall22, newBall23, newBall24, newBall25, newBall26, newBall27, newBall28, newBall29, newBall30,
    };

    public static int getItemCount() {
        return ITEM_ARRAY.length;
    }

    public static int[] getItem(int index) {
        return ITEM_ARRAY[index];
    }

    public static List<ItemData> parseList(float coin, List<ItemData> mDataList) {
        for (int i = 0; i < mDataList.size(); i++) {
            ItemData data = mDataList.get(i);
            data.isCanBeUpdated = coin >= data.updatePrice;
        }
        return mDataList;
    }

    public static int getUpdateCount(float coin, List<ItemData> mDataList) {
        int count = 0;
        for (int i = 0; i < mDataList.size(); i++) {
            ItemData data = mDataList.get(i);
            if (coin >= data.updatePrice) {
                count++;
            }
        }
        return count;
    }

    public static List<ItemData> getDataList(Context context) {
        List<ItemData> list = new ArrayList<>();
        for (int i = 0; i < ITEM_ARRAY.length; i++) {
            int[] item = ITEM_ARRAY[i];
            ItemData itemData = new ItemData();
//            itemData.title = context.getString(item[INDEX_STRING_ID]);
            int defaultLevel;
            if (i == 0) {
                defaultLevel = 1;
            } else {
                defaultLevel = 0;
            }
            itemData.level = PreferencesUtils.getInt(context, Extra.Key.getItemLevelKey(i), defaultLevel);
            itemData.resId = item[INDEX_DRAWABLE_ID];
            itemData.power = getPower(i, itemData.level);
            itemData.updatePrice = getUpdatePrice(i, itemData.level);
            list.add(itemData);
        }

        if (DEBUG) {
            Log.d(TAG, "getDataList: list.size() = " + list.size());
        }
        return list;
    }

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


}
