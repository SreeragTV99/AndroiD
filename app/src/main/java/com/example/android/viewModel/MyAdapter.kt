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
        val countryTextView : TextView
        val commonNameTextView : TextView
        val linearLayoutView : LinearLayout
        init {
            view.apply {
                countryTextView = findViewById(R.id.CountryView)
                commonNameTextView = findViewById(R.id.CommonNameView)
                linearLayoutView = findViewById(R.id.linearvehicle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.carlist, parent, false)
        return MyViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val vehicleItem = vehiclesList[position]
        holder.apply {
            vehicleItem.apply {
                countryTextView.text =vehicleItem.Country
                commonNameTextView.text = Mfr_CommonName

            }
            linearLayoutView.setOnClickListener{
                listener.onItemClick(vehicleItem)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(clickedItem: Vehicles)
    }

    override fun getItemCount(): Int {
        return vehiclesList.size
    }
}