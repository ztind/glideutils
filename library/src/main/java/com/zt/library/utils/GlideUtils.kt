package com.zt.library.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Looper
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zt.library.R
import java.io.File
import java.text.DecimalFormat

/**
Describe：
Glide4.x工具类封装
1.实现本地，网络图片的加载
2.图片预加载图片自定义
3.圆角，圆形图片裁剪变换
4.图片高斯模糊实现
5.图片占用大小获取
6.图片内存，磁盘缓存清除
Author:ZT
Date:2021/10/1
github doc:https://muyangmin.github.io/glide-docs-cn/
 */
class GlideUtils private constructor() {

    companion object{
        val defaultOptions: RequestOptions = RequestOptions()
                .placeholder(R.mipmap.image_placeholder)//图片加载前显示的占位图片
                .fallback(R.mipmap.image_fallback)//url为空的时候,显示的图片
                .error(R.mipmap.image_error)//图片加载失败显示的图片
                .priority(Priority.HIGH)
                .centerCrop()
        /**
         * 加载网络图片
         */
        fun load(context:Context,url :String,imageView:ImageView){
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .into(imageView)
        }
        /**
         * 取消加载
         */
        fun clear(context: Context,imageView: ImageView){
            Glide.with(context).clear(imageView)
        }
        /**
         * 图片默认设置的预加载，加载为null,加载失败图片
         */
        fun loadDefaultOptionsImage(context:Context,url :String,imageView:ImageView){
            Glide.with(context)
                    .load(url)
                    .apply(defaultOptions)
                    .into(imageView)
        }
        /**
         * 自定义预加载，加载为null,加载失败图片
         * @param placeholderResId 图片加载前显示的占位图片
         * @param fallbackResId url为空的时候,显示的图片
         * @param errorResId 图片加载失败显示的图片
         */
        fun loadCustomerOptions(context:Context, url :String, imageView:ImageView,@DrawableRes placeholderResId:Int,@DrawableRes fallbackResId:Int,@DrawableRes errorResId:Int){
            val options: RequestOptions = RequestOptions()
                    .placeholder(placeholderResId)//图片加载前显示的占位图片
                    .fallback(fallbackResId)//url为空的时候,显示的图片
                    .error(errorResId)//图片加载失败显示的图片
                    .priority(Priority.HIGH)
                    .centerCrop()
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView)
        }
        /**
         * 从文件加载图片
         */
        fun loadFromFile(context: Context,file:File,imageView: ImageView){
            Glide.with(context)
                    .load(file)
                    .centerCrop()
                    .into(imageView)
        }
        /**
         * 加载本地uri图片(eg:音乐专辑图片...)
         */
        fun loadFromUri(context: Context,uri:Uri,imageView: ImageView){
            Glide.with(context)
                    .load(uri)
                    .centerCrop()
                    .into(imageView)
        }
        /**
         * 加载Bitmap显示
         */
        fun loadFromBitmap(context: Context,bitmap:Bitmap,imageView: ImageView){
            Glide.with(context)
                    .load(bitmap)
                    .centerCrop()
                    .into(imageView)
        }

        /**
         * 加载圆形图片，图片宽高需一致，内部自动计算(Glide内置变换CircleCrop)
         */
        fun loadCircleImage(context:Context,url :String,imageView:ImageView){
            Glide.with(context)
                    .load(url)
                    .transform(CenterCrop(),CircleCrop())
                    .into(imageView)
        }
        /**
         * 加载圆角图片(Glide内置变换RoundedCorners)
         * @param roundingRadius 4个圆角的大小
         */
        fun loadRoundImage(context:Context,url :String,imageView:ImageView,roundingRadius:Int){
            Glide.with(context)
                    .load(url)
                    .transform(CenterCrop(),RoundedCorners(roundingRadius))
                    .into(imageView)
        }
        /**
         * 加载圆角图片，自定义设置四个角圆角大小
         * @param topLeft 顶部左圆角大小
         * @param topRight 顶部右圆角大小
         * @param bottomLeft 低部左圆角大小
         * @param bottomRight 低部右圆角大小
         */
        fun loadRoundImage(context:Context,url :String,imageView:ImageView , topLeft:Float,topRight:Float,bottomLeft:Float,bottomRight:Float){
            Glide.with(context)
                    .load(url)
                    .transform(CenterCrop(),
                        RoundTransformation(topLeft,topRight,bottomLeft,bottomRight)
                    )
                    .into(imageView)
        }
        /**
         * 图片毛玻璃效果(高斯模糊)
         * @param blurRadius 模糊半径
         */
        fun loadBlurImage(context:Context,url :String,imageView:ImageView,blurRadius:Int){
            Glide.with(context)
                    .load(url)
                    .transform(CenterCrop(), BlurTransformation(blurRadius,2))
                    .into(imageView)
        }
        /**
         * 图片毛玻璃效果(高斯模糊)
         * @param blurRadius 模糊半径
         * @param sampling 画布y单位缩放量
         */
        fun loadBlurImage(context:Context,url :String,imageView:ImageView,blurRadius:Int,sampling:Int){
            Glide.with(context)
                    .load(url)
                    .transform(CenterCrop(), BlurTransformation(blurRadius,sampling))
                    .into(imageView)
        }
        /**
         * 清除图片磁盘缓存(子线程)
         * @param context 上下文
         */
        fun clearImageDiskCache(context: Context){
            if(Thread.currentThread() == Looper.getMainLooper().thread){
                Thread(Runnable {
                    Glide.get(context).clearDiskCache()
                }).start()
            }else{
                Glide.get(context).clearDiskCache()
            }
        }
        /**
         * 清除图片内存缓存(主线程)
         * @param context 上下文
         */
        fun clearImageMemoryCache(context: Context){
            if(Thread.currentThread() == Looper.getMainLooper().thread)
                Glide.get(context).clearMemory()
        }
        /**
         * 获取图片缓存大小,缓存大小计算
         */
        fun cacheSize(context: Context):String{
            val cacheDir = Glide.getPhotoCacheDir(context)
            val parentFile = cacheDir!!.parentFile
            val size: Long = getDirSize(parentFile)
            val sizeText: String = byteConversionGBMBKB(size.toDouble())
            return sizeText
        }
        private fun getDirSize(dir: File?): Long {
            if (dir == null) {
                return 0
            }
            if (!dir.isDirectory) {
                return 0
            }
            var dirSize: Long = 0
            val files = dir.listFiles()
            for (file in files) {
                if (file.isFile) {
                    dirSize += file.length()
                } else if (file.isDirectory) {
                    dirSize += file.length()
                    dirSize += getDirSize(file) // 递归调用继续统计
                }
            }
            return dirSize
        }
        private val GB = 1024L * 1024L * 1024L .toDouble()  // 定义GB的计算常量
        private val MB = 1024L * 1024L .toDouble() // 定义MB的计算常量
        private val KB = 1024.0 // 定义KB的计算常量
        private fun byteConversionGBMBKB(kSize: Double): String{
            val df = DecimalFormat("#.00")
            var temp = 0.0
            if (kSize / GB >= 1) {
                temp = kSize / GB
                return df.format(temp).toString() + "GB"
            } else if (kSize / MB >= 1) {
                temp = kSize / MB
                return df.format(temp).toString() + "MB"
            } else if (kSize / KB >= 1) {
                temp = kSize / KB
                return df.format(temp).toString() + "KB"
            }
            return kSize.toString() + "B"
        }
    }

}