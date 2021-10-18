package com.zt.glideutils.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.zt.glideusedemo.utils.GlideUtils
import com.zt.glideutils.R

/**
 * Glide4.x api 二度封装
 */
class MainActivity : AppCompatActivity() {
    //正常正确的图片地址
    private val url = "https://vdposter.bdstatic.com/b078139c2258d2c9d6147667cf813e32.jpeg"
    //错误类型or失效的图片地址
    private val errorUrl = "https://www.baidu.com"

    private  lateinit var  imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image)
        GlideUtils.load(this, url, imageView)
    }
    fun loadClick(view: View){
        GlideUtils.load(this, url, imageView)
    }
    fun loadErrorClick(view: View){
        GlideUtils.loadDefaultOptionsImage(this, errorUrl, imageView)
    }
    fun loadCircleClick(view: View){
        GlideUtils.loadCircleImage(this, url, imageView)
    }
    fun loadRoundClick(view: View){
        GlideUtils.loadRoundImage(this, url, imageView, 20)
    }
    fun loadCustomerRoundClick(view: View){
        GlideUtils.loadRoundImage(this, url, imageView, 15f,30f,45f,80f)
    }
    fun loadBlurClick(view: View){
        GlideUtils.loadBlurImage(this, url, imageView, 15)
    }
    fun clearClick(view: View){
        GlideUtils.clear(this, imageView)
    }
    fun clearCacheClick(view: View){
        GlideUtils.clearImageMemoryCache(this)
        GlideUtils.clearImageDiskCache(this)
    }
    fun cacheSizeClick(view: View){
        val size = GlideUtils.cacheSize(this)
        Toast.makeText(this,size, Toast.LENGTH_SHORT).show()
    }
}