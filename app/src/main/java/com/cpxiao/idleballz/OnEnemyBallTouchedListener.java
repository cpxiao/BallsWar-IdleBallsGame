package com.cpxiao.idleballz;

/**
 * @author cpxiao on 2017/09/29.
 */

public interface OnEnemyBallTouchedListener {
    void onEnemyBallTouchedListener(float score);

    void onLevelUp(int gameLevel);
}
