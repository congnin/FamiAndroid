package com.app.codev.fami2020

import android.animation.Animator
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import com.app.codev.Seleton
import com.app.codev.model.QuaObj
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_quay.*
import kotlin.random.Random

class AQuayV2 : ABase() {

    var listRandom = ArrayList<QuaObj>()
    var indexGift = -1

    var soLuongquaTrongHinhVongquay = 8

    override fun onResume() {
        super.onResume()
        if (Seleton.getInstance().saveTotoRotate == 0) {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        setContentView(R.layout.a_quay_new)

        detectNavigationButtonBar()

        title_lan.text = getString(R.string.soluotquay) + " " + Seleton.getInstance().totalRotate + "/" + Seleton.getInstance().saveTotoRotate

        a_quay_tv_batdau.setOnClickListener {
            if (indexGift >= 0) {
                a_quay_tv_batdau.visibility = View.INVISIBLE
                var dgIndex = 0
                when(indexGift) {
                    // hop sua
                    0 -> {
                        val list = listOf(1, 3, 7)
                        val randomIndex = Random.nextInt(list.size)
                        dgIndex = list[randomIndex]
                    }
                    // ly
                    1 -> dgIndex = 5
                    // nuoc khoang
                    2 -> dgIndex = 2
                    // nhiet ke
                    3 -> dgIndex = 6
                    // gau bong
                    4 -> dgIndex = 0
                    // thung sua
                    5 -> dgIndex = 4
                }
                getDgreeQua(dgIndex)

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
    fun getDgreeQua(index: Int) {
        val randomVongQuay = (15..30).random() * 360
        val randomTrong1phan = (1..15).random()

        dgree = (360 / soLuongquaTrongHinhVongquay) * index + randomTrong1phan + randomVongQuay
    }

    fun roateImage() {
        a_quay_icon_rotate.animate()
                .setDuration(4000)
                .rotation(dgree.toFloat())
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        //a_quay_tv_batdau.isEnabled = true
                        Handler().postDelayed({ changeScreen() }, 2000)
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }

                })
                .start()
    }

    fun getQuaRandom() {
        if (listQuaf != null && listQuaf.size > 0) {
            for (item in listQuaf) {
                if (item.datrao < item.soqua && item.tile > 0) {
                    listRandom.add(item)
                }
            }

            var startTile = 0
            for (x in 0 until listRandom.size) {
                listRandom[x].tileFrom = startTile
                startTile += listRandom[x].tile
                Utils.loge("", "name = " + listRandom[x].name)
                Utils.loge("", " tilefrome[$x]=" + listRandom[x].tileFrom + " -to" + (listRandom[x].tile + listRandom[x].tileFrom) + "-- start=$startTile")
            }

            if (listRandom.size > 0) {
                var indexRanom = 0
                if (listRandom.size > 1) {
                    var findPercent = (0 until startTile).random()
                    Utils.loge("", "findPercent=$findPercent")
                    for (x in 0 until listRandom.size) {
                        if (findPercent >= listRandom[x].tileFrom && findPercent < (listRandom[x].tileFrom + listRandom[x].tile)) {
                            indexRanom = x
                            Utils.loge("", "indexRandome=$indexRanom")
                            Utils.loge("", "name=" + listRandom[x].name + " id=" + listRandom[x].id)
                        }
                    }
                }
                indexGift = listRandom[indexRanom].id - 1
                Utils.loge("", "indexGift=$indexGift")
                Utils.loge("", listQuaf[indexGift].toString())
                if (listQuaf[indexGift].datrao < listQuaf[indexGift].soqua) {
                    listQuaf[indexGift].datrao = listQuaf[indexGift].datrao + 1
                    Seleton.getInstance().addIDGift(indexGift)

                } else {
                    Utils.toast(this, getString(R.string.dahetqua))
                    hetqua()
                }
                Utils.ID_Qua = listQuaf[indexGift].id
                Utils.INDEX_QUA = indexGift
            } else {
                Utils.toast(this, getString(R.string.dahetqua))
                hetqua()
            }
        }
    }

    fun changeScreen() {
        object : CountDownTimer(100, 1000) {
            override fun onFinish() {
                finish()
                Utils.openAQuayWinV2(this@AQuayV2)
            }

            override fun onTick(l: Long) {}
        }.start()
    }

    fun hetqua() {
        object : CountDownTimer(1000, 1000) {
            override fun onFinish() {
                if (Seleton.getInstance().totalRotate > 1) {
                    Utils.openAQuacuabanV2(this@AQuayV2)
                }
                finish()
            }

            override fun onTick(l: Long) {}
        }.start()
    }
}