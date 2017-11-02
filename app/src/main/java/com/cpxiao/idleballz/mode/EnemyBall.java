package com.cpxiao.idleballz.mode;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.cpxiao.gamelib.mode.common.Sprite;
import com.cpxiao.idleballz.mode.extra.GameExtra;

import hugo.weaving.DebugLog;

/**
 * @author cpxiao on 2017/9/27.
 */

public class EnemyBall extends Sprite {

    private float value;

    protected EnemyBall(Build build) {
        super(build);
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void deleteValue(float value) {
        this.value -= value;
        if (this.value < 1) {
            this.value = 0;
            destroy();
        }
    }


    @DebugLog
    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        super.onDraw(canvas, paint);
        float r = (float) (0.5F * getWidth() + 0.02F * getWidth() * Math.sin(1.0F * getFrame() / 10));
        paint.setColor(Color.GREEN);
        paint.setAlpha(144);
        canvas.drawCircle(getCenterX(), getCenterY(), 1.2F * r, paint);

        paint.setColor(Color.GREEN);
        paint.setAlpha(255);
        canvas.drawCircle(getCenterX(), getCenterY(), r, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(0.6F * r);
        canvas.drawText(GameExtra.format1(value), getCenterX(), getCenterY() + 0.1F * getHeight(), paint);
    }


    public static class Build extends Sprite.Build {
        public EnemyBall build() {
            return new EnemyBall(this);
        }
    }

}
