package com.svauto.fastrvc.protocol;

import com.svauto.fastrvc.manager.TheftServiceManager;
import com.svauto.fastrvc.message.TheftServiceMessage;

import java.util.ArrayList;

public class NaviSendMsgToMCU {
    private final String TAG = this.getClass().getSimpleName();
    private volatile static NaviSendMsgToMCU mInstance;
    private static ArrayList<Integer> mCmdParam;

    public static NaviSendMsgToMCU getInstance() {
        if (null == mInstance) {
            synchronized (NaviSendMsgToMCU.class) {
                if (null == mInstance) {
                    mInstance = new NaviSendMsgToMCU();
                    mCmdParam = new ArrayList();
                }
            }
        }
        return mInstance;
    }

    public void requestAvm2MCU(int sw_val) {
        mCmdParam.clear();
        mCmdParam.add(0x05);
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_BUTTON_INFO, mCmdParam);
    }

    public void setPathLine2MCU(int sw_val) {
        mCmdParam.clear();
        mCmdParam.add(0x01);
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_BUTTON_INFO, mCmdParam);
    }

    public void mulViewSwitcher2MCU(int sw_val){
        mCmdParam.clear();
        mCmdParam.add(0x03);
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_BUTTON_INFO, mCmdParam);
    }

    public void setRestoreFactorySetting2MCU() {
        mCmdParam.clear();
        mCmdParam.add(0x04);
        mCmdParam.add(0x01);
        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_SETUP, mCmdParam);

    }

    public void setCarColor2MCU(int color) {
        mCmdParam.clear();
        mCmdParam.add(0x01);
        mCmdParam.add(color);
        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_SETUP, mCmdParam);
    }

    public void setTurningEnterAvmSwitch2MCU(int sw_val) {
        mCmdParam.clear();
        mCmdParam.add(0x02);
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_SETUP, mCmdParam);
    }

    public void set3DTouringViewSwitch2MCU(int sw_val) {
        mCmdParam.clear();
        mCmdParam.add(0x03);
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_SETUP, mCmdParam);
    }

    public void set3DViewMode2MCU(int sw_val) {
        mCmdParam.clear();
        mCmdParam.add(0x02);
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_BUTTON_INFO, mCmdParam);
    }

    public void set4AngleCamera(int angle){
        mCmdParam.clear();
        mCmdParam.add(0x06);
        mCmdParam.add(angle);
        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_BUTTON_INFO, mCmdParam);
    }
//
//    public static void main(String[] agrs) {
//        System.out.println("Hello world");
//        int lx = int2HexGetLow8Bit(640);
//        int hx = int2HexGetHight8Bit(640);
//        System.out.println("lx :"+lx+", hx :"+hx);
//
//        int ly = int2HexGetLow8Bit(1920);
//        int hy = int2HexGetHight8Bit(1920);
//        System.out.println("ly :"+ly+", hy :"+hy);
//    }

    public void sendCoordinate2MCU(int x,int y, int touchevent){
        mCmdParam.clear();
        //低位Xcoordinate L、高位Xcoordinate H
        //Ycoordinate L、高位Ycoordinate H
        mCmdParam.add(x & 0x00FF);
        mCmdParam.add((x & 0xFF00) >> 8);

        mCmdParam.add(y & 0x00FF);
        mCmdParam.add((y & 0xFF00) >> 8);
        mCmdParam.add(touchevent);
        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_TOUCH_DATA, mCmdParam);
    }


}
