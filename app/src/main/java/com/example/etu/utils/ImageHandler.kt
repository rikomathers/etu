package com.example.etu.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.util.Log
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

    fun findPixels(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val arrayY = IntArray(width)
//        for (y in 0 until height) {
//            bitmap.getPixels(arrayY, 0, width, 0, 0, width, y)
//            findPixelsInLine(arrayY)
//        }
        bitmap.getPixels(arrayY, 0, width, 0, 0, width, 1)
        findPixelsInLine(arrayY)
        return bitmap
    }

    private fun findPixelsInLine(array : IntArray){
        for (x in array.indices) {
            val red: Int = array[x] shr 16 and 0x000000FF
            val green: Int = array[x] shr 8 and 0x000000FF
            val blue: Int = array[x] and 0x000000FF
            val alpha: Int = array[x] shr 24 and 0xff

            Log.d("MyTag", "red is $red green is $green blue is $blue")
//            Log.d("MyTag", "green is $green")
//            Log.d("MyTag", "blue is $blue")
//            Log.d("MyTag", "alpha is $alpha")
        }
    }
}