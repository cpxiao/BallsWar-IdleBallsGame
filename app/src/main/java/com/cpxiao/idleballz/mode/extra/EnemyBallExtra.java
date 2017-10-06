package com.cpxiao.idleballz.mode.extra;

/**
 * @author cpxiao on 2017/09/29.
 */

public final class EnemyBallExtra {

    public static float getEnemyBallMaxValue(int gameLevel) {
        return (float) (4.0F * Math.pow(1.5, gameLevel));
    }


}
