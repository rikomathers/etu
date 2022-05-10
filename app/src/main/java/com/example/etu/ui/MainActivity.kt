package com.example.etu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.etu.utils.addFragment
import com.example.etu.utils.closeLastFragment
import com.example.etu.databinding.ActivityMainBinding
import com.example.etu.utils.hideKeyboard
import com.example.etu.utils.replaceFragment

class MainActivity : AppCompatActivity(), FragmentActions {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openStartFragment()
    }

    override fun openFragment(fragment: BaseFragment, addToBackStack: Boolean) {
        replaceFragment(fragment, binding.fragmentHolder.id, addToBackStack)
    }

    override fun closeFragment() {
        hideKeyboard()
        closeLastFragment()
    }

    private fun openStartFragment() {
        if (supportFragmentManager.findFragmentById(binding.fragmentHolder.id) == null) {
            addFragment(StartFragment(), binding.fragmentHolder.id)
        }
    }
}