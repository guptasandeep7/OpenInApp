package com.sandeepgupta.openinapp.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun goToWeb(context: Context, url:String){
    val urlIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    context.startActivity(urlIntent)
}