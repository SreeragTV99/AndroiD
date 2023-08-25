package com.example.android.view

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.Constants.Constants
import com.example.android.R
import com.example.android.model.Vehicles
import com.example.android.viewModel.MyAdapter

import com.example.android.viewModel.ViewModel1
import com.example.android.viewModel.ViewModelVehicle

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QuoteFragment : Fragment(),MyAdapter.OnItemClickListener{
    private lateinit var viewModel: ViewModel1
    private lateinit var viewModelV: ViewModelVehicle

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onItemClick(clickedItem: Vehicles){
        val detailsFragment = CarFragment.newInstance("","")
        var args = Bundle()
        args?.apply {
            putParcelable(Constants.COUNTRY_KEY,clickedItem)
        }
        detailsFragment.arguments = args
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container1, detailsFragment, "new")
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModel1::class.java)
        val quotesView:TextView = view.findViewById(R.id.quotesView)
        val refreshButton :ImageButton= view.findViewById(R.id.refreshButton)
        refreshButton.setOnClickListener{viewModel.getQuotesData()}
            viewModel.response.observe(viewLifecycleOwner){
                response->quotesView.text=response
            }

        viewModelV = ViewModelProvider(this).get(ViewModelVehicle::class.java)
        var recyclerview = view.findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerview.layoutManager = layoutManager
        viewModelV.getVehicleData()
        viewModelV.vehiclelist.observe(viewLifecycleOwner) { result ->
            var adapter = MyAdapter(result,this)
            recyclerview.adapter = adapter
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuoteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}