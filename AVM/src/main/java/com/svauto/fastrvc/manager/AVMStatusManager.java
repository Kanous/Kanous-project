package com.svauto.fastrvc.manager;

import android.util.Log;

import com.svauto.fastrvc.message.PwType;

public class AVMStatusManager {
    private final String TAG = this.getClass().getSimpleName();
    private volatile static AVMStatusManager mInstance;

    private boolean isCameraFrontSelect = false;
    private boolean isCameraBackSelect = false;
    private boolean isCameraLeftSelect = false;
    private boolean isCameraRightSelect = false;

    private boolean isCam3DFrontSelect = false;
    private boolean isCam3DBackSelect = false;
    private boolean isCam3DLeftFrontSelect = false;
    private boolean isCam3DLeftBackSelect = false;
    private boolean isCam3DRightFrontSelect = false;
    private boolean isCam3DRightBackSelect = false;

    private boolean isMulViewFrontSelect = false;
    private boolean isMulViewBackSelect = false;
    private boolean isMulViewFront2SideSelect = false;
    private boolean isMulViewBack2SideSelect = false;

    private boolean isPathLineSelect = false;
    private boolean is3DImageSelect = false;
    private boolean isMulViewSelect = false;
    private volatile boolean isSetupSelect = false;

    private int avmStatus = 0;



    public int getAvmStatus() {
        Log.d(TAG, "getAvmStatus: "+avmStatus);
        return avmStatus;
    }

    public void setAvmStatus(int status) {
        Log.d(TAG, "setAvmStatus: "+status);
        avmStatus = status;
    }



    public void quitAVMResetBtnFlag() {
        isPathLineSelect = false;
        is3DImageSelect = false;
        isMulViewSelect = false;
        isSetupSelect = false;
    }

    public void setAllCameraNotSelect() {
        isCameraFrontSelect = false;
        isCameraBackSelect = false;
        isCameraLeftSelect = false;
        isCameraRightSelect = false;
    }

    public void update4CamerStatus(int type) {
        switch (type) {
            case PwType.CAM_FRONT:
                isCameraFrontSelect = true;
                isCameraBackSelect = false;
                isCameraLeftSelect = false;
                isCameraRightSelect = false;
                break;

            case PwType.CAM_BACK:
                isCameraFrontSelect = false;
                isCameraBackSelect = true;
                isCameraLeftSelect = false;
                isCameraRightSelect = false;
                break;

            case PwType.CAM_LEFT:
                isCameraFrontSelect = false;
                isCameraBackSelect = false;
                isCameraLeftSelect = true;
                isCameraRightSelect = false;
                break;

            case PwType.CAM_RIGHT:
                isCameraFrontSelect = false;
                isCameraBackSelect = false;
                isCameraLeftSelect = false;
                isCameraRightSelect = true;
                break;

            default:
                break;
        }
    }

