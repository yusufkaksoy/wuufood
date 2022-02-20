package com.example.wuufood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wuufood.model.Yemekler
import com.example.wuufood.repo.YemeklerDaoRepository

class YemeklerFragmentViewModel : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    val yemekRepo = YemeklerDaoRepository()

    init {
        yemekleriYukle()
        yemeklerListesi = yemekRepo.yemekleriGetir()
    }

     fun yemekleriYukle() {
        yemekRepo.tumYemekleriAl()
    }
}