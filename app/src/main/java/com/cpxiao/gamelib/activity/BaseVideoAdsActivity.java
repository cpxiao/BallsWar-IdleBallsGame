package com.cpxiao.gamelib.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * @author cpxiao on 2017/11/02.
 */

public abstract class BaseVideoAdsActivity extends BaseZAdsActivity implements RewardedVideoAdListener {
    private static final String PLACE_ID = "ca-app-pub-4157365005379790/2408405335";
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    public void loadRewardedVideoAd() {
        AdRequest adRequest;
        if (DEBUG) {
            adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)// All emulators
                    .addTestDevice(TEST_DEVICE_ADMOB)
                    .build();
        } else {
            adRequest = new AdRequest.Builder()
                    .build();
        }
        mRewardedVideoAd.loadAd(PLACE_ID, adRequest);
    }

    public boolean showRewardedVideoAd() {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
            return true;
        }
        return false;
    }

    public boolean isRewardedVideoAdLoaded() {
        return mRewardedVideoAd.isLoaded();
    }

    public RewardedVideoAd getRewardedVideoAd() {
        return mRewardedVideoAd;
    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    @Override
    public void onRewarded(RewardItem reward) {
        if (DEBUG) {
            Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
                    reward.getAmount(), Toast.LENGTH_SHORT).show();
            // Reward the user.
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        if (DEBUG) {
            Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRewardedVideoAdClosed() {
        if (DEBUG) {
            Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        if (DEBUG) {
            Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        if (DEBUG) {
            Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRewardedVideoAdOpened() {
        if (DEBUG) {
            Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRewardedVideoStarted() {
        if (DEBUG) {
            Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
        }
    }
}
