package com.example.wuufood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.wuufood.R
import com.example.wuufood.databinding.CardTasarimBinding
import com.example.wuufood.fragments.YemeklerFragmentDirections
import com.example.wuufood.model.Yemekler
import com.example.wuufood.viewmodel.YemeklerFragmentViewModel
import com.squareup.picasso.Picasso

class YemeklerAdapter(
    var mContext: Context,
    var yemeklerListesi: List<Yemekler>,
    var viewModel: YemeklerFragmentViewModel
) : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(cardTasarimBinding: CardTasarimBinding) :
        RecyclerView.ViewHolder(cardTasarimBinding.root) {

        var cardTasarimBinding: CardTasarimBinding

        init {
            this.cardTasarimBinding = cardTasarimBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim =CardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemekDetay = yemeklerListesi.get(position)
        val t = holder.cardTasarimBinding
        t.yemekNesnesi=yemekDetay


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemekDetay.yemek_resim_adi}"
        Picasso.get().load(url).into(t.imageViewYemekResim)

        t.satirCard.setOnClickListener{
            val gecis = YemeklerFragmentDirections.actionYemeklerFragmentToYemeklerDetayFragment(yemekDetay)
            Navigation.findNavController(it).navigate(gecis)
           // Navigation.findNavController(it).navigate(R.id.action_yemeklerFragment_to_yemeklerDetayFragment)

        }





    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size

    }


}