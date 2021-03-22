package com.wx.customviewapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.wx.customviewapp.R
import com.wx.customviewapp.util.dp
import com.wx.customviewapp.util.getBitmap

class MultilineTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val text =
        "xxxx xxxx xxxxxx xxxx xxxxx xxxxx xxxx xxxx xxx xx xxx xxxxx xxxxx xxxxx xxxxx " +
                "xxxx xxxx xxxxxx xxxx xxxxsssx xxxxx xxxx xxxx xxx xx xxx xxxxx xxxxx xxxxx xxxxx " +
                "xxxx xxxx xxxxxx xxxx xxxxx xxxxx xxxx xxxx xxx xx xxx xxxxx xxxxx xxxxx xxxxx " +
                "xxxx xxxx xxxxxx xxxx xxxxx xxxxx xxxx xxxx xxx xx xxx xxxxx xxxxx xxxxx xxxxx " +
                "xxxx xxxx xxxxxx xxxx xxxxx xxxxx xxxx xxxx xxx xx xxx xxxxx xxxxx xxxxx xxxxx " +
                "xxxx xxxx xxxxxx xxxx xxxxx xxxxx xxxx xxxx xxx xx xxx xxxxx xxxxx xxxxx xxxxx " +
                "xxxx xxxx xxxxxx xxxx xxxxx xxxxx xxxx xxxx xxx xx xxx xxxxx xxxxx xxxxx xxxxx " +
                "xxxx xxxxx xxx xxxx xxxx xxx xx xxx xxx x xxxx xx"
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16f.dp
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16f.dp
    }
    private val bitmap = getBitmap(R.drawable.avatar, 150f.dp.toInt())
    private val fontMetrics = Paint.FontMetrics()
    private val measuredWidth = floatArrayOf(1f)

    //  val staticLayout = StaticLayout.Builder.obtain(text, 0, text.length, paint, width)
    private val staticLayout by lazy {
        StaticLayout(
            text,
            textPaint,
            width,
            Layout.Alignment.ALIGN_NORMAL,
            1f,
            0f,
            false

        )
    }


    override fun onDraw(canvas: Canvas) {
        //只有文字换行
//        staticLayout.draw(canvas)

        //自己控制换行
        canvas.drawBitmap(bitmap, width - bitmap.width.toFloat(), 50.dp, paint)
        paint.getFontMetrics(fontMetrics)
        var start = 0
        var count: Int
        var baseLine = -fontMetrics.top
        var maxWidth: Float
        while (start < text.length) {
            maxWidth =
                if (baseLine + fontMetrics.bottom < 50.dp || baseLine + fontMetrics.top > 50.dp + bitmap.height) {
                    width.toFloat()
                } else {
                    width.toFloat() - bitmap.width
                }
            count = paint.breakText(text, start, text.length, true, maxWidth, measuredWidth)
            canvas.drawText(text, start, start + count, 0f, baseLine, paint)
            start += count
            baseLine += paint.fontSpacing
        }

    }
}