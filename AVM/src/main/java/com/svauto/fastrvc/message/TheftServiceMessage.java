package com.svauto.fastrvc.message;

public class TheftServiceMessage {
    //RVC OR AVM
    public final static int RVC_OR_AVM = 0x719A;

    //NAVI -> MCU
    public final static int NAVI_TO_MCU_AVM_INIT = 0x7240;
    public final static int NAVI_TO_MCU_AVM_SETUP = 0x7241;
    public final static int NAVI_TO_MCU_AVM_BUTTON_INFO = 0x7242;
    public final static int NAVI_TO_MCU_AVM_TOUCH_DATA = 0x7243;
    public final static int NAVI_TO_MCU_REQUEST_PAS_STATUS = 0x7244;
//    public final static int NAVI_TO_MCU_AVM_HVAC = 0x7245;
//    public final static int NAVI_TO_MCU_DVR = 0x7246;

    //MCU -> NAVI
    public final static int MCU_TO_NAVI_SETUP = 0x7341;
    public final static int MCU_TO_NAVI_BUTTON_INFO = 0x7342;
    public final static int MCU_TO_NAVI_AVM_STATUS = 0x7343;
    public final static int MCU_TO_NAVI_PAS_STATUS = 0x7344;
//    public final static int MCU_TO_NAVI_HVAC_STATUS = 0x7345;
//    public final static int MCU_TO_NAVI_DVR_STATUS = 0x7346;

}
