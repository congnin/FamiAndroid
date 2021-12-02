package com.app.codev.fami2020

import com.app.codev.api.MyapiLoader
import com.app.codev.utils.Utils

class AKhachhangGame : AKhachhangSimple() {
    override fun getPath(): String = MyapiLoader.SV_PATH_GAME

    override fun goToNext() {
        Utils.openAManhgepV2(this)
    }
}