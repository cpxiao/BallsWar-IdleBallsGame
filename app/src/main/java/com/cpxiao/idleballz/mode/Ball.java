package com.cpxiao.idleballz.mode;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.cpxiao.gamelib.mode.common.Sprite;
import com.cpxiao.gamelib.mode.common.SpriteControl;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author cpxiao on 2017/9/27.
 */

public class Ball extends Sprite {
    protected ConcurrentLinkedQueue<EnemyBall> mEnemyBallQueue;

    private float power;

    public void setPower(float power) {
        this.power = power;
    }

    public void setEnemyBallQueue(ConcurrentLinkedQueue<EnemyBall> enemyBallQueue) {
        mEnemyBallQueue = enemyBallQueue;
    }

    protected Ball(Build build) {
        super(build);
    }

    @Override
    protected void beforeDraw(Canvas canvas, Paint paint) {
        super.beforeDraw(canvas, paint);

        if (DEBUG) {
            if (mEnemyBallQueue != null) {
                Log.d(TAG, "beforeDraw: " + mEnemyBallQueue.size());
            }
        }
        //判断移动范围, 到达边界后反弹
        RectF movingRangeRectF = getMovingRangeRectF();
        if (movingRangeRectF != null) {
            RectF rectF = getSpriteRectF();
            if (rectF.left <= movingRangeRectF.left || rectF.right >= movingRangeRectF.right) {
                setSpeedX(-getSpeedX());
            }
            if (rectF.top <= movingRangeRectF.top || rectF.bottom >= movingRangeRectF.bottom) {
                setSpeedY(-getSpeedY());
            }
        }

        checkCollided(this);
    }

    private synchronized void checkCollided(Sprite ball) {
        if (ball.isDestroyed()) {
            return;
        }
        for (EnemyBall enemyBall : mEnemyBallQueue) {
            if (enemyBall.isDestroyed()) {
                continue;
            }
            if (SpriteControl.isTwoCircleSpriteCollided(ball, enemyBall)) {
                //reset ball speed, 注意向量角度
                float startAngle = SpriteControl.getAngleByVector(-ball.getSpeedX(), ball.getSpeedY());
                float baseAngle = SpriteControl.getAngleByVector(ball.getCenterX() - enemyBall.getCenterX()
                        , -(ball.getCenterY() - enemyBall.getCenterY()));
                float endAngle = (720 + 2 * baseAngle - startAngle) % 360;

                float delta = Math.abs(startAngle - baseAngle);
                if (delta >= 90 && delta <= 270) {
                    continue;
                }
                if (DEBUG) {
                    Log.d(TAG, "setupBalls: start = " + startAngle
                            + ", base = " + baseAngle
                            + ", end = " + endAngle
                            + ", delta = " + delta);
                }
                enemyBall.deleteValue(power);
                float speed = (float) Math.sqrt(Math.pow(ball.getSpeedX(), 2) + Math.pow(ball.getSpeedY(), 2));

                float speedX = (float) (speed * Math.cos(Math.toRadians(endAngle)));
                float speedY = -(float) (speed * Math.sin(Math.toRadians(endAngle)));
                ball.setSpeedX(speedX);
                ball.setSpeedY(speedY);
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        super.onDraw(canvas, paint);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(getCenterX(), getCenterY(), 0.5F * getWidth(), paint);
    }

    public static class Build extends Sprite.Build {
        public Ball build() {
            return new Ball(this);
        }
    }
}
