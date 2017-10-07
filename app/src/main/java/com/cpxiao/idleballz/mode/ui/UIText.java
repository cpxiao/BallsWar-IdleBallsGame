package com.cpxiao.idleballz.mode.ui;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author cpxiao on 2017/10/05.
 */

public class UIText extends UIBase {
    private int textColor;
    private float textSize;
    private String text = "";

    protected UIText(Build build) {
        super(build);
        this.textColor = build.textColor;
        this.textSize = build.textSize;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        super.onDraw(canvas, paint);
        paint.setColor(getColorBg());
        canvas.drawRect(getSpriteRectF(), paint);

        paint.setColor(textColor);
        paint.setTextSize(textSize);
        canvas.drawText(text, getCenterX(), getCenterY(), paint);

    }

    public static class Build extends UIBase.Build {
        private int textColor;
        private float textSize;

        public UIText build() {
            return new UIText(this);
        }

        public Build setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Build setTextSize(float textSize) {
            this.textSize = textSize;
            return this;
        }
    }
}
