package com.cpxiao.idleballz.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.gamelib.mode.common.Sprite;
import com.cpxiao.gamelib.views.BaseSurfaceViewFPS;
import com.cpxiao.idleballz.mode.Ball;
import com.cpxiao.idleballz.mode.EnemyBall;
import com.cpxiao.idleballz.mode.extra.EnemyBallExtra;
import com.cpxiao.idleballz.mode.extra.Extra;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author cpxiao on 2017/9/27.
 */

public class GameView extends BaseSurfaceViewFPS {

    private int mGameLevel;
    private ConcurrentLinkedQueue<Ball> mBallQueue = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<EnemyBall> mEnemyBallQueue = new ConcurrentLinkedQueue<>();
    private int mCreatedEnemyCount = 0;
    private static final int mMaxEnemyCount = 16;

    private RectF mMovingRangeRectF;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initWidget() {
        Context context = getContext();
        mGameLevel = PreferencesUtils.getInt(context, Extra.Key.GAME_LEVEL, Extra.Key.GAME_LEVEL_DEFAULT);

        setBgColor(ContextCompat.getColor(getContext(), R.color.GameViewBg));

        mMovingRangeRectF = new RectF(0, 0, mViewWidth, mViewHeight);

        mPaint.setTextSize(0.03F * mViewWidth);

        for (int i = 0; i < 26; i++) {
            createBall();
        }
    }

    private synchronized void createBall() {
        float wh = 0.06F * mViewWidth;
        Ball ball = (Ball) new Ball.Build()
                .setW(wh)
                .setH(wh)
                .centerTo(0.5F * mViewWidth, 0.5F * mViewHeight)
                .setSpeedX((float) (10 + Math.random()))
                .setSpeedY(3)
                .setMovingRangeRectF(mMovingRangeRectF)
                .build();
        ball.setEnemyBallQueue(mEnemyBallQueue);
        ball.setPower(10);
        mBallQueue.add(ball);
    }

    private synchronized void createEnemyBall() {
        if (mFrame % 3 != 0) {
            return;
        }
        if (mCreatedEnemyCount >= mMaxEnemyCount) {
            return;
        }

        int indexX = mCreatedEnemyCount / 4;
        int indexY = mCreatedEnemyCount % 4;
        float wh = 0.1F * mViewWidth;
        mCreatedEnemyCount++;

        float cX = (float) (0.5F * wh + (indexX * mViewWidth / 4) + Math.random() * (mViewWidth / 4 - wh));
        float cY = (float) (0.5F * wh + (indexY * mViewHeight / 4) + Math.random() * (mViewHeight / 4 - wh));

        createEnemyBall(wh, cX, cY);
    }

    private synchronized void createEnemyBall(float wh, float cX, float cY) {
        EnemyBall enemyBall = (EnemyBall) new EnemyBall.Build()
                .setW(wh)
                .setH(wh)
                .centerTo(cX, cY)
                .setMovingRangeRectF(mMovingRangeRectF)
                .build();
        enemyBall.setValue(EnemyBallExtra.getEnemyBallValue(mGameLevel + 10));
        mEnemyBallQueue.add(enemyBall);
    }

    @Override
    public void drawCache() {
        drawSpriteQueue(mCanvasCache, mPaint);
    }

    private void drawSpriteQueue(Canvas canvas, Paint paint) {
        for (Sprite sprite : mEnemyBallQueue) {
            sprite.draw(canvas, paint);
        }
        for (Sprite sprite : mBallQueue) {
            sprite.draw(canvas, paint);
        }
    }

    @Override
    protected void timingLogic() {
        createEnemyBall();

        removeDestroyedSprite();

        if (mEnemyBallQueue.isEmpty()) {
            mGameLevel++;
            mCreatedEnemyCount = 0;
        }
    }

    private void removeDestroyedSprite() {
        for (EnemyBall enemyBall : mEnemyBallQueue) {
            if (enemyBall.isDestroyed()) {
                mEnemyBallQueue.remove(enemyBall);
            }
        }
    }

}
