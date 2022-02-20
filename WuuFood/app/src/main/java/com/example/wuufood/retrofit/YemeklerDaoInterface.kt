package com.example.wuufood.retrofit

import com.example.wuufood.model.CrudCevap
import com.example.wuufood.model.SepetYemeklerCevap
import com.example.wuufood.model.YemeklerCevap
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDaoInterface {
    @GET("yemekler/tumYemekleriGetir.php")

    fun tumYemekler(): Call<YemeklerCevap>


    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded

    fun sepeteYemekEkle(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): Call<CrudCevap>


    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded

    fun tumSepetGetir(
        @Field("kullanici_adi") kullanici_adi: String
    ): Call<SepetYemeklerCevap>


    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun yemekSil(@Field("sepet_yemek_id") sepet_yemek_id:Int,@Field("kullanici_adi") kullanici_adi: String): Call<CrudCevap>


}