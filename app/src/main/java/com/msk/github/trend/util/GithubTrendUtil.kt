package com.msk.github.trend.util

import android.app.ActionBar
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

val SORT_BY_STARS: String = "SORT_BY_STARS"
val SORT_BY_NAMES: String = "SORT_BY_NAMES"

// This is method for expanding view with animation
fun expand(v: View) {
    val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(
        (v.parent as View).width,
        View.MeasureSpec.EXACTLY
    )
    val wrapContentMeasureSpec =
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
    val targetHeight = v.measuredHeight

    v.layoutParams.height = 1
    v.visibility = View.VISIBLE
    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            v.layoutParams.height = if (interpolatedTime == 1f)
                ActionBar.LayoutParams.WRAP_CONTENT
            else
                (targetHeight * interpolatedTime).toInt()
            v.requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }
    a.duration = (targetHeight / v.context.resources.displayMetrics.density).toLong() * 7
    v.startAnimation(a)
}

//This is method for collapse view with animation
fun collapse(v: View) {
    val initialHeight = v.measuredHeight

    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                v.visibility = View.GONE
            } else {
                v.layoutParams.height =
                    initialHeight - (initialHeight * interpolatedTime).toInt()
                v.requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }
    a.duration = (initialHeight / v.context.resources.displayMetrics.density).toLong() * 7
    v.startAnimation(a)
}

//check for the internet connection
fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
}

//Extension function for visibility view
fun View.showView(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

//check for the time difference
fun getTimeDiff(currentTime: Long, previousTime: Long): Long {
    var diff = currentTime - previousTime
    return (diff / (60 * 60 * 1000))
}


