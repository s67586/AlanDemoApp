package com.example.basemvvm.interview

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.basemvvm.R
import com.example.basemvvm.util.ALog


/****************************************************
 * Author: alanlai
 * Create Date: 12/11/20
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/


@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
            .load(GlideUrl(imageUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()))
            .error(R.drawable.common_full_open_on_phone)
            .into(imageView)
}