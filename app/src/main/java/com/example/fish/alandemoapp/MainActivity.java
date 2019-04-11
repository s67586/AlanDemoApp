package com.example.fish.alandemoapp;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.fish.alandemoapp.fcm.FCMActivity;
import com.example.thirdpartylogin.Callback;
import com.example.thirdpartylogin.facebook.FacebookController;
import com.example.thirdpartylogin.facebook.FacebookData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FacebookController mFacebookController;
    private ConstraintLayout mClFbLogin;
    private ConstraintLayout mClGoogleLogin;
    private ConstraintLayout mClFCM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * andeoid 8.0以上 推播需要做 推播渠道
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = getString(R.string.app_name);
            String channelName = "推播通知";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
        }

        initView();
        initListener();

        //FB
        mFacebookController = FacebookController.newInstance(this);

    }

    private void initView() {
        mClFbLogin = findViewById(R.id.mainActivity_fbLoginConstraintLayout);
        mClGoogleLogin = findViewById(R.id.mainActivity_googleLoginConstraintLayout);
        mClFCM = findViewById(R.id.mainActivity_fcmConstraintLayout);
    }

    private void initListener() {
        mClFbLogin.setOnClickListener(this);
        mClGoogleLogin.setOnClickListener(this);
        mClFCM.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookController.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.mainActivity_fbLoginConstraintLayout:
                mFacebookController.onLogin(new Callback() {
                    @Override
                    public void getFacebookData(FacebookData facebookData) {
                        Log.e("TAG","facebookData.getId = "+facebookData.getId());
                        Log.e("TAG","facebookData.getEmail = "+facebookData.getEmail());
                        Log.e("TAG","facebookData.getPicture() = "+facebookData.getPicture());
                    }
                });
                break;
            case R.id.mainActivity_googleLoginConstraintLayout:
                break;
            case R.id.mainActivity_fcmConstraintLayout:

                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String newToken = instanceIdResult.getToken();
                        Log.e("FCM", "onNewToken = " + newToken);
                    }
                });

//                intent = new Intent(this, FCMActivity.class);
//                startActivity(intent);
                break;
        }
    }

    /**
     * @param channelId   渠道ID
     * @param channelName 渠道名字
     * @param importance  優先度
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
}
