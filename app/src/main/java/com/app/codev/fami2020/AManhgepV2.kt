package com.app.codev.fami2020

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_manhgep_new.*
import kotlin.math.abs

class AManhgepV2 : ABase() {

    val resultList = arrayOf(false, false, false, false, false, false)
    val imgList = arrayOf(R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5, R.id.img6)
    val viewList = listOf(R.id.view_phomai, R.id.view_caphe, R.id.view_bacha,
            R.id.view_dua, R.id.view_tauhu, R.id.view_duongden)
    val moveList = listOf(R.id.imgMovePhomai, R.id.imgMoveCaPhe, R.id.imgMoveBacHa,
            R.id.imgMoveDua, R.id.imgMoveTauHu, R.id.imgMoveDuongDen)
    var mipmapList = listOf(R.mipmap.phomai, R.mipmap.caphe, R.mipmap.bacha, R.mipmap.dua, R.mipmap.gung, R.mipmap.duongden)
    var positionList = listOf(0, 1, 2, 3, 4, 5)

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

//        val shuffPosList = positionList.toMutableList().shuffled()
//        positionList = shuffPosList
//        Utils.toast(this, positionList.toString())

        for ((index, value) in positionList.withIndex()) {
            findViewById<ImageView>(imgList[index]).setImageResource(mipmapList[value])
        }
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

        // img1
        for ((pos, img) in imgList.withIndex()) {
            findViewById<ImageView>(img).setOnTouchListener { v, event ->
                val layoutParams: ConstraintLayout.LayoutParams =
                        findViewById<ImageView>(moveList[positionList[pos]]).getLayoutParams() as ConstraintLayout.LayoutParams
                when (event.action) {

                    MotionEvent.ACTION_UP -> {
                        if (!resultList[pos]) {
                            findViewById<ImageView>(moveList[positionList[pos]]).visibility = View.INVISIBLE
                            findViewById<ImageView>(img).visibility = View.VISIBLE
                        }
                        checkResult()
                    }

                    MotionEvent.ACTION_DOWN -> {
                        findViewById<View>(viewList[positionList[pos]]).getLocationOnScreen(locationF1)
                        xWidth = findViewById<ImageView>(img).measuredWidth / 2
                        yHeight = findViewById<ImageView>(img).measuredHeight / 2
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
                        findViewById<ImageView>(moveList[positionList[pos]]).layoutParams = layoutParams
                        if (findViewById<ImageView>(moveList[positionList[pos]]).visibility == View.INVISIBLE) {
                            findViewById<ImageView>(moveList[positionList[pos]]).visibility = View.VISIBLE
                            findViewById<ImageView>(img).visibility = View.INVISIBLE
                        }


                        val disX = abs(x_cord - locationF1[0]) - 80
                        val disY = abs(y_cord - locationF1[1])
                        //Utils.loge("","disx=$disX --- disY=$disY")

                        if (disX < disCal && disY < disCal) {
                            findViewById<ImageView>(moveList[positionList[pos]]).visibility = View.GONE
                            findViewById<ImageView>(img).visibility = View.INVISIBLE
                            resultList[pos] = true
                        }
                    }
                    else -> {
                    }
                }
                true
            }
        }
    }

    fun checkResult() {
        for ((_, value) in resultList.withIndex()) {
            if (!value) {
                return
            }
        }
        changeScreen()
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