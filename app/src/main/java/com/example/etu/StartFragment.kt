package com.example.etu

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.etu.databinding.StartFragmentBinding

class StartFragment : Fragment() {

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
        binding.imageButtonCamera.setOnClickListener {
            if (!isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                requirePermissions(
                    PERMISSION_REQUEST_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            } else {
                openFile()
            }
        }
    }

    private fun openFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "video/*"
        startActivityForResult(intent, PICK_VIDEO_RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultIntent: Intent?) {

        when (requestCode) {
            PICK_VIDEO_RESULT_CODE -> {
                if (resultCode == RESULT_OK) {
                    resultIntent?.data?.let {
                    }
                }
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_STORAGE = 0
        private const val PICK_VIDEO_RESULT_CODE = 1
    }
}