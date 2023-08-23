package com.example.android.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.model.Vehicles

class MyAdapter(private val VehicleList: List<Vehicles>, val Listner:OnItemClickListener):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val country = view.findViewById<TextView>(R.id.CountryView)
        val common_name = view.findViewById<TextView>(R.id.CommonNameView)
        val linear = view.findViewById<LinearLayout>(R.id.linearvehicle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.carlist, parent, false)
        return MyViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val vehicleItem = VehicleList[position]
        holder.country.text = vehicleItem.Country
        holder.common_name.text = vehicleItem.Mfr_CommonName
        holder.linear.setOnClickListener{
            Listner.onItemClick(vehicleItem)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Vehicles)
    }

    override fun getItemCount(): Int {
        return VehicleList.size
    }

}