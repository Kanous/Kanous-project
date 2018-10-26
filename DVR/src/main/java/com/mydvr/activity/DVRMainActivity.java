package com.mydvr.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mydvr.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.RecordFragment;
import fragment.ReplayFragment;
import fragment.SetupFragment;
import protocal.NaviSendMsgToMCU;

public class DVRMainActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.im_record)
    ImageView imRecord;
    @BindView(R.id.tv_record)
    TextView tvRecord;
    @BindView(R.id.lin_record)
    LinearLayout linRecord;
    @BindView(R.id.im_replay)
    ImageView imReplay;
    @BindView(R.id.tv_replay)
    TextView tvReplay;
    @BindView(R.id.lin_replay)
    LinearLayout linReplay;
    @BindView(R.id.im_setup)
    ImageView imSetup;
    @BindView(R.id.tv_setup)
    TextView tvSetup;
    @BindView(R.id.lin_setup)
    LinearLayout linSetup;
    private RecordFragment recordFragment;
    private ReplayFragment replayFragment;
    private SetupFragment setupFragment;

    private int recordResId;
    private int replayResId;
    private int setupResId;
    private int sdcardResId;
    private int tipsResId;
    private Drawable selectDrawable;
    private View dialogView;
    private int textColor;
    private Dialog customDialog;
    private Window windows;
    private WindowManager.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initEvent();
        setFragment(0);
    }

    private void initEvent() {

    }

    private void initTheme() {
        setTheme(R.style.AppTheme_gold);
        TypedValue typedValue = new TypedValue();
        this.getTheme().resolveAttribute(R.style.AppTheme_gold, typedValue, true);

        int[] attribute = new int[]{
                R.attr.recordIcon, R.attr.replayIcon, R.attr.setupIcon,
                R.attr.SDCardIcon, R.attr.tipsIcon,
                R.attr.textColor,R.attr.itemBackground
        };

        TypedArray array = this.obtainStyledAttributes(typedValue.resourceId, attribute);
        recordResId = array.getResourceId(0, 0);
        replayResId = array.getResourceId(1, 0);
        setupResId = array.getResourceId(2, 0);
        sdcardResId = array.getResourceId(3, 0);
        tipsResId = array.getResourceId(4, 0);
        textColor = array.getColor(5, Color.WHITE);
        selectDrawable = array.getDrawable(6);
        array.recycle();
    }

    private void initView() {
        recordFragment = new RecordFragment();
        replayFragment = new ReplayFragment();
        setupFragment = new SetupFragment();
        linRecord.setBackgroundColor(Color.BLACK);
        linReplay.setBackgroundColor(Color.BLACK);
        linSetup.setBackgroundColor(Color.BLACK);
//        refreshButton();
    }


    private void refreshButton() {
        imRecord.setImageResource(R.drawable.normal_record);
        imReplay.setImageResource(R.drawable.normal_replay);
        imSetup.setImageResource(R.drawable.normal_setup);
        tvRecord.setTextColor(Color.WHITE);
        tvSetup.setTextColor(Color.WHITE);
        tvReplay.setTextColor(Color.WHITE);
        linRecord.setBackgroundColor(Color.BLACK);
        linReplay.setBackgroundColor(Color.BLACK);
        linSetup.setBackgroundColor(Color.BLACK);
    }

    private void setFragment(int val) {
        refreshButton();
        switch (val) {
            case 0:
                getFragmentManager().beginTransaction().replace(R.id.dynamic_fragment, recordFragment).commit();
                linRecord.setBackground(selectDrawable);
                imRecord.setImageResource(recordResId);
                tvRecord.setTextColor(textColor);
                break;

            case 1:
                getFragmentManager().beginTransaction().replace(R.id.dynamic_fragment, replayFragment).commit();
                linReplay.setBackground(selectDrawable);
                imReplay.setImageResource(replayResId);
                tvReplay.setTextColor(textColor);
                break;

            case 2:
                getFragmentManager().beginTransaction().replace(R.id.dynamic_fragment, setupFragment).commit();
                linSetup.setBackground(selectDrawable);
                imSetup.setImageResource(setupResId);
                tvSetup.setTextColor(textColor);
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

    @OnClick({R.id.lin_record, R.id.lin_replay, R.id.lin_setup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_record:
                Log.d(TAG, "onViewClicked: record");
                setFragment(0);
                break;

            case R.id.lin_replay:
                Log.d(TAG, "onViewClicked: replay");
                NaviSendMsgToMCU.getInstance().setCMDList(0x04);
                setFragment(1);
                break;

            case R.id.lin_setup:
                Log.d(TAG, "onViewClicked: setup");
                setFragment(2);
                break;
        }
    }

    private void setDialogParam(int type) {
        if (customDialog == null) {
            customDialog = new Dialog(this, R.style.AlertDialog);
        }
        windows = customDialog.getWindow();
        if (windows != null) {
            params = windows.getAttributes();
        }

        switch (type) {
            case 1:
                dialogView = LayoutInflater.from(this).inflate(R.layout.notify, null);
                windows.setAttributes(params);
                windows.setWindowAnimations(R.style.dialog_anim);

                windows.setGravity(Gravity.CENTER);
                customDialog.setCanceledOnTouchOutside(true);
                customDialog.setContentView(dialogView, new RelativeLayout.LayoutParams(320, 70));
                customDialog.show();
                break;


            default:
                break;
        }

    }

}
