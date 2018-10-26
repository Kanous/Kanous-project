package com.svauto.fastrvc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.svauto.fastrvc.R;
import com.svauto.fastrvc.manager.AVMStatusManager;
import com.svauto.fastrvc.message.CarType;
import com.svauto.fastrvc.message.PwType;
import com.svauto.fastrvc.protocol.NaviSendMsgToMCU;
import com.svauto.fastrvc.protocol.RadarInfo;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RadarView extends RelativeLayout {
    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.car_model)
    ImageView carModel;
    @BindView(R.id.camera_front)
    ImageView cameraFront;
    @BindView(R.id.camera_back)
    ImageView cameraBack;
    @BindView(R.id.camera_left)
    ImageView cameraLeft;
    @BindView(R.id.camera_right)
    ImageView cameraRight;
    @BindView(R.id.radar_fl)
    ImageView radarFl;
    @BindView(R.id.radar_fml)
    ImageView radarFml;
    @BindView(R.id.radar_fmr)
    ImageView radarFmr;
    @BindView(R.id.radar_fr)
    ImageView radarFr;
    @BindView(R.id.radar_bl)
    ImageView radarBl;
    @BindView(R.id.radar_bml)
    ImageView radarBml;
    @BindView(R.id.radar_bmr)
    ImageView radarBmr;
    @BindView(R.id.radar_br)
    ImageView radarBr;
    @BindView(R.id.cam_front)
    ImageView camFont;
    @BindView(R.id.cam_back)
    ImageView camBack;
    @BindView(R.id.cam_left_front)
    ImageView camLeftFront;
    @BindView(R.id.cam_right_front)
    ImageView camRightFront;
    @BindView(R.id.cam_left_back)
    ImageView camLeftBack;
    @BindView(R.id.cam_right_back)
    ImageView camRightBack;

    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.d(TAG, "init: ");
        View v = LayoutInflater.from(getContext()).inflate(R.layout.radarlayout, this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.camera_front, R.id.camera_back, R.id.camera_left, R.id.camera_right, R.id.cam_front, R.id.cam_back, R.id.cam_left_front, R.id.cam_right_front, R.id.cam_left_back, R.id.cam_right_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.camera_front:
                EventBus.getDefault().post(new CarType(PwType.CAM_FRONT));
                Log.d(TAG, "onViewClicked: camera_front");
                if (AVMStatusManager.getInstance().isCameraFrontSelect()) {
                    cameraFront.setImageResource(R.mipmap.front_n);
                    AVMStatusManager.getInstance().setCameraFrontSelect(false);
                } else {
                    allCameraSetGrey();
                    NaviSendMsgToMCU.getInstance().set4AngleCamera(0x01);
                    cameraFront.setImageResource(R.mipmap.front_p);
                    AVMStatusManager.getInstance().update4CamerStatus(PwType.CAM_FRONT);
                }
                break;

            case R.id.camera_back:
                EventBus.getDefault().post(new CarType(PwType.CAM_BACK));
                Log.d(TAG, "onViewClicked: camera_back");
                if (AVMStatusManager.getInstance().isCameraBackSelect()) {
                    cameraBack.setImageResource(R.mipmap.back_n);
                    AVMStatusManager.getInstance().setCameraBackSelect(false);
                } else {
                    allCameraSetGrey();
                    NaviSendMsgToMCU.getInstance().set4AngleCamera(0x02);
                    cameraBack.setImageResource(R.mipmap.back_p);
                    AVMStatusManager.getInstance().update4CamerStatus(PwType.CAM_BACK);
                }
                break;

            case R.id.camera_left:
                EventBus.getDefault().post(new CarType(PwType.CAM_LEFT));
                Log.d(TAG, "onViewClicked: camera_left");
                if (AVMStatusManager.getInstance().isCameraLeftSelect()) {
                    cameraLeft.setImageResource(R.mipmap.left_n);
                    AVMStatusManager.getInstance().setCameraLeftSelect(false);
                } else {
                    allCameraSetGrey();
                    NaviSendMsgToMCU.getInstance().set4AngleCamera(0x03);
                    cameraLeft.setImageResource(R.mipmap.left_p);
                    AVMStatusManager.getInstance().update4CamerStatus(PwType.CAM_LEFT);
                }
                break;

            case R.id.camera_right:
                EventBus.getDefault().post(new CarType(PwType.CAM_RIGHT));
                Log.d(TAG, "onViewClicked: camera_right");
                if (AVMStatusManager.getInstance().isCameraRightSelect()) {
                    cameraRight.setImageResource(R.mipmap.right_n);
                    AVMStatusManager.getInstance().setCameraRightSelect(false);
                } else {
                    allCameraSetGrey();
                    NaviSendMsgToMCU.getInstance().set4AngleCamera(0x04);
                    cameraRight.setImageResource(R.mipmap.right_p);
                    AVMStatusManager.getInstance().update4CamerStatus(PwType.CAM_RIGHT);
                }
                break;

            case R.id.cam_front:
                EventBus.getDefault().post(new CarType(PwType.CAM_LEFT));
                Log.d(TAG, "onViewClicked: cam_front");
                if (AVMStatusManager.getInstance().isCam3DFrontSelect()) {
                    camFont.setImageResource(R.mipmap.cam_front);
                    AVMStatusManager.getInstance().setCam3DFrontSelect(false);
                } else {
                    all3DCamSetGrey();
                    camFont.setImageResource(R.mipmap.back_p);
//                    AVMStatusManager.getInstance().setCam3DFrontSelect(true);
                    AVMStatusManager.getInstance().update3DcamerStatus(PwType.CAM_3D_FRONT);
                }
                break;

            case R.id.cam_back:
                EventBus.getDefault().post(new CarType(PwType.CAM_LEFT));
                Log.d(TAG, "onViewClicked: cam_back");
                if (AVMStatusManager.getInstance().isCam3DBackSelect()) {
                    camBack.setImageResource(R.mipmap.cam_back);
                    AVMStatusManager.getInstance().setCam3DBackSelect(false);
                } else {
                    all3DCamSetGrey();
                    camBack.setImageResource(R.mipmap.front_p);
//                    AVMStatusManager.getInstance().setCam3DBackSelect(true);
                    AVMStatusManager.getInstance().update3DcamerStatus(PwType.CAM_3D_BACK);
                }
                break;

            case R.id.cam_left_front:
                EventBus.getDefault().post(new CarType(PwType.CAM_3D_LEFT_FRONT));
                Log.d(TAG, "onViewClicked: cam_left_front");
                if (AVMStatusManager.getInstance().isCam3DLeftFrontSelect()) {
                    camLeftFront.setImageResource(R.mipmap.cam_lf);
                    AVMStatusManager.getInstance().setCam3DLeftFrontSelect(false);
                } else {
                    all3DCamSetGrey();
                    camLeftFront.setImageResource(R.mipmap.cam_lf_p);
//                    AVMStatusManager.getInstance().setCam3DLeftFrontSelect(true);
                    AVMStatusManager.getInstance().update3DcamerStatus(PwType.CAM_3D_LEFT_FRONT);
                }
                break;

            case R.id.cam_right_front:
                EventBus.getDefault().post(new CarType(PwType.CAM_3D_RIGHT_FRONT));
                Log.d(TAG, "onViewClicked: cam_right_front");
                if (AVMStatusManager.getInstance().isCam3DRightFrontSelect()) {
                    camRightFront.setImageResource(R.mipmap.cam_rf);
                    AVMStatusManager.getInstance().setCam3DRightFrontSelect(false);
                } else {
                    all3DCamSetGrey();
                    camRightFront.setImageResource(R.mipmap.cam_rf_p);
//                    AVMStatusManager.getInstance().setCam3DRightFrontSelect(true);
                    AVMStatusManager.getInstance().update3DcamerStatus(PwType.CAM_3D_RIGHT_FRONT);
                }
                break;

            case R.id.cam_left_back:
                EventBus.getDefault().post(new CarType(PwType.CAM_3D_LEFT_REAR));
                Log.d(TAG, "onViewClicked: cam_left_back");
                if (AVMStatusManager.getInstance().isCam3DLeftBackSelect()) {
                    camLeftBack.setImageResource(R.mipmap.cam_lb);
                    AVMStatusManager.getInstance().setCam3DLeftBackSelect(false);
                } else {
                    all3DCamSetGrey();
                    camLeftBack.setImageResource(R.mipmap.cam_lb_p);
//                    AVMStatusManager.getInstance().setCam3DLeftBackSelect(true);
                    AVMStatusManager.getInstance().update3DcamerStatus(PwType.CAM_3D_LEFT_REAR);
                }
                break;

            case R.id.cam_right_back:
                EventBus.getDefault().post(new CarType(PwType.CAM_3D_RIGHT_REAR));
                Log.d(TAG, "onViewClicked: cam_right_back");
                if (AVMStatusManager.getInstance().isCam3DRightBackSelect()) {
                    camRightBack.setImageResource(R.mipmap.cam_rb);
//                    AVMStatusManager.getInstance().setCam3DRightBackSelect(false);
                    AVMStatusManager.getInstance().update3DcamerStatus(PwType.CAM_3D_RIGHT_REAR);
                } else {
                    all3DCamSetGrey();
                    camRightBack.setImageResource(R.mipmap.cam_rb_p);
                    AVMStatusManager.getInstance().setCam3DRightBackSelect(true);
                }
                break;
            default:
                break;
        }
    }

    public void updateCarModel(int type) {
        Log.d(TAG, "updateCarModel: type = " + type);
        switch (type) {
            case PwType.CAR_MODEL_COLOR_WHITE:
                carModel.setImageResource(R.mipmap.white_car);
                break;

            case PwType.CAR_MODEL_COLOR_BLUE:
                carModel.setImageResource(R.mipmap.blue_car);
                break;

            case PwType.CAR_MODEL_COLOR_RED:
                carModel.setImageResource(R.mipmap.red_car);
                break;

            case PwType.CAR_MODEL_COLOR_BLACK:
                carModel.setImageResource(R.mipmap.blue_car);
                break;

            case PwType.CAR_MODEL_COLOR_GREY:
                carModel.setImageResource(R.mipmap.silver_car);
                break;
            default:
                break;
        }

    }

    public void update3DCameraSelector(int type) {
        Log.d(TAG, "updateCarModel: type = " + type);
        switch (type) {
            case PwType.CAM_3D_FRONT:
                camFont.setImageResource(R.mipmap.back_p);
                break;

            case PwType.CAM_3D_BACK:
                camBack.setImageResource(R.mipmap.front_p);
                break;

            case PwType.CAM_3D_LEFT_FRONT:
                camLeftFront.setImageResource(R.mipmap.cam_lf_p);
                break;

            case PwType.CAM_3D_RIGHT_FRONT:
                camRightFront.setImageResource(R.mipmap.cam_rf_p);
                break;

            case PwType.CAM_3D_LEFT_REAR:
                camLeftBack.setImageResource(R.mipmap.cam_lb_p);
                break;

            case PwType.CAM_3D_RIGHT_REAR:
                camRightBack.setImageResource(R.mipmap.cam_rb_p);
                break;
            default:
                break;
        }

    }

    public void set3DFullSideCameraVisible(boolean flag) {
        Log.d(TAG, "set3DFullSideCameraVisible: " + flag);
        if (flag) {

            camFont.setVisibility(VISIBLE);
            camBack.setVisibility(VISIBLE);
            camLeftFront.setVisibility(VISIBLE);
            camRightFront.setVisibility(VISIBLE);
            camLeftBack.setVisibility(VISIBLE);
            camRightBack.setVisibility(VISIBLE);
        } else {
            camFont.setVisibility(INVISIBLE);
            camBack.setVisibility(INVISIBLE);
            camLeftFront.setVisibility(INVISIBLE);
            camRightFront.setVisibility(INVISIBLE);
            camLeftBack.setVisibility(INVISIBLE);
            camRightBack.setVisibility(INVISIBLE);
        }
    }

    public void setNormalCameraVisible(boolean flag) {
        Log.d(TAG, "setNormalCameraVisible: " + flag);
        if (flag) {

            cameraFront.setVisibility(VISIBLE);
            cameraBack.setVisibility(VISIBLE);
            cameraLeft.setVisibility(VISIBLE);
            cameraRight.setVisibility(VISIBLE);
        } else {
            cameraFront.setVisibility(INVISIBLE);
            cameraBack.setVisibility(INVISIBLE);
            cameraLeft.setVisibility(INVISIBLE);
            cameraRight.setVisibility(INVISIBLE);
        }
    }

    private void allCameraSetGrey() {
        cameraFront.setImageResource(R.mipmap.front_n);
        cameraBack.setImageResource(R.mipmap.back_n);
        cameraLeft.setImageResource(R.mipmap.left_n);
        cameraRight.setImageResource(R.mipmap.right_n);
    }

    public void all3DCamSetGrey() {
        camFont.setImageResource(R.mipmap.back_n);
        camBack.setImageResource(R.mipmap.front_n);
        camLeftFront.setImageResource(R.mipmap.cam_lf);
        camRightFront.setImageResource(R.mipmap.cam_rf);
        camLeftBack.setImageResource(R.mipmap.cam_lb);
        camRightBack.setImageResource(R.mipmap.cam_rb);
    }

    //左前
    public void updateRadarLeftFront() {
        int angle = RadarInfo.getInstance().getPAS_PDC_DISTANCE_FL();
        switch (angle) {
            case 0:
                radarFl.setImageResource(R.mipmap.fl_grey);
                break;

            case 1:
                radarFl.setImageResource(R.mipmap.fl_red);
                break;

            case 2:
                radarFl.setImageResource(R.mipmap.fl_yellow2);
                break;

            case 3:
                radarFl.setImageResource(R.mipmap.fl_yellow1);
                break;

            default:
                break;
        }

    }

    //左中前
    public void updateRadarLeftMiddleFront() {
        int angle = RadarInfo.getInstance().getPAS_PDC_DISTANCE_FML();
        switch (angle) {
            case 0:
                radarFml.setImageResource(R.mipmap.fml_grey);
                break;

            case 1:
                radarFml.setImageResource(R.mipmap.fml_red);
                break;

            case 2:
                radarFml.setImageResource(R.mipmap.fml_yellow2);
                break;

            case 3:
                radarFml.setImageResource(R.mipmap.fml_yellow1);
                break;

            case 4:
                radarFml.setImageResource(R.mipmap.fml_green3);
                break;

            default:
                break;
        }
    }

    //右中前
    public void updateRadarRightMiddleFront() {
        int angle = RadarInfo.getInstance().getPAS_PDC_DISTANCE_FMR();
        switch (angle) {
            case 0:
                radarFmr.setImageResource(R.mipmap.fmr_grey);
                break;

            case 1:
                radarFmr.setImageResource(R.mipmap.fmr_red);
                break;

            case 2:
                radarFmr.setImageResource(R.mipmap.fmr_yellow2);
                break;

            case 3:
                radarFmr.setImageResource(R.mipmap.fmr_yellow1);
                break;

            case 4:
                radarFmr.setImageResource(R.mipmap.fmr_green3);
                break;

            default:
                break;
        }
    }

    //右前
    public void updateRadarRightFront() {
        int angle = RadarInfo.getInstance().getPAS_PDC_DISTANCE_FR();
        switch (angle) {
            case 0:
                radarFr.setImageResource(R.mipmap.fr_grey);
                break;

            case 1:
                radarFr.setImageResource(R.mipmap.fr_red);
                break;

            case 2:
                radarFr.setImageResource(R.mipmap.fr_yellow2);
                break;

            case 3:
                radarFr.setImageResource(R.mipmap.fr_yellow1);
                break;

            default:
                break;
        }

    }

    //左后
    public void updateRadarLeftRear() {
        int angle = RadarInfo.getInstance().getPAS_PDC_DISTANCE_RL();
        switch (angle) {
            case 0:
                radarBl.setImageResource(R.mipmap.bl_grey);
                break;

            case 1:
                radarBl.setImageResource(R.mipmap.bl_red);
                break;

            case 2:
                radarBl.setImageResource(R.mipmap.bl_yellow2);
                break;

            case 3:
                radarBl.setImageResource(R.mipmap.bl_yellow1);
                break;

            default:
                break;
        }
    }

    //左中后
    public void updateRadarLeftMiddleRear() {
        int angle = RadarInfo.getInstance().getPAS_PDC_DISTANCE_RML();
        switch (angle) {
            case 0:
                radarBml.setImageResource(R.mipmap.bml_grey);
                break;

            case 1:
                radarBml.setImageResource(R.mipmap.bml_red);
                break;

            case 2:
                radarBml.setImageResource(R.mipmap.bml_yellow2);
                break;

            case 3:
                radarBml.setImageResource(R.mipmap.bml_yellow1);
                break;

            case 4:
                radarBml.setImageResource(R.mipmap.bml_green3);
                break;

            default:
                break;
        }
    }

    //右中后
    public void updateRadarRightMiddleRear() {
        int angle = RadarInfo.getInstance().getPAS_PDC_DISTANCE_RMR();
        switch (angle) {
            case 0:
                radarBmr.setImageResource(R.mipmap.bmr_grey);
                break;

            case 1:
                radarBmr.setImageResource(R.mipmap.bmr_red);
                break;

            case 2:
                radarBmr.setImageResource(R.mipmap.bmr_yellow2);
                break;

            case 3:
                radarBmr.setImageResource(R.mipmap.bmr_yellow1);
                break;

            case 4:
                radarBmr.setImageResource(R.mipmap.bmr_green3);
                break;

            default:
                break;
        }
    }

    //右后
    public void updateRadarRightRear() {
        int angle = RadarInfo.getInstance().getPAS_PDC_DISTANCE_RR();
        switch (angle) {
            case 0:
                radarBr.setImageResource(R.mipmap.br_grey);
                break;

            case 1:
                radarBr.setImageResource(R.mipmap.br_red);
                break;

            case 2:
                radarBr.setImageResource(R.mipmap.br_yellow2);
                break;

            case 3:
                radarBr.setImageResource(R.mipmap.br_yellow1);
                break;

            default:
                break;
        }
    }

    public void setRadarWorkMode() {
        int mode = RadarInfo.getInstance().getPAS_PDC_MODE();
        switch (mode) {
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            default:
                break;
        }
    }
}
