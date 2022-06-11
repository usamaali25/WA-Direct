package com.example.wadirect

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.wadirect.databinding.ActivityRecyclerviewBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RecyclerAdapter( private val data: MutableList<DataModel>, val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ActivityRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.number.text = data[position].number
        holder.binding.date.text =  data[position].date
        holder.binding.del.setOnClickListener {
            data.removeAt(position)
            context.saveData()
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, data.size)
        }
        holder.binding.send.setOnClickListener {
            data[position].number?.let { it1 -> supportChat(it1) }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(val binding: ActivityRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

    private fun supportChat( numb: String) {
        Log.d("NUM2", "number = $numb")
        try {
            context.packageManager?.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$numb?text="))
            intent.setPackage("com.whatsapp")
            context.startActivity(intent)

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Please Install WhatsApp", Toast.LENGTH_SHORT).show()
        }





    }

}


