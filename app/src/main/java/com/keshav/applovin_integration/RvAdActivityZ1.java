package com.keshav.applovin_integration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;

import java.util.Map;

public class RvAdActivityZ1 extends AppCompatActivity {

    public static final String TAG = RvAdActivityZ1.class.getSimpleName();
    AppLovinIncentivizedInterstitial mRVAd1;
    Button reqRV1, showRV1;
    private String zoneId = "1588d1dc866147b0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_ad_z1);

        mRVAd1 = AppLovinIncentivizedInterstitial.create(zoneId, AppLovinSdk.getInstance(this));

        reqRV1 = findViewById(R.id.Req_RV_Z1);
        showRV1 = findViewById(R.id.Show_RV_Z1);
        showRV1.setEnabled(false);

        reqRV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRV1.setEnabled(false);
                mRVAd1.preload(new AppLovinAdLoadListener() {
                    @Override
                    public void adReceived(AppLovinAd appLovinAd) {
                        showRV1.setEnabled(true);
                        Log.d(TAG, "Ad received for RV Z1:" + zoneId);
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode) {
                        Log.d(TAG, "Rewarded video failed to load with error code " + errorCode);
                    }
                });
            }
        });

        showRV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRV1.setEnabled(false);
                // Event Listeners
                AppLovinAdRewardListener adRewardListener = new AppLovinAdRewardListener() {
                    @Override
                    public void userRewardVerified(AppLovinAd appLovinAd, Map map) {

                        String currencyName = (String) map.get("currency");
                        String amountGivenString = (String) map.get("amount");
                        Log.d(TAG, "Rewarded " + amountGivenString + " " + currencyName);

                    }

                    @Override
                    public void userOverQuota(AppLovinAd appLovinAd, Map map) {
                        Log.d(TAG, "Reward validation request exceeded quota with response: " + map);
                    }

                    @Override
                    public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
                        Log.d(TAG, "Reward validation request was rejected with response: " + map);
                    }

                    @Override
                    public void validationRequestFailed(AppLovinAd appLovinAd, int responseCode) {
                        if (responseCode == AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO) {

                        } else if (responseCode == AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT || responseCode == AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR) {

                        } else if (responseCode == AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED) {

                        }
                        Log.d(TAG, "Reward validation request failed with error code: " + responseCode);
                    }

                    @Override
                    public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
                        Log.d("TAG", "User declined to view ad");
                    }
                };

                // Video Playback Listener
                AppLovinAdVideoPlaybackListener adVideoPlaybackListener = new AppLovinAdVideoPlaybackListener() {
                    @Override
                    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
                        Log.d(TAG, "Video Started");
                    }

                    @Override
                    public void videoPlaybackEnded(AppLovinAd appLovinAd, double percentViewed, boolean fullyWatched) {
                        Log.d(TAG, "Video Ended");
                    }
                };

                // Ad Dispaly Listener
                AppLovinAdDisplayListener adDisplayListener = new AppLovinAdDisplayListener() {
                    @Override
                    public void adDisplayed(AppLovinAd appLovinAd) {
                        Log.d(TAG, "Ad Displayed");
                    }

                    @Override
                    public void adHidden(AppLovinAd appLovinAd) {
                        Log.d(TAG, "Ad Dismissed");
                    }
                };

                // Ad Click Listener
                AppLovinAdClickListener adClickListener = new AppLovinAdClickListener() {
                    @Override
                    public void adClicked(AppLovinAd appLovinAd) {
                        Log.d(TAG, "Ad Clicked");
                    }
                };

                mRVAd1.show(RvAdActivityZ1.this, adRewardListener, adVideoPlaybackListener, adDisplayListener, adClickListener);
            }
        });
    }
}
