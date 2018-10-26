package com.svauto.fastrvc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svauto.fastrvc.R;
import com.svauto.fastrvc.message.CarType;
import com.svauto.fastrvc.message.PwType;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarModelColorView extends RelativeLayout {
    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.color_white)
    TextView colorWhite;
    @BindView(R.id.im_white)
    ImageView imWhite;
    @BindView(R.id.color_blue)
    TextView colorBlue;
    @BindView(R.id.im_blue)
    ImageView imBlue;
    @BindView(R.id.color_red)
    TextView colorRed;
    @BindView(R.id.im_red)
    ImageView imRed;
    @BindView(R.id.color_black)
    TextView colorBlack;
    @BindView(R.id.im_black)
    ImageView imBlack;
    @BindView(R.id.color_gray)
    TextView colorGray;
    @BindView(R.id.im_gray)
    ImageView imGray;


    public CarModelColorView(Context context) {
        super(context);
        init();
    }

    public CarModelColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CarModelColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.colorofcar, this);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.color_white, R.id.im_white, R.id.color_blue, R.id.im_blue, R.id.color_red, R.id.im_red, R.id.color_black, R.id.im_black, R.id.color_gray, R.id.im_gray})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.color_white:
            case R.id.im_white:
                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_WHITE));
                break;

            case R.id.color_blue:
            case R.id.im_blue:
                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLUE));
                break;

            case R.id.color_red:
            case R.id.im_red:
                Log.d(TAG, "onViewClicked: red car");
                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_RED));
                break;

            case R.id.color_black:
            case R.id.im_black:
                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_BLACK));
                break;

            case R.id.color_gray:
            case R.id.im_gray:
                EventBus.getDefault().post(new CarType(PwType.CAR_MODEL_COLOR_GREY));
                break;
        }
    }

}
