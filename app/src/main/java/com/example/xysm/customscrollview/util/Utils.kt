package com.example.xysm.customscrollview.util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

/**
 * Utils
 * @author zlw
 * @date 2017/11/6
 */


fun getScreenSize(context:Context): DisplayMetrics {
    val activity = context as Activity
    val metrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(metrics)
    return metrics
}