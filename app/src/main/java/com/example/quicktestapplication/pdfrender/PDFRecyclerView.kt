package com.example.quicktestapplication.pdfrender

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.recyclerview.widget.RecyclerView

class PDFRecyclerView(context: Context, attrs: AttributeSet? = null) :
    RecyclerView(context, attrs) {
    private val scaleGestureDetector: ScaleGestureDetector = ScaleGestureDetector(
        context,
        object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                zoomFactor *= detector.scaleFactor
                zoomFactor = zoomFactor.coerceIn(0.5f, 3.0f) // Limit zoom between 50% and 300%
                Log.d("PDFRecyclerView", "zoomFactor = $zoomFactor")
                invalidate() // Redraw the view
                return true
            }
        })
    private var zoomFactor = 1.0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save();
        canvas.scale(zoomFactor, zoomFactor);
        canvas.restore();
    }

    fun smoothZoom(recyclerView: RecyclerView, targetScale: Float) {
        val scaleX = ObjectAnimator.ofFloat(recyclerView, "scaleX", targetScale)
        val scaleY = ObjectAnimator.ofFloat(recyclerView, "scaleY", targetScale)
        scaleX.duration = 100
        scaleY.duration = 100
        scaleX.start()
        scaleY.start()
    }
}