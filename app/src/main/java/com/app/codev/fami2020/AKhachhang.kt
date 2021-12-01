package com.app.codev.fami2020

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.app.codev.Seleton
import com.app.codev.api.MyapiLoader
import com.app.codev.api.OnLoopjCompleted
import com.app.codev.utils.Utils
import kotlinx.android.synthetic.main.a_khachhang.*
import java.lang.Exception


class AKhachhang : ABase() {

    var famigoBich =  0 //0.0f
    var famigoThungBich = 0 //0.0f
    var famigoLoc = 0 //0.0f
    var famigoThungLoc = 0 //0.0f

    var famiCanxiBich = 0 //0.0f
    var famiCanxiThungBich = 0 //0.0f
    var famiCanxiLoc = 0 //0.0f
    var famiCanxiThungLoc = 0 //0.0f

    var famiNguyenBich = 0 //0.0f
    var famiNguyenThungBich = 0 //0.0f
    var famiNguyenLoc = 0 //0.0f
    var famiNguyenThungLoc = 0 //0.0f

    var famiNguyenItBich = 0 //0.0f
    var famiNguyenThungItBich = 0 //0.0f
    var famiNguyenItLoc = 0 //0.0f
    var famiNguyenThungItLoc = 0 //0.0f

    var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_khachhang)

        detectNavigationButtonBar()

        a_kh_tv_batdau.setOnClickListener {
            //setDialog()
            checkValid()

            //Seleton.getInstance().saveTotoRotate = 3
            //Seleton.getInstance().totalRotate = 1
            //Utils.openAThamgia(this@AKhachhang)
        }

        initView()
        Seleton.getInstance().reset()
        msnv.text = "MNV: " + Seleton.getInstance().msvn
    }

    fun initView()
    {
        tv_cx_cd_bich.setOnClickListener {
            type = 11
            setDialog(famigoBich)
            //typeChoose = typeBich
            //showDialog()
        }
        tv_cx_cd_thungbich.setOnClickListener {
            type = 12
            setDialog(famigoThungBich)
            //typeChoose = typeThung
            //showDialog()
        }
        tv_cx_cd_loc.setOnClickListener {
            type = 13
            setDialog(famigoLoc)
            //typeChoose = typeLocHop
            //showDialog()
        }
        tv_cx_cd_thungloc.setOnClickListener {
            type = 14
            setDialog(famigoThungLoc)
            //typeChoose = typeThung
            //showDialog()
        }


        tv_cx_id_bich.setOnClickListener {
            type = 21
            setDialog(famiCanxiBich)
            //typeChoose = typeBich
            //showDialog()
        }
        tv_cx_id_thungbich.setOnClickListener {
            type = 22
            setDialog(famiCanxiThungBich)
            //typeChoose = typeThung
            //showDialog()
        }
        tv_cx_id_loc.setOnClickListener {
            type = 23
            setDialog(famiCanxiLoc)
            //typeChoose = typeLocHop
            //showDialog()
        }
        tv_cx_id_thungloc.setOnClickListener {
            type = 24
            setDialog(famiCanxiThungLoc)
            //typeChoose = typeThung
            //showDialog()
        }

        tv_nc_cd_bich.setOnClickListener {
            type = 31
            //setDialog(famiNguyenBich)

            typeChoose = typeBich
            showDialog()
        }
        tv_nc_cd_thungbich.setOnClickListener {
            type = 32
            //setDialog(famiNguyenThungBich)
            typeChoose = typeThung
            showDialog()
        }
        tv_nc_cd_loc.setOnClickListener {
            type = 33
            //setDialog(famiNguyenLoc)
            typeChoose = typeLocHop
            showDialog()
        }
        tv_nc_cd_thungloc.setOnClickListener {
            type = 34
            //setDialog(famiNguyenThungLoc)
            typeChoose = typeThung
            showDialog()
        }


        tv_nc_id_bich.setOnClickListener {
            type = 41
            //setDialog(famiNguyenItBich)
            typeChoose = typeBich
            showDialog()
        }
        tv_nc_id_thungbich.setOnClickListener {
            type = 42
            //setDialog(famiNguyenThungItBich)
            typeChoose = typeThung
            showDialog()
        }
        tv_nc_id_loc.setOnClickListener {
            //setDialog(famiNguyenItLoc)
            type = 43

            typeChoose = typeLocHop
            showDialog()
        }
        tv_nc_id_thungloc.setOnClickListener {
            //setDialog(famiNguyenThungItLoc)
            type = 44

            typeChoose = typeThung
            showDialog()
        }
    }

    fun checkValid()
    {
        val ten = a_kh_edt_ten.text.toString()
        if (TextUtils.isEmpty(ten)) {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
            return
        }

        val sdt = a_kh_edt_sdt.text.toString()
        if (TextUtils.isEmpty(sdt)) {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
            return
        }

        val checkin = Utils.getString(Utils.SAVE_CHECKIN_ID, this)
        if(TextUtils.isEmpty(checkin))
        {
            Utils.toast(this, getString(R.string.nhanvienchuadangnhap))
            return
        }

        if(famigoBich > 0 || famigoThungBich > 0 || famigoLoc >0 || famigoThungLoc > 0
            || famiCanxiBich > 0 || famiCanxiThungBich > 0 || famiCanxiLoc > 0 || famiCanxiThungLoc > 0
            || famiNguyenBich > 0 || famiNguyenThungBich > 0 || famiNguyenLoc > 0 || famiNguyenThungLoc > 0
            || famiNguyenItBich > 0 || famiNguyenThungItBich > 0 || famiNguyenItLoc > 0 || famiNguyenThungItLoc > 0
        )
        {
            a_kh_tv_batdau.visibility = View.INVISIBLE
            MyapiLoader.postApi(MyapiLoader.SV_PATH_KH_LOGIN, MyapiLoader.getParamKhachhang(
                ten,
                sdt,
                checkin,

                famigoBich,
                famigoThungBich,
                famigoLoc,
                famigoThungLoc,

                famiCanxiBich,
                famiCanxiThungBich,
                famiCanxiLoc,
                famiCanxiThungLoc

//                famiNguyenBich,
//                famiNguyenThungBich,
//                famiNguyenLoc,
//                famiNguyenThungLoc,
//
//                famiNguyenItBich,
//                famiNguyenThungItBich,
//                famiNguyenItLoc,
//                famiNguyenThungItLoc
            ), object : OnLoopjCompleted {
                override fun taskCompleted(resultsl: String) {
                    a_kh_tv_batdau.visibility = View.VISIBLE
                    val data = Utils.getResult(resultsl)
                    if (data != null && data.status) {
                        Utils.saveString(Utils.SAVE_LOG_ID, data.log_id.toString(), this@AKhachhang)
                        Seleton.getInstance().saveTotoRotate = data.tong_luot_quay
                        Seleton.getInstance().totalRotate = 1
                        if(data.tong_luot_quay > 0)
                        {
                            Utils.openAThamgia(this@AKhachhang)
                            finish()
                        }else
                        {
                            Utils.toast(this@AKhachhang,getString(R.string.bankhongcoluotquay))
                        }

                    }
                    if (data != null && !TextUtils.isEmpty(data.message))
                        Utils.toast(this@AKhachhang, data.message)
                }

                override fun taskError(resultsl: String) {
                    a_kh_tv_batdau.visibility = View.VISIBLE
                    Utils.toast(this@AKhachhang,resultsl)
                }

            })
        }else
        {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
        }

    }

    var text = ""
    fun setDialog(name : Int)
    {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.nhapsoluong))
        val input = EditText(this)
        input.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        if(name > 0)
            input.setText(name.toString())
        builder.setView(input)

        builder.setPositiveButton(getString(R.string.dongy),
            DialogInterface.OnClickListener
            { _, _ ->setNameField(input.text.toString())  })
        builder.setNegativeButton(R.string.huy,
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    fun setNameField(value : String)
    {
        try{

            var number   = 0 //0.0f
            try{
                if(!TextUtils.isEmpty(value)){
                    number = value.toInt()
                }
            }catch (ex : Exception){
                Utils.toast(this,getString(R.string.banhapkhongdung))
                return
            }


            if(type == 11) {
                famigoBich = number
                tv_cx_cd_bich.text = number.toString() + " " + getString(R.string.bich)
            }

            if(type == 12) {
                famigoThungBich = number
                tv_cx_cd_thungbich.text = number.toString() + " " + getString(R.string.thung_bich)
            }

            if(type == 13) {
                famigoLoc = number
                tv_cx_cd_loc.text = number.toString() + " " + getString(R.string.loc)
            }

            if(type == 14) {
                famigoThungLoc = number
                tv_cx_cd_thungloc.text = number.toString() + " " + getString(R.string.thung_loc)
            }


            if(type == 21) {
                famiCanxiBich = number
                tv_cx_id_bich.text = number.toString() + " " + getString(R.string.bich)
            }

            if(type == 22) {
                famiCanxiThungBich = number
                tv_cx_id_thungbich.text = number.toString() + " " + getString(R.string.thung_bich)
            }

            if(type == 23) {
                famiCanxiLoc = number
                tv_cx_id_loc.text = number.toString() + " " + getString(R.string.loc)
            }

            if(type == 24) {
                famiCanxiThungLoc = number
                tv_cx_id_thungloc.text = number.toString() + " " + getString(R.string.thung_loc)
            }


            if(type == 31) {
                famiNguyenBich = number
                tv_nc_cd_bich.text = number.toString() + " " + getString(R.string.bich)
            }

            if(type == 32) {
                famiNguyenThungBich = number
                tv_nc_cd_thungbich.text =
                    number.toString() + " " + getString(R.string.thung_bich)
            }

            if(type == 33) {
                famiNguyenLoc = number
                tv_nc_cd_loc.text = number.toString() + " " + getString(R.string.loc)
            }

            if(type == 34) {
                famiNguyenThungLoc = number
                tv_nc_cd_thungloc.text =
                    number.toString() + " " + getString(R.string.thung_loc)
            }

            if(type == 41) {
                famiNguyenItBich = number
                tv_nc_id_bich.text = number.toString() + " " + getString(R.string.bich)
            }

            if(type == 42) {
                famiNguyenThungItBich = number
                tv_nc_id_thungbich.text =
                    number.toString() + " " + getString(R.string.thung_bich)
            }

            if(type == 43) {
                famiNguyenItLoc = number
                tv_nc_id_loc.text = number.toString() + " " + getString(R.string.loc)
            }

            if(type == 44) {
                famiNguyenThungItLoc = number
                tv_nc_id_thungloc.text =
                    number.toString() + " " + getString(R.string.thung_loc)
            }

        }catch (ex : Exception){

        }
    }



    var typeChoose = 0
    var typeBich = 1
    var typeThung = 2
    var typeLocHop = 3
    var indexTextChoose = 0
    var textName = ""
    fun showDialog()
    {
        indexTextChoose = 0
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.chon))

        var  animals  = listOf("0.5", "1", "1.5")
        if(typeChoose == typeThung)
        {
            animals  = listOf ("1", "2")
        }else if(typeChoose == typeLocHop){
            animals = listOf ("0.5","1", "1.5","2","2.5")
        }

            val listName = animals.toTypedArray()
            builder.setSingleChoiceItems(
                listName,
                indexTextChoose,
                DialogInterface.OnClickListener { dialog, which ->
                    indexTextChoose = which
                    textName = listName[indexTextChoose]
                    setNameField(textName)
                    dialog.dismiss()
                })

            builder.setNegativeButton(getString(R.string.huy), null)
            val dialog = builder.create()
            dialog.show()
    }

}