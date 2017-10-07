package com.cpxiao.idleballz.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.gamelib.fragment.BaseZAdsFragment;
import com.cpxiao.idleballz.NormalRecyclerViewAdapter;
import com.cpxiao.idleballz.OnGameListener;
import com.cpxiao.idleballz.OnItemClicked;
import com.cpxiao.idleballz.mode.ItemData;
import com.cpxiao.idleballz.mode.extra.BallsExtra;
import com.cpxiao.idleballz.mode.extra.Extra;
import com.cpxiao.idleballz.views.GameView;

import java.util.List;

/**
 * @author cpxiao on 2017/9/27.
 */

public class GameFragment extends BaseZAdsFragment {
    private NormalRecyclerViewAdapter mAdapter;
    private GameView mGameView;
    private RecyclerView mRecyclerView;
    private List<ItemData> mDataList;

    public static GameFragment newInstance(Bundle bundle) {
        GameFragment fragment = new GameFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    int updateCount = 0;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        final Context context = getHoldingActivity();
        mDataList = BallsExtra.getDataList(context);

        mGameView = (GameView) view.findViewById(R.id.game_view);
        mGameView.setOnGameListener(new OnGameListener() {
            @Override
            public void onCoinChange(float coin) {
                int currentUpdateCount = BallsExtra.getUpdateCount(coin, mDataList);
                if (updateCount != currentUpdateCount) {
                    mDataList = BallsExtra.parseList(coin, mDataList);
                    mGameView.post(mRunnable);
                    updateCount = currentUpdateCount;
                }

            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.ballsRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);


        mAdapter = new NormalRecyclerViewAdapter(getContext(), mDataList);
        mAdapter.setOnItemClicked(new OnItemClicked() {
            @Override
            public void onItemClicked(int index, float price, int level) {
                if (DEBUG) {
                    Log.d(TAG, "onItemClicked: ");
                }
                mAdapter.notifyItemChanged(index);
                mGameView.deleteCoin(price);
                mGameView.createNewBall(context, index);
                PreferencesUtils.putInt(context, Extra.Key.getItemLevelKey(index), level);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mGameView != null) {
            mGameView.save(getHoldingActivity());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game;
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }
    };

}
