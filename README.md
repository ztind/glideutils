# glideutils

#### Glide4.x工具类封装

1. 实现本地，网络图片的加载
2. 图片预加载图片自定义
3. 圆角，圆形图片裁剪变换
4. 图片高斯模糊实现
5. 图片占用大小获取
6. 图片内存，磁盘缓存清除

### UI
<img src="screenshot/aa.jpg" width="49%"/>


#### 快速开始

1) 在 project 的 build.gradle 文件中找到 allprojects{} 代码块添加

    ```
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' } //增加jitPack Maven仓库
        }
    }
    ```
2) 开启databinding支持，在项目的build.gradle文件中的android{}下添加如下代码
    ```
     buildFeatures{
         dataBinding = true
     }
     ```
3) 在 app 的 build.gradle 文件中找到 dependencies{} 代码块添加

    ```
    dependencies {
        implementation 'io.github.ztind:mvvm:1.0.2'
    }
    ```
