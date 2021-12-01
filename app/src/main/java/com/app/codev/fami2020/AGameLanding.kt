package com.app.codev.fami2020

import android.os.Bundle
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
            Utils.openAManhgepV2(this)
        }

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }
}