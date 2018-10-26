package com.svauto.fastrvc.manager;

import android.util.Log;

import com.desaysv.vehicle.theft.Theft;
import com.desaysv.vehicle.theft.TheftListener;
import com.desaysv.vehicle.theft.TheftService;
import com.svauto.fastrvc.message.TheftServiceMessage;
import com.svauto.fastrvc.protocol.AvmInfo;
import com.svauto.fastrvc.protocol.AvmSetupInfo;
import com.svauto.fastrvc.protocol.ButtonInfo;
import com.svauto.fastrvc.protocol.RadarInfo;

import java.util.ArrayList;

public class TheftServiceManager {
    private static final String TAG = "TheftServiceManager";
    private static TheftServiceManager mInstance;
    private TheftService theftService = Theft.getTheftService();
//    private RadarInfo mRadarInfo = new RadarInfo();
//    private ButtonInfo mButtonInfo = new ButtonInfo();

    private ArrayList<Integer> sendList = new ArrayList<>();

    public static TheftServiceManager getInstance() {
        if (null == mInstance) {
            synchronized (TheftServiceManager.class) {
                if (null == mInstance) {
                    mInstance = new TheftServiceManager();
                }
            }
        }
        return mInstance;
    }

    //commandID的有效范围是0x7240-0x72FF, vecCmdParam的有效范围是0-0xFF
    public boolean sendCmdListToMCU(int commandID, ArrayList<Integer>  vecCmdParam) {
        if (theftService == null) {
            Log.e(TAG, "sendCmdToMCU theftService == null return");
            return false;
        }
        return theftService.sendCmd(commandID, vecCmdParam);
    }

    public boolean sendCmdToMCU(int commandID, int value){
        if (theftService == null) {
            Log.e(TAG, "sendCmdToMcu theftService == null return");
            return false;
        }
        String Hex16 = String.format("%04x", commandID);
        Log.d(TAG,"sendCmdToMcu commandID == " + Hex16 + " ;; value == " + value);

        sendList.clear();
        sendList.add(value);
        return theftService.sendCmd(commandID, sendList);
    }

    private TheftServiceManager() {
        Log.d(TAG, "TheftServiceManager()");
        if (theftService == null) {
            Log.e(TAG, "TheftServiceManager theftService == null return");
            return;
        }

        theftService.setOnTheftListener(new TheftListener() {
            @Override
            public void onCmdProc(int commandID, ArrayList<Integer> vecCmdParam) {
                String Hex16 = String.format("%04x", commandID);
                Log.d(TAG, "TheftServiceManager onCmdProc commandID = " + Hex16 + " vecCmdParam.size() =  " + vecCmdParam.size());
                int value[] = new int[vecCmdParam.size()];
                for(int i = 0; i < vecCmdParam.size(); i++){
                    value[i] = vecCmdParam.get(i).intValue();
                    Log.d(TAG, "TheftServiceManager onCmdProc commandID = " + Hex16 + " value.intValue() =  " + value[i]);
                }
                switch (commandID){
                    case TheftServiceMessage.MCU_TO_NAVI_SETUP:         //AVM设置的状态
                        //Read: 03 73 41 06 00 31 12 11 11 31
                              //01 73 41 06 00 01 10 10 30 10
                        AvmSetupInfo.getInstance().setAVM_STATUS(value[0]);
                        AvmSetupInfo.getInstance().setAVM_CAR_ICON_COLOR_RESP(value[1]);
                        AvmSetupInfo.getInstance().setAVM_TURNING_ENTER_AVM(value[2]);
                        AvmSetupInfo.getInstance().setAVM_3D_TOURING_KEY_RESP(value[3]);
                        AvmSetupInfo.getInstance().setAVM_RESTORE_FACTORY_RESP(value[4]);
                        break;

                    case TheftServiceMessage.MCU_TO_NAVI_BUTTON_INFO:   //AVM主界面按键状态
                        //Read: 73 42 07 00 10 10 21 00 00 30
                        ButtonInfo.getInstance().setBUTTON_BACK_STATUS(value[0]);
                        ButtonInfo.getInstance().setBUTTON_PATH_LINE_STATUS(value[1]);
                        ButtonInfo.getInstance().setBUTTON_3D_STATUS(value[2]);
                        ButtonInfo.getInstance().setBUTTON_MUL_VIEW_STATUS(value[3]);
                        ButtonInfo.getInstance().setBUTTON_SETUP_STATUS(value[4]);
                        ButtonInfo.getInstance().setAVM_SOFT_KEY(value[5]);

                        break;

                    case TheftServiceMessage.MCU_TO_NAVI_AVM_STATUS:    //AVM状态
                        AvmInfo.getInstance().setAVM_MMI_FALIURE(value[0]);
                        AvmInfo.getInstance().setAVM_CONTROL_REQUST(value[1]);
                        AvmInfo.getInstance().setAVM_FACTORY_MODE(value[2]);
                        AvmInfo.getInstance().setAVM_TURN_ON_REQUEST(value[3]);
                        AvmInfo.getInstance().setAVM_CAMERA_FLTSTS(value[4]);
                        break;

                    case TheftServiceMessage.MCU_TO_NAVI_PAS_STATUS:    //更新雷达
                        //01 73 44 0C 00 00 00 01 05 00 02 04 03 01 01 00
                        RadarInfo.getInstance().setPCD_BTN_PRESS(value[0]);
                        RadarInfo.getInstance().setBUZZER_ALARM_TYPE(value[1]);

                        RadarInfo.getInstance().setPAS_PDC_DISTANCE_FL(value[2]);
                        RadarInfo.getInstance().setPAS_PDC_DISTANCE_FML(value[3]);
                        RadarInfo.getInstance().setPAS_PDC_DISTANCE_FMR(value[4]);
                        RadarInfo.getInstance().setPAS_PDC_DISTANCE_FR(value[5]);

                        RadarInfo.getInstance().setPAS_PDC_DISTANCE_RL(value[6]);
                        RadarInfo.getInstance().setPAS_PDC_DISTANCE_RML(value[7]);
                        RadarInfo.getInstance().setPAS_PDC_DISTANCE_RMR(value[8]);
                        RadarInfo.getInstance().setPAS_PDC_DISTANCE_RR(value[9]);

                        RadarInfo.getInstance().setPAS_PDC_MODE(value[10]);
                        break;

                    default:
                        break;
                }
            }
        });
    }

    public int parseValue2Up4(int val){
        String val_ext  = Integer.toHexString(val);
        int result = Integer.parseInt(val_ext,16);
//        Log.d(TAG, "int2Hex: "+result);
        result = (result & 0xf0) >> 4;
        return result;
    }

    public int parseValue2Low4(int val){
        String val_ext  = Integer.toHexString(val);
        int result = Integer.parseInt(val_ext,16);
//        Log.d(TAG, "int2Hex: "+result);
        result = (result & 0x0f) ;
        return result;
    }

}
