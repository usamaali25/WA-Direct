package com.example.wadirect

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun Context.loadData() {
    // method to load arraylist from shared prefs
    // initializing our shared prefs with name as
    // shared preferences.
    Log.d("LOAD1", "loadData: Load Running")
    val sharedPreferences: SharedPreferences =
        getSharedPreferences("shared preferences", Context.MODE_PRIVATE)

    // creating a variable for gson.
    val gson = Gson()

    // below line is to get to string present from our
    // shared prefs if not present setting it as null.
    val json = sharedPreferences.getString("data", null)
    if (json?.isNotEmpty() == true)
    {
        // below line is to get the type of our array list.
        val type: Type = object : TypeToken<ArrayList<DataModel?>?>() {}.type
        // in below line we are getting data from gson
        // and saving it to our array list
        HomeFragment.data = gson.fromJson(json,type)
    }


    // checking below if the array list is empty or not
}

 fun Context.saveData() {
    // method for saving the data in array list.
    // creating a variable for storing data in
    // shared preferences.
    val sharedPreferences: SharedPreferences =
        getSharedPreferences("shared preferences", Context.MODE_PRIVATE)

    // creating a variable for editor to
    // store data in shared preferences.
    val editor = sharedPreferences.edit()

    // creating a new variable for gson.
    val gson = Gson()

    // getting data from gson and storing it in a string.
    val json: String = gson.toJson(HomeFragment.data)

    // below line is to save data in shared
    // prefs in the form of string.
    editor.putString("data", json)

    // below line is to apply changes
    // and save data in shared prefs.
    editor.apply()

    // after saving data we are displaying a toast message.
//        Toast.makeText(context, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
}

