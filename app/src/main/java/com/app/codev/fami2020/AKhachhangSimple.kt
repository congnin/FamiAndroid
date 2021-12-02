package com.app.codev.fami2020

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.app.codev.Seleton
import com.app.codev.api.MyapiLoader
import com.app.codev.api.OnLoopjCompleted
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_khachhang.*
import kotlinx.android.synthetic.main.a_khachhang_simple.*
import kotlinx.android.synthetic.main.a_khachhang_simple.a_kh_edt_sdt
import kotlinx.android.synthetic.main.a_khachhang_simple.a_kh_edt_ten
import kotlinx.android.synthetic.main.a_khachhang_simple.a_kh_tv_batdau

abstract class AKhachhangSimple : ABase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_khachhang_simple)

        detectNavigationButtonBar()

        a_kh_tv_batdau.setOnClickListener {
            checkValid()
        }

        initView()
        Seleton.getInstance().reset()
    }

    fun initView() {

    }

    fun checkValid() {
        val ten = a_kh_edt_ten.text.toString()
        if (TextUtils.isEmpty(ten)) {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
            return
        }

        val sdt = a_kh_edt_sdt.text.toString()
        if (TextUtils.isEmpty(sdt)) {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
            return
        }

        val checkin = Utils.getString(Utils.SAVE_CHECKIN_ID, this)
        if (TextUtils.isEmpty(checkin)) {
            Utils.toast(this, getString(R.string.nhanvienchuadangnhap))
            return
        }

        a_kh_tv_batdau.visibility = View.INVISIBLE
        MyapiLoader.postApi(getPath(), MyapiLoader.getParamKhachHangSimple(
                ten,
                sdt,
                checkin
        ), object : OnLoopjCompleted {
            override fun taskCompleted(resultsl: String) {
                a_kh_tv_batdau.visibility = View.VISIBLE
                val data = Utils.getResult(resultsl)
                if (data != null && data.status) {
                    Utils.saveString(Utils.SAVE_LOG_ID, data.log_id.toString(), this@AKhachhangSimple)
                    Utils.saveString(Utils.SAVE_KH_NAME, ten, this@AKhachhangSimple)
                    Utils.saveString(Utils.SAVE_KH_PHONE, sdt, this@AKhachhangSimple)
                    goToNext()
                }
                if (data != null && !TextUtils.isEmpty(data.message))
                    Utils.toast(this@AKhachhangSimple, data.message)
            }

            override fun taskError(resultsl: String) {
                a_kh_tv_batdau.visibility = View.VISIBLE
                Utils.toast(this@AKhachhangSimple, resultsl)
            }

        })
    }

    abstract fun getPath(): String

    abstract fun goToNext()
}