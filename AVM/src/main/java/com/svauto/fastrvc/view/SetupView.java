//package com.svauto.fastrvc.view;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.CompoundButton;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.RelativeLayout;
//import android.widget.Switch;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.desaysv.rvc.message.CarType;
//import com.desaysv.rvc.message.PwType;
//import com.desaysv.rvc.message.TheftServiceMessage;
//import com.svauto.fastrvc.R;
//import com.svauto.fastrvc.manager.TheftServiceManager;
//
//import org.greenrobot.eventbus.EventBus;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class SetupView extends RelativeLayout {
//    private final String TAG = this.getClass().getSimpleName();
//
//    @BindView(R.id.car_color)
//    TextView carColor;
//    @BindView(R.id.imageSetup)
//    TextView imageSetup;
//    @BindView(R.id.three_d_select)
//    TextView threeDSelect;
//    @BindView(R.id.three_d_switch)
//    Switch threeDSwitch;
//    @BindView(R.id.turn_linkage)
//    TextView turnLinkage;
//    @BindView(R.id.linkage_switch)
//    Switch linkageSwitch;
//    @BindView(R.id.move_detect)
//    TextView moveDetect;
//    @BindView(R.id.move_detect_switch)
//    Switch moveDetectSwitch;
//    @BindView(R.id.people_detect)
//    TextView peopleDetect;
//    @BindView(R.id.people_detect_switch)
//    Switch peopleDetectSwitch;
//    @BindView(R.id.road_rate)
//    TextView roadRate;
//    @BindView(R.id.restore_factory_setting)
//    TextView restoreFactorySetting;
//    @BindView(R.id.setupView)
//    RelativeLayout setupView;
//    private CarModelColorView carModelColorView;
//    private ImageSetupView imageSetupView;
//
//    private WindowManager.LayoutParams params;
//    private Dialog alertDialog = null;
//    private Window window;
//    private View dialogView;
//
//    public SetupView(Context context) {
//        super(context, null);
//        initView();
//    }
//
//    public SetupView(Context context, AttributeSet attrs) {
//        super(context, attrs, 0);
//        initView();
//
//    }
//
//    public SetupView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initView();
//    }
//
//    private void initView() {
//        View v = LayoutInflater.from(getContext()).inflate(R.layout.setuplayout, this);
//        ButterKnife.bind(this);
//        carModelColorView = new CarModelColorView(getContext());
//        imageSetupView = new ImageSetupView(getContext());
//
//        linkageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    setTurningEnterAvmSwitch2MCU(0x02); //打开
//                } else {
//                    setTurningEnterAvmSwitch2MCU(0x01); //关闭
//                }
//            }
//        });
//
//        threeDSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    set3DTouringViewSwitch2MCU(0x02);
//                } else {
//                    set3DTouringViewSwitch2MCU(0x01);
//                }
//            }
//        });
//    }
//
//    @OnClick({R.id.car_color, R.id.imageSetup, R.id.three_d_select, R.id.three_d_switch, R.id.turn_linkage, R.id.linkage_switch, R.id.move_detect_switch, R.id.people_detect, R.id.people_detect_switch, R.id.road_rate, R.id.restore_factory_setting, R.id.setupView})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.car_color:
//                Log.d(TAG, "onViewClicked: car_color");
////                showPopupWindow(carColor, carModelColorView, PwType.CARMODEL_COLOR_WINDOW);
//                EventBus.getDefault().post(new CarType(PwType.DISMISS_SETUP_WINDOW));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_SELECT));
////                DialogManager.getInstance(getContext()).showCarColorDialog();
//                showCarColorDialog();
//                break;
//
//            case R.id.imageSetup:
//                EventBus.getDefault().post(new CarType(PwType.DISMISS_SETUP_WINDOW));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_SELECT));
////                showPopupWindow(imageSetup, imageSetupView, PwType.IMAGE_SETUP_WINDOW);
////                DialogManager.getInstance(getContext()).showImageSetDialog();
//                showImageSetDialog();
//                break;
//
//            case R.id.three_d_select:
//                break;
//
//            case R.id.three_d_switch:
//                break;
//
//            case R.id.turn_linkage:
//                break;
//
//            case R.id.linkage_switch:
//                break;
//
//            case R.id.move_detect_switch:
//                break;
//
//            case R.id.people_detect:
//                break;
//
//            case R.id.people_detect_switch:
//                break;
//
//            case R.id.road_rate:
//                break;
//
//            case R.id.restore_factory_setting:
////                DialogManager.getInstance(getContext()).showRestoreDialog();
//                EventBus.getDefault().post(new CarType(PwType.DISMISS_SETUP_WINDOW));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_SELECT));
//                showRestoreDialog();
//                break;
//
//            default:
//                break;
//        }
//    }
//
//    private void showRestoreDialog() {
//        setRestoreFactorySetting2MCU();
//
//        setDialogParam(PwType.DIALOG_RESTORE);
//        Button restore = dialogView.findViewById(R.id.restore);
//        Button cancel = dialogView.findViewById(R.id.cancel);
//
//        restore.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                Toast.makeText(getContext(), "restore", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        cancel.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                Toast.makeText(getContext(), "cancel", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void showImageSetDialog() {
//        setDialogParam(PwType.DIALOG_IMAGE_SETUP);
//        TextView brightness = dialogView.findViewById(R.id.brightness_adjust);
//        TextView contrast = dialogView.findViewById(R.id.contrast_adjust);
//        TextView saturability = dialogView.findViewById(R.id.saturability_adjust);
//
//        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                if (!alertDialog.isShowing()) {
//                    Log.d(TAG, "isShowing: " + alertDialog.isShowing());
//                    EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                    Log.d(TAG, "alertDialog onDismiss: 2222 " + alertDialog);
//                }
//            }
//        });
//
//        brightness.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                EventBus.getDefault().post(new CarType(PwType.SETUP_SELECT));
//                showImageAdjustDialog(PwType.ADJUST_OF_BRIGHTNESS);
//            }
//        });
//
//        contrast.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                EventBus.getDefault().post(new CarType(PwType.SETUP_SELECT));
//                showImageAdjustDialog(PwType.ADJUST_OF_CONTRAST);
//            }
//        });
//
//        saturability.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                EventBus.getDefault().post(new CarType(PwType.SETUP_SELECT));
//                showImageAdjustDialog(PwType.ADJUST_OF_SATURABILITY);
//            }
//        });
//    }
//
//    private void showCarColorDialog() {
//        setDialogParam(PwType.DIALOG_CAR_MODEL_COLOR);
//        TextView white_tx = dialogView.findViewById(R.id.color_white);
//        TextView blue_tx = dialogView.findViewById(R.id.color_blue);
//        TextView red_tx = dialogView.findViewById(R.id.color_red);
//        TextView black_tx = dialogView.findViewById(R.id.color_black);
//        TextView grey_tx = dialogView.findViewById(R.id.color_gray);
//
//        ImageView white_im = dialogView.findViewById(R.id.im_white);
//        ImageView blue_im = dialogView.findViewById(R.id.im_blue);
//        ImageView red_im = dialogView.findViewById(R.id.im_red);
//        ImageView black_im = dialogView.findViewById(R.id.im_black);
//        ImageView grey_im = dialogView.findViewById(R.id.im_gray);
//
//        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//            }
//        });
//
//        white_tx.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_WHITE));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                setCarColor2MCU(0x01);
//                alertDialog.dismiss();
//            }
//        });
//
//        blue_tx.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLUE));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                setCarColor2MCU(0x02);
//                alertDialog.dismiss();
//            }
//        });
//
//        red_tx.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_RED));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                setCarColor2MCU(0x03);
//                alertDialog.dismiss();
//            }
//        });
//
//        black_tx.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLACK));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                setCarColor2MCU(0x04);
//                alertDialog.dismiss();
//            }
//        });
//
//        grey_tx.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_GREY));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                setCarColor2MCU(0x05);
//                alertDialog.dismiss();
//            }
//        });
//
//        white_im.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_WHITE));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                setCarColor2MCU(0x01);
//                alertDialog.dismiss();
//            }
//        });
//
//        blue_im.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLUE));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                setCarColor2MCU(0x02);
//                alertDialog.dismiss();
//            }
//        });
//
//        red_im.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_RED));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                setCarColor2MCU(0x03);
//                alertDialog.dismiss();
//            }
//        });
//
//        black_im.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLACK));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                setCarColor2MCU(0x04);
//                alertDialog.dismiss();
//            }
//        });
//
//        grey_im.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_GREY));
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//                setCarColor2MCU(0x05);
//                alertDialog.dismiss();
//            }
//        });
//    }
//
//    private void showImageAdjustDialog(int type) {
//        setDialogParam(PwType.DIALOG_IMAGE_ADJUST);
//        TextView image_adjust_item = dialogView.findViewById(R.id.image_adjust_item);
//        RatingBar rb_adjust = dialogView.findViewById(R.id.rb_adjust);
//
//        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                EventBus.getDefault().post(new CarType(PwType.SETUP_NOT_SELECT));
//            }
//        });
//
//        EventBus.getDefault().post(new CarType(PwType.SETUP_SELECT));
//        Log.d(TAG, "showImageAdjustDialog: 3333 " + alertDialog);
//        switch (type) {
//            case PwType.ADJUST_OF_BRIGHTNESS:
//                image_adjust_item.setText(R.string.brightnessAdjust);
//                rb_adjust.setRating(0);
//                break;
//
//            case PwType.ADJUST_OF_CONTRAST:
//                image_adjust_item.setText(R.string.contrastAdjust);
//                rb_adjust.setRating(0);
//                break;
//
//            case PwType.ADJUST_OF_SATURABILITY:
//                image_adjust_item.setText(R.string.saturabilityAdjust);
//                rb_adjust.setRating(0);
//                break;
//
//            default:
//                break;
//        }
//    }
//
//    private void setDialogParam(int type) {
//        if (alertDialog == null) {
//            alertDialog = new Dialog(getContext(), R.style.AlertDialog);
//        }
//        window = alertDialog.getWindow();
//        if (window != null) {
//            params = window.getAttributes();
//        }
//
//        switch (type) {
//            case PwType.DIALOG_RESTORE:
//                dialogView = LayoutInflater.from(getContext()).inflate(R.layout.restorelayout, null);
//                params.x = 0;
//                params.y = 0;
//                window.setAttributes(params);
//                alertDialog.show();
//                window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
//                alertDialog.setCanceledOnTouchOutside(false);
//                alertDialog.setContentView(dialogView, new LayoutParams(600, 300));
//                break;
//
//            case PwType.DIALOG_CAR_MODEL_COLOR:
//                dialogView = LayoutInflater.from(getContext()).inflate(R.layout.colorofcar, null);
//                params.x = 130;
//                params.y = 400;
//                window.setAttributes(params);
//                alertDialog.show();
//                window.setGravity(Gravity.START | Gravity.TOP);
//                alertDialog.setCanceledOnTouchOutside(true);
//                alertDialog.setContentView(dialogView, new LayoutParams(600, 500));
//                break;
//
//            case PwType.DIALOG_IMAGE_SETUP:
//                dialogView = LayoutInflater.from(getContext()).inflate(R.layout.imagesetuplayout, null);
//                params.x = 130;
//                params.y = 400;
//                window.setAttributes(params);
//                alertDialog.show();
//                window.setGravity(Gravity.START | Gravity.TOP);
//                alertDialog.setCanceledOnTouchOutside(true);
//                alertDialog.setContentView(dialogView, new LayoutParams(600, 270));
//                break;
//
//            case PwType.DIALOG_IMAGE_ADJUST:
//                dialogView = LayoutInflater.from(getContext()).inflate(R.layout.adjustlayout, null);
//                params.x = 130;
//                params.y = 400;
//                window.setAttributes(params);
//                alertDialog.show();
//                window.setGravity(Gravity.START | Gravity.TOP);
//                alertDialog.setCanceledOnTouchOutside(true);
//                alertDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(900, 150));
//                break;
//
//            default:
//                break;
//        }
//
//    }
//
//    private void setRestoreFactorySetting2MCU() {
//        ArrayList<Integer> mCmdParam = new ArrayList();
//        mCmdParam.clear();
//        mCmdParam.add(0x04);
//        mCmdParam.add(0x02);
//        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_SETUP, mCmdParam);
//
//    }
//
//    private void setCarColor2MCU(int color) {
//        ArrayList<Integer> mCmdParam = new ArrayList();
//        mCmdParam.clear();
//        mCmdParam.add(0x01);
//        mCmdParam.add(color);
//        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_SETUP, mCmdParam);
//    }
//
//    private void setTurningEnterAvmSwitch2MCU(int sw_val) {
//        ArrayList<Integer> mCmdParam = new ArrayList();
//        mCmdParam.clear();
//        mCmdParam.add(0x02);
//        mCmdParam.add(sw_val);
//        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_SETUP, mCmdParam);
//    }
//
//    private void set3DTouringViewSwitch2MCU(int sw_val) {
//        ArrayList<Integer> mCmdParam = new ArrayList();
//        mCmdParam.clear();
//        mCmdParam.add(0x03);
//        mCmdParam.add(sw_val);
//        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_SETUP, mCmdParam);
//    }
//
//    public void updateTurningEnterAVMSwitch(boolean sw){
//        if(linkageSwitch != null){
//            linkageSwitch.setChecked(sw);
//        }
//    }
//
//    public void update3DTouringAVMSwitch(boolean sw){
//        if(threeDSwitch != null){
//            threeDSwitch.setChecked(sw);
//        }
//    }
//}
