package com.example.wuufood.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SepetYemeklerCevap(@SerializedName("sepet_yemekler") @Expose var sepetyemekler:List<SepetYemekler>,
                         @SerializedName("success") @Expose var success : Int){}