package com.example.etu



interface FragmentActions {

    fun openFragment(fragment: BaseFragment, addToBackStack: Boolean = true)

    fun closeFragment()

}