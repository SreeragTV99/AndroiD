package com.example.android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.model.Vehicles
import com.example.android.viewModel.AdapterType

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CarFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var country_name: TextView
    lateinit var common_name: TextView
    lateinit var ID: TextView
    lateinit var name: TextView
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView =  inflater.inflate(R.layout.fragment_car, container, false)
            with(inflatedView){
                country_name = findViewById(R.id.CountryView)
                common_name = findViewById(R.id.CommonNameView)
                ID = findViewById(R.id.idView)
                name = findViewById(R.id.NameView)
                recyclerView=findViewById(R.id.recyclerViewType)
        }
        return inflatedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recievedData = arguments?.getParcelable<Vehicles>("country")
        common_name.text = recievedData?.Mfr_CommonName
        name.text = recievedData?.Mfr_Name
        country_name.text = recievedData?.Country
        ID.text = recievedData?.Mfr_ID.toString()

        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        val adapter= recievedData?.VehicleTypes?.let { AdapterType(it) }
        recyclerView.adapter=adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}