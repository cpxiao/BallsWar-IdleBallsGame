package com.cpxiao.idleballz.mode;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.cpxiao.gamelib.mode.common.Sprite;
import com.cpxiao.gamelib.mode.common.SpriteControl;
import com.cpxiao.idleballz.views.GameView;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author cpxiao on 2017/9/27.
 */

public class Ball extends Sprite {
    protected ConcurrentLinkedQueue<EnemyBall> mEnemyBallQueue;
    private GameView mGameView;

    private float power;
    private Bitmap[] mBitmapArray;
    private Bitmap mCurrentBitmap;

    protected Ball(Build build) {
        super(build);
        this.mBitmapArray = build.mBitmapArray;
    }


    public void setPower(float power) {
        this.power = power;
    }

    public void setEnemyBallQueue(ConcurrentLinkedQueue<EnemyBall> enemyBallQueue) {
        mEnemyBallQueue = enemyBallQueue;
    }

    public void setGameView(GameView gameView) {
        mGameView = gameView;
    }

    public Bitmap getCurrentBitmap() {
        return mCurrentBitmap;
    }

    /**
     * 发生碰撞（撞墙或者撞球）后重置当前bitmap
     */
    public void setCurrentBitmap() {
        if (mBitmapArray == null || mBitmapArray.length < 1) {
            return;
        }
        mCurrentBitmap = mBitmapArray[(int) ((getFrame()) % mBitmapArray.length)];
    }

    @Override
    protected void beforeDraw(Canvas canvas, Paint paint) {
        super.beforeDraw(canvas, paint);

        //判断移动范围, 到达边界后反弹
        RectF movingRangeRectF = getMovingRangeRectF();
        if (movingRangeRectF != null) {
            RectF rectF = getSpriteRectF();
            if (rectF.left <= movingRangeRectF.left || rectF.right >= movingRangeRectF.right) {
                setSpeedX(-getSpeedX());
                setCurrentBitmap();
            }
            if (rectF.top <= movingRangeRectF.top || rectF.bottom >= movingRangeRectF.bottom) {
                setSpeedY(-getSpeedY());
                setCurrentBitmap();
            }
        }

        checkCollided(this);

    }

    private Rect mBitmapRect = new Rect();

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        super.onDraw(canvas, paint);

        Bitmap bitmap = getCurrentBitmap();
        if (bitmap != null) {
            mBitmapRect.left = 0;
            mBitmapRect.right = bitmap.getWidth();
            mBitmapRect.top = 0;
            mBitmapRect.bottom = bitmap.getHeight();
            canvas.drawBitmap(bitmap, mBitmapRect, getSpriteRectF(), paint);
        } else {
            if (DEBUG) {
//                paint.setColor(Color.BLUE);
                canvas.drawCircle(getCenterX(), getCenterY(), 0.5F * getWidth(), paint);
            }
        }
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
                setCurrentBitmap();

                //reset ball speed, 注意向量角度
                float startAngle = SpriteControl.getAngleByVector(-ball.getSpeedX(), ball.getSpeedY());
                float baseAngle = SpriteControl.getAngleByVector(ball.getCenterX() - enemyBall.getCenterX()
                        , -(ball.getCenterY() - enemyBall.getCenterY()));
                float endAngle = (720 + 2 * baseAngle - startAngle) % 360;

                float delta = Math.abs(startAngle - baseAngle);
                if (delta >= 90 && delta <= 270) {
                    continue;
                }
                if (false && DEBUG) {
                    Log.d(TAG, "setupBalls: start = " + startAngle
                            + ", base = " + baseAngle
                            + ", end = " + endAngle
                            + ", delta = " + delta);
                }
                float value = Math.min(power, enemyBall.getValue());
                enemyBall.deleteValue(value);
                if (mGameView != null) {
                    mGameView.addCoin(value);
                }

                float speed = (float) Math.sqrt(Math.pow(ball.getSpeedX(), 2) + Math.pow(ball.getSpeedY(), 2));

                float speedX = (float) (speed * Math.cos(Math.toRadians(endAngle)));
                float speedY = -(float) (speed * Math.sin(Math.toRadians(endAngle)));
                ball.setSpeedX(speedX);
                ball.setSpeedY(speedY);
            }
        }
    }

    public static class Build extends Sprite.Build {


        private Bitmap[] mBitmapArray;

        @Override
        public Build setBitmap(Bitmap bitmap) {
            if (bitmap != null) {
                int count = 4;
                mBitmapArray = new Bitmap[count];
                for (int i = 0; i < count; i++) {
                    Bitmap newBitmap = rotatingImageView(i * 360 / count, bitmap);
                    mBitmapArray[i] = newBitmap;
                }
            }
            return this;
        }

        public Ball build() {
            return new Ball(this);
        }

        /**
         * 旋转图片
         *
         * @param angle  旋转角度
         * @param bitmap 要处理的Bitmap
         * @return 处理后的Bitmap
         */
        public Bitmap rotatingImageView(int angle, Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            // 旋转图片 动作
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            // 创建新的图片
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            //            if (resizedBitmap != bitmap && !bitmap.isRecycled()) {
            //                bitmap.recycle();
            //                bitmap = null;
            //            }
            return resizedBitmap;
        }
    }
}
