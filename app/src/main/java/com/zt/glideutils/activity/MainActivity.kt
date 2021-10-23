package com.zt.glideutils.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.zt.library.utils.GlideUtils
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

    /**
     * 加载网络图片
     */
    fun loadClick(view: View){
        GlideUtils.load(this, url, imageView)
    }
    /**
     * 加载默认属性设置的图片
     */
    fun loadErrorClick(view: View){
        GlideUtils.loadDefaultOptionsImage(this, errorUrl, imageView)
    }

    /**
     * 加载圆形图片
     */
    fun loadCircleClick(view: View){
        GlideUtils.loadCircleImage(this, url, imageView)
    }
    /**
     * 加载圆角图片
     */
    fun loadRoundClick(view: View){
        GlideUtils.loadRoundImage(this, url, imageView, 20)
    }

    /**
     * 加载4个角可以自定义的圆角图片
     */
    fun loadCustomerRoundClick(view: View){
        GlideUtils.loadRoundImage(this, url, imageView, 15f,30f,45f,80f)
    }

    /**
     * 模糊图片处理
     */
    fun loadBlurClick(view: View){
        GlideUtils.loadBlurImage(this, url, imageView, 15)
    }

    /**
     * 取消图片加载
     */
    fun clearClick(view: View){
        GlideUtils.clear(this, imageView)
    }

    /**
     * 清除图片缓存
     */
    fun clearCacheClick(view: View){
        GlideUtils.clearImageMemoryCache(this)
        GlideUtils.clearImageDiskCache(this)
    }

    /**
     * 获取图片缓存大小
     */
    fun cacheSizeClick(view: View){
        val size = GlideUtils.cacheSize(this)
        Toast.makeText(this,size, Toast.LENGTH_SHORT).show()
    }
}