package com.example.todo.telephon;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Phone status listener
 */
public class ToDoPhoneStateListener extends PhoneStateListener {
    Context context;

    public ToDoPhoneStateListener(Context pContext) {
        this.context = pContext;
    }

    /**
     * Listen call state
     * @param state
     * @param incomingNumber
     */
    public void onCallStateChanged(int state, String incomingNumber) {

        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Toast.makeText(context, "CALL_STATE_IDLE", Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Toast.makeText(context, "CALL_STATE_OFFHOOK", Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Toast.makeText(context, "CALL_STATE_RINGING #:" + incomingNumber, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDataActivity(int direction) {
        super.onDataActivity(direction);
        switch (direction) {
            case TelephonyManager.DATA_ACTIVITY_IN:
                // downloading
                Toast.makeText(context, "DATA_ACTIVITY_IN", Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.DATA_ACTIVITY_OUT:
                // uploading
                Toast.makeText(context, "DATA_ACTIVITY_OUT", Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.DATA_ACTIVITY_INOUT:
                // down & uploading
                Toast.makeText(context, "DATA_ACTIVITY_INOUT", Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.DATA_ACTIVITY_NONE:
                // no data transfer
                Toast.makeText(context, "DATA_ACTIVITY_NONE", Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.DATA_ACTIVITY_DORMANT:
                // no connection
                Toast.makeText(context, "DATA_ACTIVITY_DORMANT", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
