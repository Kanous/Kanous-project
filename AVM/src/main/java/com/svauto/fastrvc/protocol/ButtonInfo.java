package com.svauto.fastrvc.protocol;

import com.svauto.fastrvc.manager.TheftServiceManager;
import com.svauto.fastrvc.message.CarType;
import com.svauto.fastrvc.message.PwType;

import org.greenrobot.eventbus.EventBus;

public class ButtonInfo {
    private int BUTTON_BACK_STATUS = 0x01;          //返回按钮
    private int BUTTON_PATH_LINE_STATUS = 0x01;     //动态停车辅助线显示状态反馈,倒车轨迹开关
    private int BUTTON_3D_STATUS = 0x01;            //AVM视图模式反馈扩展信号,3D视角扩展按钮
    private int BUTTON_MUL_VIEW_STATUS = 0x01;      //AVM视图模式反馈多视图按钮(放大镜)
    private int BUTTON_SETUP_STATUS = 0x01;         //设置按钮
    private int AVM_SOFT_KEY = 0x01;         //设置按钮

    private int CAM_SELECTOR = 0;                   //摄像头的方位

    private volatile static ButtonInfo mInstance;

    public static ButtonInfo getInstance() {
        if (null == mInstance) {
            synchronized (ButtonInfo.class) {
                if (null == mInstance) {
                    mInstance = new ButtonInfo();
                }
            }
        }
        return mInstance;
    }

    public int getCAM_SELECTOR() {
        return CAM_SELECTOR;
    }

    public void setCAM_SELECTOR(int CAM_SELECTOR) {
        this.CAM_SELECTOR = CAM_SELECTOR;
    }

    public int getAVM_SOFT_KEY() {
        return AVM_SOFT_KEY;
    }

    public void setAVM_SOFT_KEY(int AVM_SOFT_KEY) {
        this.AVM_SOFT_KEY = AVM_SOFT_KEY;
//        if(AVM_SOFT_KEY == 0){
//            EventBus.getDefault().post(new CarType(PwType.SOFTKEY_AVM_DISPLAY));
//        }else if(AVM_SOFT_KEY == 1){
//
//        }
    }

    public int getBUTTON_BACK_STATUS() {
        return BUTTON_BACK_STATUS;
    }

    public void setBUTTON_BACK_STATUS(int BUTTON_BACK_STATUS) {
        this.BUTTON_BACK_STATUS = BUTTON_BACK_STATUS;
        if(BUTTON_BACK_STATUS == 0){    //disable
            EventBus.getDefault().post(new CarType(PwType.BUTTON_BACK));
        }
    }

    public int getBUTTON_PATH_LINE_STATUS() {
        return BUTTON_PATH_LINE_STATUS;
    }

    public void setBUTTON_PATH_LINE_STATUS(int BUTTON_PATH_LINE_STATUS) {
        this.BUTTON_PATH_LINE_STATUS = BUTTON_PATH_LINE_STATUS;
        int up4 = TheftServiceManager.getInstance().parseValue2Up4(BUTTON_PATH_LINE_STATUS);
        int low4 = TheftServiceManager.getInstance().parseValue2Low4(BUTTON_PATH_LINE_STATUS);

        switch (up4) {
            case 0x00:  //disable
                EventBus.getDefault().post(new CarType(PwType.BUTTON_PATHLINE));
                break;

            case 0x01:
                break;

            case 0x02:
                break;

            case 0x03:
                break;

            default:
                break;
        }

    }

    public int getBUTTON_3D_STATUS() {
        return BUTTON_3D_STATUS;
    }

    public void setBUTTON_3D_STATUS(int BUTTON_3D_STATUS) {
        this.BUTTON_3D_STATUS = BUTTON_3D_STATUS;
        int up4 = TheftServiceManager.getInstance().parseValue2Up4(BUTTON_3D_STATUS);
        int low4 = TheftServiceManager.getInstance().parseValue2Low4(BUTTON_3D_STATUS);
        setCAM_SELECTOR(low4);

        switch (up4) {
            case 0x00:
                EventBus.getDefault().post(new CarType(PwType.BUTTON_THREED));
                break;

            case 0x01:
                break;

            case 0x02://active
                EventBus.getDefault().post(new CarType(PwType.UPDATE_CAM_SELECTOR));
                break;

            case 0x03:
                break;

            default:
                break;
        }

    }

    public int getBUTTON_MUL_VIEW_STATUS() {
        return BUTTON_MUL_VIEW_STATUS;
    }

    public void setBUTTON_MUL_VIEW_STATUS(int BUTTON_MUL_VIEW_STATUS) {
        this.BUTTON_MUL_VIEW_STATUS = BUTTON_MUL_VIEW_STATUS;
        if(BUTTON_MUL_VIEW_STATUS == 0){
            EventBus.getDefault().post(new CarType(PwType.BUTTON_MULVIEW));
        }
    }

    public int getBUTTON_SETUP_STATUS() {
        return BUTTON_SETUP_STATUS;
    }

    public void setBUTTON_SETUP_STATUS(int BUTTON_SETUP_STATUS) {
        this.BUTTON_SETUP_STATUS = BUTTON_SETUP_STATUS;
        if(BUTTON_SETUP_STATUS == 0){
            EventBus.getDefault().post(new CarType(PwType.BUTTON_SETUP));
        }
    }
}
