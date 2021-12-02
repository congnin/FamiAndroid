package com.app.codev.fami2020

import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.app.codev.Seleton
import com.app.codev.api.MyapiLoader
import com.app.codev.api.OnLoopjCompleted
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_game_chienthang.*

class AChucMungGame : ABase() {
    private var clickFirst = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_game_chienthang)

        detectNavigationButtonBar()

        layoutView.setOnClickListener {
            if (!clickFirst) {
                clickFirst = true
                val sdk = android.os.Build.VERSION.SDK_INT
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    layoutView.setBackgroundDrawable(ContextCompat.getDrawable(this, R.mipmap.qua_hop_sua))
                } else {
                    layoutView.background = ContextCompat.getDrawable(this, R.mipmap.qua_hop_sua)
                }
                postGift()
            } else {
                Utils.openAFinalV2(this)
                finish()
            }
        }
    }

    fun postGift() {
        val logID = Utils.getString(Utils.SAVE_LOG_ID, this)
        val name = Utils.getString(Utils.SAVE_KH_NAME, this)
        val phone = Utils.getString(Utils.SAVE_KH_PHONE, this)
        var awardId = 0
        if (TextUtils.isEmpty(logID)) {
            Utils.toast(this, "khong co log_id")
            return
        }
        if (TextUtils.isEmpty(name)) {
            Utils.toast(this, "khong co name")
            return
        }
        if (TextUtils.isEmpty(phone)) {
            Utils.toast(this, "khong co phone")
            return
        }

        // check hop sua fami con hay ko
        if (listQuaf[0].datrao < listQuaf[0].soqua) {
            listQuaf[0].datrao = listQuaf[0].datrao + 1
            awardId = listQuaf[0].id
        } else {
            Utils.toast(this, getString(R.string.dahetqua))
            return
        }

        MyapiLoader.postApi(MyapiLoader.SV_PATH_GAME_AWARD, MyapiLoader.getParamGameAward(
                fullName = name,
                phone = phone,
                log_id = logID,
                award_id = awardId.toString()), object : OnLoopjCompleted {
            override fun taskCompleted(resultsl: String) {
                val data = Utils.getResult(resultsl)
                if (data != null && data.status) {
                    listQuaf[0].datrao = listQuaf[0].datrao + 1
                } else {
                    Utils.toast(this@AChucMungGame, data.message)
                }
            }

            override fun taskError(resultsl: String) {
            }
        })
    }
}