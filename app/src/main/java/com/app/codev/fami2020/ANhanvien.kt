package com.app.codev.fami2020


import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.app.codev.Seleton
import com.app.codev.api.MyapiLoader
import com.app.codev.api.OnLoopjCompleted
import com.app.codev.model.CuahangData
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_nhanvien.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ANhanvien : ABase() {


    var cuahangData: CuahangData? = null
    var indexCuahang = 0
    var idCuahang: String = ""
    var tenCuahang: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_nhanvien)

        detectNavigationButtonBar()

        a_nhanvien_tv_tenst.setOnClickListener {
            if (cuahangData != null) {
                showDialog()
            } else {
                getListCuahang()
            }

        }

        getListCuahang()

        a_nhanvien_tv_ngaylv.text = getDay()

        a_nhanvien_tv_dangnhap.setOnClickListener {
            checkDangnhap()
        }

    }

    override fun onResume() {
        super.onResume()
        setSystemUiVisibility(false)
    }


    fun getDay(): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val currentDate = sdf.format(Date())
        return currentDate
    }


    fun setNameCuahang() {
        tenCuahang = cuahangData!!.data[indexCuahang].ten_cua_hang + " - " + cuahangData!!.data[indexCuahang].dia_chi_cua_hang
        a_nhanvien_tv_tenst.text = tenCuahang
        idCuahang = cuahangData!!.data[indexCuahang].id.toString()

    }

    fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choncuahang))
        if (cuahangData!!.data != null && cuahangData!!.data.size > 0) {
            var list = ArrayList<String>()
            for (item in cuahangData!!.data) {
                list.add(item.ten_cua_hang + " - " + item.dia_chi_cua_hang)
            }
            val animals = list.toTypedArray()
            builder.setSingleChoiceItems(
                    animals,
                    indexCuahang,
                    DialogInterface.OnClickListener { dialog, which ->
                        indexCuahang = which
                        setNameCuahang()
                        dialog.dismiss()
                    })

            builder.setNegativeButton(getString(R.string.huy), null)
            val dialog = builder.create()
            dialog.show()
        }
    }


    fun getListCuahang() {
        MyapiLoader.postApi(MyapiLoader.SV_PATH_LIST_CUAHANG, MyapiLoader.getParamCuahang(), object : OnLoopjCompleted {
            override fun taskCompleted(resultsl: String) {
                cuahangData = Utils.getCuahangData(resultsl)
                if (cuahangData != null && cuahangData!!.status) {
                    if (cuahangData!!.data != null && cuahangData!!.data.size > 0) {
                        setNameCuahang()
                    }
                } else {
                    Utils.toast(this@ANhanvien, getString(R.string.loiketnoi))
                }
            }

            override fun taskError(resultsl: String) {
                Utils.toast(this@ANhanvien, getString(R.string.loiketnoi))
            }

        })
    }

    fun checkDangnhap() {
        val ten = a_nhanvien_edt_manhanvien.text.toString()
        if (TextUtils.isEmpty(ten)) {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
        }

        val pass = a_nhanvien_edt_matma.text.toString()
        if (TextUtils.isEmpty(pass)) {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
        }


        if (TextUtils.isEmpty(idCuahang)) {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
        }

        Seleton.getInstance().msvn = ten
        a_nhanvien_tv_dangnhap.visibility = View.INVISIBLE
        MyapiLoader.postApi(MyapiLoader.SV_PATH_NV_LOGIN, MyapiLoader.getParamNhanvienLogin(ten, pass, idCuahang), object : OnLoopjCompleted {
            override fun taskCompleted(resultsl: String) {
                a_nhanvien_tv_dangnhap.visibility = View.VISIBLE
                val data = Utils.getResult(resultsl)
                if (data != null && data.status) {
                    // luu dia diem
                    Seleton.getInstance().diadiem = tenCuahang
                    Utils.saveString(Utils.SAVE_PG_ID, data.pg_id, this@ANhanvien)
                    Utils.saveString(Utils.SAVE_CHECKIN_ID, data.checkin_id.toString(), this@ANhanvien)
                    finish()
                }
                if (data != null && !TextUtils.isEmpty(data.message))
                    Utils.toast(this@ANhanvien, data.message)
            }

            override fun taskError(resultsl: String) {
                a_nhanvien_tv_dangnhap.visibility = View.VISIBLE
            }

        })

    }

    override fun onBackPressed() {

    }

}