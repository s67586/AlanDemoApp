package com.example.basemvvm.base

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/****************************************************
 * Author: AlanLai
 * Create Date: 2020/5/13
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

open class BaseDialogFragment : DialogFragment() {

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            manager.beginTransaction().remove(this).commit()
            if (!isVisible) {
                super.show(manager, tag)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}