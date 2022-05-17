package com.example.etu.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.graphics.get
import com.example.etu.utils.ImageHandler
import com.example.etu.utils.YUVtoRGB
import com.example.etu.databinding.CameraFragmentBinding
import com.google.common.util.concurrent.ListenableFuture

class CameraFragment : BaseFragment() {
    private lateinit var binding: CameraFragmentBinding
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private val translator = YUVtoRGB()
    private val imageHandler = ImageHandler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CameraFragmentBinding.inflate(layoutInflater)
        initCamera()
        return binding.root
    }

    private fun initCamera() {
        val executor = ContextCompat.getMainExecutor(requireContext())
        cameraProviderFuture =
            context?.let { ProcessCameraProvider.getInstance(it) } as ListenableFuture<ProcessCameraProvider>
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val imageAnalysis = imageHandler.getImageAnalysis(1024, 768)
            val cameraSelector = imageHandler.getCameraSelector()
            imageAnalysis.setAnalyzer(executor, getAnalyzer())
            cameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis)
        }, executor)
    }

    private fun getAnalyzer(): ImageAnalysis.Analyzer {
        return ImageAnalysis.Analyzer { imageProxy ->

            val image = imageProxy.image
            var bitmap = translator.translateYUV(image, context)
            bitmap = imageHandler.findPixels(bitmap)
            binding.imageCamera.rotation = imageProxy.imageInfo.rotationDegrees.toFloat()
            binding.imageCamera.setImageBitmap(bitmap)
//            imageProxy.close()
        }
    }
}