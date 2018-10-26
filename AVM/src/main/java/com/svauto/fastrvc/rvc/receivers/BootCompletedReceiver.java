package com.svauto.fastrvc.rvc.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.svauto.fastrvc.rvc.services.FastRvcService;


public class BootCompletedReceiver extends BroadcastReceiver {
    private final String TAG = this.getClass().getSimpleName();
	@Override
	public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onReceive() Action = " + action);
        if (action.equals("android.intent.action.BOOT_COMPLETED") || action.equals("android.intent.action.SV_RVC_BOOT_COMPLETED")) {
            Log.i(TAG, "I have received broadcast of boot completed");
            Intent it = new Intent(context, FastRvcService.class);
            context.startService(it);
        }
	}

}
