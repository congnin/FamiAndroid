package com.app.codev.fami2020

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.app.codev.Seleton
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_quacuaban.*

class AQuacuaban : ABase() {

    var nameQua= arrayOf(
        R.mipmap.qua1
        ,R.mipmap.qua2
        ,R.mipmap.qua3
        ,R.mipmap.qua4
        ,R.mipmap.qua5
        )
    var idView= arrayOf(
        R.id.framequa1
        ,R.id.framequa2
        ,R.id.framequa3
        ,R.id.framequa4
        ,R.id.framequa5
        ,R.id.framequa6
        ,R.id.framequa7
    )

    var idTextNumberView= arrayOf(
        R.id.tvqua1
        ,R.id.tvqua2
        ,R.id.tvqua3
        ,R.id.tvqua4
        ,R.id.tvqua5
        ,R.id.tvqua6
        ,R.id.tvqua7
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_quacuaban)

        detectNavigationButtonBar()
        var listID = Seleton.getInstance().list

        for(i in 0 until listID.size)
        {
            if(listID[i] > 0)
            {
                if(i<idView.size)
                {
                    val view = findViewById<FrameLayout>(idView[i])
                    view.visibility = View.VISIBLE

                    val textnumber = findViewById<TextView>(idTextNumberView[i])
                    textnumber.text  = listID[i].toString()
                }
            }
        }

        linear_click_all.setOnClickListener {
            Seleton.getInstance().reset()
            Utils.openAFinal(this)
            finish()
        }
    }

}