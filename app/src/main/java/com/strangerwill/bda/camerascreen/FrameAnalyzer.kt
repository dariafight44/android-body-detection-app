package com.strangerwill.bda.camerascreen

import android.util.Size
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions
import java.lang.Integer.max
import java.lang.Integer.min

@ExperimentalGetImage
class FrameAnalyzer(dopustimImyaDrawView: Skillet): ImageAnalysis.Analyzer
{
    val options = PoseDetectorOptions.Builder()
        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .build()
    val detector = PoseDetection.getClient(options)
    val pozeView = dopustimImyaDrawView

    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if (mediaImage != null){
            val imageDlyaDetectora = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)
            detector.process(imageDlyaDetectora)
                .addOnSuccessListener { prinyatayaPoza ->
                    val minimalizm = min(image.height, image.width)
                    val maximalizm = max(image.height, image.width)
                    val turgetSize = Size(minimalizm, maximalizm)
                    pozeView.parametersustanovka(prinyatayaPoza, turgetSize)
                    image.close()
                }
                .addOnFailureListener {
                    println("i wanna rock!!!!!!!")
                    image.close()
                }
        }












    }

}