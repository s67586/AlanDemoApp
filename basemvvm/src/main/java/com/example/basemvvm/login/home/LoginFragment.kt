package com.example.basemvvm.login.home

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.basemvvm.R
import com.example.basemvvm.base.BaseFragment
import com.example.basemvvm.databinding.FragmentLoginBinding
import com.example.basemvvm.login.LoginMainRepository
import com.example.basemvvm.login.LoginMainViewModel
import com.example.basemvvm.util.ALog
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.fragment_login.*

/****************************************************
 * Copyright (C) 雲端互動 Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/7/28
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginMainViewModel, LoginViewModel>() {
    companion object {
        const val GOOGLE_SIGN_IN = 12333;
    }

    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun getActivityViewModelFactory(): ViewModelProvider.Factory? {
        return LoginMainViewModel.Factory(requireActivity().application, LoginMainRepository())
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        return LoginViewModel.Factory(requireActivity().application, LoginRepository())
    }

    override fun initConfiguration() {
        mFirebaseAuth = FirebaseAuth.getInstance()

        navigate_btn.setPermissions("email", "public_profile")
        navigate_btn.registerCallback(mActivityViewModel.mFacebookCallbackManager, object : FacebookCallback<LoginResult> {
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

       
        navigate_btn2.setOnClickListener {
            startActivityForResult(GoogleSignIn.getClient(requireActivity(), GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.google_web_client_id))
                    .requestEmail()
                    .build()).signInIntent, GOOGLE_SIGN_IN)
        }
    }

    override fun observeLiveData() {
        mViewModel.mFireBaseUserLiveData.observe(this, Observer {
            mFirebaseAuth.signOut()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                ALog.logError("firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                ALog.logError("Google sign in failed${e}")
                // ...
            }
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        ALog.logError("handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        ALog.logError("signInWithCredential:success email = ${mFirebaseAuth.currentUser?.email}")
                        mViewModel.mFireBaseUserLiveData.postValue(mFirebaseAuth.currentUser)
                        val navController = findNavController(this)
                        navController.navigate(R.id.action_AFragment_to_BFragment)

                    } else {
                        // If sign in fails, display a message to the user.
                        ALog.logError("signInWithCredential:failure${task.exception}")
                        Toast.makeText(requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        mViewModel.mFireBaseUserLiveData.postValue(null)
                    }
                }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        ALog.logError("signInWithCredential:success email = ${mFirebaseAuth.currentUser?.email}")
                        mViewModel.mFireBaseUserLiveData.postValue(mFirebaseAuth.currentUser)
                        val navController = findNavController(this)
                        navController.navigate(R.id.action_AFragment_to_BFragment)
                    } else {
                        // If sign in fails, display a message to the user.
                        ALog.logError("signInWithCredential:failure${task.exception}")
                        // ...
                        Toast.makeText(requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        mViewModel.mFireBaseUserLiveData.postValue(null)
                    }

                    // ...
                }
    }
}