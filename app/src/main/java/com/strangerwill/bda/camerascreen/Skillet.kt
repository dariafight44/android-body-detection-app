package com.strangerwill.bda.camerascreen

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Size
import android.view.View
import com.google.mlkit.vision.common.PointF3D
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import java.util.jar.Attributes

class Skillet(
    context:Context,
    attributes: AttributeSet
): View(context, attributes) {
    private val kistochka = Paint(ANTI_ALIAS_FLAG)
    private var shlyapaSize: Size = Size(0, 0)
    private var batvaSize: Size = Size(0, 0)
    private var drawingPose: Pose? = null

    init {
        kistochka.color = Color.CYAN
        kistochka.strokeWidth = 4.0f
        kistochka.style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        batvaSize = Size(w, h)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPoints(canvas)
        drulines(canvas)
    }

    fun parametersustanovka(pose: Pose, size: Size) {
        shlyapaSize = size
        drawingPose = pose
        invalidate()
    }

    private fun drawPoints(canvas: Canvas) {
        drawOdnuTochku(canvas, drawingPose?.getPoseLandmark(PoseLandmark.NOSE))
        drawOdnuTochku(canvas, drawingPose?.getPoseLandmark(PoseLandmark.RIGHT_EYE))
    }

    fun drawOdnuTochku(canvas: Canvas, poseLandmark: PoseLandmark?) {
        if (poseLandmark != null) {
            val reye = convertingPoint(poseLandmark.position3D)
            canvas.drawCircle(reye.x, reye.y, 4F, kistochka)
        }
    }

    private fun drulines(canvas: Canvas)
    {
        val firstLandmark = drawingPose?.getPoseLandmark(PoseLandmark.LEFT_ELBOW)
        val secondLandmark = drawingPose?.getPoseLandmark(PoseLandmark.LEFT_WRIST)
        risuyuOdnuLiniyu(canvas, firstLandmark, secondLandmark)
    }

    private fun risuyuOdnuLiniyu(canvas: Canvas, firstLandmark: PoseLandmark?, secondLandmark: PoseLandmark?)
    {
        if(firstLandmark != null && secondLandmark != null)
        {
            val p1: PointF = convertingPoint(firstLandmark.position3D)
            val p2: PointF = convertingPoint(secondLandmark.position3D)
            canvas.drawLine(p1.x, p1.y, p2.x,p2.y, kistochka)
        }


    }



    private fun convertingPoint(inishlPoint: PointF3D): PointF
    {
        val x1 = inishlPoint.x
        val y1 = inishlPoint.y
        val w1 = shlyapaSize.width
        val h1 = shlyapaSize.height
        val w2 = batvaSize.width
        val h2 = batvaSize.height

        val x2 = x1*w2/w1
        val y2 = y1*h2/h1
        return PointF(x2, y2)
    }
}