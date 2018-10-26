package fragment;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mydvr.R;

import application.DvrApplication;
import protocal.NaviSendMsgToMCU;

/**
 * Created by uidq0348 on 2018/6/28.
 */

public class RecordFragment extends Fragment implements View.OnClickListener {
    private String TAG = this.getClass().getSimpleName();
    private ImageView stopRecordVideo, stopRecordVoice;
    private TextView tvStopRecord,tvStopRecordVoice;
    private View view;

    private boolean isRecordingVideo = true;   //默认正在录制
    private boolean isTaping = false;     //默认关闭录制

    private int stopRecordResID;
    private int startRecordResID;
    private int stopRecVoiceResID;
    private int startRecVoiceResID;
    private int textColor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initTheme();
        view = inflater.inflate(R.layout.record_fragment, container,false);
        initView();
        initEvent();
        return view;
    }

    private void initEvent() {
        stopRecordVideo.setOnClickListener(this);
        stopRecordVoice.setOnClickListener(this);
        tvStopRecord.setOnClickListener(this);
        tvStopRecordVoice.setOnClickListener(this);
    }

    private void initTheme() {
        DvrApplication.getInstance().setTheme(R.style.AppTheme_gold);
        TypedValue typedValue = new TypedValue();
        DvrApplication.getInstance().getTheme().resolveAttribute(R.style.AppTheme_gold, typedValue, true);

        int[] attribute = new int[]{
                R.attr.stopRecordIcon, R.attr.startRecordIcon, R.attr.stopRecordVoiceIcon,
                R.attr.recordingIcon, R.attr.textColor
        };

        TypedArray array = DvrApplication.getInstance().obtainStyledAttributes(typedValue.resourceId, attribute);

        stopRecordResID = array.getResourceId(0, 0);
        startRecordResID = array.getResourceId(1, 0);
        stopRecVoiceResID = array.getResourceId(2, 0);
        startRecVoiceResID = array.getResourceId(3, 0);
        textColor = array.getColor(4, Color.WHITE);

        array.recycle();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        isRecordingVideo = false;
        isTaping = false;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    private void initView() {
        stopRecordVideo = view.findViewById(R.id.im_stop_record);
        stopRecordVoice = view.findViewById(R.id.im_stop_record_voice);
        tvStopRecord = view.findViewById(R.id.tv_stop_record);
        tvStopRecordVoice = view.findViewById(R.id.tv_stop_record_voice);

        stopRecordVideo.setImageResource(stopRecordResID);  //正在录制，要显示停止录制的icon
        stopRecordVoice.setImageResource(stopRecVoiceResID);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.im_stop_record:
            case R.id.tv_stop_record:
                if(isRecordingVideo){
                    isRecordingVideo = false;
                    stopRecordVideo.setImageResource(stopRecordResID);
                    tvStopRecord.setText(R.string.stop_record_video);
                    NaviSendMsgToMCU.getInstance().setCMDList(0x01);
                }else{
                    isRecordingVideo = true;
                    stopRecordVideo.setImageResource(startRecordResID);
                    tvStopRecord.setText(R.string.start_record_video);
                    NaviSendMsgToMCU.getInstance().setCMDList(0x02);
                }
                break;

            case R.id.im_stop_record_voice:
            case R.id.tv_stop_record_voice:
                if(isTaping){
                    isTaping = false;
                    stopRecordVoice.setImageResource(startRecVoiceResID);
                    tvStopRecordVoice.setText(R.string.start_record_voice);
                    NaviSendMsgToMCU.getInstance().setTapeSet(0x01);
                }else{
                    isTaping = true;
                    stopRecordVoice.setImageResource(stopRecVoiceResID);
                    tvStopRecordVoice.setText(R.string.stop_record_voice);
                    NaviSendMsgToMCU.getInstance().setTapeSet(0x02);
                }
                break;
        }
    }
}
