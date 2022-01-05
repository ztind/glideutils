package com.zt.library.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
Describe：databinding中的ImageView与Glide结合使用
Author:ZT
Date:2021/10/18
 */
object ImageViewAttrAdapter {
    /**
     * 加载正常类图片，加载前/图片地址为空/加载错误时显示的图片
     * app:img_normal_url="@{xxx}"方式在ImageView控件里使用
     * @param imageView    图片控件本身
     * @param img_normal_url  图片地址
     */
    @BindingAdapter("img_normal_url")
    @JvmStatic
    fun loadNormalImage(imageView: ImageView, img_normal_url: String) {
        GlideUtils.loadDefaultOptionsImage(imageView.context,img_normal_url,imageView)
    }
    /**
     * 模糊图片,高斯模糊
     * app:img_blur_url="@{xxx}" 图片地址
     * app:blur_radius="@{xxx}" 模糊半径
     */
    @BindingAdapter("img_blur_url","blur_radius")
    @JvmStatic
    fun loadBlurImage(imageView: ImageView, img_normal_url: String,blur_radius:Int){
        GlideUtils.loadBlurImage(imageView.context,img_normal_url,imageView,blur_radius)
    }
    /**
     * 加载圆形图片，图片宽高需一致，内部自动计算(Glide内置变换CircleCrop)
     */
    @BindingAdapter("img_circle_url")
    @JvmStatic
    fun loadCircleImage(imageView: ImageView,img_circle_url:String){
        GlideUtils.loadCircleImage(imageView.context,img_circle_url,imageView)
    }
}