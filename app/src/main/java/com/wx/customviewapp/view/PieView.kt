package com.wx.customviewapp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.wx.customviewapp.util.dp
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 100f.dp
private val ANGLES = floatArrayOf(60f, 90f, 150f, 60f)
private val COLORS = listOf("#ff0000", "#0fff00", "#000000", "#ffff00")

class PieView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var centerX = 0f
    private var centerY = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)


    //view尺寸改变时候回调这个方法，开始就会调用这个方法
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = width / 2f
        centerY = height / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var startAngle = 0f
        for ((index, angle) in ANGLES.withIndex()) {
            paint.color = Color.parseColor(COLORS[index])

            if (index == 1){
                canvas.save()
                val offset = 10f.dp
                val translateX =  offset * cos(Math.toRadians((startAngle+angle/2).toDouble())).toFloat()
                val translateY =  offset * sin(Math.toRadians((startAngle+angle/2).toDouble())).toFloat()
                canvas.translate(translateX,translateY)
            }
            canvas.drawArc(
                centerX - RADIUS,
                centerY - RADIUS,
                centerX + RADIUS,
                centerY + RADIUS,
                startAngle,
                angle,
                true,
                paint
            )
            startAngle += angle
            if (index == 1){
                canvas.restore()
            }

        }


    }


}