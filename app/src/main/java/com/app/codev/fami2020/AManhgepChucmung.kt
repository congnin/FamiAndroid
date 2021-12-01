package com.app.codev.fami2020

import android.os.Bundle
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_manhgep_chucmung.*

class AManhgepChucmung : ABase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_manhgep_chucmung)

        detectNavigationButtonBar()


        a_manhgepchucmung_ct.setOnClickListener {
            changeGameQuay()
        }
    }

    fun changeGameQuay()
    {
        finish()
        Utils.openALuotquay(this)
        //Utils.openAQuay(this)
    }
}