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
import com.example.android.R
import com.example.android.model.Vehicles
import com.example.android.viewModel.MyAdapter

import com.example.android.viewModel.ViewModel1
import com.example.android.viewModel.ViewModelVehicle

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuoteFragment : Fragment(),MyAdapter.OnItemClickListener{
    private lateinit var viewModel: ViewModel1
    private lateinit var viewModelV: ViewModelVehicle
    // TODO: Rename and change types of parameters
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
            putParcelable("country",clickedItem)
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModel1::class.java)
        val quotesView:TextView = view.findViewById(R.id.quotesView)
        val refreshButton :ImageButton= view.findViewById(R.id.refreshButton)
        refreshButton.setOnClickListener{viewModel.getQuotesData()}
            viewModel.response.observe(viewLifecycleOwner){
                response->quotesView.text=response[0]
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuoteFragment.
         */
        // TODO: Rename and change types and number of parameters
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