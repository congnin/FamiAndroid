package com.app.codev.fami2020

import android.os.Bundle
import kotlinx.android.synthetic.main.a_sampling.*

class ASamplingLanding : ABase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_sampling)

        detectNavigationButtonBar()

        btn_sampling.setOnClickListener {

        }

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }
}