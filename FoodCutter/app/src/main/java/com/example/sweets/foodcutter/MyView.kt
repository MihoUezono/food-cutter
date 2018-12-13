package com.example.sweets.foodcutter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.*
import kotlin.math.*

//図形描画のクラス
class MyView(context: Context, attrs: AttributeSet) : View(context, attrs), View.OnTouchListener, ScaleGestureDetector.OnScaleGestureListener {

    internal var paint: Paint
    private var partCount: Int = 2
    private var cutMode: Int = 0 // 0:circle, 1:rectangle
    private var viewflg: Boolean = false
    private var scaleGesture: ScaleGestureDetector

    private var currentWidth : Float
    private var currentHeight: Float

    init {
        paint = Paint()
        viewflg = true
        scaleGesture = ScaleGestureDetector(getContext(), this)
        setOnTouchListener(this)

        currentWidth = this.width.toFloat()
        currentHeight = this.height.toFloat()

        Log.v("init", "$currentWidth, $currentHeight")
    }

    override fun onDraw(canvas: Canvas) {

        var cX: Int = this.width/2
        var cY: Int = this.height/2

        // 背景、透明
        canvas.drawColor(Color.argb(0, 0, 0, 0))

        when (cutMode) {
            0 -> {
                Log.v("beforeDraw", "$currentWidth, $currentHeight")
                var radius : Float = (sqrt(currentWidth * currentWidth + currentHeight * currentHeight) * 0.9 / 2).toFloat()
                // 円
                paint.color = Color.argb(255, 68, 255, 255)
                paint.strokeWidth = 10f
                paint.isAntiAlias = true
                paint.style = Paint.Style.STROKE
                canvas.drawCircle(cX.toFloat(), cY.toFloat(), radius, paint)

                // 線
                paint.strokeWidth = 10f
                paint.color = Color.argb(255, 0, 255, 120)
                var count: Int = 0
                while (++count <= partCount) {
                    canvas.drawLine(cX.toFloat(), cY.toFloat(),
                            cX.toFloat() + radius * sin(PI * 2 * count / partCount).toFloat(),
                            cY.toFloat() - radius * cos(PI * 2 * count / partCount).toFloat(), paint)
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

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return scaleGesture.onTouchEvent(event)
    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        Log.v("onScaleBegin", detector.currentSpan.toString())
        return true
    }

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        Log.v("onScale", detector.currentSpan.toString())
        currentWidth = detector.currentSpanX
        currentHeight = detector.currentSpanY
        showCanvas(true)
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector) {
        Log.v("onScaleEnd", detector.currentSpan.toString())
    }

    fun showCanvas(flg: Boolean) {
        viewflg = flg
        // 再描画
        invalidate()
    }

    fun incrementParts() {
         if(partCount < 100)partCount += 1
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
