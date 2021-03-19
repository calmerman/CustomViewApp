package com.wx.customviewapp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wx.customviewapp.R
import com.wx.customviewapp.util.dp
import com.wx.customviewapp.util.getBitmap

private val IMAGE_WIDTH = 200f.dp
private val IMAGE_PADDING = 20f.dp

class AvatarView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val bounds = RectF(
        IMAGE_PADDING,
        IMAGE_PADDING,
        IMAGE_PADDING + IMAGE_WIDTH,
        IMAGE_PADDING + IMAGE_WIDTH
    )

    override fun onDraw(canvas: Canvas) {
        //离屏缓冲，比较好资源，区域尽量要小
        val saveLayerCount = canvas.saveLayer(bounds, null)
        canvas.drawOval(
            IMAGE_PADDING,
            IMAGE_PADDING,
            IMAGE_PADDING + IMAGE_WIDTH,
            IMAGE_PADDING + IMAGE_WIDTH,
            paint
        )

        paint.xfermode = xfermode

        canvas.drawBitmap(
            getBitmap(R.drawable.avatar, IMAGE_WIDTH.toInt()),
            IMAGE_PADDING,
            IMAGE_PADDING,
            paint
        )
        paint.xfermode = null
        //恢复离屏缓冲
        canvas.restoreToCount(saveLayerCount)
    }


}