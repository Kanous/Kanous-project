//package com.svauto.fastrvc.view;
//
//import android.content.Context;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.desaysv.rvc.message.PwType;
//import com.desaysv.rvc.message.TheftServiceMessage;
//import com.svauto.fastrvc.R;
//import com.svauto.fastrvc.manager.AVMStatusManager;
//import com.svauto.fastrvc.manager.TheftServiceManager;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class MulView extends LinearLayout {
//    private final String TAG = this.getClass().getSimpleName();
//    @BindView(R.id.front_plus_view)
//    ImageView frontPlusView;
//    @BindView(R.id.back_plus_view)
//    ImageView backPlusView;
//    @BindView(R.id.front_two_sides)
//    ImageView frontTwoSides;
//    @BindView(R.id.back_two_sides)
//    ImageView backTwoSides;
//
//    public MulView(Context context) {
//        super(context);
//        init();
//    }
//
//    public MulView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//
//    public MulView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        View v = LayoutInflater.from(getContext()).inflate(R.layout.mulviewlayout, this);
//        ButterKnife.bind(this);
//    }
//
//    @OnClick({R.id.front_plus_view, R.id.back_plus_view, R.id.front_two_sides, R.id.back_two_sides})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.front_plus_view:
//                Log.d(TAG, "onViewClicked: fff = "+ AVMStatusManager.getInstance().isMulViewFrontSelect());
//                if (AVMStatusManager.getInstance().isMulViewFrontSelect()) {
//                    AVMStatusManager.getInstance().setMulViewFrontSelect(false);
//                    frontPlusView.setImageResource(R.mipmap.front_plus_view);
//                    mulViewSwitcher2MCU(0x00);
//                } else {
////                    AVMStatusManager.getInstance().setMulViewFrontSelect(true);
//                    AVMStatusManager.getInstance().updateMulViewStatus(PwType.MUL_VIEW_FRONT_SIDE);
//                    allMulViewGrey();
//                    mulViewSwitcher2MCU(0x01);
//                    frontPlusView.setImageResource(R.mipmap.front_plus_view_p);
//                }
//                break;
//
//            case R.id.back_plus_view:
//                Log.d(TAG, "onViewClicked: bbb = "+AVMStatusManager.getInstance().isMulViewBackSelect());
//                if (AVMStatusManager.getInstance().isMulViewBackSelect()) {
//                    AVMStatusManager.getInstance().setMulViewBackSelect(false);
//                    backPlusView.setImageResource(R.mipmap.back_plus_view);
//                    mulViewSwitcher2MCU(0x00);
//                } else {
//                    AVMStatusManager.getInstance().updateMulViewStatus(PwType.MUL_VIEW_BACK_SIDE);
//                    allMulViewGrey();
//                    mulViewSwitcher2MCU(0x02);
//                    backPlusView.setImageResource(R.mipmap.back_plus_view_p);
//                }
//
//                break;
//
//            case R.id.front_two_sides:
//                if (AVMStatusManager.getInstance().isMulViewFront2SideSelect()) {
//                    AVMStatusManager.getInstance().setMulViewFront2SideSelect(false);
//                    frontTwoSides.setImageResource(R.mipmap.front_two_sides);
//                    mulViewSwitcher2MCU(0x00);
//                } else {
//                    AVMStatusManager.getInstance().updateMulViewStatus(PwType.MUL_VIEW_FRONT_2_SIDE);
//                    allMulViewGrey();
//                    mulViewSwitcher2MCU(0x03);
//                    frontTwoSides.setImageResource(R.mipmap.front_two_sides_p);
//                }
//
//                break;
//
//            case R.id.back_two_sides:
//                if (AVMStatusManager.getInstance().isMulViewBack2SideSelect()) {
//                    AVMStatusManager.getInstance().setMulViewBack2SideSelect(false);
//                    backTwoSides.setImageResource(R.mipmap.back_two_sides);
//                    mulViewSwitcher2MCU(0x00);
//                } else {
//                    AVMStatusManager.getInstance().updateMulViewStatus(PwType.MUL_VIEW_BACK_2_SIDE);
//                    allMulViewGrey();
//                    mulViewSwitcher2MCU(0x04);
//                    backTwoSides.setImageResource(R.mipmap.back_two_sides_p);
//                }
//
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void allMulViewGrey(){
//        frontPlusView.setImageResource(R.mipmap.front_plus_view);
//        backPlusView.setImageResource(R.mipmap.back_plus_view);
//        frontTwoSides.setImageResource(R.mipmap.front_two_sides);
//        backTwoSides.setImageResource(R.mipmap.back_two_sides);
//    }
//
//    private void mulViewSwitcher2MCU(int sw_val){
//        ArrayList<Integer> mCmdParam = new ArrayList();
//        mCmdParam.clear();
//        mCmdParam.add(0x03);
//        mCmdParam.add(sw_val);
//        TheftServiceManager.getInstance().sendCmdListToMCU(TheftServiceMessage.NAVI_TO_MCU_AVM_BUTTON_INFO, mCmdParam);
//    }
//}
