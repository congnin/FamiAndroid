package com.app.codev.fami2020

import android.os.Bundle
import android.text.TextUtils
import com.app.codev.model.QuaObj
import com.app.codev.utils.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.splash.*

class Splash : ABase() {

    var isSetLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.splash)

        detectNavigationButtonBar()

        splash_tv_nhanvien.setOnClickListener {
            Utils.openANhanvien(this)
        }

        splash_ct.setOnClickListener {
//            if (checKQua()) {
//                Utils.openAKhachhang(this)
//            } else
//                Utils.toast(this, getString(R.string.dahetqua))
            if (checKQua()) {
                Utils.openAHoatdong(this)
            } else {
                Utils.toast(this, getString(R.string.dahetqua))
            }
        }

        splash_tv_setting.setOnClickListener {
            Utils.openASetting(this)
        }

        if (!isSetLogin) {
            isSetLogin = true
            Utils.openANhanvien(this)
        }
    }

    override fun onResume() {
        super.onResume()
        val value = Utils.getString(Utils.SAVE_SETTING, this)
        if (!TextUtils.isEmpty(value)) {
            listQuaf = Gson().fromJson(value, object : TypeToken<List<QuaObj?>?>() {}.type)
        } else {
            checkGift()
        }
    }

    var listRandom = ArrayList<QuaObj>()
    fun checKQua(): Boolean {
        listRandom.clear()
        if (listQuaf != null && listQuaf.size > 0) {
            for (item in listQuaf) {
                if (item.datrao < item.soqua && item.tile > 0) {
                    listRandom.add(item)
                }
            }
        }
        if (listRandom.size > 0) {
            return true
        }
        return false
    }
}