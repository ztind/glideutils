package com.zt.glideusedemo.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

/**
Describe：文件描述
Author:ZT
Date:2021/10/1
 */
class BlurTransformation : BitmapTransformation{

    private val VERSION = 1
    private val ID = javaClass.name+VERSION

    private val MAX_RADIUS = 25 //默认最大模糊半径
    private val DEFAULT_DOWN_SAMPLING = 1//默认画布y单位缩放量

    private var radius = 0 //用户传入的模糊半径
    private var sampling = 0 //用户传入的画布y单位缩放量

    constructor():this(25, 1)

    constructor(radius: Int):this(radius, 1)

    constructor(radius: Int, sampling: Int){
        this.radius = radius
        this.sampling = sampling
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap?{
        val width = toTransform.width
        val height = toTransform.height
        val scaledWidth = width / sampling
        val scaledHeight = height / sampling
        var bitmap: Bitmap? = pool[scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888]
        val canvas = Canvas(bitmap!!)
        canvas.scale(1 / sampling.toFloat(), 1 / sampling.toFloat())
        val paint = Paint()
        paint.flags = Paint.FILTER_BITMAP_FLAG
        canvas.drawBitmap(toTransform, 0f, 0f, paint)
        bitmap = FastBlur.blur(bitmap, radius, true)
        return bitmap
    }

    override fun toString(): String{
        return "BlurTransformation(radius=$radius, sampling=$sampling)"
    }

    override fun equals(o: Any?): Boolean {
        return o is BlurTransformation && o.radius == radius && o.sampling == sampling
    }

    override fun hashCode(): Int {
        return ID.hashCode() + radius * 1000 + sampling * 10
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update((ID + radius + sampling).toByteArray(CHARSET))
    }


}