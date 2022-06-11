package com.example.wadirect

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hbb20.CountryCodePicker
import java.lang.reflect.Type
import java.util.*


class HomeFragment : Fragment() {

    companion object{
        var data:MutableList<DataModel> = ArrayList()

    }
    private lateinit var view1:View
    private lateinit var countryCodePicker: CountryCodePicker
    private lateinit var number: EditText
    private lateinit var message: EditText
    private lateinit var send:Button
    private lateinit var clear:Button
    val bool = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view1 = inflater.inflate(R.layout.fragment_home, container, false)
        addView(view1)
        requireActivity().loadData()
        Log.d("DATA2", "onCreateView: ${data.size}")
        /*if (data.isNotEmpty())
            loadData()*/

       /* val appIntro = activity?.getSharedPreferences("hasRunBefore_appIntro", 0) //load the preferences
        val hasRun: Boolean = appIntro!!.getBoolean("hasRun_appIntro", false)
        if(!hasRun)
        {
            val settings: SharedPreferences = requireActivity().getSharedPreferences("hasRunBefore_appIntro", 0)
            val edit = settings.edit()
            edit.putBoolean("hasRun_appIntro", true)
            edit.apply()
        }
        else
        {
            Log.d("ELSE1", "onCreateView: Else running")
            loadData()
        }*/
        return view1
    }




    private fun addView(views: View) {
        countryCodePicker = views.findViewById(R.id.numberCodePicker)
        number = views.findViewById(R.id.numbers)
        message = views.findViewById(R.id.enter_messages)
        send = views.findViewById(R.id.send)
        clear = views.findViewById(R.id.clear)
        var code = "91"
        countryCodePicker.setOnCountryChangeListener{
            code=countryCodePicker.selectedCountryCode.toString()
        }

        send.setOnClickListener {
            if (number.text.toString().isEmpty() || message.text.toString().isEmpty()) {
                Toast.makeText(context, "Enter complete detail", Toast.LENGTH_SHORT).show()
                Log.d("NUM1", "number = ${number.text},Message = $message")
            }
            else {
                val str = code + number.text.toString()
                val currentTime: Date = Calendar.getInstance().time
                val dataModel = DataModel()
                dataModel.setNumbers(str)
                dataModel.setDates(currentTime.toString())
                data.add(dataModel)
                requireActivity().saveData()
                val preferences: SharedPreferences = requireActivity().getSharedPreferences("key", 0)
                val editor = preferences.edit()
                editor.putBoolean("bool", bool)
                editor.apply()
                Log.d("DATE1", "Number: $currentTime")
                Log.d("NUMS1", "Number: $data")
                supportChat(message.text.toString(), str)

            }
        }

        clear.setOnClickListener {
            number.text = null
            message.text = null
        }



    }



    private fun supportChat(msg: String, numb: String) {
        Log.d("NUM2", "number = $numb")
        Log.d("NUM2", "Message = $msg")
        val length = numb.length
        Log.d("NUM2", "number = $length")
        if(length < 15)
        {
            try {

                activity?.packageManager?.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$numb?text=$msg"))
                intent.setPackage("com.whatsapp")
                startActivity(intent)

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Please Install WhatsApp", Toast.LENGTH_SHORT).show()
            }


        }


    }

    /*private fun loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        Log.d("LOAD1", "loadData: Load Running")
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("shared preferences", MODE_PRIVATE)

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
            data = gson.fromJson(json,type)
        }


        // checking below if the array list is empty or not
    }

    private fun saveData() {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("shared preferences", MODE_PRIVATE)

        // creating a variable for editor to
        // store data in shared preferences.
        val editor = sharedPreferences.edit()

        // creating a new variable for gson.
        val gson = Gson()

        // getting data from gson and storing it in a string.
        val json: String = gson.toJson(data)

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("data", json)

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply()

        // after saving data we are displaying a toast message.
//        Toast.makeText(context, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
    }*/



}
