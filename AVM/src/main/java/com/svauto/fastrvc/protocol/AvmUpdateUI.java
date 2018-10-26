package com.svauto.fastrvc.protocol;

import android.content.Context;

public class AvmUpdateUI {
    private static final String TAG = "AvmUpdateUI";
    private volatile static AvmUpdateUI mInstance;
    private Context mContext;

    public static AvmUpdateUI getInstance() {
        if (null == mInstance) {
            synchronized (AvmUpdateUI.class) {
                if (null == mInstance) {
                    mInstance = new AvmUpdateUI();
                }
            }
        }
        return mInstance;
    }

    public class RVCMessageCommandId{
        public static final int UPDATE_FB_RADAR_DISTANCE = 0;
        public static final int UPDATE_LR_RADAR_DISTANCE = 1;
        public static final int UPDATE_RADAT_STATUS = 2;
        public static final int UPDATE_RADAT_SOUND_STATUS = 3;
        public static final int UPDATE_AVM_1_STATUS = 4;
        public static final int UPDATE_AVM_2_STATUS = 5;
        public static final int CONFIGURE_REVERSE = 6;
        public static final int Finish_RVC = 7;

    }
}
