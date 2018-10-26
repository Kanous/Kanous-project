package fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mydvr.R;

import protocal.NaviSendMsgToMCU;

public class ReplayFragment extends Fragment implements View.OnClickListener {
    private String TAG = this.getClass().getSimpleName();
    private RelativeLayout topBar;
    private LinearLayout topEdit;
    private View view,dialogView;
    private TextView normal_video, emergency_video, photo_replay;
    private Button replay_edit, cancel, allSelect, move, delete, delete_all;
    private Dialog customDialog;
    private Window windows;
    private WindowManager.LayoutParams params;
    private final static int ENTER_GENERAL_RECORD = 0x01;
    private final static int ENTER_PAUSE_RECORD = 0x02;
    private final static int ENTER_EMERGENCY_RECORD = 0x03;

    private final static int ENTER_GENERAL_BROWSE_MODE = 0x04;
    private final static int ENTER_EMERGENCY_BROWSE_MODE = 0x05;
    private final static int ENTER_RISK_BROWSE_MODE = 0x06;
    private final static int ENTER_PHOTO_BROWSE_MODE = 0x07;
    private final static int EXIT_BROWSE_MODE = 0x08;
    private final static int MOVE_TO_EMERGENCY = 0x0A;
    private final static int DELETE_FILE = 0x0B;
    private final static int DELETE_ALL_FILE = 0x0C;
    private final static int PLAY_ITEM = 0x0D;
    private final static int PAUSE_ITEM = 0x0E;
    private final static int PLAY_PREV_ITEM = 0x0F;
    private final static int PLAY_NEXT_ITEM = 0x10;
    private final static int EXIT_PLAY = 0x11;
    private final static int COPY_SD_CARD = 0x12;

    private parentListener listener;

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: ");
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.replay_fragment, null);
        initView();
        initEvents();
        return view;
    }

    private void initEvents() {
        normal_video.setOnClickListener(this);
        emergency_video.setOnClickListener(this);
        photo_replay.setOnClickListener(this);
        replay_edit.setOnClickListener(this);
        cancel.setOnClickListener(this);
        allSelect.setOnClickListener(this);
        move.setOnClickListener(this);
        delete.setOnClickListener(this);
        delete_all.setOnClickListener(this);
    }

    private void initView() {
        topBar = view.findViewById(R.id.topBar);
        topEdit = view.findViewById(R.id.topEdit);
        normal_video = view.findViewById(R.id.normal_video_replay);
        emergency_video = view.findViewById(R.id.emergency_video_replay);
        photo_replay = view.findViewById(R.id.photo_replay);
        replay_edit = view.findViewById(R.id.replay_edit);
        cancel = view.findViewById(R.id.cancel);
        allSelect = view.findViewById(R.id.all_select);
        move = view.findViewById(R.id.move);
        delete = view.findViewById(R.id.delete);
        delete_all = view.findViewById(R.id.delete_all);

        normal_video.setTextColor(Color.parseColor("#FFFFDF66"));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void refreshTab() {
        normal_video.setTextColor(Color.WHITE);
        emergency_video.setTextColor(Color.WHITE);
        photo_replay.setTextColor(Color.WHITE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_video_replay:
                refreshTab();
                setDialogParam(1);
                NaviSendMsgToMCU.getInstance().setCMDList(ENTER_GENERAL_BROWSE_MODE);
                normal_video.setTextColor(Color.parseColor("#FFFFDF66"));
                break;

            case R.id.emergency_video_replay:
                refreshTab();
                NaviSendMsgToMCU.getInstance().setCMDList(ENTER_EMERGENCY_BROWSE_MODE);
                emergency_video.setTextColor(Color.parseColor("#FFFFDF66"));
                break;

            case R.id.photo_replay:
                refreshTab();
                NaviSendMsgToMCU.getInstance().setCMDList(ENTER_PHOTO_BROWSE_MODE);
                photo_replay.setTextColor(Color.parseColor("#FFFFDF66"));
                break;

            case R.id.replay_edit:
                if (view != null) {
                    if (topBar == null) {
                        topBar = view.findViewById(R.id.topBar);
                    }
                    if (topEdit == null) {
                        topEdit = view.findViewById(R.id.topEdit);
                    }
//                    NaviSendMsgToMCU.getInstance().setCMDList(EXIT_BROWSE_MODE);
                    topBar.setVisibility(View.GONE);
                    topEdit.setVisibility(View.VISIBLE);
                } else {
                    Log.d(TAG, "replay_edit: view == null");
                }
                break;

            case R.id.delete:
                //NaviSendMsgToMCU.getInstance().setCMDList(DELETE_FILE);
                break;

            case R.id.delete_all:
                //弹框提示是否清空
                //NaviSendMsgToMCU.getInstance().setCMDList(DELETE_ALL_FILE);
                break;


            case R.id.all_select:
                break;

            case R.id.move:
                //弹框提示是否清空
                //NaviSendMsgToMCU.getInstance().setCMDList(MOVE_TO_EMERGENCY);
                break;

            case R.id.cancel:
                if (view != null) {
                    if (topBar == null) {
                        topBar = view.findViewById(R.id.topBar);
                    }
                    if (topEdit == null) {
                        topEdit = view.findViewById(R.id.topEdit);
                    }
                    NaviSendMsgToMCU.getInstance().setCMDList(ENTER_GENERAL_BROWSE_MODE);
                    topEdit.setVisibility(View.GONE);
                    topBar.setVisibility(View.VISIBLE);
                } else {
                    Log.d(TAG, "cancel: view == null");
                }

            default:
                break;
        }
    }

    public void setParentListener(parentListener listener) {
        this.listener = listener;
    }


    public interface parentListener {
        void SendView(View view);
    }


    private void setDialogParam(int type) {
        if (customDialog == null) {
            customDialog = new Dialog(getActivity(), R.style.AlertDialog);
        }
        windows = customDialog.getWindow();
        if (windows != null) {
            params = windows.getAttributes();
        }

        switch (type) {
            case 1:
                dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.notify, null);
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
