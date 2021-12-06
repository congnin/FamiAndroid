package com.app.codev.fami2020

import android.content.DialogInterface
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.app.codev.Seleton
import com.app.codev.api.MyapiLoader
import com.app.codev.api.OnLoopjCompleted
import com.app.codev.utils.Utils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.a_khachhang.*
import kotlinx.android.synthetic.main.a_khachhang.a_kh_edt_sdt
import kotlinx.android.synthetic.main.a_khachhang.a_kh_edt_ten
import kotlinx.android.synthetic.main.a_khachhang.a_kh_tv_batdau
import kotlinx.android.synthetic.main.a_khachhang.msnv
import kotlinx.android.synthetic.main.a_khachhang_new.*
import java.lang.Exception

enum class SP {
    FM_NC_BICH,
    FM_NC_THUNGBICH,
    FM_NC_LOC,
    FM_NC_THUNGLOC,
    FM_CX_BICH,
    FM_CX_THUNGBICH,
    FM_CX_LOC,
    FM_CX_THUNGLOC,
    FM_KID_LOC,
    FM_KID_THUNGLOC,
    FM_GO_BICH,
    FM_GO_THUNGBICH,
    FM_GO_LOC,
    FM_GO_THUNGLOC,
    VINASOY_LOC,
    VINASOY_THUNGLOC
}

enum class TYPE {
    BICH,
    THUNGBICH,
    LOC,
    THUNGLOC
}

class AKhachhangV2 : ABase() {

    var famiNguyenBich = 0 //0.0f
    var famiNguyenThungBich = 0 //0.0f
    var famiNguyenLoc = 0 //0.0f
    var famiNguyenThungLoc = 0 //0.0f

    var famiCanxiBich = 0 //0.0f
    var famiCanxiThungBich = 0 //0.0f
    var famiCanxiLoc = 0 //0.0f
    var famiCanxiThungLoc = 0 //0.0f

    var famigoBich = 0 //0.0f
    var famigoThungBich = 0 //0.0f
    var famigoLoc = 0 //0.0f
    var famigoThungLoc = 0 //0.0f

    var vinasoyLoc = 0 //0.0f
    var vinasoyThungLoc = 0 //0.0f
    var famiKidLoc = 0 //0.0f
    var famiKidThungLoc = 0 //0.0f

    lateinit var sp: SP
    lateinit var type: TYPE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setContentView(R.layout.a_khachhang_new)

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

