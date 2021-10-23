#### 快速开始

1) 在 project 的 build.gradle 文件中找到 allprojects{} 代码块添加

    ```
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' } //增加jitPack Maven仓库
        }
    }
    ```
2) 在 app 的 build.gradle 文件中找到 dependencies{} 代码块添加<br/>
   最新版本 <img src="https://jitpack.io/v/ztind/glideutils.svg"/>

    ```
    dependencies {
        implementation 'com.github.ztind:glideutils:latest.release'
    }
    ```

#### Glide4.x工具类封装

1. 实现本地，网络图片的加载
2. 图片预加载图片自定义
3. 圆角，圆形图片裁剪变换
4. 图片高斯模糊实现
5. 图片占用大小获取
6. 图片内存，磁盘缓存清除

### UI
<img src="screenshot/aa.jpg" width="49%"/>

#### how to use api?

  ```
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
        GlideUtils.loadDefaultOptionsImage(this, url, imageView)
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
  ```
