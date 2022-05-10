package com.example.etu.utils

import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis

class ImageHandler() {

    fun getImageAnalysis(width: Int = 1024, height: Int = 768): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setTargetResolution(Size(width, height)) //задаем размер изображения
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) //обрабатывает только те изображения которые успел
            .build()
    }

    fun getCameraSelector(): CameraSelector {
        return CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK) // направление камеры
            .build()
    }
}