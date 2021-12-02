package com.app.codev.api

import com.app.codev.utils.Utils
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*
import java.lang.Exception


interface OnLoopjCompleted {
    fun taskCompleted(resultsl: String)
    fun taskError(resultsl: String)
}

interface ServerInterface {

    @FormUrlEncoded
    @POST("{type}")
    fun postApi(@Path("type") type : String, @FieldMap options :Map<String, String> ) : Call<ResponseBody>


    @FormUrlEncoded
    @POST("{type}")
    fun getListCuaHang(@Path("type") type : String ) : Call<ResponseBody>

}
class MyapiLoader{

    companion object {

        val SUCCESS_STATUS = 1


//        const val SV_LIVE = "http://projects.codev.vn/2020/106-vector-fami-canxi/api/"
        //const val SV_LIVE = "http://projects.codev.vn/2020/62-delta-fami-canxi-game-cho/api/"
        //const val SV_LIVE = "http://projects.codev.vn/2020/60-vector-fami-go-sampling/api/"
        //const val SV_LIVE = "http://projects.codev.vn/2020/21-vector-fami-3-tac-dong-chac-khoe-xuong/api/"
        //const val SV_LIVE = "http://192.168.0.8/CoDev/2020/21-Vector-Fami-3-tac-dong-chac-khoe-xuong/Develop/server/api/"
        const val SV_LIVE = "http://projects.codev.vn/2021/98-fami-canxi-tet/api/"
        const val SV_PATH_LIST_CUAHANG = "get_list_cua_hang"
        const val SV_PATH_NV_LOGIN = "login"
        const val SV_PATH_KH_LOGIN = "register"

        const val SV_PATH_LUCKY = "luckydraw"
        const val SV_PATH_GAME = "game"
        const val SV_PATH_SAMPLING = "sampling"
        const val SV_PATH_GAME_AWARD = "game_award"

        private var rfit: Retrofit? = null
        private fun getClient(link: String): Retrofit {
            val client = OkHttpClient.Builder().build()
            rfit = Retrofit.Builder().baseUrl(link).client(client).build()
            return rfit!!
        }

        fun postApi(path : String,param : HashMap<String, String>,listener: OnLoopjCompleted) {
            val apiInterface = getClient(SV_LIVE)
                .create(ServerInterface::class.java)

            val call = apiInterface.postApi(path,param)
            Utils.loge("",param.toString())
            call.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    if (t != null && !t.localizedMessage.isNullOrEmpty()) {
                        Utils.loge("api", t.localizedMessage)
                        listener.taskError(t.localizedMessage)
                    }
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (listener != null) {
                        try{
                            val value: String = response.body()!!.string()
                            Utils.loge("api", value)
                            listener.taskCompleted(value)
                        }catch (ex:Exception){
                            listener.taskError(response.toString())
                        }

                    }
                }
            })
        }

        fun getListCuaHang(path : String,listener: OnLoopjCompleted) {
            val apiInterface = getClient(SV_LIVE)
                .create(ServerInterface::class.java)

            val call = apiInterface.getListCuaHang(path)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    if (t != null && !t.localizedMessage.isNullOrEmpty()) {
                        Utils.loge("api", t.localizedMessage)
                        listener.taskError(t.localizedMessage)
                    }
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (listener != null) {
                        val value: String = response.body()!!.string()
                        Utils.loge("api", value)
                        listener.taskCompleted(value)
                    }
                }
            })
        }

        fun getParamNhanvienLogin(username : String,password : String,cua_hang_id : String) : HashMap<String, String>
        {
            val requestParams = HashMap<String, String>()
            requestParams["username"] = username
            requestParams["password"] = password
            requestParams["cua_hang_id"] = cua_hang_id
            return requestParams
        }

        fun getParamCuahang() : HashMap<String, String>
        {
            val requestParams = HashMap<String, String>()
            requestParams["username"] = "name"
            return requestParams
        }

        fun getParamLucky(log_id : String ,award_id : String,tong_luot_quay : Int,luot_quay_hien_tai : Int) : HashMap<String, String>
        {
            val requestParams = HashMap<String, String>()
            requestParams["key"] = "KdwAcfRdw"
            requestParams["log_id"] = log_id
            requestParams["award_id"] = award_id
            requestParams["tong_luot_quay"] = tong_luot_quay.toString()
            requestParams["luot_quay_hien_tai"] = luot_quay_hien_tai.toString()
            return requestParams
        }


        fun getParamKhachhang(full_name : String ,phone : String ,checkin_id : String
        ,col_1_1 : Int
        ,col_1_2 : Int
        ,col_1_3 : Int
        ,col_1_4 : Int

                              ,col_2_1 : Int
                              ,col_2_2 : Int
                              ,col_2_3 : Int
                              ,col_2_4 : Int

//                              ,col_3_1 : Float
//                              ,col_3_2 : Float
//                              ,col_3_3 : Float
//                              ,col_3_4 : Float
//
//                              ,col_4_1 : Float
//                              ,col_4_2 : Float
//                              ,col_4_3 : Float
//                              ,col_4_4 : Float
        ) : HashMap<String, String>
        {
            val requestParams = HashMap<String, String>()
            requestParams["full_name"] = full_name
            requestParams["phone"] = phone
            requestParams["key"] = "KdwAcfRdw"
            requestParams["checkin_id"] = checkin_id

            requestParams["col_1_1"] = col_1_1.toString()
            requestParams["col_1_2"] = col_1_2.toString()
            requestParams["col_1_3"] = col_1_3.toString()
            requestParams["col_1_4"] = col_1_4.toString()

            requestParams["col_2_1"] = col_2_1.toString()
            requestParams["col_2_2"] = col_2_2.toString()
            requestParams["col_2_3"] = col_2_3.toString()
            requestParams["col_2_4"] = col_2_4.toString()

//            requestParams["col_3_1"] = col_3_1.toString()
//            requestParams["col_3_2"] = col_3_2.toString()
//            requestParams["col_3_3"] = col_3_3.toString()
//            requestParams["col_3_4"] = col_3_4.toString()
//
//            requestParams["col_4_1"] = col_4_1.toString()
//            requestParams["col_4_2"] = col_4_2.toString()
//            requestParams["col_4_3"] = col_4_3.toString()
//            requestParams["col_4_4"] = col_4_4.toString()

            return requestParams
        }

        fun getParamKhachhangV2(full_name : String ,phone : String ,checkin_id : String
                                ,col_1_1 : Int
                                ,col_1_2 : Int
                                ,col_1_3 : Int
                                ,col_1_4 : Int

                                ,col_2_1 : Int
                                ,col_2_2 : Int
                                ,col_2_3 : Int
                                ,col_2_4 : Int

                                ,col_3_1 : Int
                                ,col_3_2 : Int
//                              ,col_3_3 : Float
//                              ,col_3_4 : Float
//
                                ,col_4_1 : Int
                                ,col_4_2 : Int
                                ,col_4_3 : Int
                                ,col_4_4 : Int

                                ,col_5_1 : Int
                                ,col_5_2 : Int
        ) : HashMap<String, String>
        {
            val requestParams = HashMap<String, String>()
            requestParams["full_name"] = full_name
            requestParams["phone"] = phone
            requestParams["key"] = "KdwAcfRdw"
            requestParams["checkin_id"] = checkin_id

            requestParams["col_1_1"] = col_1_1.toString()
            requestParams["col_1_2"] = col_1_2.toString()
            requestParams["col_1_3"] = col_1_3.toString()
            requestParams["col_1_4"] = col_1_4.toString()

            requestParams["col_2_1"] = col_2_1.toString()
            requestParams["col_2_2"] = col_2_2.toString()
            requestParams["col_2_3"] = col_2_3.toString()
            requestParams["col_2_4"] = col_2_4.toString()

            requestParams["col_3_1"] = col_3_1.toString()
            requestParams["col_3_2"] = col_3_2.toString()
//            requestParams["col_3_3"] = col_3_3.toString()
//            requestParams["col_3_4"] = col_3_4.toString()
//
            requestParams["col_4_1"] = col_4_1.toString()
            requestParams["col_4_2"] = col_4_2.toString()
            requestParams["col_4_3"] = col_4_3.toString()
            requestParams["col_4_4"] = col_4_4.toString()

            requestParams["col_5_1"] = col_5_1.toString()
            requestParams["col_5_2"] = col_5_2.toString()

            return requestParams
        }

        fun getParamKhachHangSimple(fullName : String, phone : String ,checkin_id : String
        ) : HashMap<String, String>
        {
            val requestParams = HashMap<String, String>()
            requestParams["full_name"] = fullName
            requestParams["phone"] = phone
            requestParams["key"] = "KdwAcfRdw"
            requestParams["checkin_id"] = checkin_id

            return requestParams
        }

        fun getParamGameAward(fullName : String, phone : String,
                              log_id : String, award_id : String) : HashMap<String, String>
        {
            val requestParams = HashMap<String, String>()
            requestParams["full_name"] = fullName
            requestParams["phone"] = phone
            requestParams["key"] = "KdwAcfRdw"
            requestParams["log_id"] = log_id
            requestParams["award_id"] = award_id
            return requestParams
        }
    }
}
