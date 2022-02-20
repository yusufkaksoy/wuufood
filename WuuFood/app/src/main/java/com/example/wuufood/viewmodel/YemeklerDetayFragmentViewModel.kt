package com.example.wuufood.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wuufood.repo.YemeklerDaoRepository

class YemeklerDetayFragmentViewModel : ViewModel() {
    val yemekRepo = YemeklerDaoRepository()

    fun ekle(yemek_adi:String,
             yemek_resim_adi:String,
             yemek_fiyat:Int,
             yemek_siparis_adet:Int,
             kullanici_adi : String){

        yemekRepo.yemekSepetKayit(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }
}