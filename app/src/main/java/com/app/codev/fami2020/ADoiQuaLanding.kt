package com.app.codev.fami2020

import android.os.Bundle
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_doiqua_landing.*

class ADoiQuaLanding : ABase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_doiqua_landing)

        detectNavigationButtonBar()

        btn_doiqua.setOnClickListener {
            Utils.openAKhachhangV2(this)
        }

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }
}