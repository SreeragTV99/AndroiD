package com.example.android.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.model.Vehicles

class MyAdapter(private val vehiclesList: List<Vehicles>, val listener:OnItemClickListener):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryTextView = view.findViewById<TextView>(R.id.CountryView)
        val commonNameTextView = view.findViewById<TextView>(R.id.CommonNameView)
        val linearLayoutView = view.findViewById<LinearLayout>(R.id.linearvehicle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.carlist, parent, false)
        return MyViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val vehicleItem = vehiclesList[position]
        holder.countryTextView.text = vehicleItem.Country
        holder.commonNameTextView.text = vehicleItem.Mfr_CommonName
        holder.linearLayoutView.setOnClickListener{
            listener.onItemClick(vehicleItem)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(item: Vehicles)
    }

    override fun getItemCount(): Int {
        return vehiclesList.size
    }
}