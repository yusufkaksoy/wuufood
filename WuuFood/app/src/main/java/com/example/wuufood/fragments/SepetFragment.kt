package com.example.wuufood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wuufood.R
import com.example.wuufood.adapter.SepetYemeklerAdapter
import com.example.wuufood.databinding.FragmentSepetBinding
import com.example.wuufood.viewmodel.SepetFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class SepetFragment : Fragment() {
    private lateinit var tasarim : FragmentSepetBinding
    private lateinit var  adapter: SepetYemeklerAdapter
    private lateinit var viewModel : SepetFragmentViewModel
    var toplam : Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_sepet,container,false)
        tasarim.toolbarSepetBaslik="wuuFood"
        tasarim.sepetFragment = this

        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner,{

            adapter = SepetYemeklerAdapter(requireContext(),it,viewModel)
            tasarim.sepetYemekAdapter=adapter

            tasarim.buttonSiparisVer.setOnClickListener { Navigation.findNavController(it).navigate(R.id.sepettenSiparisEkranina) }



            val callback = object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.sepetFragmenttoYemeklerFragment)
                }
            }
            requireActivity().onBackPressedDispatcher.addCallback(callback)



        })


        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SepetFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetYemekleriYukle()
    }



}