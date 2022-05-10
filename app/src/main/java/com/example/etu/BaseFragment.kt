package com.example.etu

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected lateinit var fragmentActions: FragmentActions

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentActions = context as FragmentActions
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
}