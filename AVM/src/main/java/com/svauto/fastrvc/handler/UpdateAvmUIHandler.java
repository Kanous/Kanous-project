package com.desaysv.rvc.handler;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

public class UpdateAvmUIHandler {

    protected static final String TAG = "UpdateAvmUIHandler";
    private static Handler mHandler = null;
    public static List<Message> Msgs = new ArrayList();

    public static void setUpdateAvmUIHandler(Handler handler) {
        mHandler = handler;
    }

    public static void releaseUpdateAvmUIHandler() {
        mHandler = null;
    }

    public static void sendMessage(int what){  //, int arg) {
        Message msg = Message.obtain();
        msg.what = what;
        //msg.arg1 = arg;
        if (mHandler != null) {
            mHandler.removeMessages(msg.what);
            mHandler.sendMessage(msg);
        } else {
            SaveMegToQuence(msg);
        }
    }

    public static void SaveMegToQuence(Message message) {
        Msgs.add(message);
    }
}