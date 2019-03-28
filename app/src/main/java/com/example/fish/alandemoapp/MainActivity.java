package com.example.fish.alandemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.thirdpartylogin.Callback;
import com.example.thirdpartylogin.facebook.FacebookController;
import com.example.thirdpartylogin.facebook.FacebookData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FacebookController mFacebookController;
    private ConstraintLayout mClFbLogin;
    private ConstraintLayout mClGoogleLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        mFacebookController = FacebookController.newInstance(this);
        mFacebookController.getHashKey();
    }

    private void initView() {
        mClFbLogin = findViewById(R.id.mainActivity_fbLoginConstraintLayout);
        mClGoogleLogin = findViewById(R.id.mainActivity_googleLoginConstraintLayout);
    }

    private void initListener() {
        mClFbLogin.setOnClickListener(this);
        mClGoogleLogin.setOnClickListener(this);
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
        }
    }
}
