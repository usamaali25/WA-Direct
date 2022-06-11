package com.example.wadirect

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.RatingBar

class CustomDialog(var c: Activity) : Dialog(c), View.OnClickListener {
    lateinit var ratingBar:RatingBar
    @SuppressLint("NonConstantResourceId")
    override fun onClick(view: View) {
        when (view.id) {
            R.id.exit -> c.finish()
            R.id.rate -> {
                rateApp(c)
            }

            else -> {}
        }
        dismiss()
    }
}