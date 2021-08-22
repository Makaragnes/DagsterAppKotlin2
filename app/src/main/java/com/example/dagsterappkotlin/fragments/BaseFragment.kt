package com.example.dagsterappkotlin.fragments

import androidx.fragment.app.Fragment
import com.example.dagsterappkotlin.utilits.APP_ACTIVITY

open class BaseFragment(layout:Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }
}