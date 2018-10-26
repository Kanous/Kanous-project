package fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mydvr.R;

import protocal.NaviSendMsgToMCU;

public class SetupFragment extends Fragment implements View.OnClickListener {
    private String TAG = this.getClass().getSimpleName();
    private View view, dialogView;
    private TextView timeSetting, defaultTime, imageSize, defaultSize, formatSDCard, restoreFactory;
    private ImageView timeArrows, sizeArrows;
    private Dialog customDialog;
    private static final int TIME_SETTING = 0;
    private static final int IMAGE_SIZE_SETTING = TIME_SETTING + 1;
    private static final int FORMAT_SD_CARD = IMAGE_SIZE_SETTING + 1;
    private static final int RESTORE_FACTORY_SETTING = FORMAT_SD_CARD + 1;
    private Window windows;
    private WindowManager.LayoutParams params;
    private int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.settings_fragment, null);
        initView();
        initEvents();
        return view;
    }

    private void initEvents() {
        timeSetting.setOnClickListener(this);
        defaultTime.setOnClickListener(this);
        imageSize.setOnClickListener(this);
        defaultSize.setOnClickListener(this);
        formatSDCard.setOnClickListener(this);
        restoreFactory.setOnClickListener(this);
        timeArrows.setOnClickListener(this);
        sizeArrows.setOnClickListener(this);
    }

    private void initView() {
        timeSetting = view.findViewById(R.id.timeSetting);
        defaultTime = view.findViewById(R.id.defaultTime);
        imageSize = view.findViewById(R.id.imageSizeSetting);
        defaultSize = view.findViewById(R.id.defaultSize);
        formatSDCard = view.findViewById(R.id.formatSDCard);
        restoreFactory = view.findViewById(R.id.restoreFactory);

        timeArrows = view.findViewById(R.id.timeArrows);
        sizeArrows = view.findViewById(R.id.sizeArrows);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timeSetting:
            case R.id.defaultTime:
            case R.id.timeArrows:
                showTimeSetDialog();
                break;

            case R.id.imageSizeSetting:
            case R.id.defaultSize:
            case R.id.sizeArrows:
                showImageSizeDialog();
                break;

            case R.id.formatSDCard:
                showFormatSDCardDialog();
                break;

            case R.id.restoreFactory:
                showRestoreDialog();
                break;
        }
    }

    private void setDialogParam(int type) {
        if (customDialog == null) {
            customDialog = new Dialog(getActivity(), R.style.AlertDialog);
        }
        windows = customDialog.getWindow();
        if (windows != null) {
            params = windows.getAttributes();
//            windows.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
//            windows.getDecorView().setSystemUiVisibility(flags);
        }

        switch (type) {
            case TIME_SETTING:
                dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.timesetlayout, null);
                params.dimAmount = 0.85f;
                windows.setAttributes(params);
                windows.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//与dimAmount配合，设置背景模糊
                windows.setWindowAnimations(R.style.dialog_anim);

                windows.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                customDialog.setCanceledOnTouchOutside(true);
                customDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(600, 509));
                customDialog.show();
                break;

            case IMAGE_SIZE_SETTING:
                dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.imagesizelayout, null);
                params.dimAmount = 0.85f;
                windows.setAttributes(params);
                windows.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//与dimAmount配合，设置背景模糊
                windows.setWindowAnimations(R.style.dialog_anim);

                windows.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                customDialog.setCanceledOnTouchOutside(true);
                customDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(600, 419));
                customDialog.show();
                break;

            case FORMAT_SD_CARD:
                dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.formatsdcardlayout, null);
                params.dimAmount = 0.85f;
                windows.setAttributes(params);
                windows.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//与dimAmount配合，设置背景模糊
                windows.setWindowAnimations(R.style.dialog_anim);

                windows.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                customDialog.setCanceledOnTouchOutside(true);
                customDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(600, 340));
                customDialog.show();
                break;

            case RESTORE_FACTORY_SETTING:
                dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.restorelayout, null);
                params.dimAmount = 0.85f;
                windows.setAttributes(params);
                windows.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//与dimAmount配合，设置背景模糊
                windows.setWindowAnimations(R.style.dialog_anim);

                windows.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                customDialog.setCanceledOnTouchOutside(true);
                customDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(600, 340));
                customDialog.show();

                break;

            default:
                break;
        }

    }


    private void showTimeSetDialog() {
        setDialogParam(TIME_SETTING);

        TextView oneMin = dialogView.findViewById(R.id.one_min);
        TextView threeMins = dialogView.findViewById(R.id.three_min);
        TextView fiveMins = dialogView.findViewById(R.id.five_min);
        TextView cancel = dialogView.findViewById(R.id.time_set_cancel);

        oneMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaviSendMsgToMCU.getInstance().setRecordCycle(0x01);
                customDialog.dismiss();
            }
        });
        threeMins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaviSendMsgToMCU.getInstance().setRecordCycle(0x02);
                customDialog.dismiss();
            }
        });
        fiveMins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaviSendMsgToMCU.getInstance().setRecordCycle(0x03);
                customDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
    }

    private void showImageSizeDialog() {
        setDialogParam(IMAGE_SIZE_SETTING);
        TextView _720p = dialogView.findViewById(R.id._720p);
        TextView _1080p = dialogView.findViewById(R.id._1080p);
        TextView cancel = dialogView.findViewById(R.id.image_set_cancel);

        _720p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaviSendMsgToMCU.getInstance().setResolution(0x02);
                customDialog.dismiss();
            }
        });

        _1080p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaviSendMsgToMCU.getInstance().setResolution(0x01);
                customDialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
    }

    private void showFormatSDCardDialog() {
        setDialogParam(FORMAT_SD_CARD);
        TextView reset = dialogView.findViewById(R.id.reset_sd);
        TextView cancel = dialogView.findViewById(R.id.cancel_format);

        customDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!customDialog.isShowing()) {
                    dialogView = null;
                }
                customDialog.dismiss();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaviSendMsgToMCU.getInstance().setSDFormat(0x01);
                customDialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
    }

    private void showRestoreDialog() {
        setDialogParam(RESTORE_FACTORY_SETTING);
        TextView restore = dialogView.findViewById(R.id.restore);
        TextView cancel = dialogView.findViewById(R.id.cancel);

        customDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!customDialog.isShowing()) {
                    Log.d(TAG, "restore dismiss :11111 ");
                    dialogView = null;
                }
                Log.d(TAG, "restore dismiss :22222 ");
                customDialog.dismiss();
            }
        });
        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaviSendMsgToMCU.getInstance().setRestoreFactorySetting(0x01);
                customDialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
    }

}
