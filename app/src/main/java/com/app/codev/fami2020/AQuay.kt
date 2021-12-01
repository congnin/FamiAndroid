package com.app.codev.fami2020

import android.animation.Animator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.app.codev.Seleton
import com.app.codev.model.QuaObj
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_quay.*

class AQuay : ABase() {

    var listRandom = ArrayList<QuaObj>()
    var indexGift = -1

    var soLuongquaTrongHinhVongquay = 6

    var listIndexQuaVongQuay= arrayOf(
        0 //tui
        ,1 // ly
        ,2 // thung
        ,3 // non
        ,4 // canuoc
    )


    override fun onResume() {
        super.onResume()
        if(Seleton.getInstance().saveTotoRotate == 0)
        {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        setContentView(R.layout.a_quay)

        detectNavigationButtonBar()

        title_lan.text = getString(R.string.soluotquay) + " " + Seleton.getInstance().totalRotate  + "/" + Seleton.getInstance().saveTotoRotate

        a_quay_tv_batdau.setOnClickListener {
            if(indexGift >= 0)
            {
                a_quay_tv_batdau.visibility = View.INVISIBLE
                getDgreeQua(listIndexQuaVongQuay[indexGift])

                roateImage()
                saveQua()
            }
        }


        checkGift()
        getQuaRandom()
    }

    override fun onBackPressed() {

    }

    var dgree = 0
    fun getDgreeQua(index : Int)
    {
        val randomVongQuay = (20..40).random() * 360
        val randomTrong1phan = (1..20).random()

        dgree = (360/soLuongquaTrongHinhVongquay)*index + randomTrong1phan  + randomVongQuay
    }
    fun roateImage()
    {
        a_quay_icon_rotate.animate()
            .setDuration(4000)
            .rotation(dgree.toFloat())
            .setListener(object : Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    //a_quay_tv_batdau.isEnabled = true
                    changeScreen()
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }

            })
            .start()
    }

    fun getQuaRandom()
    {
        if(listQuaf!=null && listQuaf.size > 0)
        {
            for(item in listQuaf)
            {
                if(item.datrao < item.soqua && item.tile > 0)
                {
                    listRandom.add(item)
                }
            }

            var startTile = 0
            for (x in 0 until listRandom.size)
            {
                listRandom[x].tileFrom = startTile
                startTile += listRandom[x].tile
                Utils.loge("","name = " + listRandom[x].name )
                Utils.loge(""," tilefrome[$x]="+listRandom[x].tileFrom + " -to" + (listRandom[x].tile+listRandom[x].tileFrom) + "-- start=$startTile")
            }

            if(listRandom.size > 0)
            {
                var indexRanom = 0
                if(listRandom.size > 1)
                {
                    var findPercent = (0 until startTile).random()
                    Utils.loge("","findPercent=$findPercent")
                    for (x in 0 until listRandom.size)
                    {
                        if(findPercent >= listRandom[x].tileFrom && findPercent < (listRandom[x].tileFrom + listRandom[x].tile) )
                        {
                            indexRanom = x
                            Utils.loge("","indexRandome=$indexRanom")
                            Utils.loge("","name=" + listRandom[x].name + " id=" + listRandom[x].id)
                        }
                    }
                }
                indexGift = listRandom[indexRanom].id - 1
                Utils.loge("","indexGift=$indexGift")
                Utils.loge("",listQuaf[indexGift].toString())
                if(listQuaf[indexGift].datrao < listQuaf[indexGift].soqua)
                {
                    listQuaf[indexGift].datrao = listQuaf[indexGift].datrao + 1
                    Seleton.getInstance().addIDGift(indexGift)

                }else
                {
                    Utils.toast(this,getString(R.string.dahetqua))
                    hetqua()
                }
                Utils.ID_Qua = listQuaf[indexGift].id
                Utils.INDEX_QUA = indexGift
            }else
            {
                Utils.toast(this,getString(R.string.dahetqua))
                hetqua()
            }
        }
    }

    fun changeScreen()
    {
        object : CountDownTimer(100, 1000) {
            override fun onFinish() {
                finish()
                Utils.openAQuayWin(this@AQuay)
            }

            override fun onTick(l: Long) {}
        }.start()
    }

    fun hetqua()
    {
        object : CountDownTimer(1000, 1000) {
            override fun onFinish() {
                if(Seleton.getInstance().totalRotate > 1)
                {
                    Utils.openAQuacuaban(this@AQuay)
                }
                finish()
            }

            override fun onTick(l: Long) {}
        }.start()
    }
}