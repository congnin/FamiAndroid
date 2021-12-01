package com.app.codev.fami2020

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_manhgep.*
import kotlin.math.abs


class AManhgep : ABase() {

    var isActive1 = false
    var isActive2 = false
    var isActive3 = false

    private var locationF1 = IntArray(2)
    private var locationF2 = IntArray(2)
    private var locationF3 = IntArray(2)


    var xWidth = 0
    var yHeight = 0

    val disCal = 30

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_manhgep)

        detectNavigationButtonBar()

        a_manhgep_ct.setOnClickListener {

        }

        initView()

        frame1.setOnClickListener {
            locationF1 = IntArray(2)
            frame1.getLocationOnScreen(locationF1)
            Toast.makeText(
                this,
                "X axis is " + locationF1[0] + "and Y axis is " + locationF1[1],
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun getLocationOnScreen(view: View): Point? {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return Point(location[0], location[1])
    }

    var windowwidth = 0
    var windowheight = 0
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("ClickableViewAccessibility")
    fun initView()
    {

        windowwidth = windowManager.defaultDisplay.getWidth()
        windowheight = windowManager.defaultDisplay.getHeight()
        Utils.loge("","w=$windowwidth")
        Utils.loge("","h=$windowheight")

        frame11.setOnTouchListener { v, event ->
            val layoutParams: ConstraintLayout.LayoutParams = imgMove1.getLayoutParams() as ConstraintLayout.LayoutParams
            when (event.action) {

                MotionEvent.ACTION_UP -> {
                    if(!isActive1)
                    {
                        imgMove1.visibility = View.INVISIBLE
                        frame11.visibility = View.VISIBLE
                    }
                    checkResult()
                }

                MotionEvent.ACTION_DOWN -> {
                    frame1.getLocationOnScreen(locationF1)
                    xWidth = frame11.measuredWidth/2
                    yHeight = frame11.measuredHeight/2
                    Utils.loge("","xWidth=$xWidth --- yHeight=$yHeight")
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
                    imgMove1.layoutParams = layoutParams
                    if(imgMove1.visibility == View.INVISIBLE) {
                        imgMove1.visibility = View.VISIBLE
                        frame11.visibility = View.INVISIBLE
                    }


                    val disX = abs(x_cord - locationF1[0]) - 80
                    val disY =  abs(y_cord - locationF1[1])
                    //Utils.loge("","disx=$disX --- disY=$disY")

                    if(disX < disCal && disY< disCal)
                    {
                        imgMove1.visibility = View.GONE
                        frame11.visibility= View.INVISIBLE
                        frame1.visibility = View.VISIBLE
                        isActive1 = true
                    }
                }
                else -> {
                }
            }
            true
        }



        frame22.setOnTouchListener { v, event ->
            val layoutParams: ConstraintLayout.LayoutParams = imgMove2.getLayoutParams() as ConstraintLayout.LayoutParams
            when (event.action) {

                MotionEvent.ACTION_UP -> {
                    if(!isActive2)
                    {
                        imgMove2.visibility = View.INVISIBLE
                        frame22.visibility = View.VISIBLE
                    }
                    checkResult()
                }

                MotionEvent.ACTION_DOWN -> {
                    frame2.getLocationOnScreen(locationF2)
                    xWidth = frame22.measuredWidth/2
                    yHeight = frame22.measuredHeight/2
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
                    imgMove2.layoutParams = layoutParams
                    if(imgMove2.visibility == View.INVISIBLE) {
                        imgMove2.visibility = View.VISIBLE
                        frame22.visibility = View.INVISIBLE
                    }


                    val disX = abs(x_cord - locationF2[0]) - 80
                    val disY =  abs(y_cord - locationF2[1])
                    Utils.loge("","disx=$disX --- disY=$disY")

                    if(disX < disCal && disY< disCal)
                    {
                        imgMove2.visibility = View.GONE
                        frame22.visibility= View.INVISIBLE
                        frame2.visibility = View.VISIBLE
                        isActive2 = true
                    }
                }
                else -> {
                }
            }
            true
        }



        frame33.setOnTouchListener { v, event ->
            val layoutParams: ConstraintLayout.LayoutParams = imgMove3.getLayoutParams() as ConstraintLayout.LayoutParams
            when (event.action) {

                MotionEvent.ACTION_UP -> {
                    if(!isActive3)
                    {
                        imgMove3.visibility = View.INVISIBLE
                        frame33.visibility = View.VISIBLE
                    }
                    checkResult()
                }

                MotionEvent.ACTION_DOWN -> {
                    frame3.getLocationOnScreen(locationF3)
                    xWidth = frame33.measuredWidth/2
                    yHeight = frame33.measuredHeight/2
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
                    imgMove3.layoutParams = layoutParams
                    if(imgMove3.visibility == View.INVISIBLE) {
                        imgMove3.visibility = View.VISIBLE
                        frame33.visibility = View.INVISIBLE
                    }


                    val disX = abs(x_cord - locationF3[0]) - 80
                    val disY =  abs(y_cord - locationF3[1])
                    Utils.loge("","disx=$disX --- disY=$disY")

                    if(disX < disCal && disY< disCal)
                    {
                        imgMove3.visibility = View.GONE
                        frame33.visibility= View.INVISIBLE
                        frame3.visibility = View.VISIBLE
                        isActive3 = true
                    }
                }
                else -> {
                }
            }
            true
        }


    }

    fun checkResult()
    {
        if(isActive1 && isActive2 && isActive3)
        {
            changeScreen()
        }
    }


    fun changeScreen()
    {
        object : CountDownTimer(500, 1000) {
            override fun onFinish() {
                finish()
                Utils.openAManhgepChucmung(this@AManhgep)
            }

            override fun onTick(l: Long) {}
        }.start()
    }

    override fun onBackPressed() {

    }
}