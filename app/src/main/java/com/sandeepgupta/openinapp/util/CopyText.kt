package com.sandeepgupta.openinapp.util

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

@SuppressLint("ServiceCast")
fun copyText(
    context: Context,
    text:String
) {
    val clipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData: ClipData = ClipData.newPlainText(
        "text",text)
    clipboardManager.setPrimaryClip(clipData)
}