package com.app.codev.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class CuahangData(
        var data: ArrayList<CuahangObj>
) : BaseResponse()

@Parcelize
data class CuahangObj(
        var id: Int,
        var dia_chi_cua_hang: String,
        var ten_cua_hang: String
): Parcelable

