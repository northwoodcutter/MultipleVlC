package com.ambi.multiplevlc;

import com.ambi.multiplevlc.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.media.MediaCodecInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.GridLayout;

public class VideoActivity extends Activity {
    public final static String TAG = "VideoActivity";

    public final static String LOCATION =
            "com.compdigitec.libvlcandroidsample.VideoActivity.location";

    GridLayout mRootVideoContainer = (GridLayout) findViewById(R.id.main_container);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.layout);

        //String video = "android.resource://" + getPackageName() + "/" + R.raw.video;
        // add feed fragment to view
        //this.showFragment(VideoFragment.newInstance(video), R.id.video_container);
        @TargetApi(Build.VERSION_CODES.M)
        int maxSupportedInstances = MediaCodecInfo.CodecCapabilities.getMaxSupportedInstances();
        initPlayerContainers(maxSupportedInstances);
    }

    private void initPlayerContainers(int maxSupportedInstances) {
        String video = "http://playertest.longtailvideo.com/adaptive/bipbop/bipbopall.m3u8";
        for (int i = 0; i < maxSupportedInstances; i++) {
            FrameLayout videoContainer = new FrameLayout(this);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(380, 380);
            videoContainer.setLayoutParams(lp);
            mRootVideoContainer.addView(videoContainer);
            this.showFragment(VideoFragment.newInstance(video), videoContainer.getId());
        }
    }

    protected void showFragment(Fragment newFragment, int container) {
        FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
        transaction.add(container, newFragment);
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

}
