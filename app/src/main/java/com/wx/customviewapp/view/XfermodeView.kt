package com.wx.customviewapp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wx.customviewapp.util.dp

class XfermodeView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)

    private val circleBitmap =
        Bitmap.createBitmap(150f.dp.toInt(), 150f.dp.toInt(), Bitmap.Config.ARGB_8888)
    private val squareBitmap =
        Bitmap.createBitmap(150f.dp.toInt(), 150f.dp.toInt(), Bitmap.Config.ARGB_8888)


    init {

        val canvasCircle = Canvas(circleBitmap)
        paint.color = Color.parseColor("#d81b60")
        canvasCircle.drawOval(50f.dp, 0f.dp, 150f.dp, 100f.dp, paint)

        val canvasSquare = Canvas(squareBitmap)
        paint.color = Color.parseColor("#2196f3")
        canvasSquare.drawRect(0f.dp, 50f.dp, 100f.dp, 150f.dp, paint)
    }

    override fun onDraw(canvas: Canvas) {

        val saveLayerCount = canvas.saveLayer(150f.dp, 50f.dp, 300f.dp, 200f.dp, null)

        canvas.drawBitmap(circleBitmap,150f.dp,50f.dp, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(squareBitmap,150f.dp,50f.dp, paint)
        paint.xfermode = null
        canvas.restoreToCount(saveLayerCount)
    }
}