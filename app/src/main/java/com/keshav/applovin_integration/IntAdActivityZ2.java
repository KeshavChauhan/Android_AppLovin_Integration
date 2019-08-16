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

public class IntAdActivityZ2 extends AppCompatActivity {

    public static final String TAG = IntAdActivityZ2.class.getSimpleName();
    private AppLovinAd loadedAd;
    private AppLovinInterstitialAdDialog intAd;
    private String zoneId = "988c04284a1c2742";

    Button reqInt2, showInt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int_ad_z2);

        intAd = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(this), this);

        reqInt2 = findViewById(R.id.Req_IS_Z2);
        showInt2 = findViewById(R.id.Show_IS_Z2);
        showInt2.setEnabled(false);

        reqInt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppLovinSdk.getInstance(getApplicationContext())
                        .getAdService()
                        .loadNextAdForZoneId(zoneId, new AppLovinAdLoadListener() {
                            @Override
                            public void adReceived(AppLovinAd ad) {
                                Log.d(TAG, "Ad received for IS Z2: " + zoneId);
                                loadedAd = ad;
                                showInt2.setEnabled(true);
                            }

                            @Override
                            public void failedToReceiveAd(int errorCode) {

                            }
                        });
            }
        });


        showInt2.setOnClickListener(new View.OnClickListener() {
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
                showInt2.setEnabled(false);
            }

            @Override
            public void adHidden(AppLovinAd appLovinAd) {
                Log.d("INT2", "Interstitial Hidden");
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
