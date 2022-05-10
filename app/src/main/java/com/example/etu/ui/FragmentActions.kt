package com.example.etu.ui



interface FragmentActions {

    fun openFragment(fragment: BaseFragment, addToBackStack: Boolean = true)

    fun closeFragment()

}