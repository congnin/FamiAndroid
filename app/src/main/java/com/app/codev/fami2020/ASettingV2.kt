package com.app.codev.fami2020

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import com.app.codev.model.QuaObj
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_setting_new.*

class ASettingV2 : ABase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_setting_new)

        detectNavigationButtonBar()

        checkGift()
        setData()

        a_setting_tv_luu.setOnClickListener {
            SaveData()
        }

        a_setting_tv_reset.setOnClickListener {
            listQuaf = ArrayList<QuaObj>()
            resetDataV2()
            setData()
        }

        a_setting_tv_quaylai.setOnClickListener {
            onBackPressed()
        }
    }


    fun setData() {
        setText(listQuaf[0], tv_qua1_soqua, tv_qua1_tile, tv_qua1_sotrao, tv_qua1_conlai)
        setText(listQuaf[1], tv_qua2_soqua, tv_qua2_tile, tv_qua2_sotrao, tv_qua2_conlai)
        setText(listQuaf[2], tv_qua3_soqua, tv_qua3_tile, tv_qua3_sotrao, tv_qua3_conlai)
        setText(listQuaf[3], tv_qua4_soqua, tv_qua4_tile, tv_qua4_sotrao, tv_qua4_conlai)
        setText(listQuaf[4], tv_qua5_soqua, tv_qua5_tile, tv_qua5_sotrao, tv_qua5_conlai)
        setText(listQuaf[5], tv_qua6_soqua, tv_qua6_tile, tv_qua6_sotrao, tv_qua6_conlai)
    }

    fun setText(item: QuaObj, edtSoqua: EditText, edtTile: EditText, tvSotrao: TextView, tvConlai: TextView) {
        edtSoqua.setText(item.soqua.toString())
        edtTile.setText(item.tile.toString())
        tvSotrao.text = item.datrao.toString()
        tvConlai.text = (item.soqua - item.datrao).toString()
    }

    fun getValue(item: QuaObj, edtSoqua: EditText, edtTile: EditText, tvSotrao: TextView): Boolean {
        val soqua = edtSoqua.text.toString()
        if (TextUtils.isEmpty(soqua)) {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
            return false
        }
        item.soqua = soqua.toInt()
        if (item.soqua < 0 || item.soqua < item.datrao) {
            Utils.toast(this, getString(R.string.soquakhonghople))
            return false
        }

        val tile = edtTile.text.toString()
        if (TextUtils.isEmpty(tile)) {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
            return false
        }
        item.tile = tile.toInt()

        return true
    }

    fun SaveData() {
        if (getValue(listQuaf[0], tv_qua1_soqua, tv_qua1_tile, tv_qua1_sotrao)
                && getValue(listQuaf[1], tv_qua2_soqua, tv_qua2_tile, tv_qua2_sotrao)
                && getValue(listQuaf[2], tv_qua3_soqua, tv_qua3_tile, tv_qua3_sotrao)
                && getValue(listQuaf[3], tv_qua4_soqua, tv_qua4_tile, tv_qua4_sotrao)
                && getValue(listQuaf[4], tv_qua5_soqua, tv_qua5_tile, tv_qua5_sotrao)
                && getValue(listQuaf[5], tv_qua6_soqua, tv_qua6_tile, tv_qua6_sotrao)
        ) {
            var total = 0
            for (item in listQuaf) {
                total += item.tile
            }
            if (total != 100) {
                Utils.toast(this, getString(R.string.tilekodu))
            } else {
                saveQua()
                finish()
            }
        }
    }
}