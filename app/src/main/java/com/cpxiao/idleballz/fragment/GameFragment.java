package com.cpxiao.idleballz.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cpxiao.R;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.gamelib.fragment.BaseZAdsFragment;
import com.cpxiao.idleballz.mode.ItemData;
import com.cpxiao.idleballz.mode.extra.BallsExtra;
import com.cpxiao.idleballz.mode.extra.Extra;
import com.cpxiao.idleballz.views.GameView;

import java.util.List;

/**
 * @author cpxiao on 2017/9/27.
 */

public class GameFragment extends BaseZAdsFragment {
    private float mScore;
    private TextView mScoreTextView;
    private TextView mGameLevelTextView;

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

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Context context = getHoldingActivity();
        mScore = PreferencesUtils.getFloat(context, Extra.Key.SCORE, Extra.Key.SCORE_DEFAULT);
        mDataList = BallsExtra.getDataList(getContext());
        mDataList = BallsExtra.parseList(mScore, mDataList);

        mScoreTextView = (TextView) view.findViewById(R.id.score);
        mScoreTextView.setText(BallsExtra.format2(mScore));

        mGameView = (GameView) view.findViewById(R.id.game_view);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.balls);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);


        NormalRecyclerViewAdapter adapter = new NormalRecyclerViewAdapter(getContext(), mDataList);

        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game;
    }


}
