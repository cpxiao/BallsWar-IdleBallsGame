package com.cpxiao.idleballz.mode;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.cpxiao.gamelib.mode.common.Sprite;
import com.cpxiao.idleballz.mode.extra.GameExtra;

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

    public void deleteValue(float value) {
        this.value -= value;
        if (this.value <= 0) {
            this.value = 0;
            destroy();
        }
    }


    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        super.onDraw(canvas, paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(getCenterX(), getCenterY(), 0.5F * getWidth(), paint);

        paint.setColor(Color.BLACK);
        canvas.drawText(GameExtra.format1(value), getCenterX(), getCenterY() + 0.1F * getHeight(), paint);
    }


    public static class Build extends Sprite.Build {
        public EnemyBall build() {
            return new EnemyBall(this);
        }
    }

}
