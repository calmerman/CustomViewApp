package com.wx.customviewapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.wx.customviewapp.util.dp

private val CIRCLE_COLOR = Color.parseColor("#90a4ae")
private val HIGHLIGHT_COLOR = Color.parseColor("#ff4081")
private val RING_WIDTH = 20f.dp
private val RADIUS = 150f.dp

class SportView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 100f.dp
        //设置字体
//        typeface = ResourcesCompat.getFont(context,R.font.xxx)
//        isFakeBoldText //加粗，假的
        textAlign = Paint.Align.CENTER
    }
    private var centerX = 0f
    private var centerY = 0f

    private val bounds = Rect()
    private val fontMetrics = Paint.FontMetrics()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width / 2f
        centerY = height / 2f
    }

    override fun onDraw(canvas: Canvas) {
        //绘制圆环
        paint.color = CIRCLE_COLOR
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = RING_WIDTH
        canvas.drawCircle(centerX, centerY, RADIUS, paint)

        //绘制进度条
        paint.color = HIGHLIGHT_COLOR
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            centerX - RADIUS,
            centerY - RADIUS,
            centerX + RADIUS,
            centerY + RADIUS,
            -90f,
            270f,
            false,
            paint
        )

        //1、绘制文字垂直居中
        paint.style = Paint.Style.FILL
        //静态文字垂直居中
//        paint.getTextBounds("abab", 0, "abab".length, bounds)
//        val baseLine = centerY - (bounds.top + bounds.bottom) / 2
//        canvas.drawText("abab", centerX, baseLine, paint)
        //动态文字垂直居中
        paint.getFontMetrics(fontMetrics)
        val baseLine = centerY - (fontMetrics.ascent + fontMetrics.descent) / 2
        canvas.drawText("abab", centerX, baseLine, paint)

        //2、绘制文字上贴边
        paint.textAlign = Paint.Align.LEFT
        paint.getFontMetrics(fontMetrics)
        canvas.drawText("abab", -bounds.left.toFloat(), -fontMetrics.top, paint)

    }

}