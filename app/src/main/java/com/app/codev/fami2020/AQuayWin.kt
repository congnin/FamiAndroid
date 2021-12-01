package com.app.codev.fami2020

import android.os.Bundle
import android.text.TextUtils
import com.app.codev.Seleton
import com.app.codev.api.MyapiLoader
import com.app.codev.api.OnLoopjCompleted
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_quay_win.*

class AQuayWin : ABase() {
    var listQua= arrayOf(
        R.mipmap.qua1
        ,R.mipmap.qua2
        ,R.mipmap.qua3
        ,R.mipmap.qua4
        ,R.mipmap.qua5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_quay_win)

        detectNavigationButtonBar()

        a_quaywin_ct.setOnClickListener {
            Seleton.getInstance().addRotate()
            if(Seleton.getInstance().isHaveGift)
            {
                Utils.openAQuay(this)
            }else
            {
                Utils.openAQuacuaban(this)
            }
            finish()
        }


        if(Utils.ID_Qua > 0)
        {
            postGift()
        }
        a_quay_win_img_qua.setImageResource(listQua[Utils.INDEX_QUA])
    }

    fun postGift()
    {
        val logID = Utils.getString(Utils.SAVE_LOG_ID,this)
        if(TextUtils.isEmpty(logID))
        {
            Utils.toast(this,"khong co log_id")
            return
        }
        MyapiLoader.postApi(MyapiLoader.SV_PATH_LUCKY,MyapiLoader.getParamLucky(logID,Utils.ID_Qua.toString()
            ,Seleton.getInstance().saveTotoRotate,Seleton.getInstance().totalRotate),object : OnLoopjCompleted{
            override fun taskCompleted(resultsl: String) {
                val data = Utils.getResult(resultsl)
                if(data!=null && !TextUtils.isEmpty(data.message))
                    Utils.toast(this@AQuayWin,data.message)
            }
            override fun taskError(resultsl: String) {
            }
        })
    }
}