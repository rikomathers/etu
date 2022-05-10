package com.example.etu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.etu.databinding.StartFragmentBinding

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
        binding.imageButtonCamera.setOnClickListener {
            fragmentActions.openFragment(CameraFragment())
        }
    }
}