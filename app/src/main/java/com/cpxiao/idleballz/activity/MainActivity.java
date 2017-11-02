package com.cpxiao.idleballz.activity;

import android.os.Bundle;

import com.cpxiao.gamelib.activity.BaseVideoAdsActivity;
import com.cpxiao.gamelib.fragment.BaseFragment;
import com.cpxiao.idleballz.fragment.GameFragment;
import com.cpxiao.zads.ZAdManager;
import com.cpxiao.zads.core.ZAdPosition;

public class MainActivity extends BaseVideoAdsActivity {

    @Override
    protected BaseFragment getFirstFragment() {
//        return HomeFragment.newInstance(null);
        return GameFragment.newInstance(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZAdManager.getInstance().init(getApplicationContext());
        loadAds();
    }

    private void loadAds() {
        initAdMobAds100("ca-app-pub-4157365005379790/2632993970");
        initFbAds90("2090519740961758_2090523687628030");
        loadZAds(ZAdPosition.POSITION_MAIN);
    }

    @Override
    public void onDestroy() {
        ZAdManager.getInstance().destroyAllPosition(this);
        super.onDestroy();
    }
}
