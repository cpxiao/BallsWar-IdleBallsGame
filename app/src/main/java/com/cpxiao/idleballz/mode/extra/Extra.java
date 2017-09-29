package com.cpxiao.idleballz.mode.extra;

/**
 * @author cpxiao on 2017/09/29.
 */

public final class Extra {
    public static final class Key {
        public static final String GAME_LEVEL = "GAME_LEVEL";
        public static final int GAME_LEVEL_DEFAULT = 0;

        public static final String SCORE = "SCORE";
        public static final long SCORE_DEFAULT = 1000L;

        private static final String BALL_LEVEL_FORMAT = "BALL_LEVEL_FORMAT_%s";

        public static String getBallLevelKey(int ballLevel) {
            return String.format(BALL_LEVEL_FORMAT, ballLevel);
        }
    }
}

