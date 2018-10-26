package com.svauto.fastrvc.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.desaysv.rvc.handler.UpdateAvmUIHandler;
import com.svauto.camera.Camera;
import com.svauto.camera.CameraService;
import com.svauto.fastrvc.R;
import com.svauto.fastrvc.manager.AVMStatusManager;
import com.svauto.fastrvc.manager.PopupWindowManager;
import com.svauto.fastrvc.message.CarType;
import com.svauto.fastrvc.message.PwType;
import com.svauto.fastrvc.protocol.ButtonInfo;
import com.svauto.fastrvc.protocol.NaviSendMsgToMCU;
import com.svauto.fastrvc.rvc.services.FastRvcService;
import com.svauto.fastrvc.util.GetTopActivity;
import com.svauto.fastrvc.view.RadarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AvmMainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.path_line)
    ImageView pathLine;
    @BindView(R.id.three_d)
    ImageView threeD;
    @BindView(R.id.mul_view)
    ImageView mulView;
    @BindView(R.id.setup)
    ImageView setup;
    @BindView(R.id.mainView)
    LinearLayout mainView;
    @BindView(R.id.radar_view)
    RadarView radarView;
    @BindView(R.id.loadingView)
    FrameLayout loadingView;

    private final GetTopActivity mgGetTopActivity = new GetTopActivity();
    private Unbinder unbinder;
    private PopupWindowManager popupWindow;
    //    private SetupView customSetupView;
    //    private MulView customMulView;
    private RadarView customRadarView;
    public volatile boolean ActivityShowing = false;
    private CameraService cs;
    public static Activity rvc;
    private WindowManager.LayoutParams params;
    private Dialog alertDialog = null;
    private Window window;
    private View dialogView;
    private ImageView frontPlusView, backPlusView, frontTwoSides, backTwoSides;
    private boolean swTuringEnterAvm = false;
    private boolean sw3DTouring = false;
    private Dialog restoreDialog;
    private Handler handler;

    private int mBackResId;
    private int mPathLineResId;
    private int mThreeDResId;
    private int mViewSelectResId;
    private int mSetupResId;
    private boolean testFlag = true;

    private final int QUIT_AVM = 0;
    private final int ICON_SHOW_AVM = 1;
    private final int RVC_SHOW_AVM = 2;

    public boolean isActivityShowing() {
        return ActivityShowing;
    }

    public void setActivityShowing(boolean activityShowing) {
        ActivityShowing = activityShowing;
    }

    private int touchX, touchY, xMove, yMove;
    private int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        rvc = this;
        initTheme();
        getWindow().getDecorView().setSystemUiVisibility(flags);    //隐藏systemUI
        setContentView(R.layout.activity_avm_main);

        if (AVMStatusManager.getInstance().getAvmStatus() == QUIT_AVM) {
            //点击icon,主动显示全景,发送显示请求给AVM
            AVMStatusManager.getInstance().setAvmStatus(ICON_SHOW_AVM);
            NaviSendMsgToMCU.getInstance().requestAvm2MCU(0x01);
        }

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        Intent it = new Intent(this, FastRvcService.class);
        this.startService(it);

    }

    private Runnable showLayoutRunnable = new Runnable() {
        @Override
        public void run() {
//            if (loadingView != null) {
//                loadingView.setVisibility(View.GONE);
//            }
        }
    };

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        if (cs == null) {
            cs = Camera.getCameraService();
        }
        new Handler().post(new Runnable() {
            public void run() {
                if (cs != null) cs.SetUiVisible(true);
            }
        });

