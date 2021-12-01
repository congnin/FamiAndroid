package com.app.codev.fami2020

import android.os.Bundle
import kotlinx.android.synthetic.main.a_final.*

class AFinal : ABase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_final)

        detectNavigationButtonBar()

        a_final_layout.setOnClickListener {
            finish()
        }
    }
}