package com.example.xysm.customscrollview

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.xysm.customscrollview.util.getScreenSize
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        custom_scroll.apply {
            //设置一页显示多少张图片
            pageCount = 2

            //设置滑动的阈值 值越小，即滑动越小的距离松手即可自动滚动到上/下页
            SCROLL_DISTANCE = getScreenSize(this@MainActivity).widthPixels / 4
        }

        for (i in 0..8){
//            ivLists.add()
            custom_scroll.addImageView(ImageView(this).apply {
                setBackgroundResource(R.mipmap.ic_cover_1 + i)
            })

        }
    }
}