//        TheftServiceManager.getInstance().sendCmdToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_INIT, 0x03);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    private void initTheme() {
        setTheme(R.style.AppTheme_default);
        TypedValue typedValue = new TypedValue();
        this.getTheme().resolveAttribute(R.style.AppTheme_blue, typedValue, true);

        int[] attribute = new int[]{
                R.attr.backKeyIcon, R.attr.pathLineIcon, R.attr.threeDIcon,
                R.attr.viewSelectIcon, R.attr.setupIcon
        };

        TypedArray array = this.obtainStyledAttributes(typedValue.resourceId, attribute);
        mBackResId = array.getResourceId(0, 0);
        mPathLineResId = array.getResourceId(1, 0);
        mThreeDResId = array.getResourceId(2, 0);
        mViewSelectResId = array.getResourceId(3, 0);
        mSetupResId = array.getResourceId(4, 0);
        array.recycle();
    }

    private void initView() {
        cs = Camera.getCameraService();
        if (cs != null && !cs.isRVCSignalActive()) {
            Log.i(TAG, "Rvc signal not active come before onCreate");
        }

        customRadarView = findViewById(R.id.radar_view);
        loadingView.findViewById(R.id.loadingView);
        if (AVMStatusManager.getInstance().getAvmStatus() == RVC_SHOW_AVM) {
            loadingView.setVisibility(View.GONE);

        }

//        if (testFlag) {
//            testFlag = false;
//            if (handler == null) {
//                handler = new Handler();
//            }
//            handler.postDelayed(showLayoutRunnable, 5000);
//        }

    }

