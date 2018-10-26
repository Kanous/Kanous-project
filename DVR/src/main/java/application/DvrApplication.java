package application;

import android.app.Application;

/**
 * Created by uidq0348 on 2018/5/23.
 */

public class DvrApplication extends Application {
    private static DvrApplication mInstance = null;

    public static DvrApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

    }

    private static synchronized void setInstance(DvrApplication Instance) {
        mInstance = Instance;
    }

}
