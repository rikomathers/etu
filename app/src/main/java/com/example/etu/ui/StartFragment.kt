package com.example.etu.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.etu.databinding.StartFragmentBinding
import com.example.etu.utils.isPermissionGranted
import com.example.etu.utils.requirePermissions

class StartFragment : BaseFragment() {

    private lateinit var binding: StartFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StartFragmentBinding.inflate(layoutInflater)
        initViews()
        return binding.root
    }

    private fun initViews() {
        initImageCamera()
    }

    private fun initImageCamera() {
        binding.cameraContainer.setOnClickListener {
            if (isPermissionGranted(Manifest.permission.CAMERA)) {
                fragmentActions.openFragment(CameraFragment())
            } else {
                requirePermissions(
                    PERMISSION_REQUEST_CAMERA,
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                fragmentActions.openFragment(CameraFragment())
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CAMERA = 1
    }
}