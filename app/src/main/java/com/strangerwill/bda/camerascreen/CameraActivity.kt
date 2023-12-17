package com.strangerwill.bda.camerascreen

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.strangerwill.bda.R

@ExperimentalGetImage class CameraActivity: AppCompatActivity() {
    private lateinit var cameraFuture: ListenableFuture<ProcessCameraProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        cameraFuture = ProcessCameraProvider.getInstance(this)

        cameraFuture.addListener({
           val wesdfg = cameraFuture.get()
            aftgSetCamera(wesdfg)
        }, ContextCompat.getMainExecutor(this))

    }

    fun aftgSetCamera(glogolam: ProcessCameraProvider)
    {
        val preview = Preview.Builder().build()
        val previewView = findViewById<PreviewView>(R.id.first_view_of_camera)
        preview.setSurfaceProvider(previewView.surfaceProvider)
        var cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        val skeletView = findViewById<Skillet>(R.id.skeletyKrutye)
        val kuragbombey = FrameAnalyzer(skeletView)
        val evanescence = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build()
        evanescence.setAnalyzer(mainExecutor, kuragbombey, )
        glogolam.bindToLifecycle(this, cameraSelector,preview, evanescence)
    }

}