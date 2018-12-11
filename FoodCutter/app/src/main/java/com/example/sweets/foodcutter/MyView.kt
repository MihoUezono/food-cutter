package com.example.sweets.foodcutter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.TextureView
import android.view.View
import android.view.WindowManager
import android.util.Log
import kotlin.math.*

//図形描画のクラス
class MyView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    internal var paint: Paint
    private var partCount: Int = 2
    private var cutMode: Int = 0 // 0:circle, 1:rectangle
    private var viewflg: Boolean = false

    init {
        paint = Paint()
        viewflg = true
    }

    override fun onDraw(canvas: Canvas) {

        val layoutWidth: Int = this.width
        val layoutHeight: Int = this.height

        // 背景、透明
        canvas.drawColor(Color.argb(0, 0, 0, 0))

        when (cutMode) {
            0 -> {
                // 円
                paint.color = Color.argb(255, 68, 255, 255)
                paint.strokeWidth = 10f
                paint.isAntiAlias = true
                paint.style = Paint.Style.STROKE
                canvas.drawCircle(layoutWidth.toFloat() / 2, layoutHeight.toFloat() / 2, 500f, paint)

                // 線
                paint.strokeWidth = 10f
                paint.color = Color.argb(255, 0, 255, 120)
                var count: Int = 0
                while (++count <= partCount) {
                    canvas.drawLine(layoutWidth.toFloat() / 2, layoutHeight.toFloat() / 2,
                            layoutWidth.toFloat() / 2 + 500f * sin(PI * 2 * count / partCount).toFloat(),
                            layoutHeight.toFloat() / 2 - 500f * cos(PI * 2 * count / partCount).toFloat(), paint)
                    Log.v("width", (500f * sin(PI * 2 * count / partCount)).toString())
                    Log.v("Height", (500f * cos(PI * 2 * count / partCount)).toString())
                }
            }
            1 -> {
                // 矩形
                paint.color = Color.argb(255, 255, 190, 0)
                paint.strokeWidth = 10f
                paint.style = Paint.Style.STROKE
                // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
                canvas.drawRect(240f, 240f, 1000f, 880f, paint)

                // 線
                paint.strokeWidth = 10f
                paint.color = Color.argb(255, 0, 255, 120)
                // (x1,y1,x2,y2,paint) 始点の座標(x1,y1), 終点の座標(x2,y2)
                canvas.drawLine(350f, 850f, 750f, 630f, paint)


                var count: Int = 0
                do {
                    count++
                    canvas.drawLine(350f, 850f * count / partCount, 750f, 850f * count / partCount, paint)
                } while (count + 1 <= partCount)
            }
        }
    }

    fun showCanvas(flg: Boolean) {
        viewflg = flg
        // 再描画
        invalidate()
    }

    fun incrementParts() {
         if(partCount < 10)partCount += 1
         showCanvas(true)
    }

    fun decrementParts() {
        if(partCount > 2) partCount -= 1
        showCanvas(true)
    }

    fun changeMode() {
        //cutMode = (++cutMode%2)
        showCanvas(true)
    }
}
