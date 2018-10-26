package com.svauto.fastrvc.message;

public class PwType {
    //popupwindow类型
    public static final int SETUP_WINDOW = 0;
    public static final int MULVIEW_WINDOW = 1;
    public static final int CARMODEL_COLOR_WINDOW = 2;
    public static final int IMAGE_SETUP_WINDOW = 3;
    public static final int IMAGE_ADJUST_WINDOW = 4;

    public static final int SOFTKEY_AVM_DISPLAY = 5;
    public static final int SOFTKEY_QUIT_AVM = 6;
    public static final int SOFTKEY_ENTER_AVM = 7;

    //按键状态
    public static final int BUTTON_BACK = 8;
    public static final int BUTTON_PATHLINE = 9;
    public static final int BUTTON_THREED = 10;
    public static final int BUTTON_MULVIEW = 11;
    public static final int BUTTON_SETUP = 12;

    //车模颜色
    public static final int CAR_MODEL_COLOR_WHITE = 0xA0;
    public static final int CAR_MODEL_COLOR_BLUE = 0xA1;
    public static final int CAR_MODEL_COLOR_RED = 0xA2;
    public static final int CAR_MODEL_COLOR_BLACK = 0xA3;
    public static final int CAR_MODEL_COLOR_GREY = 0xA4;

    //dialog类型
    public static final int DIALOG_RESTORE = 0xA5;
    public static final int DIALOG_CAR_MODEL_COLOR= 0xA6;
    public static final int DIALOG_IMAGE_SETUP = 0xA7;
    public static final int DIALOG_IMAGE_ADJUST = 0xA8;
    public static final int DIALOG_AVM_SETUP = 0xA9;
    public static final int DIALOG_MUL_VIEW = 0xAA;

    //多视图模式选择
    public static final int MUL_VIEW_FRONT_SIDE = 0xB0;
    public static final int MUL_VIEW_BACK_SIDE = 0xB1;
    public static final int MUL_VIEW_FRONT_2_SIDE = 0xB2;
    public static final int MUL_VIEW_BACK_2_SIDE = 0xB3;

    //主界面的按键选择
    public static final int PATH_LINE_SELECT = 0xC0;
    public static final int THREE_D_SELECT = 0xC1;
    public static final int MUL_VIEW_SELECT = 0xC2;
    public static final int SETUP_SELECT = 0xC3;

    //影像调节
    public static final int ADJUST_OF_BRIGHTNESS = 0xD0;
    public static final int ADJUST_OF_CONTRAST = 0xD1;
    public static final int ADJUST_OF_SATURABILITY = 0xD2;

    //转向自动侧视开关、3D环绕开关
    public static final int AVM_TURNING_ENTER_AVM_ON = 0xD3;
    public static final int AVM_TURNING_ENTER_AVM_OFF = 0xD4;
    public static final int AVM_3D_TOURING_KEY_RESP_ON = 0xD5;
    public static final int AVM_3D_TOURING_KEY_RESP_OFF = 0xD6;

    //摄像头包括普通和3D摄像头
    public static final int CAM_FRONT = 0xE0;
    public static final int CAM_BACK = 0xE1;
    public static final int CAM_LEFT = 0xE2;
    public static final int CAM_RIGHT = 0xE3;
    public static final int CAM_3D_FRONT = 0xE4;
    public static final int CAM_3D_BACK = 0xE5;
    public static final int CAM_3D_LEFT_FRONT = 0xE6;
    public static final int CAM_3D_RIGHT_FRONT = 0xE7;
    public static final int CAM_3D_LEFT_REAR = 0xE8;
    public static final int CAM_3D_RIGHT_REAR = 0xE9;
    public static final int UPDATE_CAM_SELECTOR = 0xEA;

    //雷达图
    public static final int RADAR_LEFT_FRONT = 0xF0;
    public static final int RADAR_LEFT_MIDDLE_FRONT = 0xF1;
    public static final int RADAR_RIGHT_MIDDLE_FRONT = 0xF2;
    public static final int RADAR_RIGHT_FRONT = 0xF3;
    public static final int RADAR_LEFT_REAR = 0xF4;
    public static final int RADAR_LEFT_MIDDLE_REAR = 0xF5;
    public static final int RADAR_RIGHT_MIDDLE_REAR = 0xF6;
    public static final int RADAR_RIGHT_REAR = 0xF7;
    public static final int RADAR_PDC_MODE = 0xF8;

}
