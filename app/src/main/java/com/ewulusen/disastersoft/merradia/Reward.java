package com.ewulusen.disastersoft.merradia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import br.com.bloder.magic.view.MagicButton;

public class Reward extends AppCompatActivity {
    private RewardedVideoAd mRewardedVideoAd;
    public Button rewBtn;
    public TextView rewtxt;
    public static Intent intent;
    public static String id,idC,seged;
    public static String[] seged_a;
    DatabaseHelper userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        intent = getIntent();
        seged = intent.getStringExtra("datas");
        seged_a=seged.split(",");
        id=seged_a[0];
        idC=seged_a[1];
        userDB = new DatabaseHelper(this);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewBtn=findViewById(R.id.rewardB);
        rewBtn.setEnabled(false);
        rewtxt=findViewById(R.id.rewardTxt);
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {

            @Override
            public void onRewarded(RewardItem rewardItem) {
                rewtxt.setText(getString(R.string.reward_get)+" "+rewardItem.getAmount()+" "+ rewardItem.getType());
            userDB.chesFound(idC,rewardItem.getAmount());
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                   loadRewardedVideoAd();
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int errorCode) {
            rewtxt.setText("Sorry the Add is not loaded");
            }

            @Override
            public void onRewardedVideoCompleted() {

            }

            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }
        });

        loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        AdRequest.Builder adM=new AdRequest.Builder();
            adM.addTestDevice("38A8AA7DB212075D2553B6527A8E588B");
        mRewardedVideoAd.loadAd(getString(R.string.admob_id),
                adM.build());
        rewBtn.setEnabled(true);
        rewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        // showing the ad to user
        showRewardedVideo();
        rewBtn.setEnabled(false);
            }
        });
    }

    private void showRewardedVideo() {
        // make sure the ad is loaded completely before showing it
        if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
        }
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
}
