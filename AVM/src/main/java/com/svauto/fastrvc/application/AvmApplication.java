package com.svauto.fastrvc.application;

import android.app.Application;

/**
 * Created by uidq0348 on 2018/5/23.
 */

public class AvmApplication extends Application {
    private static AvmApplication mInstance = null;

    public static AvmApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

    }

    private static synchronized void setInstance(com.svauto.fastrvc.application.AvmApplication Instance) {
        mInstance = Instance;
    }

}
