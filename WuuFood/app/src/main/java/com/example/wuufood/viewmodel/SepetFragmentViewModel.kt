package com.example.wuufood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wuufood.model.SepetYemekler
import com.example.wuufood.repo.YemeklerDaoRepository
import com.google.firebase.auth.FirebaseAuth

class SepetFragmentViewModel : ViewModel() {
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()
    var yemekRepo = YemeklerDaoRepository()
    val user = FirebaseAuth.getInstance().currentUser
    val emailgelen = user?.email

    init {
        sepetYemekleriYukle()
        sepetYemeklerListesi = yemekRepo.sepetiGetir()

    }

    fun sepetYemekleriYukle(){

        yemekRepo.sepetYemekAl("${emailgelen.toString()}")
    }

    fun sil(sepet_yemek_id:Int,kullanici_adi:String)
    {
        yemekRepo.sepetYemekSil(sepet_yemek_id,"${emailgelen.toString()}")

    }
}