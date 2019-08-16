package com.keshav.applovin_integration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewDisplayErrorCode;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdk;

public class MainActivity extends AppCompatActivity {

    Button mIntZone1, mIntZone2, mRVZone1, mRVZone2, mBannerRefresh;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppLovinSdk.initializeSdk(this);

        final AppLovinAdView adView = findViewById(R.id.ad_view);
        final Context context = MainActivity.this;


        mIntZone1 = findViewById(R.id.IntAdZone1);
        mIntZone2 = findViewById(R.id.IntAdZone2);
        mRVZone1 = findViewById(R.id.RVAdZone1);
        mRVZone2 = findViewById(R.id.RVAdZone2);
        mBannerRefresh = findViewById(R.id.banner);

        mIntZone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntZ1Intent = new Intent(context, IntAdActivityZ1.class);
                startActivity(IntZ1Intent);
            }
        });

        mIntZone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntZ2Intent = new Intent(context, IntAdActivityZ2.class);
                startActivity(IntZ2Intent);
            }
        });

        mRVZone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RVZ1Intent = new Intent(context, RvAdActivityZ1.class);
                startActivity(RVZ1Intent);
            }
        });

        mRVZone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RVZ2Intent = new Intent(context, RvAdActivityZ2.class);
                startActivity(RVZ2Intent);
            }
        });

        mBannerRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adView.loadNextAd();
            }
        });

        bannerAds(adView);
    }

    private void bannerAds(final AppLovinAdView adView) {
        adView.loadNextAd();
        // Event Listeners
        adView.setAdLoadListener(new AppLovinAdLoadListener() {
            @Override
            public void adReceived(final AppLovinAd ad) {
                Log.d("TAG", "Banner loaded");
            }

            @Override
            public void failedToReceiveAd(final int errorCode) {
                Log.d("TAG", "Banner failed to load with error code " + errorCode);
            }
        });

        adView.setAdDisplayListener(new AppLovinAdDisplayListener() {

            @Override
            public void adDisplayed(AppLovinAd ad) {
                Log.d(TAG, "Banner Displayed");

            }

            @Override
            public void adHidden(AppLovinAd ad) {
                Log.d(TAG, "Banner Hidden");

            }
        });

        adView.setAdClickListener(new AppLovinAdClickListener() {
            @Override
            public void adClicked(final AppLovinAd ad) {
                Log.d(TAG, "Banner Clicked");
                adView.loadNextAd();

            }
        });

        adView.setAdViewEventListener(new AppLovinAdViewEventListener() {
            @Override
            public void adOpenedFullscreen(final AppLovinAd ad, final AppLovinAdView adView) {
                Log.d(TAG, "Banner opened fullscreen");
            }

            @Override
            public void adClosedFullscreen(final AppLovinAd ad, final AppLovinAdView adView) {
                Log.d(TAG, "Banner closed fullscreen");
            }

            @Override
            public void adLeftApplication(final AppLovinAd ad, final AppLovinAdView adView) {
                Log.d(TAG, "Banner left application");
            }

            @Override
            public void adFailedToDisplay(final AppLovinAd ad, final AppLovinAdView adView, final AppLovinAdViewDisplayErrorCode code) {
                Log.d(TAG, "Banner failed to display with error code " + code);
            }
        });
    }
}