//    private void showPopupWindow(View parent, View customView, int type) {
//        popupWindow = PopupWindowManager.getInstance();
//        popupWindow.setCustomViewToWin(parent, customView, type);
//    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged: " + hasFocus);
        getWindow().getDecorView().setSystemUiVisibility(flags);
        if (hasFocus) {
            setActivityShowing(true);
        } else {
            setActivityShowing(false);
        }
    }

    private boolean dismissNormalDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
            return true;
        }
        return false;
    }

    private void dismissRestoreDialog() {
        if (restoreDialog != null && restoreDialog.isShowing()) {
            restoreDialog.dismiss();
        }
    }

    @OnClick({R.id.back, R.id.path_line, R.id.three_d, R.id.mul_view, R.id.setup})
    public void onViewClicked(View view) {

        switch (view.getId()) {
//            case R.id.mainView:
//                Log.d(TAG, "onViewClicked: 1111111");
//                break;

            case R.id.back:
                Log.d(TAG, "onViewClicked: R.id.back");
//                if (popupWindow != null && popupWindow.isPwShowing()) {
//                    popupWindow.dismissPW();
//                    popupWindow.setPwShowing(false);
//                } else {
//                    finish();
//                }
                dismissRestoreDialog();
                allBtnSetGrey();
//                back.setImageResource(mBackResId);
                AVMStatusManager.getInstance().quitAVMResetBtnFlag();
                NaviSendMsgToMCU.getInstance().requestAvm2MCU(0x02);
//                finish();
                break;

            case R.id.path_line:
                Log.d(TAG, "onViewClicked: R.id.path_line");
                dismissRestoreDialog();
                dismissNormalDialog();
                allBtnSetGrey();
                if (AVMStatusManager.getInstance().isPathLineSelect()) {
                    AVMStatusManager.getInstance().quitAVMResetBtnFlag();
                    NaviSendMsgToMCU.getInstance().setPathLine2MCU(0x01);
                } else {
                    AVMStatusManager.getInstance().updateAvmActivityButton(PwType.PATH_LINE_SELECT);
//                    pathLine.setImageResource(R.mipmap.r_path_line);
                    pathLine.setImageResource(mPathLineResId);

                    NaviSendMsgToMCU.getInstance().setPathLine2MCU(0x02);
                }
                break;

            case R.id.three_d:
                Log.d(TAG, "3D select: " + AVMStatusManager.getInstance().isIs3DImageSelect());
                dismissRestoreDialog();
                dismissNormalDialog();
                allBtnSetGrey();
                if (AVMStatusManager.getInstance().isIs3DImageSelect()) {
                    AVMStatusManager.getInstance().quitAVMResetBtnFlag();
                    NaviSendMsgToMCU.getInstance().set3DViewMode2MCU(0x01);
                    if (customRadarView == null) {
                        customRadarView = findViewById(R.id.radar_view);
                    }
                    customRadarView.set3DFullSideCameraVisible(false);
                    customRadarView.setNormalCameraVisible(true);

                } else {
                    Log.d(TAG, "onViewClicked: 3D need set icon");
                    threeD.setImageResource(mThreeDResId);
                    AVMStatusManager.getInstance().updateAvmActivityButton(PwType.THREE_D_SELECT);
                    NaviSendMsgToMCU.getInstance().set3DViewMode2MCU(0x03);
                    if (customRadarView == null) {
                        customRadarView = findViewById(R.id.radar_view);
                    }
                    customRadarView.set3DFullSideCameraVisible(true);
                    customRadarView.setNormalCameraVisible(false);
                }

                break;

            case R.id.mul_view:
                Log.d(TAG, "onViewClicked: R.id.path_line");
                dismissRestoreDialog();
                allBtnSetGrey();
                if (AVMStatusManager.getInstance().isMulViewSelect()) {
                    AVMStatusManager.getInstance().quitAVMResetBtnFlag();
                } else {
                    mulView.setImageResource(mViewSelectResId);
                    AVMStatusManager.getInstance().updateAvmActivityButton(PwType.MUL_VIEW_SELECT);
                    showMulViewDialog();
//                    showAddViewTest();

                }

                break;

            case R.id.setup:
                Log.d(TAG, "onViewClicked: R.id.setup select = " + AVMStatusManager.getInstance().isSetupSelect());
                dismissRestoreDialog();
                allBtnSetGrey();
                if (AVMStatusManager.getInstance().isSetupSelect()) {
                    AVMStatusManager.getInstance().quitAVMResetBtnFlag();
//                    if (popupWindow != null && popupWindow.isPwShowing()) {
//                        popupWindow.dismissPW();
//                        popupWindow.setPwShowing(false);
//                    }
                } else {
                    setup.setImageResource(mSetupResId);
                    AVMStatusManager.getInstance().updateAvmActivityButton(PwType.SETUP_SELECT);
                    showSetupDialog();
//                    if (isActivityShowing()) {
//                        showPopupWindow(setup, customSetupView, PwType.SETUP_WINDOW);
//                    }
                }


                break;

            default:
                break;
        }
    }

    public void startHomeActivity() {
        Log.d(TAG, "startHomeActivity()");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //如果是服务里调用，必须加入new task标识
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    private void allBtnSetGrey() {
        pathLine.setImageResource(R.mipmap.path_line);
        threeD.setImageResource(R.mipmap.three_d);
        mulView.setImageResource(R.mipmap.mul_view_change);
        setup.setImageResource(R.mipmap.setup);

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
//        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        UpdateAvmUIHandler.releaseUpdateAvmUIHandler();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCar(CarType carType) {
        switch (carType.getType()) {
            case PwType.SOFTKEY_QUIT_AVM:
                AVMStatusManager.getInstance().setAvmStatus(QUIT_AVM);
                if (!isFinishing()) {
                    Log.d(TAG, "updateCar: finish");
                    finish();
                }
                break;

            case PwType.BUTTON_BACK:
                if (back != null) {
                    back.setImageResource(R.mipmap.disable_back);
                }
                break;

            case PwType.BUTTON_PATHLINE:
                if (pathLine != null) {
                    pathLine.setImageResource(R.mipmap.disable_pathline);
                }
                break;

            case PwType.BUTTON_THREED:
                if (threeD != null) {
                    threeD.setImageResource(R.mipmap.disable_threed);
                }
                break;

            case PwType.BUTTON_MULVIEW:
                if (mulView != null) {
                    mulView.setImageResource(R.mipmap.disable_mulview);
                }
                break;

            case PwType.BUTTON_SETUP:
                if (setup != null) {
                    setup.setImageResource(R.mipmap.disable_setup);
                }
                break;

            case PwType.SOFTKEY_AVM_DISPLAY:
                if (loadingView == null) {
                    loadingView = findViewById(R.id.loadingView);
                }
//                setContentView(R.layout.activity_avm_main);
                Log.d(TAG, "updateCar: hide loading");
                loadingView.setVisibility(View.GONE);
                break;

            case PwType.CAR_MODEL_COLOR_WHITE:
            case PwType.CAR_MODEL_COLOR_BLUE:
            case PwType.CAR_MODEL_COLOR_RED:
            case PwType.CAR_MODEL_COLOR_BLACK:
            case PwType.CAR_MODEL_COLOR_GREY:
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(carType.getType());

                break;

            case PwType.UPDATE_CAM_SELECTOR:
                int cam = ButtonInfo.getInstance().getCAM_SELECTOR();
                AVMStatusManager.getInstance().updateAvmActivityButton(PwType.THREE_D_SELECT);
                threeD.setImageResource(R.mipmap.r_three_d);
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.set3DFullSideCameraVisible(true);
                customRadarView.setNormalCameraVisible(false);
                updateCamSelector(cam);
                break;

            // radar part start:-------------------
            case PwType.RADAR_LEFT_FRONT:
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateRadarLeftFront();

                break;

            case PwType.RADAR_LEFT_MIDDLE_FRONT:
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateRadarLeftMiddleFront();
                break;

            case PwType.RADAR_RIGHT_MIDDLE_FRONT:
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateRadarRightMiddleFront();

                break;

            case PwType.RADAR_RIGHT_FRONT:
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateRadarRightFront();

                break;

            case PwType.RADAR_LEFT_REAR:
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateRadarLeftRear();

                break;

            case PwType.RADAR_LEFT_MIDDLE_REAR:
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateRadarLeftMiddleRear();

                break;

            case PwType.RADAR_RIGHT_MIDDLE_REAR:
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateRadarRightMiddleRear();

                break;

            case PwType.RADAR_RIGHT_REAR:
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateRadarRightRear();

                break;

            case PwType.RADAR_PDC_MODE:

                break;
            // radar part end:-------------------


            case PwType.AVM_TURNING_ENTER_AVM_ON:
                swTuringEnterAvm = true;
                break;

            case PwType.AVM_TURNING_ENTER_AVM_OFF:
                swTuringEnterAvm = false;
                break;

            case PwType.AVM_3D_TOURING_KEY_RESP_ON:
                sw3DTouring = true;
                break;

            case PwType.AVM_3D_TOURING_KEY_RESP_OFF:
                sw3DTouring = false;
                break;

            case PwType.CAM_FRONT:
            case PwType.CAM_BACK:
            case PwType.CAM_LEFT:
            case PwType.CAM_RIGHT:
            case PwType.CAM_3D_FRONT:
            case PwType.CAM_3D_BACK:
            case PwType.CAM_3D_LEFT_FRONT:
            case PwType.CAM_3D_RIGHT_FRONT:
            case PwType.CAM_3D_LEFT_REAR:
            case PwType.CAM_3D_RIGHT_REAR:
                dismissNormalDialog();
                dismissRestoreDialog();
                break;
            default:
                break;
        }
    }

//    @SuppressLint("HandlerLeak")
//    private Handler updateAvmHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//            }
//
//        }
//    };

    private void updateCamSelector(int cam) {
        if (customRadarView == null) {
            Log.d(TAG, "updateCamSelector: customRadarView is null !");
            customRadarView = findViewById(R.id.radar_view);
        }
        customRadarView.all3DCamSetGrey();

        switch (cam) {
            case 0x01:
                customRadarView.update3DCameraSelector(PwType.CAM_3D_FRONT);
                break;

            case 0x04:
                customRadarView.update3DCameraSelector(PwType.CAM_3D_BACK);
                break;

            case 0x08:
                customRadarView.update3DCameraSelector(PwType.CAM_3D_LEFT_FRONT);
                break;

            case 0x09:
                customRadarView.update3DCameraSelector(PwType.CAM_3D_RIGHT_FRONT);
                break;

            case 0x0A:
                customRadarView.update3DCameraSelector(PwType.CAM_3D_LEFT_REAR);
                break;

            case 0x0B:
                customRadarView.update3DCameraSelector(PwType.CAM_3D_RIGHT_REAR);
                break;

            default:
                break;


        }
    }

    private void allMulViewGrey() {
        frontPlusView.setImageResource(R.mipmap.front_plus_view);
        backPlusView.setImageResource(R.mipmap.back_plus_view);
        frontTwoSides.setImageResource(R.mipmap.front_two_sides);
        backTwoSides.setImageResource(R.mipmap.back_two_sides);
    }

    private void showAddViewTest() {
        View view = LayoutInflater.from(this).inflate(R.layout.mulviewlayout, null);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        params.width = 500;
        params.height = 220;
        params.x = 130;
        params.y = 400;
        params.gravity = Gravity.START | Gravity.TOP;
        getWindowManager().addView(view, params);

//        if (alertDialog == null) {
//            alertDialog = new CustomDialog(this, R.style.customDialog,R.layout.mulviewlayout);
//        }
//        window = alertDialog.getWindow();
//        if (window != null) {
//            params = window.getAttributes();
////            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
////                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
////            window.getDecorView().setSystemUiVisibility(flags);
//        }
//        dialogView = LayoutInflater.from(this).inflate(R.layout.mulviewlayout, null);
//        params.x = 130;
//        params.y = 450;
//        window.setAttributes(params);
//
//        window.setGravity(Gravity.START | Gravity.TOP);
//        alertDialog.setCanceledOnTouchOutside(true);
//        alertDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(600, 130));
//        alertDialog.show();
    }

    private void showMulViewDialog() {

        setDialogParam(PwType.DIALOG_MUL_VIEW);
        frontPlusView = dialogView.findViewById(R.id.front_plus_view);
        backPlusView = dialogView.findViewById(R.id.back_plus_view);
        frontTwoSides = dialogView.findViewById(R.id.front_two_sides);
        backTwoSides = dialogView.findViewById(R.id.back_two_sides);

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!alertDialog.isShowing()) {
                    allBtnSetGrey();
                    AVMStatusManager.getInstance().quitAVMResetBtnFlag();
                }
            }
        });

        frontPlusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AVMStatusManager.getInstance().isMulViewFrontSelect()) {
                    AVMStatusManager.getInstance().setMulViewFrontSelect(false);
                    NaviSendMsgToMCU.getInstance().mulViewSwitcher2MCU(0x00);
                    frontPlusView.setImageResource(R.mipmap.front_plus_view);
                } else {
                    AVMStatusManager.getInstance().updateMulViewStatus(PwType.MUL_VIEW_FRONT_SIDE);
                    allMulViewGrey();
                    NaviSendMsgToMCU.getInstance().mulViewSwitcher2MCU(0x01);
                    frontPlusView.setImageResource(R.mipmap.front_plus_view_p);
                }
            }
        });

        backPlusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AVMStatusManager.getInstance().isMulViewBackSelect()) {
                    AVMStatusManager.getInstance().setMulViewBackSelect(false);
                    backPlusView.setImageResource(R.mipmap.back_plus_view);
                    NaviSendMsgToMCU.getInstance().mulViewSwitcher2MCU(0x00);
                } else {
                    AVMStatusManager.getInstance().updateMulViewStatus(PwType.MUL_VIEW_BACK_SIDE);
                    allMulViewGrey();
                    NaviSendMsgToMCU.getInstance().mulViewSwitcher2MCU(0x02);
                    backPlusView.setImageResource(R.mipmap.back_plus_view_p);
                }
            }
        });

        frontTwoSides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AVMStatusManager.getInstance().isMulViewFront2SideSelect()) {
                    AVMStatusManager.getInstance().setMulViewFront2SideSelect(false);
                    frontTwoSides.setImageResource(R.mipmap.front_two_sides);
                    NaviSendMsgToMCU.getInstance().mulViewSwitcher2MCU(0x00);
                } else {
                    AVMStatusManager.getInstance().updateMulViewStatus(PwType.MUL_VIEW_FRONT_2_SIDE);
                    allMulViewGrey();
                    NaviSendMsgToMCU.getInstance().mulViewSwitcher2MCU(0x03);
                    frontTwoSides.setImageResource(R.mipmap.front_two_sides_p);
                }
            }
        });

        backTwoSides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AVMStatusManager.getInstance().isMulViewBack2SideSelect()) {
                    AVMStatusManager.getInstance().setMulViewBack2SideSelect(false);
                    backTwoSides.setImageResource(R.mipmap.back_two_sides);
                    NaviSendMsgToMCU.getInstance().mulViewSwitcher2MCU(0x00);
                } else {
                    AVMStatusManager.getInstance().updateMulViewStatus(PwType.MUL_VIEW_BACK_2_SIDE);
                    allMulViewGrey();
                    NaviSendMsgToMCU.getInstance().mulViewSwitcher2MCU(0x04);
                    backTwoSides.setImageResource(R.mipmap.back_two_sides_p);
                }
            }
        });
    }

    private void showCarColorDialog() {
        setDialogParam(PwType.DIALOG_CAR_MODEL_COLOR);
        TextView white_tx = dialogView.findViewById(R.id.color_white);
        TextView blue_tx = dialogView.findViewById(R.id.color_blue);
        TextView red_tx = dialogView.findViewById(R.id.color_red);
        TextView black_tx = dialogView.findViewById(R.id.color_black);
        TextView grey_tx = dialogView.findViewById(R.id.color_gray);

        ImageView white_im = dialogView.findViewById(R.id.im_white);
        ImageView blue_im = dialogView.findViewById(R.id.im_blue);
        ImageView red_im = dialogView.findViewById(R.id.im_red);
        ImageView black_im = dialogView.findViewById(R.id.im_black);
        ImageView grey_im = dialogView.findViewById(R.id.im_gray);

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                allBtnSetGrey();
                AVMStatusManager.getInstance().quitAVMResetBtnFlag();
            }
        });

        white_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(PwType.CAR_MODEL_COLOR_WHITE);
                NaviSendMsgToMCU.getInstance().setCarColor2MCU(0x01);
                alertDialog.dismiss();
            }
        });

        blue_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(PwType.CAR_MODEL_COLOR_BLUE);
                NaviSendMsgToMCU.getInstance().setCarColor2MCU(0x02);
                alertDialog.dismiss();
            }
        });

        red_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(PwType.CAR_MODEL_COLOR_RED);
                NaviSendMsgToMCU.getInstance().setCarColor2MCU(0x03);
                alertDialog.dismiss();
            }
        });

        black_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(PwType.CAR_MODEL_COLOR_BLACK);
                NaviSendMsgToMCU.getInstance().setCarColor2MCU(0x04);
                alertDialog.dismiss();
            }
        });

        grey_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(PwType.CAR_MODEL_COLOR_GREY);
                NaviSendMsgToMCU.getInstance().setCarColor2MCU(0x05);
                alertDialog.dismiss();
            }
        });

        white_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(PwType.CAR_MODEL_COLOR_WHITE);
                NaviSendMsgToMCU.getInstance().setCarColor2MCU(0x01);
                alertDialog.dismiss();
            }
        });

        blue_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(PwType.CAR_MODEL_COLOR_BLUE);
                NaviSendMsgToMCU.getInstance().setCarColor2MCU(0x02);
                alertDialog.dismiss();
            }
        });

        red_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(PwType.CAR_MODEL_COLOR_RED);
                NaviSendMsgToMCU.getInstance().setCarColor2MCU(0x03);
                alertDialog.dismiss();
            }
        });

        black_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(PwType.CAR_MODEL_COLOR_BLACK);
                NaviSendMsgToMCU.getInstance().setCarColor2MCU(0x04);
                alertDialog.dismiss();
            }
        });

        grey_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRadarView == null) {
                    customRadarView = findViewById(R.id.radar_view);
                }
                customRadarView.updateCarModel(PwType.CAR_MODEL_COLOR_GREY);
                NaviSendMsgToMCU.getInstance().setCarColor2MCU(0x05);
                alertDialog.dismiss();
            }
        });
    }

    private void showSetupDialog() {
        setDialogParam(PwType.DIALOG_AVM_SETUP);
        TextView carColor = dialogView.findViewById(R.id.car_color);
        TextView restoreFactorySetting = dialogView.findViewById(R.id.restore_factory_setting);
        Switch linkageSwitch = dialogView.findViewById(R.id.linkage_switch);
        Switch threeDSwitch = dialogView.findViewById(R.id.three_d_switch);

        linkageSwitch.setChecked(swTuringEnterAvm);
        threeDSwitch.setChecked(sw3DTouring);

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.d(TAG, "onDismiss: setup dialog");
                if (!alertDialog.isShowing()) {
                    allBtnSetGrey();
                    AVMStatusManager.getInstance().quitAVMResetBtnFlag();
                    alertDialog = null;
                }
            }
        });

        carColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCarColorDialog();
            }
        });

        restoreFactorySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog != null) {
                    alertDialog.dismiss();
                }
                showRestoreDialog();
            }
        });

        linkageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NaviSendMsgToMCU.getInstance().setTurningEnterAvmSwitch2MCU(0x02);
                } else {
                    NaviSendMsgToMCU.getInstance().setTurningEnterAvmSwitch2MCU(0x01);
                }
            }
        });

        threeDSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    NaviSendMsgToMCU.getInstance().set3DTouringViewSwitch2MCU(0x02);
                } else {
                    NaviSendMsgToMCU.getInstance().set3DTouringViewSwitch2MCU(0x01);
                }
            }
        });
    }

    private void showRestoreDialog() {

//        setDialogParam(PwType.DIALOG_RESTORE);
        restoreDialog = new Dialog(this, R.style.AlertDialog);
        Window windows = restoreDialog.getWindow();
        WindowManager.LayoutParams restoreParams = window.getAttributes();

        dialogView = LayoutInflater.from(this).inflate(R.layout.restorelayout, null);
        restoreParams.x = 0;
        restoreParams.y = 0;
        restoreParams.dimAmount = 0.85f;
        assert windows != null;
        windows.setAttributes(params);
        windows.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        windows.setWindowAnimations(R.style.dialog_anim);

        windows.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        restoreDialog.setCanceledOnTouchOutside(false);
        restoreDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(600, 300));
        restoreDialog.show();

        TextView restore = dialogView.findViewById(R.id.restore);
        TextView cancel = dialogView.findViewById(R.id.cancel);

        restoreDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!restoreDialog.isShowing()) {
                    Log.d(TAG, "restore dismiss :11111 ");
                    allBtnSetGrey();
                    AVMStatusManager.getInstance().quitAVMResetBtnFlag();
                    dialogView = null;
                }
                Log.d(TAG, "restore dismiss :22222 ");
            }
        });
        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaviSendMsgToMCU.getInstance().setRestoreFactorySetting2MCU();
                restoreDialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreDialog.dismiss();
            }
        });
    }

    private void setDialogParam(int type) {
        if (alertDialog == null) {
            alertDialog = new Dialog(this, R.style.AlertDialog);
        }
        window = alertDialog.getWindow();
        if (window != null) {
            params = window.getAttributes();
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            window.getDecorView().setSystemUiVisibility(flags);
        }

        switch (type) {
            case PwType.DIALOG_RESTORE:
                dialogView = LayoutInflater.from(this).inflate(R.layout.restorelayout, null);
                params.x = 0;
                params.y = 0;
                window.setAttributes(params);
                alertDialog.getWindow().setWindowAnimations(R.style.dialog_anim);

                window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(600, 300));
                alertDialog.show();
                break;

            case PwType.DIALOG_AVM_SETUP:
                dialogView = LayoutInflater.from(this).inflate(R.layout.setuplayouttmp, null);
                params.x = 130;
                params.y = 350;
                window.setAttributes(params);

                window.setGravity(Gravity.START | Gravity.TOP);
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(508, 350));
                alertDialog.show();
                break;

            case PwType.DIALOG_MUL_VIEW:
                dialogView = LayoutInflater.from(this).inflate(R.layout.mulviewlayout, null);
                params.x = 130;
                params.y = 480;
                window.setAttributes(params);

                window.setGravity(Gravity.START | Gravity.TOP);
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(560, 140));
                alertDialog.show();
                break;

            case PwType.DIALOG_CAR_MODEL_COLOR:
                dialogView = LayoutInflater.from(this).inflate(R.layout.colorofcar, null);
                params.x = 130;
                params.y = 250;
                window.setAttributes(params);
                alertDialog.show();
                window.setGravity(Gravity.START | Gravity.TOP);
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(508, 425));
                break;

            default:
                break;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (dismissNormalDialog()) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = (int) event.getX();
                touchY = (int) event.getY();
                if (touchX >= 640) {
                    NaviSendMsgToMCU.getInstance().sendCoordinate2MCU(touchX, touchY, 1);
                }
                Log.d(TAG, "onTouchEvent  down : x = " + touchX + " , y = " + touchX);
                break;

            case MotionEvent.ACTION_UP:
                touchX = (int) event.getX();
                touchY = (int) event.getY();
                if (touchX >= 640) {
                    Log.d(TAG, "onTouchEvent up : x = " + touchX + " , y = " + touchY);
                    NaviSendMsgToMCU.getInstance().sendCoordinate2MCU(touchX, touchY, 2);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                xMove = (int) event.getX();
                yMove = (int) event.getY();
                Log.d(TAG, "onTouchEvent move : x = " + touchX + " , y = " + touchY);

                if (xMove >= 640) {
                    NaviSendMsgToMCU.getInstance().sendCoordinate2MCU(touchX, touchY, 3);
                }
                break;


            default:
                break;

        }

        return true;

    }
}
