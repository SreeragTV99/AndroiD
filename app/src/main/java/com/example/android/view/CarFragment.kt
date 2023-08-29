package com.example.android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.Constants.Constants
import com.example.android.R
import com.example.android.model.Vehicles
import com.example.android.viewModel.AdapterType

class CarFragment : Fragment() {

    lateinit var countryName: TextView
    lateinit var commonName: TextView
    lateinit var vehicleId: TextView
    lateinit var name: TextView
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_car, container, false)
            with(inflatedView){
                countryName = findViewById(R.id.CountryView)
                commonName = findViewById(R.id.CommonNameView)
                vehicleId = findViewById(R.id.idView)
                name = findViewById(R.id.NameView)
                recyclerView=findViewById(R.id.recyclerViewType)
        }
        return inflatedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Vehicles>(Constants.COUNTRY_KEY)?.let { recievedData ->
            commonName.text = recievedData.Mfr_CommonName
            name.text = recievedData.Mfr_Name
            countryName.text = recievedData.Country
            vehicleId.text = recievedData.Mfr_ID.toString()

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val adapter = recievedData?.VehicleTypes?.let { AdapterType(it) }
            recyclerView.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CarFragment()
    }
}