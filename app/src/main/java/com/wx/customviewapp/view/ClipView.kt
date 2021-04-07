package com.wx.customviewapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.wx.customviewapp.R
import com.wx.customviewapp.util.dp
import com.wx.customviewapp.util.getBitmap

private val BITMAP_SIZE = 200.dp
private val BITMAP_PADDING = 100.dp

//范围裁切 clipRect()  clipPath()   clipOutRect()//选中区域内不要   clipOutPath()
//几何变换 translate(x,y)  rotate(degree,x,y)  scale(x,y)  skew(x,y) 要倒着写
//Matrix的几何变换 pre+上面，post+上面  post...可以正着写，不用思考坐标系变化

class ClipView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getBitmap(R.drawable.avatar, BITMAP_SIZE.toInt())
    private val cliped = Path().apply {
        addOval(
            BITMAP_PADDING,
            BITMAP_PADDING,
            BITMAP_PADDING + BITMAP_SIZE,
            BITMAP_PADDING + BITMAP_SIZE,
            Path.Direction.CCW
        )
    }

    override fun onDraw(canvas: Canvas) {
//        canvas.clipRect(
//            BITMAP_PADDING,
//            BITMAP_PADDING,
//            BITMAP_PADDING + BITMAP_SIZE / 2f,
//            BITMAP_PADDING + BITMAP_SIZE / 2f
//        )
        canvas.clipPath(cliped)
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
    }
}