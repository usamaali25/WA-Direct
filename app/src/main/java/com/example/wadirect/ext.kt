package com.example.wadirect

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun rateApp(context:Context) {

    val uri = Uri.parse("market://details?id=" + context.packageName)
    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
    try {
        context.startActivity(myAppLinkToMarket)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            context,
            "Impossible to find an application for the market",
            Toast.LENGTH_SHORT
        ).show()
    }

}

fun shareIt(context: Context) {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.putExtra(Intent.EXTRA_SUBJECT, "WA Direct")
    intent.putExtra(
        Intent.EXTRA_TEXT,
        " https://play.google.com/store/apps/details?id=com.tutorial.personal.androidstudiopro "
    )
    intent.type = "text/plain"
    context.startActivity(intent)

}
fun Context.privacylink(){
    val  url = "https://simplelifeapplication.blogspot.com/p/simple-life-applications-privacy-policy.html"
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    startActivity(i)
}