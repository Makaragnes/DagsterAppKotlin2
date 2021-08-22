package com.example.dagsterappkotlin.fragments

import com.example.dagsterappkotlin.R
import com.example.dagsterappkotlin.utilits.APP_ACTIVITY
import com.example.dagsterappkotlin.utilits.hideKeyboard

class QRFragment: BaseFragment(R.layout.fragment_qr) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = ""
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
    }

    // Холдер для захвата ViewGroup



}