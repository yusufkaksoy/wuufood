package com.example.wuufood.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CrudCevap(@SerializedName("success") @Expose var success:Int,
                     @SerializedName("message") @Expose var message : String
) {}