package com.app.codev.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.app.codev.fami2020.*
import com.app.codev.model.BaseResponse
import com.app.codev.model.CuahangData
import com.google.gson.Gson

class Utils {

    companion object {

        const val IS_DEBUG = false

        const val SAVE_NAME = "SAVE_NAME"
        const val SAVE_PG_ID = "SAVE_PG_ID"
        const val SAVE_CHECKIN_ID = "SAVE_CHECKIN_ID"

        const val SAVE_LOG_ID = "SAVE_LOG_ID"
        const val SAVE_SETTING = "SAVE_SETTING"
        const val SAVE_TONG_QUAY = "SAVE_TONG_QUAY"

        const val SAVE_KETQUA1 = "SAVE_KETQUA1"
        const val SAVE_KETQUA2 = "SAVE_KETQUA2"
        const val SAVE_KETQUA3 = "SAVE_KETQUA3"


        var INDEX_QUA = -1
        var ID_Qua = -1


        const val SAVE_USER_NAME = "SAVE_USER_NAME"
        const val SAVE_USER_PHONE = "SAVE_USER_PHONE"

        const val SAVE_KH_NAME = "SAVE_FULL_NAME"
        const val SAVE_KH_PHONE = "SAVE_KH_PHONE"

        fun loge(tag: String, mess: String) {
            Log.e("AQua=$tag", mess)
        }

        fun toast(mContext: Context, s: String) {
            Toast.makeText(mContext, s, Toast.LENGTH_LONG).show()
        }

        const val COMMONPREFS = "COMMONPREFS"
        fun saveString(key: String, value: String, context: Context) {
            val prefs = context.getSharedPreferences(COMMONPREFS, Activity.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString(key, value)
            editor.commit()
        }

        fun getString(key: String,  activity: Activity): String {
            val prefs = activity.getSharedPreferences(COMMONPREFS, Activity.MODE_PRIVATE)
            val language : String = prefs.getString(key, "") !!
            return language
        }

        fun saveBool(key: String, value: Boolean, activity: Activity) {
            val prefs = activity.getSharedPreferences(COMMONPREFS, Activity.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putBoolean(key, value)
            editor.commit()
        }

        fun getBool(key: String,  activity: Activity): Boolean {
            val prefs = activity.getSharedPreferences(COMMONPREFS, Activity.MODE_PRIVATE)
            val language : Boolean = prefs.getBoolean(key, false)
            return language
        }

        fun openANhanvien(mc: Context) {
            val intent = Intent(mc, ANhanvien::class.java)
            mc.startActivity(intent)
        }

        fun openAThamgia(mc: Context) {
            val intent = Intent(mc, AThamgia::class.java)
            mc.startActivity(intent)
        }

        fun openAKhachhang(mc: Context) {
            val intent = Intent(mc, AKhachhang::class.java)
            mc.startActivity(intent)
        }

        fun openAKhachhangV2(mc: Context) {
            val intent = Intent(mc, AKhachhangV2::class.java)
            mc.startActivity(intent)
        }

        fun openASampling(mc: Context) {
            val intent = Intent(mc, ASamplingLanding::class.java)
            mc.startActivity(intent)
        }

        fun openADoiQua(mc: Context) {
            val intent = Intent(mc, ADoiQuaLanding::class.java)
            mc.startActivity(intent)
        }

        fun openAGameLanding(mc: Context) {
            val intent = Intent(mc, AGameLanding::class.java)
            mc.startActivity(intent)
        }

        fun openAHoatdong(mc: Context) {
            val intent = Intent(mc, AHoatDong::class.java)
            mc.startActivity(intent)
        }

        fun openAManhgep(mc: Context) {
            val intent = Intent(mc, AManhgep::class.java)
            mc.startActivity(intent)
        }

        fun openAManhgepV2(mc: Context) {
            val intent = Intent(mc, AManhgepV2::class.java)
            mc.startActivity(intent)
        }

        fun openAManhgepChucmung(mc: Context) {
            val intent = Intent(mc, AManhgepChucmung::class.java)
            mc.startActivity(intent)
        }

        fun openALuotquay(mc: Context) {
            val intent = Intent(mc, ALuotquay::class.java)
            mc.startActivity(intent)
        }

        fun openAQuay(mc: Context) {
            val intent = Intent(mc, AQuay::class.java)
            mc.startActivity(intent)
        }

        fun openAQuayV2(mc: Context) {
            val intent = Intent(mc, AQuayV2::class.java)
            mc.startActivity(intent)
        }

        fun openAQuayWin(mc: Context) {
            val intent = Intent(mc, AQuayWin::class.java)
            mc.startActivity(intent)
        }

        fun openAChucmungGame(mc: Context) {
            val intent = Intent(mc, AChucMungGame::class.java)
            mc.startActivity(intent)
        }

        fun openAQuayWinV2(mc: Context) {
            val intent = Intent(mc, AQuayWinV2::class.java)
            mc.startActivity(intent)
        }

        fun openASetting(mc: Context) {
            val intent = Intent(mc, ASettingV2::class.java)
            mc.startActivity(intent)
        }

        fun openAFinal(mc: Context) {
            val intent = Intent(mc, AFinal::class.java)
            mc.startActivity(intent)
        }

        fun openAFinalV2(mc: Context) {
            val intent = Intent(mc, AFinalV2::class.java)
            mc.startActivity(intent)
        }

        fun openAQuacuaban(mc: Context) {
            val intent = Intent(mc, AQuacuaban::class.java)
            mc.startActivity(intent)
        }

        fun openAQuacuabanV2(mc: Context) {
            val intent = Intent(mc, AQuacuabanV2::class.java)
            mc.startActivity(intent)
        }

        fun openAKhachhangSampling(mc: Context) {
            val intent = Intent(mc, AKhachhangSampling::class.java)
            mc.startActivity(intent)
        }

        fun openAKhachhangGame(mc: Context) {
            val intent = Intent(mc, AKhachhangGame::class.java)
            mc.startActivity(intent)
        }

        fun getCuahangData(value : String) : CuahangData
        {
            val data: CuahangData = Gson().fromJson<CuahangData>(value, CuahangData::class.java!!)
            return data
        }

        fun getResult(value : String) : BaseResponse
        {
            val data: BaseResponse = Gson().fromJson<BaseResponse>(value, BaseResponse::class.java!!)
            return data
        }


    }
}