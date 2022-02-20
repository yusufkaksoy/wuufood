package com.example.wuufood.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.wuufood.model.*
import com.example.wuufood.retrofit.ApiUtils
import com.example.wuufood.retrofit.YemeklerDaoInterface
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository {
    var yemeklerListesi: MutableLiveData<List<Yemekler>>
    var yemekDao: YemeklerDaoInterface
    var sepetYemeklerListesi : MutableLiveData<List<SepetYemekler>>
    val user = FirebaseAuth.getInstance().currentUser
    val emailgelen = user?.email

    init {
        yemekDao = ApiUtils.getYemeklerDaoInterface()
        yemeklerListesi = MutableLiveData()
        sepetYemeklerListesi = MutableLiveData()

    }
    fun sepetiGetir(): MutableLiveData<List<SepetYemekler>>{

        return  sepetYemeklerListesi
    }


    fun yemekleriGetir(): MutableLiveData<List<Yemekler>> {

        return yemeklerListesi
    }
    fun yemekSepetKayit(yemek_adi:String,
                        yemek_resim_adi:String,
                        yemek_fiyat:Int,
                        yemek_siparis_adet:Int,
                        kullanici_adi : String

    ){
        yemekDao.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(object : Callback<CrudCevap>{
            override fun onResponse(call: Call<CrudCevap>?, response: Response<CrudCevap>?) {
                Log.e("Retrofit Basarılı",response.toString())
            }
            override fun onFailure(call: Call<CrudCevap>?, t: Throwable?) {
                Log.e("Retrofit Başarısız",t.toString())
            }
        })
    }

    fun sepetYemekAl(kullanici_adi:String){
        yemekDao.tumSepetGetir(kullanici_adi).enqueue(object : retrofit2.Callback<SepetYemeklerCevap>{
            override fun onResponse(call: Call<SepetYemeklerCevap>, response: Response<SepetYemeklerCevap>) {
                val liste = response.body().sepetyemekler
                sepetYemeklerListesi.value= liste
            }

            override fun onFailure(call: Call<SepetYemeklerCevap>, t: Throwable) {}


        })

    }
    fun sepetYemekSil(sepet_yemek_id:Int,kullanici_adi: String){

        yemekDao.yemekSil(sepet_yemek_id, kullanici_adi).enqueue(object : retrofit2.Callback<CrudCevap>{
            override fun onFailure(call: Call<CrudCevap>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<CrudCevap>?, response: Response<CrudCevap>?) {
                sepetYemekAl("${emailgelen.toString()}")
            }


        })

    }





    fun tumYemekleriAl() {
        yemekDao.tumYemekler().enqueue(object : retrofit2.Callback<YemeklerCevap> {

            override fun onResponse(call: Call<YemeklerCevap>, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemeklerListesi.value= liste
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })

    }
}