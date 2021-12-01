package com.app.codev.fami2020

import android.os.Bundle
import com.app.codev.Seleton
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_chon_hoatdong.*

class AHoatDong : ABase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_chon_hoatdong)

        detectNavigationButtonBar()

        btn_sampling.setOnClickListener {
            Utils.openASampling(this)
        }

        btn_doiqua.setOnClickListener {
            Utils.openADoiQua(this)
        }

        btn_game.setOnClickListener {
            Utils.openAGameLanding(this)
        }

        if (Seleton.getInstance().diadiem != null && Seleton.getInstance().diadiem.isNotEmpty()) {
            edt_diadiem.text = Seleton.getInstance().diadiem
        } else {
            edt_diadiem.text = "Địa điểm thực hiện"
        }

    }
}