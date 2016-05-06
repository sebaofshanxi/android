package com.liu.liu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by yulong.liu on 2016/4/11 0011.
 */
public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
//        Intent i = new Intent(this,MainActivity.class);
//        PendingIntent padd =  PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);
//
//        Notification notification = new Notification();
//        notification.icon = R.drawable.ic_launcher;
//        notification.tickerText = "liuyulong";
//
//        //Context context,CharSequence contentTitle, CharSequence contentText, PendingIntent contentIntent)
//        notification.setLatestEventInfo(this,"nihao","内容",padd);
////        NotificationManager a = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
////        a.notify(1,notification);
//        startForeground(1,notification);
    }


    @Override
    public void onStart(Intent intent, int startId) {
        Log.i("info","启动服务，执行onStart()");

        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("info","启动服务，执行onStartCommand()");
        Intent i = new Intent(this,MainActivity.class);
        PendingIntent padd =  PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = new Notification();
        notification.icon = R.drawable.ic_launcher;
        notification.tickerText = "liuyulong";

        //Context context,CharSequence contentTitle, CharSequence contentText, PendingIntent contentIntent)
        notification.setLatestEventInfo(this,"nihao","内容",padd);
//        NotificationManager a = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
//        a.notify(1,notification);
        startForeground(1,notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
