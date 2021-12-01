package com.app.codev.fami2020

import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.app.codev.Seleton
import com.app.codev.api.MyapiLoader
import com.app.codev.api.OnLoopjCompleted
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_quay_win_new.*

class AQuayWinV2 : ABase() {
    var listQua = arrayOf(
            R.mipmap.qua_hop_sua, R.mipmap.qua_ly, R.mipmap.qua_nuoc_khoang,
            R.mipmap.qua_nhietke, R.mipmap.qua_gaubong, R.mipmap.qua_thungsua
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_quay_win_new)

        detectNavigationButtonBar()

        a_quaywin_ct.setOnClickListener {
            Seleton.getInstance().addRotate()
            if (Seleton.getInstance().isHaveGift) {
                Utils.openAQuayV2(this)
            } else {
                Utils.openAQuacuabanV2(this)
            }
            finish()
        }

        if (Utils.ID_Qua > 0) {
            postGift()
        }
        val sdk = android.os.Build.VERSION.SDK_INT
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            a_quaywin_ct.setBackgroundDrawable(ContextCompat.getDrawable(this, listQua[Utils.INDEX_QUA]))
        } else {
            a_quaywin_ct.background = ContextCompat.getDrawable(this, listQua[Utils.INDEX_QUA])
        }
    }

    fun postGift() {
        val logID = Utils.getString(Utils.SAVE_LOG_ID, this)
        if (TextUtils.isEmpty(logID)) {
            Utils.toast(this, "khong co log_id")
            return
        }
        MyapiLoader.postApi(MyapiLoader.SV_PATH_LUCKY, MyapiLoader.getParamLucky(logID,
                Utils.ID_Qua.toString(), Seleton.getInstance().saveTotoRotate,
                Seleton.getInstance().totalRotate), object : OnLoopjCompleted {
            override fun taskCompleted(resultsl: String) {
                val data = Utils.getResult(resultsl)
                if (data != null && !TextUtils.isEmpty(data.message))
                    Utils.toast(this@AQuayWinV2, data.message)
            }

            override fun taskError(resultsl: String) {
            }
        })
    }
}