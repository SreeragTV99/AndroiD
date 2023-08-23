package com.example.android.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.model.Types

class AdapterType(private val typeList: List<Types>):

    RecyclerView.Adapter<AdapterType.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vehicle_type = view.findViewById<TextView>(R.id.TypeView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.typelist, parent, false)
        return MyViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val type = typeList[position]
        holder.vehicle_type.text = type.Name
    }

    override fun getItemCount(): Int {
        return typeList.size
    }
}
