package com.svauto.fastrvc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svauto.fastrvc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdjustView extends RelativeLayout {
    private static final int MAX_LEVEL = 9;
    @BindView(R.id.image_adjust_item)
    TextView imageAdjustItem;
    @BindView(R.id.rb_adjust)
    RatingBar rbAdjust;

    public ImageAdjustView(Context context) {
        super(context);
        init();
    }

    public ImageAdjustView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageAdjustView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.adjustlayout, this);
        ButterKnife.bind(this);
    }

    public void updateTextView(String text) {
        imageAdjustItem.setText(text);
        rbAdjust.setRating(0);
    }

}
