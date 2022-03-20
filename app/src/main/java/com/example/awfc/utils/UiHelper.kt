package com.example.awfc.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.example.awfc.R
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class UiHelper @Inject constructor(
    private val application: Application
) {
    fun getConnectivityStatus(): Boolean {
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun showSnackBarShort(activity: Activity, content: String) =
        Snackbar.make(activity.findViewById(android.R.id.content), content, Snackbar.LENGTH_SHORT)
            .show()

    fun showSnackBarShortNoData(activity: Activity) =
        Snackbar.make(
            activity.findViewById(android.R.id.content),
            application.applicationContext.getString(R.string.errorResponse_noData),
            Snackbar.LENGTH_SHORT
        )
            .show()

    fun showSnackBarShortFailedToRetrieveData(activity: Activity) =
        Snackbar.make(
            activity.findViewById(android.R.id.content),
            application.applicationContext.getString(R.string.errorResponse_failedToRetrieveData),
            Snackbar.LENGTH_SHORT
        )
            .show()

    fun showSnackBarLong(activity: Activity, content: String) =
        Snackbar.make(activity.findViewById(android.R.id.content), content, Snackbar.LENGTH_LONG)
            .show()

    fun showToastShortFailedToRetrieveData(context: Context) =
        Toast.makeText(
            context,
            application.applicationContext.getString(R.string.errorResponse_failedToRetrieveData),
            Toast.LENGTH_SHORT
        )
            .show()

    fun showToastShortTest(context: Context) =
        Toast.makeText(context, "Test", Toast.LENGTH_SHORT)
            .show()

    fun showToastShortCustom(context: Context, text: String) =
        Toast.makeText(context, text, Toast.LENGTH_SHORT)
            .show()

    fun showToastNoInternet(context: Context) =
        Toast.makeText(
            context,
            context.getString(R.string.no_internet_connection),
            Toast.LENGTH_SHORT
        )
            .show()

    fun showToastLongCustom(context: Context, text: String) =
        Toast.makeText(context, text, Toast.LENGTH_LONG)
            .show()
}