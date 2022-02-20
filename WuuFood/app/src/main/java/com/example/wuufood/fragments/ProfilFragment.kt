package com.example.wuufood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.wuufood.databinding.FragmentProfilBinding
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wuufood.R
import com.google.firebase.auth.FirebaseAuth

class ProfilFragment : Fragment() {

    private lateinit var tasarim : FragmentProfilBinding

    val user = FirebaseAuth.getInstance().currentUser
    val emailgelen = user?.email




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = FragmentProfilBinding.inflate(inflater,container,false)


       tasarim.textViewProfilEmail.setText(emailgelen.toString())

        tasarim.buttonLogout.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            Navigation.findNavController(it).navigate(R.id.profildenLogine)


        }
        val callback = object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.profildenYemeklere)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return tasarim.root



    }




}
