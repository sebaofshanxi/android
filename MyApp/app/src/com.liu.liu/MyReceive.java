package com.liu.liu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by yulong.liu on 2016/4/11 0011.
 */
public class MyReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("info",""+intent.getAction());
        if(intent.getAction().equals("com.liu.liu.aoo")){
            Intent it = new Intent(context,MyService.class);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(it);
        }

    }
}
