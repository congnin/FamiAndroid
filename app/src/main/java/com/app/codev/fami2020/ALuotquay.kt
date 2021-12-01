package com.app.codev.fami2020

import android.os.Bundle
import com.app.codev.Seleton
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_luotquay.*

class ALuotquay : ABase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
//        setContentView(R.layout.a_luotquay)
        setContentView(R.layout.a_luotquay_new)

        detectNavigationButtonBar()


        a_luotquay_ct.setOnClickListener {
            changeGameQuay()
        }

        tv_number.text = "" + Seleton.getInstance().saveTotoRotate
    }

    fun changeGameQuay()
    {
        finish()
        Utils.openAQuayV2(this)
    }
}