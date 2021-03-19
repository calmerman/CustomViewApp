package com.wx.customviewapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.wx.customviewapp.util.dp

private val RADIUS = 100f.dp

class TestBaseView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var centerX = 0f
    private var centerY = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    private lateinit var pathMeasure: PathMeasure




    //view尺寸改变时候回调这个方法，开始就会调用这个方法
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = width / 2f
        centerY = height / 2f
        path.reset() //重置
        //Path.Direction CW clockwise顺时针，CCW counter-clockwise逆时针
        path.addCircle(centerX, centerY, RADIUS, Path.Direction.CW)
        path.addRect(
            centerX - RADIUS,
            centerY,
            centerX + RADIUS,
            centerY + RADIUS * 2,
            Path.Direction.CCW
        )
        //Path的fillType属性判断重叠绘制部分的内外 WINDING(默认，0外) / EVEN_ODD(奇内偶外，做镂空)
//        path.fillType = Path.FillType.EVEN_ODD
        //测量path
        pathMeasure = PathMeasure(path,false) //第二个参数含义是是否测量闭合的path
        //path的长度
        pathMeasure.length
        //pathMeasure.getPosTan(); 切角的正切值
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画线
//        canvas.drawLine(100f, 100f, 200f, 200f, paint)
        //画圆
//        canvas.drawCircle(centerX, centerY, RADIUS, paint)
        //画path
        canvas.drawPath(path, paint)

    }


}