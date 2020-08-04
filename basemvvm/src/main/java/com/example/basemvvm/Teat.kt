package com.example.basemvvm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.basemvvm.util.ALog
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_test.*

/****************************************************
 * Copyright (C) 雲端互動 Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/8/3
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class Teat :AppCompatActivity() {
    private lateinit var mCallbackManager: CallbackManager
    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        mCallbackManager = CallbackManager.Factory.create()
        mFirebaseAuth = FirebaseAuth.getInstance()

        test_facebook_btn.setReadPermissions("email", "public_profile")
        test_facebook_btn.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                ALog.logError("onSuccess")
                result?.accessToken?.let { handleFacebookAccessToken(it) }
            }

            override fun onCancel() {
                ALog.logError("onCancel")
            }

            override fun onError(error: FacebookException?) {
                ALog.logError("onError")
            }
        })
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mFirebaseAuth.currentUser
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        ALog.logError("onActivityResult")
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        ALog.logError("handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        ALog.logError("signInWithCredential:success")
                        val user = mFirebaseAuth.currentUser
                        user?.let { ALog.logError("user = ${it.email}") }

                    } else {
                        // If sign in fails, display a message to the user.
                        ALog.logError("signInWithCredential:failure${task.exception}")
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }

}