package com.wx.customviewapp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wx.customviewapp.util.dp

private val DASH_WIDTH = 2.dp
private val DASH_LENGTH = 10.dp
private val OFFSET = 150f.dp
private const val OPEN_ANGLE = 120f
private val POINTER_LENGTH = 120f.dp

class DashboardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var centerX = 0f
    private var centerY = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val pathDash = Path()
    private lateinit var pathMeasure: PathMeasure
    private lateinit var pathDashEffect: PathEffect
    private var perAngle = 0f

    init {
        paint.strokeWidth = 3f.dp
        paint.style = Paint.Style.STROKE
        //paint.setPathEffect(PathDashPathEffect)为path设置效果
    }


    //view尺寸改变时候回调这个方法，开始就会调用这个方法
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = width / 2f
        centerY = height / 2f
        path.reset()
        pathDash.reset()
        //圈
        path.addArc(
            centerX - OFFSET,
            centerY - OFFSET,
            centerX + OFFSET,
            centerY + OFFSET,
            90f + OPEN_ANGLE / 2, 360f - OPEN_ANGLE
        )

        //刻度
        pathMeasure = PathMeasure(path, false)
        val pre = (pathMeasure.length - DASH_WIDTH) / 20
        perAngle = (pathMeasure.length - DASH_WIDTH) / 20
        pathDash.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
        pathDashEffect = PathDashPathEffect(pathDash, pre, 0f, PathDashPathEffect.Style.ROTATE)


    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)

        paint.pathEffect = pathDashEffect

        canvas.drawPath(path, paint)

        paint.pathEffect = null

        //指针

        val currentPoint = 5
        val indexAngle = 90 + OPEN_ANGLE / 2 + (360 - OPEN_ANGLE) / 20f * currentPoint
        canvas.drawLine(
            centerX,
            centerY,
            centerX + POINTER_LENGTH * Math.cos(Math.toRadians(indexAngle.toDouble())).toFloat(),
            centerY + POINTER_LENGTH * Math.sin(Math.toRadians(indexAngle.toDouble())).toFloat(),
            paint
        )


    }



}