package com.cpxiao.idleballz.mode.ui;

import com.cpxiao.gamelib.mode.common.Sprite;

/**
 * @author cpxiao on 2017/10/07.
 */

public class UIBase extends Sprite {
    private int colorBg;

    protected UIBase(Build build) {
        super(build);
        this.colorBg = build.colorBg;
    }

    public int getColorBg() {
        return colorBg;
    }

    public void setColorBg(int colorBg) {
        this.colorBg = colorBg;
    }

    public static class Build extends Sprite.Build {
        private int colorBg;

        public UIBase build() {
            return new UIBase(this);
        }

        public Build setColorBg(int colorBg) {
            this.colorBg = colorBg;
            return this;
        }
    }
}
