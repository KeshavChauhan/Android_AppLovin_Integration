package com.keshav.applovin_integration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

public class IntAdActivityZ1 extends AppCompatActivity {

    public static final String TAG = IntAdActivityZ1.class.getSimpleName();
    private AppLovinAd loadedAd;
    private AppLovinInterstitialAdDialog intAd;
    private String zoneId = "74b8d1debe8a6f98";

    Button reqInt1, showInt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int_ad_z1);

        intAd = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(this), this);

        reqInt1 = findViewById(R.id.Req_IS_Z1);
        showInt1 = findViewById(R.id.Show_IS_Z1);
        showInt1.setEnabled(false);

        reqInt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppLovinSdk.getInstance(getApplicationContext())
                        .getAdService()
                        .loadNextAdForZoneId(zoneId, new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                Log.d(TAG, "Ad received for IS Z1: " + zoneId);

                                loadedAd = ad;
                                showInt1.setEnabled(true);
                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {

                            }
                        });
            }
        });

        showInt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadedAd != null) {
                    intAd.showAndRender(loadedAd);
                }
            }
        });

        intAd.setAdDisplayListener(new AppLovinAdDisplayListener() {
            @Override
            public void adDisplayed(AppLovinAd appLovinAd) {
                Log.d(TAG, "Interstitial Displayed");
                showInt1.setEnabled(false);
            }

            @Override
            public void adHidden(AppLovinAd appLovinAd) {
                Log.d(TAG, "Interstitial Hidden");
            }
        });

        intAd.setAdClickListener(new AppLovinAdClickListener() {
            @Override
            public void adClicked(AppLovinAd appLovinAd) {
                Log.d(TAG, "Interstitial Clicked");
            }
        });

        intAd.setAdVideoPlaybackListener(new AppLovinAdVideoPlaybackListener() {
            @Override
            public void videoPlaybackBegan(AppLovinAd appLovinAd) {
                Log.d(TAG, "Video Started");
            }

            @Override
            public void videoPlaybackEnded(AppLovinAd appLovinAd, double percentViewed, boolean wasFullyViewed) {
                Log.d(TAG, "Video Ended");
            }
        });
    }
}

