package com.app.codev.fami2020

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.app.codev.model.QuaObj
import com.app.codev.utils.Utils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception

open class ABase : AppCompatActivity() {

    var currentLat: String? = ""
    var currentLong: String? = ""

    // The Fused Location Provider provides access to location APIs.
    protected val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    // Allows class to cancel the location request if it exits the activity.
    // Typically, you use one cancellation source per lifecycle.
    protected var cancellationTokenSource = CancellationTokenSource()

    fun setFullScreen() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }


    /**
     * CHECK VIRTUAL KEYBOARD
     */
    private var isShowNavigationButtonBar = false
    fun detectNavigationButtonBar() {
        hideNavigationButtonBar()
        val decorView = getWindow().decorView
        decorView.setOnSystemUiVisibilityChangeListener { i ->
            if (i == 0) isShowNavigationButtonBar = true
        }
    }

    private fun hideNavigationButtonBar() {
        getWindow().decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE)
    }

    fun setSystemUiVisibility(visible: Boolean) {
        if (isShowNavigationButtonBar && !visible) {
            var newVis =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            if (!visible) {
                newVis =
                        newVis or (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                or View.SYSTEM_UI_FLAG_IMMERSIVE)
            }
            val decorView = getWindow().decorView
            decorView.systemUiVisibility = newVis
        }
    }

    /**
     * data
     */
    var listQuaf = ArrayList<QuaObj>()
    fun checkGift() {
        val value = Utils.getString(Utils.SAVE_SETTING, this)
        if (!TextUtils.isEmpty(value)) {
            listQuaf = Gson().fromJson(value, object : TypeToken<List<QuaObj?>?>() {}.type)
        } else {
            resetDataV2()
        }
    }

    fun resetData() {
        if (listQuaf.size == 0) {
            listQuaf = ArrayList<QuaObj>()
            listQuaf.add(QuaObj(1, getString(R.string.q1name), 10, 4, 0, 0))
            listQuaf.add(QuaObj(2, getString(R.string.q2name), 20, 89, 0, 0))
            listQuaf.add(QuaObj(3, getString(R.string.q3name), 2, 1, 0, 0))
            listQuaf.add(QuaObj(4, getString(R.string.q4name), 2, 3, 0, 0))
            listQuaf.add(QuaObj(5, getString(R.string.q5name), 2, 3, 0, 0))

//            <string name="q1name">Nón bảo hiểm</string>
//            <string name="q2name">Thùng fami</string>
//            <string name="q3name">Ly fami</string>
//            <string name="q4name">Sữa hộp</string>
//            <string name="q5name">Muỗng inox</string>
        } else if (listQuaf.size > 0) {
            listQuaf[0].datrao = 0
            listQuaf[1].datrao = 0
            listQuaf[2].datrao = 0
            listQuaf[3].datrao = 0
            listQuaf[4].datrao = 0
        }
    }

    fun resetDataV2() {
        if (listQuaf.size == 0) {
            listQuaf = ArrayList<QuaObj>()
            listQuaf.add(QuaObj(1, getString(R.string.q1nameV2), 200, 30, 0, 0))
            listQuaf.add(QuaObj(2, getString(R.string.q2nameV2), 200, 15, 0, 0))
            listQuaf.add(QuaObj(3, getString(R.string.q3nameV2), 200, 15, 0, 0))
            listQuaf.add(QuaObj(4, getString(R.string.q4nameV2), 200, 15, 0, 0))
            listQuaf.add(QuaObj(5, getString(R.string.q5nameV2), 200, 15, 0, 0))
            listQuaf.add(QuaObj(6, getString(R.string.q6nameV2), 200, 10, 0, 0))
        } else if (listQuaf.size > 0) {
            listQuaf[0].datrao = 0
            listQuaf[1].datrao = 0
            listQuaf[2].datrao = 0
            listQuaf[3].datrao = 0
            listQuaf[4].datrao = 0
            listQuaf[5].datrao = 0
        }
    }

    fun saveQua() {
        val jsonne = Gson().toJson(listQuaf)
        Utils.saveString(Utils.SAVE_SETTING, jsonne, this)
    }

    protected fun requestCurrentLocation() {
        // Check Fine permission
        if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            doSomethingWhenGetLocation()
            // Main code
            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                    PRIORITY_HIGH_ACCURACY,
                    cancellationTokenSource.token
            )

            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                val result = if (task.isSuccessful) {
                    val result: Location = task.result
                    "Location (success): ${result.latitude}, ${result.longitude}"
                    updateLocation(location = result)
                } else {
                    val exception = task.exception
                    "Location (failure): $exception"
                    showErrorLocation(exception = exception)
                }

                Log.d("TAG", "getCurrentLocation() result: $result")
            }
        } else {
            // Request fine location permission (full code below).

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE)
            }
        }
    }

    open fun doSomethingWhenGetLocation() {}

    open fun updateLocation(location: Location) {
        currentLat = location.latitude.toString()
        currentLong = location.longitude.toString()
    }

    open fun showErrorLocation(exception: Exception?) {}

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> {
                    Log.d(TAG, "User interaction was cancelled.")
                    requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE)
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED ->
                    requestCurrentLocation()
                else -> {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts(
                            "package",
                            BuildConfig.APPLICATION_ID,
                            null
                    )
                    intent.data = uri
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        cancellationTokenSource.cancel()
    }

    companion object {
        private const val TAG = "TAG"
        private const val REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE = 34
    }
}