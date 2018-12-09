package com.example.sweets.foodcutter

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.util.Log

//図形描画のクラス
class MyView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    internal var paint: Paint
    private var parts: Int = 2
    private var viewflg: Boolean = false

    init {
        paint = Paint()
        viewflg = true
    }

    override fun onDraw(canvas: Canvas) {

//        val layout : TextureView = findViewById(R.id.mySurfaceView)
//        val layoutWidth : Int = layout.getWidth()
//        val layoutHeight : Int = layout.getHeight()

        // TODO: サンプルまま
        // 背景、透明
        canvas.drawColor(Color.argb(0, 0, 0, 0))

        // 円
        paint.color = Color.argb(255, 68, 255, 255)
        paint.strokeWidth = 10f
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        // (x1,y1,r,paint) 中心x1座標, 中心y1座標, r半径
        canvas.drawCircle(450f, 450f, 100f, paint)

        // 矩形
        paint.color = Color.argb(255, 255, 190, 0)
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        // (x1,y1,x2,y2,paint) 左上の座標(x1,y1), 右下の座標(x2,y2)
        canvas.drawRect(240f, 240f, 850f, 880f, paint)

        // 線
        paint.strokeWidth = 10f
        paint.color = Color.argb(255, 0, 255, 120)
        // (x1,y1,x2,y2,paint) 始点の座標(x1,y1), 終点の座標(x2,y2)
        canvas.drawLine(350f, 850f, 750f, 630f, paint)
    }

     fun incrementParts() {
        parts += 1
        Log.v("parts", parts.toString())
        showCanvas(true)
    }

    fun showCanvas(flg: Boolean) {
        viewflg = flg
        // 再描画
        invalidate()
    }
}
