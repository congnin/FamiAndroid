package com.app.codev.fami2020

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_manhgep_new.*
import kotlin.math.abs


class AManhgepV2 : ABase() {

    var phomaiActive = false
    var capheActive = false
    var bachaActive = false
    var duaActive = false
    var tauhuActive = false
    var duongdenActive = false

    private var locationF1 = IntArray(2)

    var xWidth = 0
    var yHeight = 0

    val disCal = 30

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_manhgep_new)

        detectNavigationButtonBar()

        initView()
    }

    fun getLocationOnScreen(view: View): Point {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return Point(location[0], location[1])
    }

    var windowwidth = 0
    var windowheight = 0

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("ClickableViewAccessibility")
    fun initView() {

        windowwidth = windowManager.defaultDisplay.getWidth()
        windowheight = windowManager.defaultDisplay.getHeight()
        Utils.loge("", "w=$windowwidth")
        Utils.loge("", "h=$windowheight")

        // phomai
        img_phomai.setOnTouchListener { v, event ->
            val layoutParams: ConstraintLayout.LayoutParams =
                    imgMovePhomai.getLayoutParams() as ConstraintLayout.LayoutParams
            when (event.action) {

                MotionEvent.ACTION_UP -> {
                    if (!phomaiActive) {
                        imgMovePhomai.visibility = View.INVISIBLE
                        img_phomai.visibility = View.VISIBLE
                    }
                    checkResult()
                }

                MotionEvent.ACTION_DOWN -> {
                    view_phomai.getLocationOnScreen(locationF1)
                    xWidth = img_phomai.measuredWidth / 2
                    yHeight = img_phomai.measuredHeight / 2
                    Utils.loge("", "xWidth=$xWidth --- yHeight=$yHeight")
                }
                MotionEvent.ACTION_MOVE -> {
                    var x_cord = event.rawX.toInt() - xWidth
                    var y_cord = event.rawY.toInt() - yHeight
                    if (x_cord > windowwidth) {
                        x_cord = windowwidth
                    }
                    if (y_cord > windowheight) {
                        y_cord = windowheight
                    }

                    layoutParams.marginStart = x_cord
                    layoutParams.topMargin = y_cord
                    imgMovePhomai.layoutParams = layoutParams
                    if (imgMovePhomai.visibility == View.INVISIBLE) {
                        imgMovePhomai.visibility = View.VISIBLE
                        img_phomai.visibility = View.INVISIBLE
                    }


                    val disX = abs(x_cord - locationF1[0]) - 80
                    val disY = abs(y_cord - locationF1[1])
                    //Utils.loge("","disx=$disX --- disY=$disY")

                    if (disX < disCal && disY < disCal) {
                        imgMovePhomai.visibility = View.GONE
                        img_phomai.visibility = View.INVISIBLE
                        view_phomai.visibility = View.VISIBLE
                        phomaiActive = true
                    }
                }
                else -> {
                }
            }
            true
        }

        // ca phe
        img_caphe.setOnTouchListener { v, event ->
            val layoutParams: ConstraintLayout.LayoutParams =
                    imgMoveCaPhe.getLayoutParams() as ConstraintLayout.LayoutParams
            when (event.action) {

                MotionEvent.ACTION_UP -> {
                    if (!capheActive) {
                        imgMoveCaPhe.visibility = View.INVISIBLE
                        img_caphe.visibility = View.VISIBLE
                    }
                    checkResult()
                }

                MotionEvent.ACTION_DOWN -> {
                    view_caphe.getLocationOnScreen(locationF1)
                    xWidth = img_caphe.measuredWidth / 2
                    yHeight = img_caphe.measuredHeight / 2
                    Utils.loge("", "xWidth=$xWidth --- yHeight=$yHeight")
                }
                MotionEvent.ACTION_MOVE -> {
                    var x_cord = event.rawX.toInt() - xWidth
                    var y_cord = event.rawY.toInt() - yHeight
                    if (x_cord > windowwidth) {
                        x_cord = windowwidth
                    }
                    if (y_cord > windowheight) {
                        y_cord = windowheight
                    }

                    layoutParams.marginStart = x_cord
                    layoutParams.topMargin = y_cord
                    imgMoveCaPhe.layoutParams = layoutParams
                    if (imgMoveCaPhe.visibility == View.INVISIBLE) {
                        imgMoveCaPhe.visibility = View.VISIBLE
                        img_caphe.visibility = View.INVISIBLE
                    }


                    val disX = abs(x_cord - locationF1[0]) - 80
                    val disY = abs(y_cord - locationF1[1])
                    //Utils.loge("","disx=$disX --- disY=$disY")

                    if (disX < disCal && disY < disCal) {
                        imgMoveCaPhe.visibility = View.GONE
                        img_caphe.visibility = View.INVISIBLE
                        view_caphe.visibility = View.VISIBLE
                        capheActive = true
                    }
                }
                else -> {
                }
            }
            true
        }

        // bac ha
        img_bacha.setOnTouchListener { v, event ->
            val layoutParams: ConstraintLayout.LayoutParams =
                    imgMoveBacHa.getLayoutParams() as ConstraintLayout.LayoutParams
            when (event.action) {

                MotionEvent.ACTION_UP -> {
                    if (!bachaActive) {
                        imgMoveBacHa.visibility = View.INVISIBLE
                        img_bacha.visibility = View.VISIBLE
                    }
                    checkResult()
                }

                MotionEvent.ACTION_DOWN -> {
                    view_bacha.getLocationOnScreen(locationF1)
                    xWidth = img_bacha.measuredWidth / 2
                    yHeight = img_bacha.measuredHeight / 2
                    Utils.loge("", "xWidth=$xWidth --- yHeight=$yHeight")
                }
                MotionEvent.ACTION_MOVE -> {
                    var x_cord = event.rawX.toInt() - xWidth
                    var y_cord = event.rawY.toInt() - yHeight
                    if (x_cord > windowwidth) {
                        x_cord = windowwidth
                    }
                    if (y_cord > windowheight) {
                        y_cord = windowheight
                    }

                    layoutParams.marginStart = x_cord
                    layoutParams.topMargin = y_cord
                    imgMoveBacHa.layoutParams = layoutParams
                    if (imgMoveBacHa.visibility == View.INVISIBLE) {
                        imgMoveBacHa.visibility = View.VISIBLE
                        img_bacha.visibility = View.INVISIBLE
                    }


                    val disX = abs(x_cord - locationF1[0]) - 80
                    val disY = abs(y_cord - locationF1[1])
                    //Utils.loge("","disx=$disX --- disY=$disY")

                    if (disX < disCal && disY < disCal) {
                        imgMoveBacHa.visibility = View.GONE
                        img_bacha.visibility = View.INVISIBLE
                        view_bacha.visibility = View.VISIBLE
                        bachaActive = true
                    }
                }
                else -> {
                }
            }
            true
        }

        // dua
        img_dua.setOnTouchListener { v, event ->
            val layoutParams: ConstraintLayout.LayoutParams =
                    imgMoveDua.getLayoutParams() as ConstraintLayout.LayoutParams
            when (event.action) {

                MotionEvent.ACTION_UP -> {
                    if (!duaActive) {
                        imgMoveDua.visibility = View.INVISIBLE
                        img_dua.visibility = View.VISIBLE
                    }
                    checkResult()
                }

                MotionEvent.ACTION_DOWN -> {
                    view_dua.getLocationOnScreen(locationF1)
                    xWidth = img_dua.measuredWidth / 2
                    yHeight = img_dua.measuredHeight / 2
                    Utils.loge("", "xWidth=$xWidth --- yHeight=$yHeight")
                }
                MotionEvent.ACTION_MOVE -> {
                    var x_cord = event.rawX.toInt() - xWidth
                    var y_cord = event.rawY.toInt() - yHeight
                    if (x_cord > windowwidth) {
                        x_cord = windowwidth
                    }
                    if (y_cord > windowheight) {
                        y_cord = windowheight
                    }

                    layoutParams.marginStart = x_cord
                    layoutParams.topMargin = y_cord
                    imgMoveDua.layoutParams = layoutParams
                    if (imgMoveDua.visibility == View.INVISIBLE) {
                        imgMoveDua.visibility = View.VISIBLE
                        img_dua.visibility = View.INVISIBLE
                    }


                    val disX = abs(x_cord - locationF1[0]) - 80
                    val disY = abs(y_cord - locationF1[1])
                    //Utils.loge("","disx=$disX --- disY=$disY")

                    if (disX < disCal && disY < disCal) {
                        imgMoveDua.visibility = View.GONE
                        img_dua.visibility = View.INVISIBLE
                        view_dua.visibility = View.VISIBLE
                        duaActive = true
                    }
                }
                else -> {
                }
            }
            true
        }

        // tau hu
        img_tauhu.setOnTouchListener { v, event ->
            val layoutParams: ConstraintLayout.LayoutParams =
                    imgMoveTauHu.getLayoutParams() as ConstraintLayout.LayoutParams
            when (event.action) {

                MotionEvent.ACTION_UP -> {
                    if (!tauhuActive) {
                        imgMoveTauHu.visibility = View.INVISIBLE
                        img_tauhu.visibility = View.VISIBLE
                    }
                    checkResult()
                }

                MotionEvent.ACTION_DOWN -> {
                    view_tauhu.getLocationOnScreen(locationF1)
                    xWidth = img_tauhu.measuredWidth / 2
                    yHeight = img_tauhu.measuredHeight / 2
                    Utils.loge("", "xWidth=$xWidth --- yHeight=$yHeight")
                }
                MotionEvent.ACTION_MOVE -> {
                    var x_cord = event.rawX.toInt() - xWidth
                    var y_cord = event.rawY.toInt() - yHeight
                    if (x_cord > windowwidth) {
                        x_cord = windowwidth
                    }
                    if (y_cord > windowheight) {
                        y_cord = windowheight
                    }

                    layoutParams.marginStart = x_cord
                    layoutParams.topMargin = y_cord
                    imgMoveTauHu.layoutParams = layoutParams
                    if (imgMoveTauHu.visibility == View.INVISIBLE) {
                        imgMoveTauHu.visibility = View.VISIBLE
                        img_tauhu.visibility = View.INVISIBLE
                    }


                    val disX = abs(x_cord - locationF1[0]) - 80
                    val disY = abs(y_cord - locationF1[1])
                    //Utils.loge("","disx=$disX --- disY=$disY")

                    if (disX < disCal && disY < disCal) {
                        imgMoveTauHu.visibility = View.GONE
                        img_tauhu.visibility = View.INVISIBLE
                        view_tauhu.visibility = View.VISIBLE
                        tauhuActive = true
                    }
                }
                else -> {
                }
            }
            true
        }

        // duong den
        img_duongden.setOnTouchListener { v, event ->
            val layoutParams: ConstraintLayout.LayoutParams =
                    imgMoveDuongDen.getLayoutParams() as ConstraintLayout.LayoutParams
            when (event.action) {

                MotionEvent.ACTION_UP -> {
                    if (!duongdenActive) {
                        imgMoveDuongDen.visibility = View.INVISIBLE
                        img_duongden.visibility = View.VISIBLE
                    }
                    checkResult()
                }

                MotionEvent.ACTION_DOWN -> {
                    view_duongden.getLocationOnScreen(locationF1)
                    xWidth = img_duongden.measuredWidth / 2
                    yHeight = img_duongden.measuredHeight / 2
                    Utils.loge("", "xWidth=$xWidth --- yHeight=$yHeight")
                }
                MotionEvent.ACTION_MOVE -> {
                    var x_cord = event.rawX.toInt() - xWidth
                    var y_cord = event.rawY.toInt() - yHeight
                    if (x_cord > windowwidth) {
                        x_cord = windowwidth
                    }
                    if (y_cord > windowheight) {
                        y_cord = windowheight
                    }

                    layoutParams.marginStart = x_cord
                    layoutParams.topMargin = y_cord
                    imgMoveDuongDen.layoutParams = layoutParams
                    if (imgMoveDuongDen.visibility == View.INVISIBLE) {
                        imgMoveDuongDen.visibility = View.VISIBLE
                        img_duongden.visibility = View.INVISIBLE
                    }


                    val disX = abs(x_cord - locationF1[0]) - 80
                    val disY = abs(y_cord - locationF1[1])
                    //Utils.loge("","disx=$disX --- disY=$disY")

                    if (disX < disCal && disY < disCal) {
                        imgMoveDuongDen.visibility = View.GONE
                        img_duongden.visibility = View.INVISIBLE
                        view_duongden.visibility = View.VISIBLE
                        duongdenActive = true
                    }
                }
                else -> {
                }
            }
            true
        }
    }

    fun checkResult() {
        if (phomaiActive && capheActive && bachaActive && duaActive && tauhuActive && duongdenActive) {
            changeScreen()
        }
    }


    fun changeScreen() {
        object : CountDownTimer(500, 1000) {
            override fun onFinish() {
                finish()
                Utils.openAChucmungGame(this@AManhgepV2)
            }

            override fun onTick(l: Long) {}
        }.start()
    }

    override fun onBackPressed() {

    }
}