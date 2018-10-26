package com.svauto.fastrvc.protocol;

//Radio Dispatch PAS Status Info:0x7344

import com.svauto.fastrvc.message.CarType;
import com.svauto.fastrvc.message.PwType;

import org.greenrobot.eventbus.EventBus;

public class RadarInfo {
    private int PCD_BTN_PRESS = 0;//信号指示PDC禁止按钮是否被按下。如果功能处于故障模式，按钮被按下（尝试激活功能），HMI可使用信号提供PDC相关故障状态消息。
    private int BUZZER_ALARM_TYPE = 0;//蜂鸣器报警方式
    private int PAS_PDC_DISTANCE_FL = 0;//左前雷达
    private int PAS_PDC_DISTANCE_FML = 0;//左中前雷达
    private int PAS_PDC_DISTANCE_FMR = 0;//右中前雷达
    private int PAS_PDC_DISTANCE_FR = 0;//右前雷达
    private int PAS_PDC_DISTANCE_RL = 0;//左后雷达
    private int PAS_PDC_DISTANCE_RML = 0;//左中后雷达
    private int PAS_PDC_DISTANCE_RMR = 0;//右中后雷达
    private int PAS_PDC_DISTANCE_RR = 0;//右后雷达
    private int PAS_PDC_MODE = 0;//雷达工作模式


    private volatile static RadarInfo mInstance;

    public static RadarInfo getInstance() {
        if (null == mInstance) {
            synchronized (RadarInfo.class) {
                if (null == mInstance) {
                    mInstance = new RadarInfo();
                }
            }
        }
        return mInstance;
    }


    public int getPCD_BTN_PRESS() {
        return PCD_BTN_PRESS;
    }

    public void setPCD_BTN_PRESS(int PCD_BTN_PRESS) {
        this.PCD_BTN_PRESS = PCD_BTN_PRESS;
    }

    public int getBUZZER_ALARM_TYPE() {
        return BUZZER_ALARM_TYPE;
    }

    public void setBUZZER_ALARM_TYPE(int BUZZER_ALARM_TYPE) {
        this.BUZZER_ALARM_TYPE = BUZZER_ALARM_TYPE;
    }

    public int getPAS_PDC_DISTANCE_FL() {
        return PAS_PDC_DISTANCE_FL;
    }

    public int getPAS_PDC_DISTANCE_FML() {
        return PAS_PDC_DISTANCE_FML;
    }

    public int getPAS_PDC_DISTANCE_FMR() {
        return PAS_PDC_DISTANCE_FMR;
    }

    public int getPAS_PDC_DISTANCE_FR() {
        return PAS_PDC_DISTANCE_FR;
    }

    public int getPAS_PDC_DISTANCE_RL() {
        return PAS_PDC_DISTANCE_RL;
    }

    public int getPAS_PDC_DISTANCE_RML() {
        return PAS_PDC_DISTANCE_RML;
    }

    public int getPAS_PDC_DISTANCE_RMR() {
        return PAS_PDC_DISTANCE_RMR;
    }

    public int getPAS_PDC_DISTANCE_RR() {
        return PAS_PDC_DISTANCE_RR;
    }

    public int getPAS_PDC_MODE() {
        return PAS_PDC_MODE;
    }

    public void setPAS_PDC_DISTANCE_FL(int PAS_PDC_DISTANCE_FL) {
        this.PAS_PDC_DISTANCE_FL = PAS_PDC_DISTANCE_FL;
        EventBus.getDefault().post(new CarType(PwType.RADAR_LEFT_FRONT));
    }

    public void setPAS_PDC_DISTANCE_FML(int PAS_PDC_DISTANCE_FML) {
        this.PAS_PDC_DISTANCE_FML = PAS_PDC_DISTANCE_FML;
        EventBus.getDefault().post(new CarType(PwType.RADAR_LEFT_MIDDLE_FRONT));
    }

    public void setPAS_PDC_DISTANCE_FMR(int PAS_PDC_DISTANCE_FMR) {
        this.PAS_PDC_DISTANCE_FMR = PAS_PDC_DISTANCE_FMR;
        EventBus.getDefault().post(new CarType(PwType.RADAR_RIGHT_MIDDLE_FRONT));
    }

    public void setPAS_PDC_DISTANCE_FR(int PAS_PDC_DISTANCE_FR) {
        this.PAS_PDC_DISTANCE_FR = PAS_PDC_DISTANCE_FR;
        EventBus.getDefault().post(new CarType(PwType.RADAR_RIGHT_FRONT));
    }

    public void setPAS_PDC_DISTANCE_RL(int PAS_PDC_DISTANCE_RL) {
        this.PAS_PDC_DISTANCE_RL = PAS_PDC_DISTANCE_RL;
        EventBus.getDefault().post(new CarType(PwType.RADAR_LEFT_REAR));
    }

    public void setPAS_PDC_DISTANCE_RML(int PAS_PDC_DISTANCE_RML) {
        this.PAS_PDC_DISTANCE_RML = PAS_PDC_DISTANCE_RML;
        EventBus.getDefault().post(new CarType(PwType.RADAR_LEFT_MIDDLE_REAR));
    }

    public void setPAS_PDC_DISTANCE_RMR(int PAS_PDC_DISTANCE_RMR) {
        this.PAS_PDC_DISTANCE_RMR = PAS_PDC_DISTANCE_RMR;
        EventBus.getDefault().post(new CarType(PwType.RADAR_RIGHT_MIDDLE_REAR));
    }

    public void setPAS_PDC_DISTANCE_RR(int PAS_PDC_DISTANCE_RR) {
        this.PAS_PDC_DISTANCE_RR = PAS_PDC_DISTANCE_RR;
        EventBus.getDefault().post(new CarType(PwType.RADAR_RIGHT_REAR));
    }

    public void setPAS_PDC_MODE(int PAS_PDC_MODE) {
        this.PAS_PDC_MODE = PAS_PDC_MODE;
        EventBus.getDefault().post(new CarType(PwType.RADAR_PDC_MODE));
    }
}
