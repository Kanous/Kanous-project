package com.svauto.fastrvc.rvc.services;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;

import com.svauto.camera.Camera;
import com.svauto.camera.CameraService;
import com.svauto.camera.CameraSetupService;
import com.svauto.camera.OnCameraListener;
import com.svauto.fastrvc.activity.AvmMainActivity;
import com.svauto.fastrvc.message.CarType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class FastRvcService extends Service {
    private final String TAG = this.getClass().getSimpleName();
    CameraService cs;
    CameraSetupService css;
    public static FastRvcService rvcServer;
    public static boolean mFirstBoot = true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "fast com.desay_svautomotivesv.rvc.rvc services has been stared by boradcast of bootcompleted");
        rvcServer = this;
        EventBus.getDefault().register(this);
        cs = Camera.getCameraService();
        cs.setOnCameraListener(new OnCameraListener() {
            @Override
            public void onRvcSignalChange(boolean isRVC) {
                Log.i(TAG, "onRvcSignalChange:" + isRVC + ", mFirstBoot: " + mFirstBoot);
                if (isRVC) {
                    if (mFirstBoot) {
                        Intent intent = new Intent(FastRvcService.this, AvmMainActivity.class);
                        intent.putExtra("mFirstBoot", 0);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        mFirstBoot = false;

                    } else {
                        Intent intent = new Intent(FastRvcService.this, AvmMainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }

                } else {
                    if (AvmMainActivity.rvc != null) {
//                        AvmMainActivity.rvc.finish();
                        AvmMainActivity.rvc = null;
                    } else {
                        Log.i(TAG, "AvmMainActivity rvc is null!");
//                        EventBus.getDefault().post(new CarType(PwType.SOFTKEY_QUIT_AVM));
                    }
                    mFirstBoot = false;
                }
            }
        });
        cs.startService();
        cs.reportBootCompleted();
        Configuration newConfig = getResources().getConfiguration();
        String language = newConfig.locale.getLanguage();
        cs.setLanguage(language);
        css = cs.getSetupService();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showAVM(CarType type) {
//        if (type.getType() == PwType.SOFTKEY_AVM_DISPLAY) {
//
//        }
    }

    private String getTopActivityName() {
        List<ActivityManager.RunningTaskInfo> info;
        ActivityManager am;
        ComponentName topActivity;

        am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        if (am == null)
            return null;

        info = am.getRunningTasks(1);

        if (info == null)
            return null;

        topActivity = info.get(0).topActivity;

        if (topActivity == null)
            return null;
        Log.d(TAG, "FastRvcService getTopActivityName == " + topActivity.getClassName());
        return topActivity.getClassName();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        cs.releaseSetupService();
        Camera.releaseCameraService();
    }

    public int getBrightness() {
        return css.getBrightness();
    }

    public int getContrast() {
        return css.getContrast();
    }

    public int getHue() {
        return css.getHue();
    }

    public int getSaturation() {
        return css.getSaturation();
    }

    public int getMaxBrightness() {
        return css.getMaxBrightness();
    }

    public int getMaxContrast() {
        return css.getMaxContrast();
    }

    public int getMaxHue() {
        return css.getMaxHue();
    }

    public int getMaxSaturation() {
        return css.getMaxSaturation();
    }

    public void setBrightness(int value) {
        css.setBrightness(value);
    }

    public void setContrast(int value) {
        css.setContrast(value);
    }

    public void setHue(int value) {
        css.setHue(value);
    }

    public void setSaturation(int value) {
        css.setSaturation(value);
    }

    public void reset() {
        css.reset();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String language = newConfig.locale.getLanguage();
        Log.i(TAG, "xxxxxx: " + language);
        cs.setLanguage(language);
    }


}
