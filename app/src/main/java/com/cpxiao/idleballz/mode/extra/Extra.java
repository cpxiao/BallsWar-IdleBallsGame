package com.cpxiao.idleballz.mode.extra;

/**
 * @author cpxiao on 2017/09/29.
 */

public final class Extra {
    public static final class Key {

        /**
         * 金钱
         */
        public static final String COIN = "COIN";
        public static final float COIN_DEFAULT = 1000F;
//        public static final float COIN_DEFAULT = 1000000000000000000F;

        /**
         * 关卡
         */
        public static final String GAME_LEVEL = "GAME_LEVEL";
        public static final int GAME_LEVEL_DEFAULT = 1;

        /**
         * enemy ball 的value，坐标位置(x,y)
         */
        private static final String ENEMY_BALL_ITEM_LEVEL_FORMAT = "ITEM_LEVEL_FORMAT_%s";

        public static String getEnemyBallItemLevelFormat(int index) {
            return String.format(ENEMY_BALL_ITEM_LEVEL_FORMAT, index);
        }

        /**
         * balls的等级
         */
        private static final String ITEM_LEVEL_FORMAT = "ITEM_LEVEL_FORMAT_%s";

        public static String getItemLevelKey(int itemIndex) {
            return String.format(ITEM_LEVEL_FORMAT, itemIndex);
        }


    }
}