    public void update3DcamerStatus(int type) {
        switch (type) {
            case PwType.CAM_3D_FRONT:
                isCam3DFrontSelect = true;
                isCam3DBackSelect = false;
                isCam3DLeftFrontSelect = false;
                isCam3DLeftBackSelect = false;
                isCam3DRightFrontSelect = false;
                isCam3DRightBackSelect = false;
                break;

            case PwType.CAM_3D_BACK:
                isCam3DFrontSelect = false;
                isCam3DBackSelect = true;
                isCam3DLeftFrontSelect = false;
                isCam3DLeftBackSelect = false;
                isCam3DRightFrontSelect = false;
                isCam3DRightBackSelect = false;
                break;

            case PwType.CAM_3D_LEFT_FRONT:
                isCam3DFrontSelect = false;
                isCam3DBackSelect = false;
                isCam3DLeftFrontSelect = true;
                isCam3DLeftBackSelect = false;
                isCam3DRightFrontSelect = false;
                isCam3DRightBackSelect = false;
                break;

            case PwType.CAM_3D_RIGHT_FRONT:
                isCam3DFrontSelect = false;
                isCam3DBackSelect = false;
                isCam3DLeftFrontSelect = false;
                isCam3DLeftBackSelect = false;
                isCam3DRightFrontSelect = true;
                isCam3DRightBackSelect = false;
                break;

            case PwType.CAM_3D_LEFT_REAR:
                isCam3DFrontSelect = false;
                isCam3DBackSelect = false;
                isCam3DLeftFrontSelect = false;
                isCam3DLeftBackSelect = true;
                isCam3DRightFrontSelect = false;
                isCam3DRightBackSelect = false;
                break;

            case PwType.CAM_3D_RIGHT_REAR:
                isCam3DFrontSelect = false;
                isCam3DBackSelect = false;
                isCam3DLeftFrontSelect = false;
                isCam3DLeftBackSelect = false;
                isCam3DRightFrontSelect = false;
                isCam3DRightBackSelect = true;
                break;

            case PwType.UPDATE_CAM_SELECTOR:
                isCam3DFrontSelect = false;
                isCam3DBackSelect = false;
                isCam3DLeftFrontSelect = false;
                isCam3DLeftBackSelect = false;
                isCam3DRightFrontSelect = false;
                isCam3DRightBackSelect = false;
                break;

            default:
                break;
        }
    }

    public void updateAvmActivityButton(int type) {
        switch (type) {
            case PwType.PATH_LINE_SELECT:
                isPathLineSelect = true;
                is3DImageSelect = false;
                isMulViewSelect = false;
                isSetupSelect = false;
                break;

            case PwType.THREE_D_SELECT:
                isPathLineSelect = false;
                is3DImageSelect = true;
                isMulViewSelect = false;
                isSetupSelect = false;
                break;

            case PwType.MUL_VIEW_SELECT:
                isPathLineSelect = false;
                is3DImageSelect = false;
                isMulViewSelect = true;
                isSetupSelect = false;
                break;

            case PwType.SETUP_SELECT:
                isPathLineSelect = false;
                is3DImageSelect = false;
                isMulViewSelect = false;
                isSetupSelect = true;
                break;

            default:
                break;
        }
    }

    public boolean isPathLineSelect() {
        return isPathLineSelect;
    }

    public void setPathLineSelect(boolean pathLineSelect) {
        isPathLineSelect = pathLineSelect;
    }

    public boolean isIs3DImageSelect() {
        return is3DImageSelect;
    }

    public void setIs3DImageSelect(boolean is3DImageSelect) {
        this.is3DImageSelect = is3DImageSelect;
    }

    public boolean isMulViewSelect() {
        return isMulViewSelect;
    }

    public void setMulViewSelect(boolean mulViewSelect) {
        isMulViewSelect = mulViewSelect;
    }

    public boolean isSetupSelect() {
        return isSetupSelect;
    }

    public void setSetupSelect(boolean setupSelect) {
        isSetupSelect = setupSelect;
    }


    public void updateMulViewStatus(int type) {

        switch (type) {
            case PwType.MUL_VIEW_FRONT_SIDE:
                isMulViewFrontSelect = true;
                isMulViewBackSelect = false;
                isMulViewFront2SideSelect = false;
                isMulViewBack2SideSelect = false;
                break;

            case PwType.MUL_VIEW_BACK_SIDE:
                isMulViewFrontSelect = false;
                isMulViewBackSelect = true;
                isMulViewFront2SideSelect = false;
                isMulViewBack2SideSelect = false;
                break;

            case PwType.MUL_VIEW_FRONT_2_SIDE:
                isMulViewFrontSelect = false;
                isMulViewBackSelect = false;
                isMulViewFront2SideSelect = true;
                isMulViewBack2SideSelect = false;
                break;

            case PwType.MUL_VIEW_BACK_2_SIDE:
                isMulViewFrontSelect = false;
                isMulViewBackSelect = false;
                isMulViewFront2SideSelect = false;
                isMulViewBack2SideSelect = true;
                break;

            default:
                break;
        }
    }

