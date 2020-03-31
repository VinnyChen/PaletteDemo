package com.vinny.palettedemo

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private val url1 =
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585217951164&di=5f724461e5aecea01d79861c593878e0" +
        "&imgtype=0&src=http%3A%2F%2Fhbimg.huabanimg.com%2F5494450357e426011255c0868a0e03d664d62d0d2c1dc-v3O3vq_fw658"
  private val url2 =
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585217999078&di=2fc05a76015bc8c4ec63591a85484fbf" +
        "&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0187d958cbd012a801219c770054d2.jpg"
  private val url3 =
    "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2600035538,2001720319&fm=26&gp=0.jpgi"
  private val url4 =
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585218041862&di=432dcde64e0ff7bf57a05651b0dfc4e2" +
        "&imgtype=0&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D1931416355%2C642115345%26fm%3D214%26gp%3D0.jpg"

  private val urls = arrayOf(url1, url2, url3, url4)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    banner.run {
      setData(urls.toList(), null)
      setmAdapter { _, _, view, position ->
        Glide.with(this@MainActivity)
            .load(urls[position])
            .into(view as ImageView)
      }
      setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(
          position: Int,
          positionOffset: Float,
          positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
          Glide.with(this@MainActivity)
              .asBitmap()
              .load(urls[position])
              .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                  bitmap: Bitmap,
                  transition: Transition<in Bitmap>?
                ) {
                  PaletteHelper.setPaletteColor(bitmap, bg_home)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
              })
        }
      })
    }
  }
}
