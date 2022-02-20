package com.example.wuufood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wuufood.R
import com.example.wuufood.adapter.YemeklerAdapter
import com.example.wuufood.databinding.FragmentYemeklerBinding
import com.example.wuufood.viewmodel.YemeklerFragmentViewModel
import com.google.firebase.auth.FirebaseAuth


class YemeklerFragment : Fragment() {
    private lateinit var tasarim : FragmentYemeklerBinding
    private lateinit var adapter: YemeklerAdapter
    private lateinit var viewModel : YemeklerFragmentViewModel




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_yemekler,container,false)
        tasarim.yemeklerToolbarBaslik="WuuFOOD"
        tasarim.yemeklerFragment=this

        viewModel.yemeklerListesi.observe(viewLifecycleOwner,{

            adapter = YemeklerAdapter(requireContext(),it,viewModel)
            tasarim.yemeklerAdapter=adapter


        })
        tasarim.rv.layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        tasarim.imageViewYemeklerCKSYap.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            Navigation.findNavController(it).navigate(R.id.action_yemeklerFragment_to_loginScreenFragment)
        }
        tasarim.imageViewYemeklerProfileGit.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.yemeklerdenProfile)
        }



        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : YemeklerFragmentViewModel by viewModels()
        viewModel = tempViewModel


    }







}