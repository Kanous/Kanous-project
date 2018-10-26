package manager;

import android.util.Log;

import com.desaysv.vehicle.theft.Theft;
import com.desaysv.vehicle.theft.TheftListener;
import com.desaysv.vehicle.theft.TheftService;

import java.util.ArrayList;

import message.DvrProtocalID;

public class TheftServiceManager {
    private static final String TAG = "TheftServiceManager";
    private static TheftServiceManager mInstance;
    private TheftService theftService = Theft.getTheftService();

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
                    case DvrProtocalID.RSP_DVR_STATUS_INFO:

                        break;

                    case DvrProtocalID.RSP_DVR_ACTION_RESULT:

                        break;

                    case DvrProtocalID.RSP_DVR_SYS_VERSION:

                        break;

                    case DvrProtocalID.RSP_DVR_CURRENT_VIDEO_INFO:

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
