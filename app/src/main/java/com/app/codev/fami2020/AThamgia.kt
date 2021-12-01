package com.app.codev.fami2020

import android.os.Bundle
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_thamgia.*

class AThamgia : ABase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_thamgia)

        detectNavigationButtonBar()

        a_thamgia_ct.setOnClickListener {
            finish()
            Utils.openAManhgep(this)
        }
    }
}