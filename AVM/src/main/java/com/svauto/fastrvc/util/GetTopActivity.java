package com.svauto.fastrvc.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class GetTopActivity {
    public String getTopActivityName(Context context) {
        List<ActivityManager.RunningTaskInfo> info;
        ActivityManager am;
        ComponentName topActivity;

        am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        if (am == null)
            return null;

        info = am.getRunningTasks(1);

        if (info == null)
            return null;

        topActivity = info.get(0).topActivity;

        if (topActivity == null)
            return null;

        Log.d("GetTopActivity", "getTopActivityName: "+topActivity.getClassName());
        return topActivity.getClassName();
    }
}
