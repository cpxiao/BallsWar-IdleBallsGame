package com.cpxiao.idleballz.mode.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * @author cpxiao on 2017/10/04.
 */

public class UIProgressBar extends UIBase {
    private int colorProgress;

    /**
     * 0-100
     */
    private float progress;

    private RectF mProgressRectF = new RectF();

    protected UIProgressBar(Build build) {
        super(build);
        this.progress = build.progress;
        this.colorProgress = build.colorProgress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public float getProgress() {
        return progress;
    }

    public RectF getProgressRectF() {
        RectF rectF = getSpriteRectF();

        mProgressRectF.left = rectF.left;
        mProgressRectF.top = rectF.top;
        mProgressRectF.bottom = rectF.bottom;
        mProgressRectF.right = mProgressRectF.left + progress * rectF.width() / 100;

        return mProgressRectF;
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        super.onDraw(canvas, paint);
        progress = Math.max(progress, 100 * getHeight() / getWidth());
        if (progress < 0) {
            progress = 0;
        }
        if (progress > 100) {
            progress = 100;
        }
        float rXY = 0.5F * getHeight();
        paint.setColor(getColorBg());
        canvas.drawRoundRect(getSpriteRectF(), rXY, rXY, paint);
        paint.setColor(colorProgress);
        canvas.drawRoundRect(getProgressRectF(), rXY, rXY, paint);

    }

    public static class Build extends UIBase.Build {
        private float progress;
        private int colorProgress;

        public UIProgressBar build() {
            return new UIProgressBar(this);
        }

        public Build setProgress(float progress) {
            this.progress = progress;
            return this;
        }

        public Build setColorProgress(int colorProgress) {
            this.colorProgress = colorProgress;
            return this;
        }

    }
}
