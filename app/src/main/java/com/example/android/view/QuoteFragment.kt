package com.example.android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.Constants.Constants
import com.example.android.R
import com.example.android.model.Vehicles
import com.example.android.viewModel.MyAdapter
import com.example.android.viewModel.ViewModelQuotes
import com.example.android.viewModel.ViewModelVehicle

class QuoteFragment : Fragment(),MyAdapter.OnItemClickListener{
    private lateinit var viewModelQuotes: ViewModelQuotes
    private lateinit var viewModelV: ViewModelVehicle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onItemClick(clickedItem: Vehicles){
        val detailsFragment = CarFragment.newInstance()
        val args = Bundle()
        args.apply {
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
        viewModelQuotes = ViewModelProvider(this).get(ViewModelQuotes::class.java)
        viewModelQuotes.getQuotesData()
        view.apply {
            val quotesView: TextView = findViewById(R.id.quotesView)
            val refreshButton: ImageButton = findViewById(R.id.refreshButton)
            refreshButton.setOnClickListener { viewModelQuotes.getQuotesData() }
            viewModelQuotes.response.observe(viewLifecycleOwner) { response ->
                quotesView.text = response
            }

            viewModelV = ViewModelProvider(this@QuoteFragment).get(ViewModelVehicle::class.java)
            val recyclerview: RecyclerView = findViewById(R.id.recyclerView)
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerview.layoutManager = layoutManager
            viewModelV.getVehicleData()
            viewModelV.vehicleList.observe(viewLifecycleOwner) { result ->
                val adapter = MyAdapter(result, this@QuoteFragment)
                recyclerview.adapter = adapter
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuoteFragment()
    }
}