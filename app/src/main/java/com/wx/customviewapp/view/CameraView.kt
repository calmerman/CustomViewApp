package com.wx.customviewapp.view

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.wx.customviewapp.R
import com.wx.customviewapp.util.dp
import com.wx.customviewapp.util.getBitmap

private val BITMAP_SIZE = 200.dp
private val BITMAP_PADDING = 80.dp

class CameraView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getBitmap(R.drawable.avatar, BITMAP_SIZE.toInt())

    private val camera = Camera()

    init {
        camera.rotateX(30f)
        camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }


    override fun onDraw(canvas: Canvas) {
//        //要倒着写
//        //4、移动回去
//        canvas.translate((BITMAP_PADDING+BITMAP_SIZE/2),(BITMAP_PADDING+BITMAP_SIZE/2))
//        //3、旋转
//        camera.applyToCanvas(canvas)
//        //2、移动
//        canvas.translate(-(BITMAP_PADDING+BITMAP_SIZE/2),-(BITMAP_PADDING+BITMAP_SIZE/2))
//        //1 先绘制
//        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)

        //上部分
        canvas.save()
        canvas.translate((BITMAP_PADDING + BITMAP_SIZE / 2), (BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.rotate(-45f)
        canvas.clipRect(
            -(BITMAP_SIZE ).toInt(), -(BITMAP_SIZE).toInt(),
            (BITMAP_SIZE ).toInt(),
           0
        )
        canvas.rotate(45f)
        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()

        //下部分
        canvas.save()
        canvas.translate((BITMAP_PADDING+BITMAP_SIZE/2),(BITMAP_PADDING+BITMAP_SIZE/2))
        canvas.rotate(-45f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(
            -(BITMAP_SIZE ).toInt(), 0,
            (BITMAP_SIZE ).toInt(),
            (BITMAP_SIZE).toInt()
        )
        canvas.rotate(45f)
        canvas.translate(-(BITMAP_PADDING+BITMAP_SIZE/2),-(BITMAP_PADDING+BITMAP_SIZE/2))
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()


    }
}