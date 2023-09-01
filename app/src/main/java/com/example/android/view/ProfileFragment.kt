package com.example.android.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.android.R
import com.example.android.model.LoginRes
import com.example.android.model.setImage
import com.google.gson.Gson

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var image:ImageView
    lateinit var uid:TextView
    lateinit var username:TextView
    lateinit var firstName: TextView
    lateinit var lastName:TextView
    lateinit var gender:TextView
    lateinit var email:TextView

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
        val inflatedView = inflater.inflate(R.layout.fragment_profile, container, false)
        with(inflatedView){
            image = findViewById(R.id.profileImage)
            uid = findViewById(R.id.idView)
            username = findViewById(R.id.usernameView)
            firstName = findViewById(R.id.firstnameView)
            lastName = findViewById(R.id.lastnameView)
            gender = findViewById(R.id.genderView)
            email = findViewById(R.id.emailView)
        }
        return inflatedView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = retriveSharedpreferencesData()
        if (user!=null){
            image.setImage(user.image)
            uid.text = user.id.toString()
            username.text = user.username
            firstName.text = user.firstName
            lastName.text =  user.lastName
            gender.text = user.gender
            email.text = user.email
        }
    }
    fun retriveSharedpreferencesData():LoginRes{
        val sharedpreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val jsonData = sharedpreferences.getString("shared_data", null)
        val userData= Gson().fromJson(jsonData, LoginRes::class.java)
        return userData
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}