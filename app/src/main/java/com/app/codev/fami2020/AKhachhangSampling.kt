package com.app.codev.fami2020

import com.app.codev.api.MyapiLoader
import com.app.codev.utils.Utils

class AKhachhangSampling : AKhachhangSimple() {
    override fun getPath(): String = MyapiLoader.SV_PATH_SAMPLING

    override fun goToNext() {
        Utils.openAFinalV2(this)
        finish()
    }
}