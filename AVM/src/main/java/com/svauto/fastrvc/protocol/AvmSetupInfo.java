package com.svauto.fastrvc.protocol;

import android.util.Log;

import com.svauto.fastrvc.manager.AVMStatusManager;
import com.svauto.fastrvc.manager.TheftServiceManager;
import com.svauto.fastrvc.message.CarType;
import com.svauto.fastrvc.message.PwType;

import org.greenrobot.eventbus.EventBus;

public class AvmSetupInfo {
    private final String TAG = this.getClass().getSimpleName();
    private int AVM_STATUS = 0x00;                                  //AVM状态反馈，暂时保留不用
    private int AVM_CAR_ICON_COLOR_RESP = 0x00;                     //AVM车辆图标颜色设置响应
    private int AVM_TURNING_ENTER_AVM = 0x00;                      //转向自动AVM侧视图功能开启响应
    private int AVM_3D_TOURING_KEY_RESP = 0x00;                     //3D环绕开关应答
    private int AVM_RESTORE_FACTORY_RESP = 0x00;                    //AVM恢复出厂设置应答
    private volatile static AvmSetupInfo mInstance;
    private final int QUIT_AVM = 0;
    private final int ICON_SHOW_AVM = 1;
    private final int RVC_SHOW_AVM = 2;

    public static AvmSetupInfo getInstance() {
        if (null == mInstance) {
            synchronized (AvmSetupInfo.class) {
                if (null == mInstance) {
                    mInstance = new AvmSetupInfo();
                }
            }
        }
        return mInstance;
    }

    public int getAVM_STATUS() {
        return AVM_STATUS;
    }

    public void setAVM_STATUS(int AVM_STATUS) {
        this.AVM_STATUS = AVM_STATUS;
        Log.d(TAG, "setAVM_STATUS: " + AVM_STATUS);
        if (AVM_STATUS == 0) {
            //退出avm
            EventBus.getDefault().post(new CarType(PwType.SOFTKEY_QUIT_AVM));

        } else if (AVM_STATUS == 1) {       //进入avm
            if (AVMStatusManager.getInstance().  getAvmStatus() == ICON_SHOW_AVM) {
                //点击图标，主动进来avm
                EventBus.getDefault().post(new CarType(PwType.SOFTKEY_AVM_DISPLAY));
            } else if (AVMStatusManager.getInstance().getAvmStatus() == QUIT_AVM) {
                // rvc,被动显示avm
                AVMStatusManager.getInstance().setAvmStatus(RVC_SHOW_AVM);
            }
        }
    }


    public int getAVM_CAR_ICON_COLOR_RESP() {
        return AVM_CAR_ICON_COLOR_RESP;
    }

    public void setAVM_CAR_ICON_COLOR_RESP(int AVM_CAR_ICON_COLOR_RESP) {
        this.AVM_CAR_ICON_COLOR_RESP = AVM_CAR_ICON_COLOR_RESP;
        int up4 = TheftServiceManager.getInstance().parseValue2Up4(AVM_CAR_ICON_COLOR_RESP);
        if (up4 == 0x01) {
            int low4 = TheftServiceManager.getInstance().parseValue2Low4(AVM_CAR_ICON_COLOR_RESP);
            switch (low4) {
                case 0:
                    EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_WHITE));
                    break;
                case 1:
                    EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLUE));
                    break;
                case 2:
                    EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_RED));
                    break;
                case 3:
                    EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLACK));
                    break;
                case 4:
                    EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_GREY));
                    break;
                default:
                    break;
            }
        } else {
            Log.d(TAG, "setAVM_CAR_ICON_COLOR_RESP: ");
        }

    }

    public int getAVM_TURNING_ENTER_AVM() {
        return AVM_TURNING_ENTER_AVM;
    }

    public void setAVM_TURNING_ENTER_AVM(int AVM_TURNING_ENTER_AVM) {
        this.AVM_TURNING_ENTER_AVM = AVM_TURNING_ENTER_AVM;
        int up4 = TheftServiceManager.getInstance().parseValue2Up4(AVM_TURNING_ENTER_AVM);
        int low4 = TheftServiceManager.getInstance().parseValue2Low4(AVM_TURNING_ENTER_AVM);
        Log.d(TAG, "setAVM_TURNING_ENTER_AVM: up4 = " + up4 + " ,low4 = " + low4);

        switch (up4) {
            case 0x00:
                break;

            case 0x01://enable
                if (low4 == 0x00) {
                    EventBus.getDefault().post(new CarType(PwType.AVM_TURNING_ENTER_AVM_OFF));
                } else if (low4 == 0x01) {
                    EventBus.getDefault().post(new CarType(PwType.AVM_TURNING_ENTER_AVM_ON));
                }
                break;

            case 0x02://active
                break;

            case 0x03:
                break;

            default:
                break;
        }

    }

    public int getAVM_3D_TOURING_KEY_RESP() {
        return AVM_3D_TOURING_KEY_RESP;
    }

    public void setAVM_3D_TOURING_KEY_RESP(int AVM_3D_TOURING_KEY_RESP) {
        this.AVM_3D_TOURING_KEY_RESP = AVM_3D_TOURING_KEY_RESP;
        int up4 = TheftServiceManager.getInstance().parseValue2Up4(AVM_3D_TOURING_KEY_RESP);
        int low4 = TheftServiceManager.getInstance().parseValue2Low4(AVM_3D_TOURING_KEY_RESP);

        switch (up4) {
            case 0x00:
                break;

            case 0x01://enable
//                if (low4 == 0x00) {
//                    EventBus.getDefault().post(new CarType(PwType.AVM_3D_TOURING_KEY_RESP_OFF));
//                } else if (low4 == 0x01) {
//                    EventBus.getDefault().post(new CarType(PwType.AVM_3D_TOURING_KEY_RESP_ON));
//                }
                break;

            case 0x02://active
                break;

            case 0x03:
                if (low4 == 0x00) {
                    EventBus.getDefault().post(new CarType(PwType.AVM_3D_TOURING_KEY_RESP_OFF));
                } else if (low4 == 0x01) {
                    EventBus.getDefault().post(new CarType(PwType.AVM_3D_TOURING_KEY_RESP_ON));
                }
                break;

            default:
                break;
        }
    }

    public int getAVM_RESTORE_FACTORY_RESP() {
        return AVM_RESTORE_FACTORY_RESP;
    }

    public void setAVM_RESTORE_FACTORY_RESP(int AVM_RESTORE_FACTORY_RESP) {
        this.AVM_RESTORE_FACTORY_RESP = AVM_RESTORE_FACTORY_RESP;
        this.AVM_3D_TOURING_KEY_RESP = AVM_RESTORE_FACTORY_RESP;
        int up4 = TheftServiceManager.getInstance().parseValue2Up4(AVM_RESTORE_FACTORY_RESP);
        int low4 = TheftServiceManager.getInstance().parseValue2Low4(AVM_RESTORE_FACTORY_RESP);

        switch (up4) {
            case 0x00:
                break;

            case 0x01://enable

                break;

            case 0x02://active
                break;

            case 0x03:
                break;

            default:
                break;
        }
    }
}