        requestCurrentLocation()
    }

    fun initView() {
        // Fami nguyen chat
        tv_fm_nc_bich.setOnClickListener {
            sp = SP.FM_NC_BICH
            type = TYPE.BICH
            setDialog(famiNguyenBich)
        }
        tv_fm_nc_thungbich.setOnClickListener {
            sp = SP.FM_NC_THUNGBICH
            type = TYPE.THUNGBICH
            setDialog(famiNguyenThungBich)
        }
        tv_fm_nc_loc.setOnClickListener {
            sp = SP.FM_NC_LOC
            type = TYPE.LOC
            setDialog(famiNguyenLoc)
        }
        tv_fm_nc_thungloc.setOnClickListener {
            sp = SP.FM_NC_THUNGLOC
            type = TYPE.THUNGLOC
            setDialog(famiNguyenThungLoc)
        }

        // Fami Canxi
        tv_fm_cx_bich.setOnClickListener {
            sp = SP.FM_CX_BICH
            type = TYPE.BICH
            setDialog(famiCanxiBich)
        }
        tv_fm_cx_thungbich.setOnClickListener {
            sp = SP.FM_CX_THUNGBICH
            type = TYPE.THUNGBICH
            setDialog(famiCanxiThungBich)
        }
        tv_fm_cx_loc.setOnClickListener {
            sp = SP.FM_CX_LOC
            type = TYPE.LOC
            setDialog(famiCanxiLoc)
        }
        tv_fm_cx_thungloc.setOnClickListener {
            sp = SP.FM_CX_THUNGLOC
            type = TYPE.THUNGLOC
            setDialog(famiCanxiThungLoc)
        }

        // Fami kid
        tv_fm_kid_loc.setOnClickListener {
            sp = SP.FM_KID_LOC
            type = TYPE.LOC
            setDialog(famiKidLoc)
        }
        tv_fm_kid_thungloc.setOnClickListener {
            sp = SP.FM_KID_THUNGLOC
            type = TYPE.THUNGLOC
            setDialog(famiKidThungLoc)
        }

        // Fami Go
        tv_fm_go_bich.setOnClickListener {
            sp = SP.FM_GO_BICH
            type = TYPE.BICH
            setDialog(famigoBich)
        }
        tv_fm_go_thungbich.setOnClickListener {
            sp = SP.FM_GO_THUNGBICH
            type = TYPE.THUNGBICH
            setDialog(famigoThungBich)
        }
        tv_fm_go_loc.setOnClickListener {
            sp = SP.FM_GO_LOC
            type = TYPE.LOC
            setDialog(famigoLoc)
        }
        tv_fm_go_thungloc.setOnClickListener {
            sp = SP.FM_GO_THUNGLOC
            type = TYPE.THUNGLOC
            setDialog(famigoThungLoc)
        }

        // Vinasoy
        tv_vinasoy_loc.setOnClickListener {
            sp = SP.VINASOY_LOC
            type = TYPE.LOC
            setDialog(vinasoyLoc)
        }
        tv_visasoy_thungloc.setOnClickListener {
            sp = SP.VINASOY_THUNGLOC
            type = TYPE.THUNGLOC
            setDialog(vinasoyThungLoc)
        }
    }

    fun checkValid() {
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
        if (TextUtils.isEmpty(checkin)) {
            Utils.toast(this, getString(R.string.nhanvienchuadangnhap))
            return
        }

        if (famigoBich > 0 || famigoThungBich > 0 || famigoLoc > 0 || famigoThungLoc > 0
                || famiCanxiBich > 0 || famiCanxiThungBich > 0 || famiCanxiLoc > 0 || famiCanxiThungLoc > 0
                || famiNguyenBich > 0 || famiNguyenThungBich > 0 || famiNguyenLoc > 0 || famiNguyenThungLoc > 0
                || famiKidLoc > 0 || famiKidThungLoc > 0 || vinasoyLoc > 0 || vinasoyThungLoc > 0
        ) {
            a_kh_tv_batdau.visibility = View.INVISIBLE
            MyapiLoader.postApi(MyapiLoader.SV_PATH_KH_LOGIN, MyapiLoader.getParamKhachhangV2(
                    ten,
                    sdt,
                    checkin,

                    // col 1
                    famiNguyenBich,
                    famiNguyenThungBich,
                    famiNguyenLoc,
                    famiNguyenThungLoc,

                    // col 2
                    famiCanxiBich,
                    famiCanxiThungBich,
                    famiCanxiLoc,
                    famiCanxiThungLoc,

                    // col 3
                    famiKidLoc,
                    famiKidThungLoc,

                    // col 4
                    famigoBich,
                    famigoThungBich,
                    famigoLoc,
                    famigoThungLoc,

                    // col 5
                    vinasoyLoc,
                    vinasoyThungLoc
            ), object : OnLoopjCompleted {
                override fun taskCompleted(resultsl: String) {
                    a_kh_tv_batdau.visibility = View.VISIBLE
                    val data = Utils.getResult(resultsl)
                    if (data != null && data.status) {
                        Utils.saveString(Utils.SAVE_LOG_ID, data.log_id.toString(), this@AKhachhangV2)
                        Seleton.getInstance().saveTotoRotate = data.tong_luot_quay
                        Seleton.getInstance().totalRotate = 1
                        if (data.tong_luot_quay > 0) {
                            Utils.openALuotquay(this@AKhachhangV2)
                            finish()
                        } else {
                            Utils.toast(this@AKhachhangV2, getString(R.string.bankhongcoluotquay))
                        }

                    }
                    if (data != null && !TextUtils.isEmpty(data.message))
                        Utils.toast(this@AKhachhangV2, data.message)
                }

                override fun taskError(resultsl: String) {
                    a_kh_tv_batdau.visibility = View.VISIBLE
                    Utils.toast(this@AKhachhangV2, resultsl)
                }

            })
        } else {
            Utils.toast(this, getString(R.string.vuilongnhapdayduthongtin))
        }

    }

    var text = ""
    fun setDialog(name: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.nhapsoluong))
        val input = EditText(this)
        input.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        if (name > 0)
            input.setText(name.toString())
        builder.setView(input)

        builder.setPositiveButton(getString(R.string.dongy),
                DialogInterface.OnClickListener
                { _, _ -> setNameField(input.text.toString()) })
        builder.setNegativeButton(R.string.huy,
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    fun setNameField(value: String) {
        try {

            var number = 0 //0.0f
            try {
                if (!TextUtils.isEmpty(value)) {
                    number = value.toInt()
                }
            } catch (ex: Exception) {
                Utils.toast(this, getString(R.string.banhapkhongdung))
                return
            }

            // Fami Go
            if (sp == SP.FM_GO_BICH) {
                famigoBich = number
                tv_fm_go_bich.text = number.toString() + " " + getString(R.string.bich)
            }

            if (sp == SP.FM_GO_THUNGBICH) {
                famigoThungBich = number
                tv_fm_go_thungbich.text = number.toString() + " " + getString(R.string.thung_bich)
            }

            if (sp == SP.FM_GO_LOC) {
                famigoLoc = number
                tv_fm_go_loc.text = number.toString() + " " + getString(R.string.loc)
            }

            if (sp == SP.FM_GO_THUNGLOC) {
                famigoThungLoc = number
                tv_fm_go_thungloc.text = number.toString() + " " + getString(R.string.thung_loc)
            }

            // Fami Canxi
            if (sp == SP.FM_CX_BICH) {
                famiCanxiBich = number
                tv_fm_cx_bich.text = number.toString() + " " + getString(R.string.bich)
            }

            if (sp == SP.FM_CX_THUNGBICH) {
                famiCanxiThungBich = number
                tv_fm_cx_thungbich.text = number.toString() + " " + getString(R.string.thung_bich)
            }

            if (sp == SP.FM_CX_LOC) {
                famiCanxiLoc = number
                tv_fm_cx_loc.text = number.toString() + " " + getString(R.string.loc)
            }

            if (sp == SP.FM_CX_THUNGLOC) {
                famiCanxiThungLoc = number
                tv_fm_cx_thungloc.text = number.toString() + " " + getString(R.string.thung_loc)
            }

            // Fami Nguyen Chat
            if (sp == SP.FM_NC_BICH) {
                famiNguyenBich = number
                tv_fm_nc_bich.text = number.toString() + " " + getString(R.string.bich)
            }

            if (sp == SP.FM_NC_THUNGBICH) {
                famiNguyenThungBich = number
                tv_fm_nc_thungbich.text =
                        number.toString() + " " + getString(R.string.thung_bich)
            }

            if (sp == SP.FM_NC_LOC) {
                famiNguyenLoc = number
                tv_fm_nc_loc.text = number.toString() + " " + getString(R.string.loc)
            }

            if (sp == SP.FM_NC_THUNGLOC) {
                famiNguyenThungLoc = number
                tv_fm_nc_thungloc.text =
                        number.toString() + " " + getString(R.string.thung_loc)
            }

            // Fami Kid
            if (sp == SP.FM_KID_LOC) {
                famiKidLoc = number
                tv_fm_kid_loc.text = number.toString() + " " + getString(R.string.loc)
            }

            if (sp == SP.FM_KID_THUNGLOC) {
                famiKidThungLoc = number
                tv_fm_kid_thungloc.text =
                        number.toString() + " " + getString(R.string.thung_loc)
            }

            // Vinasoy
            if (sp == SP.VINASOY_LOC) {
                vinasoyLoc = number
                tv_vinasoy_loc.text = number.toString() + " " + getString(R.string.loc)
            }

            if (sp == SP.VINASOY_THUNGLOC) {
                vinasoyThungLoc = number
                tv_visasoy_thungloc.text =
                        number.toString() + " " + getString(R.string.thung_loc)
            }

        } catch (ex: Exception) {

        }
    }


    var typeChoose = 0
    var typeBich = 1
    var typeThung = 2
    var typeLocHop = 3
    var indexTextChoose = 0
    var textName = ""
    fun showDialog() {
        indexTextChoose = 0
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.chon))

        var animals = listOf("0.5", "1", "1.5")
        if (typeChoose == typeThung) {
            animals = listOf("1", "2")
        } else if (typeChoose == typeLocHop) {
            animals = listOf("0.5", "1", "1.5", "2", "2.5")
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

    override fun updateLocation(location: Location) {
        super.updateLocation(location)
        tvPosition.text = "LAT: ${location.latitude} - LONG: ${location.longitude}"
    }
}