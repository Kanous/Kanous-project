package protocal;


import java.util.ArrayList;

import manager.TheftServiceManager;
import message.DvrProtocalID;

public class NaviSendMsgToMCU {
    private final String TAG = this.getClass().getSimpleName();
    private volatile static NaviSendMsgToMCU mInstance;
    private static ArrayList<Integer> mCmdParam;

    public static NaviSendMsgToMCU getInstance() {
        if (null == mInstance) {
            synchronized (NaviSendMsgToMCU.class) {
                if (null == mInstance) {
                    mInstance = new NaviSendMsgToMCU();
                    mCmdParam = new ArrayList();
                }
            }
        }
        return mInstance;
    }

    public void setTapeSet(int sw_val) {    //设置录音
        mCmdParam.clear();
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(DvrProtocalID.MMI_SET_TAPE, mCmdParam);
    }

    public void setRecordCycle(int sw_val) {    //录制时长
        mCmdParam.clear();
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(DvrProtocalID.MMI_SET_RECORD_CYCLE, mCmdParam);
    }

    public void setResolution(int sw_val) { //设置分辨率
        mCmdParam.clear();
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(DvrProtocalID.MMI_SET_RESOLUTION, mCmdParam);
    }

    public void setSDFormat(int sw_val) {   //格式化SD卡
        mCmdParam.clear();
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(DvrProtocalID.MMI_REQ_SD_FORMAT, mCmdParam);
    }

    public void setRestoreFactorySetting(int sw_val) {   //恢复出厂设置
        mCmdParam.clear();
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(DvrProtocalID.MMI_REQ_RESTORE_FACTORY_SETTING, mCmdParam);
    }

    public void setCMDList(int sw_val) {   //指令列表
        mCmdParam.clear();
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(DvrProtocalID.MMI_CMD_LIST, mCmdParam);
    }

    public void setCurrentSelected(int sw_val) {   //视频选择,用于播放视频
        mCmdParam.clear();
        mCmdParam.add(sw_val);
        TheftServiceManager.getInstance().sendCmdListToMCU(DvrProtocalID.MMI_CMD_LIST, mCmdParam);
    }
}
