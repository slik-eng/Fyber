package com.sliksoft.fybertest.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.sliksoft.fybertest.R;
import com.sliksoft.fybertest.eventbus.Events;
import com.sliksoft.fybertest.eventbus.GlobalBus;
import com.sliksoft.fybertest.views.fragments.MainFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private static String sGoogleAdId;
    private static boolean sLimitAdTrackingEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * Initialization
     */
    private void init() {
        setContentView(R.layout.activity_main);
        GlobalBus.getBus().register(this);
        GlobalBus.getBus().post(new Events.GoogleAdvertising());
    }

    /**
     * Call Main fragment
     */
    private void callMainFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame_layout, MainFragment.newInstance(sGoogleAdId, sLimitAdTrackingEnabled)).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        GlobalBus.getBus().unregister(this);
        super.onDestroy();
    }

    /**
     * Get the google ad id value and the tracking limits and set to ui
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onGetAdvertisingIdInfo(Events.GoogleAdvertising googleAdvertising) {
        if (googleAdvertising == null || googleAdvertising.getGoogleAdvertisingId() == null) {
            try {
                googleAdvertising.setGoogleAdvertisingId(AdvertisingIdClient.getAdvertisingIdInfo(this).getId());
                sGoogleAdId = googleAdvertising.getGoogleAdvertisingId();
                sLimitAdTrackingEnabled = AdvertisingIdClient.getAdvertisingIdInfo(this).isLimitAdTrackingEnabled();
                callMainFragment();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
