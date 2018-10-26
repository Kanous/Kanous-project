package com.svauto.fastrvc.protocol;

public class AvmInfo {
    private int AVM_MMI_FALIURE = 0x00;             //如果AVM系统故障，不能进入AVM模式
    private int AVM_CONTROL_REQUST = 0x00;          //进入退出控制请求
    private int AVM_FACTORY_MODE = 0x00;            //AVM工厂模式状态
    private int AVM_TURN_ON_REQUEST = 0x00;         //AVM开启请求
    private int AVM_CAMERA_FLTSTS = 0x00;           //AVM摄像头故障状态
    private volatile static AvmInfo mInstance;

    public static AvmInfo getInstance() {
        if (null == mInstance) {
            synchronized (AvmInfo.class) {
                if (null == mInstance) {
                    mInstance = new AvmInfo();
                }
            }
        }
        return mInstance;
    }

    public int getAVM_MMI_FALIURE() {
        return AVM_MMI_FALIURE;
    }

    public void setAVM_MMI_FALIURE(int AVM_MMI_FALIURE) {
        this.AVM_MMI_FALIURE = AVM_MMI_FALIURE;
    }

    public int getAVM_CONTROL_REQUST() {
        return AVM_CONTROL_REQUST;
    }

    public void setAVM_CONTROL_REQUST(int AVM_CONTROL_REQUST) {
        this.AVM_CONTROL_REQUST = AVM_CONTROL_REQUST;
    }

    public int getAVM_FACTORY_MODE() {
        return AVM_FACTORY_MODE;
    }

    public void setAVM_FACTORY_MODE(int AVM_FACTORY_MODE) {
        this.AVM_FACTORY_MODE = AVM_FACTORY_MODE;
    }

    public int getAVM_TURN_ON_REQUEST() {
        return AVM_TURN_ON_REQUEST;
    }

    public void setAVM_TURN_ON_REQUEST(int AVM_TURN_ON_REQUEST) {
        this.AVM_TURN_ON_REQUEST = AVM_TURN_ON_REQUEST;
    }

    public int getAVM_CAMERA_FLTSTS() {
        return AVM_CAMERA_FLTSTS;
    }

    public void setAVM_CAMERA_FLTSTS(int AVM_CAMERA_FLTSTS) {
        this.AVM_CAMERA_FLTSTS = AVM_CAMERA_FLTSTS;
    }
}
