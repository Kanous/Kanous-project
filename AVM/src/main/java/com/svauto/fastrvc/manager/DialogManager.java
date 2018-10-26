//package com.desaysv.avm.manager;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.desaysv.avm.R;
//import com.desaysv.avm.message.CarType;
//import com.desaysv.avm.message.PwType;
//
//import org.greenrobot.eventbus.EventBus;
//
//public class DialogManager {
//    private Context mContext;
//    private final String TAG = this.getClass().getSimpleName();
//    private static DialogManager mInstance;
//    private static Dialog alertDialog;
//    private WindowManager.LayoutParams params;
//    private Window window;
//
//    public static DialogManager getInstance(Context context) {
//        if (null == mInstance) {
//            synchronized (DialogManager.class) {
//                if (null == mInstance) {
//                    mInstance = new DialogManager(context);
//                }
//            }
//        }
//        return mInstance;
//    }
//
//    private DialogManager(Context context) {
//        mContext = context;
//        alertDialog = new Dialog(mContext, R.style.AlertDialog);
//        window = alertDialog.getWindow();
//        params = window.getAttributes();
//
//    }
//
//    public void showCarColorDialog() {
//        View dialogView = LayoutInflater.from(application.AvmApplication.getInstance()).inflate(R.layout.colorofcar, null);
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
//        params.x = 130;
//        params.y = 400;
//
//        window.setAttributes(params);
//
//        alertDialog.show();
//        window.setGravity(Gravity.START | Gravity.TOP);
//        alertDialog.setCanceledOnTouchOutside(true);
//        alertDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(600, 500));
//
//        white_tx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_WHITE));
//                alertDialog.dismiss();
//                mInstance = null;
//                alertDialog = null;
//            }
//        });
//
//        blue_tx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLUE));
//                alertDialog.dismiss();
//                mInstance = null;
//                alertDialog = null;
//            }
//        });
//
//        red_tx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_RED));
//                alertDialog.dismiss();
//            }
//        });
//
//        black_tx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLACK));
//                alertDialog.dismiss();
//            }
//        });
//
//        grey_tx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_GREY));
//                alertDialog.dismiss();
//            }
//        });
//
//        white_im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_WHITE));
//                alertDialog.dismiss();
//            }
//        });
//
//        blue_im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLUE));
//                alertDialog.dismiss();
//            }
//        });
//
//        red_im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_RED));
//                alertDialog.dismiss();
//            }
//        });
//
//        black_im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLACK));
//                alertDialog.dismiss();
//            }
//        });
//
//        grey_im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_GREY));
//                alertDialog.dismiss();
//            }
//        });
//    }
//
//    public void showImageSetDialog() {
//        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.imagesetuplayout, null);
//
//        TextView brightness = dialogView.findViewById(R.id.brightness_adjust);
//        TextView contrast = dialogView.findViewById(R.id.contrast_adjust);
//        TextView saturability = dialogView.findViewById(R.id.saturability_adjust);
//
//        params.x = 130;
//        params.y = 400;
//
//        window.setAttributes(params);
//
//        alertDialog.show();
//        window.setGravity(Gravity.START | Gravity.TOP);
//        alertDialog.setCanceledOnTouchOutside(true);
//        alertDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(600, 270));
//
//        brightness.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                showImageAdjustDialog(PwType.ADJUST_OF_BRIGHTNESS);
//            }
//        });
//
//        contrast.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                showImageAdjustDialog(PwType.ADJUST_OF_CONTRAST);
//            }
//        });
//
//        saturability.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                showImageAdjustDialog(PwType.ADJUST_OF_SATURABILITY);
//            }
//        });
//    }
//
//    public void showRestoreDialog() {
//        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.alertdialoglayout, null);
//        Button restore = dialogView.findViewById(R.id.restore);
//        Button cancel = dialogView.findViewById(R.id.cancel);
//
//        alertDialog.show();
//        alertDialog.setCanceledOnTouchOutside(false);
//        alertDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(600, 300));
//
//        restore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                Toast.makeText(mContext, "restore", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                Toast.makeText(mContext, "cancel", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void showImageAdjustDialog(int type) {
//        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.adjustlayout, null);
//        TextView image_adjust_item = dialogView.findViewById(R.id.image_adjust_item);
//        RatingBar rb_adjust = dialogView.findViewById(R.id.rb_adjust);
//
//        alertDialog.show();
//        alertDialog.setCanceledOnTouchOutside(true);
//        alertDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(900, 150));
//
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
//
//}
