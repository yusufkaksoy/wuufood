package com.example.wuufood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.wuufood.databinding.SepetCardBinding
import com.example.wuufood.model.SepetYemekler
import com.example.wuufood.viewmodel.SepetFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import okio.Utf8.size
import java.nio.file.Files.size

class SepetYemeklerAdapter(var mContext:Context,var sepetYemeklerListesi:List<SepetYemekler>,var viewModel: SepetFragmentViewModel)
    : RecyclerView.Adapter<SepetYemeklerAdapter.SepetCardTutucu>(){


        inner class  SepetCardTutucu(sepetCardBinding: SepetCardBinding) : RecyclerView.ViewHolder(sepetCardBinding.root){

            var sepetCardBinding:SepetCardBinding
            init {
                this.sepetCardBinding = sepetCardBinding
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = SepetCardBinding.inflate(layoutInflater,parent,false)
        return SepetCardTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: SepetCardTutucu, position: Int) {
        val sepetYemek = sepetYemeklerListesi.get(position)
        val t = holder.sepetCardBinding
        t.sepetNesnesi = sepetYemek



        val url =  "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"
        Picasso.get().load(url).into(t.imageViewSepetResim)

        t.imageViewSilResim.setOnClickListener {
            Snackbar.make(it,"${sepetYemek.yemek_adi} Silinsin mi?",Snackbar.LENGTH_LONG)
                .setAction("Evet"){
                    viewModel.sil(sepetYemek.sepet_yemek_id,sepetYemek.kullanici_adi)
                }.show()

        }
    }

    override fun getItemCount(): Int {
        return  sepetYemeklerListesi.size
    }
}