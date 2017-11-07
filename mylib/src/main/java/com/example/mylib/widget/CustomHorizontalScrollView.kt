package com.example.mylib.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import com.example.mylib.util.getScreenSize

/**
 * CustomHorizontalScrollView
 * @author zlw
 * @date 2017/11/6
 *r
 */
class CustomHorizontalScrollView @JvmOverloads constructor(context: Context, attrSets: AttributeSet? = null, defStyleAttrs: Int = 0)
    : HorizontalScrollView(context, attrSets, defStyleAttrs) {

    //滑动的阈值，默认为屏幕宽度是一半
    var SCROLL_DISTANCE: Int = getScreenSize(context).widthPixels / 4

    //图片之间的边距
    var itemMargin = 10

    //一页显示几张图片
    var pageCount = 3

    //当前的页数
    var pageIndex = 0

    //按下时候的x坐标
    private var mDownX: Float = 0F
    //滑动的距离
    private var mDistance: Float = 0F
    //按下的时候保留当前滑动的值
    private var mCurPosition: Int = 0

    //屏幕的宽度
    val screenWidth by lazy {
        getScreenSize(context).widthPixels
    }

    //ScrolView下面的唯一的容器
    private val mContainer by lazy {
        LinearLayout(context).apply {
            val param = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            layoutParams = param
            orientation = LinearLayout.HORIZONTAL
        }
    }

    init {
        addView(mContainer)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = ev.x
                mCurPosition = scrollX
            }

            MotionEvent.ACTION_MOVE -> {
                mDistance = ev.x - mDownX
            }

            MotionEvent.ACTION_UP -> {
//                Log.e(TAG, "scrollX = $scrollX scroll_distance = $SCROLL_DISTANCE")
                scroll(mDistance < 0)
            }

        }
        return super.onTouchEvent(ev)
    }

    private fun scroll(flag: Boolean) {
        if (Math.abs(mDistance) > SCROLL_DISTANCE) {
            //调用post方法，否则smoothScrollTo方法会失效
            post {
                if (flag) {
                    pageIndex++
                } else {
                    pageIndex--
                }
                smoothScrollTo(pageIndex * screenWidth, 0)
            }
        } else {
            post {
                smoothScrollTo(mCurPosition, 0)
            }
        }
    }


    fun addImageView(vararg ig: View): CustomHorizontalScrollView {
        ig.forEach {
            mContainer.addView(it.apply {
                var param = layoutParams
                if (param == null) {
//                param = MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
                    param = LinearLayout.LayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
                }
                if (mContainer.childCount != 0 && (mContainer.childCount + 1) % pageCount == 0) {
                    (param as LinearLayout.LayoutParams).leftMargin = itemMargin
                }
                //设置图片的尺寸
                //首先设置图片的宽度 (screen - screenLeftPadding - screenRightPadding - (pageCount - 1) * itemMargin) / itemcount
                param.width = (screenWidth - mContainer.paddingLeft - mContainer.paddingRight - (pageCount - 1) * itemMargin) / pageCount
                // param.height = ...
                //TODO 这里可以设置图片比例不变 保证图片不变形 以后可以再写

                layoutParams = param
            })
        }
        return this
    }

}