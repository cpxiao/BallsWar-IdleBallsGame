package com.cpxiao.idleballz.mode.extra;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.cpxiao.AppConfig;
import com.cpxiao.R;
import com.cpxiao.idleballz.mode.data.SettingsItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cpxiao on 2017/09/27.
 */

public final class SettingsExtra {
    private static final boolean DEBUG = AppConfig.DEBUG;
    private static final String TAG = SettingsExtra.class.getSimpleName();


    private static final int INDEX_TITLE_ID = 0;
    private static final int INDEX_DRAWABLE_ID = 1;

    private static final int[] newBall0 = {R.string.settings_music, R.drawable._0tap};
    private static final int[] newBall1 = {R.string.settings_sound, R.drawable._1};
    private static final int[] newBall2 = {R.string.settings, R.drawable._2};

    private static final int[][] ITEM_ARRAY = {
            newBall0,
            newBall1,
            newBall2
    };

    public static List<SettingsItemData> getDataList(Context context) {
        List<SettingsItemData> list = new ArrayList<>();
        for (int[] item : ITEM_ARRAY) {
            SettingsItemData itemData = new SettingsItemData();
            itemData.title = context.getString(item[INDEX_TITLE_ID]);
            itemData.drawable = ContextCompat.getDrawable(context, item[INDEX_DRAWABLE_ID]);
            list.add(itemData);
        }

        if (DEBUG) {
            Log.d(TAG, "getDataList: list.size() = " + list.size());
        }
        return list;
    }


}
