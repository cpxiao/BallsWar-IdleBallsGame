package com.cpxiao.idleballz.mode;

import com.cpxiao.gamelib.mode.common.Sprite;

/**
 * @author cpxiao on 2017/10/05.
 */

public class UIText extends Sprite {
    protected UIText(Build build) {
        super(build);
    }

    public static class Build extends Sprite.Build {
        public UIText build() {
            return new UIText(this);
        }
    }
}