    public boolean isMulViewFrontSelect() {
        return isMulViewFrontSelect;
    }

    public void setMulViewFrontSelect(boolean mulViewFrontSelect) {
        isMulViewFrontSelect = mulViewFrontSelect;
    }

    public boolean isMulViewBackSelect() {
        return isMulViewBackSelect;
    }

    public void setMulViewBackSelect(boolean mulViewBackSelect) {
        isMulViewBackSelect = mulViewBackSelect;
    }

    public boolean isMulViewFront2SideSelect() {
        return isMulViewFront2SideSelect;
    }

    public void setMulViewFront2SideSelect(boolean mulViewFront2SideSelect) {
        isMulViewFront2SideSelect = mulViewFront2SideSelect;
    }

    public boolean isMulViewBack2SideSelect() {
        return isMulViewBack2SideSelect;
    }

    public void setMulViewBack2SideSelect(boolean mulViewBack2SideSelect) {
        isMulViewBack2SideSelect = mulViewBack2SideSelect;
    }

    public boolean isIs3DFullSideSelect() {
        return is3DFullSideSelect;
    }

    public void setIs3DFullSideSelect(boolean is3DFullSideSelect) {
        this.is3DFullSideSelect = is3DFullSideSelect;
    }

    private boolean is3DFullSideSelect = false;

    public static AVMStatusManager getInstance() {
        if (null == mInstance) {
            synchronized (AVMStatusManager.class) {
                if (null == mInstance) {
                    mInstance = new AVMStatusManager();
                }
            }
        }
        return mInstance;
    }

    public boolean isCameraFrontSelect() {
        return isCameraFrontSelect;
    }

    public void setCameraFrontSelect(boolean cameraFrontSelect) {
        isCameraFrontSelect = cameraFrontSelect;
    }

    public boolean isCameraBackSelect() {
        return isCameraBackSelect;
    }

    public void setCameraBackSelect(boolean cameraBackSelect) {
        isCameraBackSelect = cameraBackSelect;
    }

    public boolean isCameraLeftSelect() {
        return isCameraLeftSelect;
    }

    public void setCameraLeftSelect(boolean cameraLeftSelect) {
        isCameraLeftSelect = cameraLeftSelect;
    }

    public boolean isCameraRightSelect() {
        return isCameraRightSelect;
    }

    public void setCameraRightSelect(boolean cameraRightSelect) {
        isCameraRightSelect = cameraRightSelect;
    }

    public boolean isCam3DFrontSelect() {
        return isCam3DFrontSelect;
    }

    public void setCam3DFrontSelect(boolean cam3DFrontSelect) {
        isCam3DFrontSelect = cam3DFrontSelect;
    }

    public boolean isCam3DBackSelect() {
        return isCam3DBackSelect;
    }

    public void setCam3DBackSelect(boolean cam3DBackSelect) {
        isCam3DBackSelect = cam3DBackSelect;
    }

    public boolean isCam3DLeftFrontSelect() {
        return isCam3DLeftFrontSelect;
    }

    public void setCam3DLeftFrontSelect(boolean cam3DLeftFrontSelect) {
        isCam3DLeftFrontSelect = cam3DLeftFrontSelect;
    }

    public boolean isCam3DLeftBackSelect() {
        return isCam3DLeftBackSelect;
    }

    public void setCam3DLeftBackSelect(boolean cam3DLeftBackSelect) {
        isCam3DLeftBackSelect = cam3DLeftBackSelect;
    }

    public boolean isCam3DRightFrontSelect() {
        return isCam3DRightFrontSelect;
    }

    public void setCam3DRightFrontSelect(boolean cam3DRightFrontSelect) {
        isCam3DRightFrontSelect = cam3DRightFrontSelect;
    }

    public boolean isCam3DRightBackSelect() {
        return isCam3DRightBackSelect;
    }

    public void setCam3DRightBackSelect(boolean cam3DRightBackSelect) {
        isCam3DRightBackSelect = cam3DRightBackSelect;
    }
}
