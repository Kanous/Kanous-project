package com.svauto.fastrvc.manager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.svauto.fastrvc.message.PwType;


public class PopupWindowManager {
    private final String TAG = this.getClass().getSimpleName();
    private PopupWindow popupWindow;
    private static PopupWindowManager mInstance;
    private int[] location = new int[2];
    private final int PW_DISMISS_TIMER = 0;
    private Handler handler;

    public boolean isPwShowing() {
        Log.d(TAG, "isPwShowing: "+isPwShowing);
        return isPwShowing;
    }

    public void setPwShowing(boolean pwShowing) {
        Log.d(TAG, "setPwShowing: "+pwShowing);
        isPwShowing = pwShowing;
    }

    private boolean isPwShowing = false;

    public static PopupWindowManager getInstance() {
        if (null == mInstance) {
            synchronized (PopupWindowManager.class) {
                if (null == mInstance) {
                    mInstance = new PopupWindowManager();
                }
            }
        }
        return mInstance;
    }

    private PopupWindowManager() {
        if (popupWindow == null) {
            popupWindow = new PopupWindow();
            handler = new Handler();
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setOutsideTouchable(true);
            popupWindow.setTouchable(true);

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    Log.d(TAG, "onDismiss: 111111");
                    if(handler != null){
                        handler.removeCallbacks(dismissRunnable);
                    }
                    setPwShowing(false);
//                    EventBus.getDefault().post(new CarType(PwType.ALL_BTN_GREY));

                }
            });
        }
    }

    public PopupWindow getPopupWindow() {
        if (popupWindow == null) {
            new PopupWindowManager();
        }
        return popupWindow;
    }



    private Runnable dismissRunnable = new Runnable() {
        @Override
        public void run() {
            dismissPW();
//            EventBus.getDefault().post(new CarType(PwType.ALL_BTN_GREY));
        }
    };

    public void setCustomViewToWin(View parent, View customView, int type) {

        if (popupWindow != null) {
            if (isPwShowing()) {
                popupWindow.dismiss();
                Log.d(TAG, "dismiss 222: ");
            }

            handler.postDelayed(dismissRunnable, 10 * 1000);

            popupWindow.setContentView(customView);
            Log.d(TAG, "setViewToWin: v =" + customView);
            showPopupByType(parent, type);
            setPwShowing(true);
        } else {
            Log.d(TAG, "popupWindow == null!");
        }
    }

    private void showPopupByType(View parent, int type) {
        switch (type) {
            case PwType.SETUP_WINDOW:
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                showSetupWin(parent);
                break;

            case PwType.MULVIEW_WINDOW:
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                showMulViewWin(parent);
                break;

            case PwType.CARMODEL_COLOR_WINDOW:
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                showCarColorWin(parent);
                break;

            case PwType.IMAGE_SETUP_WINDOW:
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                showImageSetupWin(parent);
                break;

            case PwType.IMAGE_ADJUST_WINDOW:
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                showImageAdjustWin(parent);
                break;

            default:
                break;
        }
    }

    private void showSetupWin(View parent) {
        if (popupWindow != null) {
            parent.getLocationOnScreen(location);
            Log.d(TAG, "showPopupWindow: location[0]= " + location[0] + ",location[1] = " + location[1]);
            popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.START, location[0] + parent.getWidth() + 60, location[1]);
        }
    }

    private void showMulViewWin(View parent) {
        if (popupWindow != null) {
            parent.getLocationOnScreen(location);
            Log.d(TAG, "showPopupWindow: location[0]= " + location[0] + ",location[1] = " + location[1]);
            popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.START, location[0] + parent.getWidth() + 60, location[1] - 200);
        }
    }

    private void showCarColorWin(View parent) {
        if (popupWindow != null) {
            parent.getLocationOnScreen(location);
            Log.d(TAG, "showPopupWindow: location[0]= " + location[0] + ",location[1] = " + location[1]);
            popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.START, parent.getWidth() - 100, -1000);
        }
    }

    private void showImageSetupWin(View parent) {
        if (popupWindow != null) {
            parent.getLocationOnScreen(location);
            Log.d(TAG, "showPopupWindow: location[0]= " + location[0] + ",location[1] = " + location[1]);
            popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.START, parent.getWidth(), -1000);
        }
    }

    private void showImageAdjustWin(View parent) {
        if (popupWindow != null) {
            parent.getLocationOnScreen(location);
            popupWindow.showAtLocation(parent, Gravity.CENTER_HORIZONTAL, 0, 600);
        }
    }

    public void dismissPW(){
        if (popupWindow != null) {
            popupWindow.dismiss();
            Log.d(TAG, "dismiss 333: ");
            if(handler != null){
                handler.removeCallbacks(dismissRunnable);
            }
        }
    }
    
    
}
