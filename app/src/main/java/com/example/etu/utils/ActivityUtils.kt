package com.example.etu.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Fragment.isPermissionGranted(permission: String): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
    return true
}


fun FragmentActivity.replaceFragment(
    fragment: Fragment,
    containerId: Int,
    addToBackStack: Boolean = true
) {
    supportFragmentManager.beginTransaction().apply {
        val fragmentTag = fragment::class.java.simpleName
        if (addToBackStack) {
            addToBackStack(fragmentTag)
        }
        replace(containerId, fragment, fragmentTag)
        commit()
    }
}

fun FragmentActivity.addFragment(fragment: Fragment, containerId: Int) {
    supportFragmentManager.beginTransaction().apply {
        val fragmentTag = fragment::class.java.simpleName
        add(containerId, fragment, fragmentTag)
        commit()
    }
}
fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val focusedView = currentFocus ?: View(this)
    inputMethodManager.hideSoftInputFromWindow(focusedView.windowToken, 0)
}

fun Fragment.hideKeyboard() {
    requireActivity().hideKeyboard()
}

fun FragmentActivity.closeLastFragment() {
    supportFragmentManager.popBackStack()
}

fun Fragment.requirePermissions(requestCode: Int, vararg permissions: String) =
    requestPermissions(permissions, requestCode)

fun Activity.requirePermissions(requestCode: Int, vararg permissions: String) =
    ActivityCompat.requestPermissions(this, permissions, requestCode)