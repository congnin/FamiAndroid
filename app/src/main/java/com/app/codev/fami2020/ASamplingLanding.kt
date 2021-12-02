package com.app.codev.fami2020

import android.os.Bundle
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_sampling.*

class ASamplingLanding : ABase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_sampling)

        detectNavigationButtonBar()

        btn_sampling.setOnClickListener {
            Utils.openAKhachhangSampling(this)
        }

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }
}