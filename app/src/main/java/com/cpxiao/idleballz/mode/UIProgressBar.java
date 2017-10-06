package com.cpxiao.idleballz.mode;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.cpxiao.gamelib.mode.common.Sprite;

/**
 * @author cpxiao on 2017/10/04.
 */

public class UIProgressBar extends Sprite {
    private int colorBG;
    private int colorProgress;

    /**
     * 0-100
     */
    private float progress;

    private RectF mProgressRectF = new RectF();

    protected UIProgressBar(Build build) {
        super(build);
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
        if (progress < 0) {
            progress = 0;
        }
        if (progress > 100) {
            progress = 100;
        }
        float rXY = 0.5F * getHeight();
        paint.setColor(colorBG);
        canvas.drawRoundRect(getSpriteRectF(), rXY, rXY, paint);
        paint.setColor(colorProgress);
        canvas.drawRoundRect(getProgressRectF(), rXY, rXY, paint);


    }

    public static class Build extends Sprite.Build {
        public UIProgressBar build() {
            return new UIProgressBar(this);
        }
    }
}
