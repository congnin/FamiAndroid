package com.app.codev.fami2020

import android.os.Bundle
import kotlinx.android.synthetic.main.a_final_new.*

class AFinalV2 : ABase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_final_new)

        detectNavigationButtonBar()

        a_final_layout.setOnClickListener {
            finish()
        }
    }
}