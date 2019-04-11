package com.example.fish.alandemoapp.fcm;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.fish.alandemoapp.MainActivity;
import com.example.fish.alandemoapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("FCM", "onNewToken = " + s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e("FCM", "onMessageReceived:" + remoteMessage.getFrom());
        Log.e("FCM", "onMessageReceived getData :" + remoteMessage.getData());
        Log.e("FCM", "onMessageReceived getNotification :" + remoteMessage.getNotification());
        Log.e("FCM", "onMessageReceived isAppForeground :" + isAppForeground());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);

            showNotification(this, remoteMessage, intent);

        }
    }

    /**
     * 顯示通知
     * remoteMessage.getData()         -  無論app在什麼狀態下皆會執行 MyFirebaseMessagingService(需對應Service設定的字串文字)
     * remoteMessage.getNotification() -  只會在app顯示時,執行app的接收訊息 MyFirebaseMessagingService
     */
    private void showNotification(Context context, RemoteMessage remoteMessage, Intent intent) {

        PendingIntent iPending = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 建立BigTextStyle
        final NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        // 當BigTextStyle顯示時，用BigTextStyle的setBigContentTitle覆蓋setContentTitle的設定
        bigTextStyle.setBigContentTitle(remoteMessage.getData().get("bigTitle"));
        // 設定BigTextStyle的文字內容
        bigTextStyle.bigText(remoteMessage.getData().get("bigMessage"));

        NotificationCompat.Builder mBuilder = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                new NotificationCompat.Builder(this, getString(R.string.app_name))
                        .setStyle(bigTextStyle)
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_cat))
                        //需對應Service設定的字串文字
                        .setContentTitle(remoteMessage.getData().get("title"))
                        //需對應Service設定的字串文字
                        .setContentText(remoteMessage.getData().get("message"))
                        //振动 振500 停500 再振500
                        .setVibrate(new long[]{0, 500, 500, 500})
                        //設定當前時間響應
                        .setWhen(System.currentTimeMillis())
                        //顯示基本信息，如通知的圖標，但隱藏通知的全部內容
                        .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                        //設定優先級
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        //點擊後取消
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setContentIntent(iPending)
                :
                new NotificationCompat.Builder(this)
                        .setStyle(bigTextStyle)
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_cat))
                        //需對應Service設定的字串文字
                        .setContentTitle(remoteMessage.getData().get("title"))
                        //需對應Service設定的字串文字
                        .setContentText(remoteMessage.getData().get("message"))
                        //振动 振500 停500 再振500
                        .setVibrate(new long[]{0, 500, 500, 500})
                        //設定當前時間響應
                        .setWhen(System.currentTimeMillis())
                        //顯示基本信息，如通知的圖標，但隱藏通知的全部內容
                        .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                        //設定優先級
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        //點擊後取消
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setContentIntent(iPending);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(R.drawable.icon_cat1);
            mBuilder.setColor(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));
        } else {
            mBuilder.setSmallIcon(R.drawable.icon_cat1);
        }

        Notification notification = mBuilder.build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(1, notification);
    }

    /**
     * 應用是否處於前台
     *
     * @return
     */
    private boolean isAppForeground() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = manager.getRunningAppProcesses();
        if (appProcessInfos == null || appProcessInfos.isEmpty()) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo info : appProcessInfos) {
            //當前應用處於運行中，並且在前台
            if (info.processName.equals(getPackageName()) && info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }
}

