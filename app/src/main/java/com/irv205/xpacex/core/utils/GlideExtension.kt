package com.irv205.xpacex.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.irv205.xpacex.R
import com.squareup.picasso.Picasso

fun <T> ImageView.setCircleImage(image: T?){
    try {
        Glide.with(context)
            .load(image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(R.drawable.default_user)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    } catch (e: Exception) {
        println()
    }
}

fun <T> ImageView.setCircleImageCache(image: T?){
    try {
        Glide.with(context)
            .load(image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .placeholder(R.drawable.default_user)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    } catch (e: Exception){}
}

fun <T> ImageView.setGlideImage(image: T?){
    try {
        Glide.with(context)
            .load(image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(R.drawable.image_placeholder)
            .apply(RequestOptions.centerCropTransform())
            .into(this)
    } catch (e: Exception){}
}

fun <T> ImageView.setGlideImageBG(image: T?){
    try {
        Glide.with(context)
            .load(image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(R.drawable.image_placeholder)
            .apply(RequestOptions.centerCropTransform())
            .into(this)
    } catch (e: Exception){}
}

fun <T> ImageView.setGlideImageGif(image: T?){
    try {
        Glide.with(context)
            .asGif()
            .load(image)
            .placeholder(R.drawable.image_placeholder)
            .apply(RequestOptions.centerCropTransform())
            .into(this)
    } catch (e: java.lang.Exception){}
}

fun <T> ImageView.setGlideImageCache(image: T?){
    try {
        Glide.with(context)
            .load(image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .placeholder(R.drawable.image_placeholder)
            .apply(RequestOptions.centerCropTransform())
            .into(this)
    } catch (e: Exception) {}
}

//fun <T> ImageView.setPicassoImage(image: T?){
//    try {
//        Picasso.get().load(image)
//            .placeholder(R.drawable.image_placeholder)
//            .resize(50, 50)
//            .centerCrop()
//            .into(this)
//    } catch (e: Exception) {}
//}