package com.vinny.palettedemo

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.palette.graphics.Palette

/**
 * Description : PaletteHelper 主色调工具类
 * Created : CG
 * Time : 2020/3/24
 * Version : 0.0.1
 */
object PaletteHelper {
  /**
   * 设置图片主色调
   *
   * @param bitmap
   * @return
   */
  fun setPaletteColor(
    bitmap: Bitmap?,
    view: View
  ) {
    if (bitmap == null) {
      return
    }
    Palette.from(bitmap)
        .maximumColorCount(10)
        .generate { palette ->
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
                view.setBackgroundColor(it.rgb)
              }
            }
          }
        }
  }
}