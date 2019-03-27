package com.example.fish.alandemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ConstraintLayout mClFbLogin;
    private ConstraintLayout mClGoogleLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainActivity_fbLoginConstraintLayout:
                break;
            case R.id.mainActivity_googleLoginConstraintLayout:
                break;
        }
    }
}
