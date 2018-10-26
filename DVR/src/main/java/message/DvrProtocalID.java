package message;

public class DvrProtocalID {
    // NAVI -> MCU
    public final static int MMI_SET_TAPE = 0;   //设置录音
    public final static int MMI_SET_RESOLUTION = 1;   //设置分辨率
    public final static int MMI_SET_RECORD_CYCLE = 2;   //设置录制时长
    public final static int MMI_REQ_SD_FORMAT = 3;   //请求格式化SD卡
    public final static int MMI_REQ_RESTORE_FACTORY_SETTING = 4;   //请求恢复出厂设置
    public final static int MMI_ENTER_UPDATE_MODE = 5;   //进入升级模式
    public final static int MMI_DVR_GO_HOME = 6;   //返回主菜单
    public final static int MMI_BROWSE_CMD = 7;   //浏览指令/翻页指令
    public final static int MMI_CMD_LIST = 8;   //指令列表
    public final static int MMI_CURRENT_SELECTED = 9;   //视频片段选择列表

    // MCU -> NAVI
    public final static int RSP_DVR_STATUS_INFO = 0;    //DVR状态信息
    public final static int RSP_DVR_ACTION_RESULT = 1;    //DVR动作结果
    public final static int RSP_DVR_SYS_VERSION = 2;    //系统版本号
    public final static int RSP_DVR_CURRENT_VIDEO_INFO = 3;    //当前页录像信息

}
