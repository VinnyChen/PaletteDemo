# PaletteDemo
androidx取图片主色调，并将主色调设置为ImageView的背景

## 效果图

首先看一下取色的效果图：

![网络图片取主色调](http://q81ofivxp.bkt.clouddn.com/blog/image/palette_color.gif)

## 一、添加三方库

```
// 用来将网络图片装成bitmap
api 'com.github.bumptech.glide:glide:4.9.0'
// 用来获取网络图片的主色调
api 'androidx.palette:palette:1.0.0
```

## 二、获取图片主色调

```
Glide.with(mContext).asBitmap()
    .load(imgUrl)
    .into(object : CustomTarget<Bitmap>() {
        override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
            // 从bitmap图片中取主色调为bgImageView上色，bitmap为网络图片
            // PaletteHelper是图片取色、上色工具类
            PaletteUtils.setPaletteColor(bitmap, bgImageView)
            }

        override fun onLoadCleared(placeholder: Drawable?) {}
          })
```
## 三、为控件上色


```
object PaletteHelper {
  /**
   * 设置图片主色调
   *
   * @param bitmap
   * @return
   */
  fun setPaletteColor(bitmap: Bitmap?, view: View) {
    if (bitmap == null) {
      return
    }
    Palette.from(bitmap).maximumColorCount(10).generate { palette ->
      palette?.run {
        //获取主色调
        val mainColorValue = dominantSwatch
        //获取活力色调
        val colorValue01 = vibrantSwatch
        //获取活力黑色调
        val colorValue02 = darkVibrantSwatch
        //获取活力亮色调
        val colorValue03 = lightVibrantSwatch
        //获取柔和色调
        val colorValue04 = mutedSwatch
        //获取柔和黑色调
        val colorValue05 = darkMutedSwatch
        //获取柔和亮色调
        val colorValue06 = lightMutedSwatch
        mainColorValue?.let {
          if (view is ImageView) {
            // 当ImageView含有图片资源文件时，要改变图片的颜色，用此方法
            view.setColorFilter(it.rgb)
          } else {
            view.setBackgroundColor(mainColorValue.getRgb())
          }
        }
      }

    }
  }
}
```
**扩展：**
1.  为普通控件或者ImageView添加背景色
```
view.setBackgroundColor(int颜色值)
```
2.  为含有src资源的图片改变颜色(即改变图片的颜色)
```
view.setColorFilter(it.rgb)
```


[代码直通车](https://github.com/VinnyChen/PaletteDemo)


