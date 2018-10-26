package com.svauto.fastrvc.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.svauto.fastrvc.R;
import com.svauto.fastrvc.manager.PopupWindowManager;
import com.svauto.fastrvc.message.PwType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageSetupView extends RelativeLayout {
    @BindView(R.id.brightness_adjust)
    TextView brightnessAdjust;
    @BindView(R.id.contrast_adjust)
    TextView contrastAdjust;
    @BindView(R.id.saturability_adjust)
    TextView saturabilityAdjust;

    private ImageAdjustView imageAdjustView;
    private PopupWindowManager popupWindowManager;
    public ImageSetupView(Context context) {
        super(context);
        init();
    }

    public ImageSetupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageSetupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.imagesetuplayout, this);
        imageAdjustView = new ImageAdjustView(getContext());
        ButterKnife.bind(this);
    }

    private void showPopupWindow(View parent, View customView, int type) {
        popupWindowManager = PopupWindowManager.getInstance();
        popupWindowManager.setCustomViewToWin(parent, customView, type);
    }

    @OnClick({R.id.brightness_adjust, R.id.contrast_adjust, R.id.saturability_adjust})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.brightness_adjust:
                imageAdjustView.updateTextView("明亮度调节");
                showPopupWindow(brightnessAdjust, imageAdjustView, PwType.IMAGE_ADJUST_WINDOW);
                break;

            case R.id.contrast_adjust:
                imageAdjustView.updateTextView("对比度调节");
                showPopupWindow(contrastAdjust, imageAdjustView, PwType.IMAGE_ADJUST_WINDOW);
                break;

            case R.id.saturability_adjust:
                imageAdjustView.updateTextView("饱和度调节");
                showPopupWindow(saturabilityAdjust, imageAdjustView, PwType.IMAGE_ADJUST_WINDOW);
                break;
        }
    }
}
