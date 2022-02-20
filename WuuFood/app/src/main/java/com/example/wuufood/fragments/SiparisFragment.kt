package com.example.wuufood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wuufood.R
import com.example.wuufood.databinding.FragmentSiparisBinding
import com.squareup.picasso.Picasso


class SiparisFragment : Fragment() {
    private lateinit var  tasarim : FragmentSiparisBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = FragmentSiparisBinding.inflate(inflater,container,false)

        Glide.with(this).load(R.drawable.siparisverildi).into(tasarim.imageViewSiparisGif)
        val callback = object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.siparistenYemeklere)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return tasarim.root
    }


}