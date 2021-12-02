package com.app.codev.fami2020

import android.os.Bundle
import com.app.codev.Seleton
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_game_landing.*
import kotlinx.android.synthetic.main.a_game_landing.btn_back

class AGameLanding : ABase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_game_landing)

        detectNavigationButtonBar()

        btn_game.setOnClickListener {
            // check hop sua fami con hay ko
            if (listQuaf[0].datrao < listQuaf[0].soqua) {
                Utils.openAKhachhangGame(this)
            } else {
                Utils.toast(this, getString(R.string.dahetqua))
            }
        }

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }
}