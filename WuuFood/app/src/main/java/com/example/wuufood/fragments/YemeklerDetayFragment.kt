
package com.example.wuufood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wuufood.R
import com.example.wuufood.databinding.FragmentYemeklerDetayBinding
import com.example.wuufood.viewmodel.YemeklerDetayFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


class YemeklerDetayFragment : Fragment() {

    private lateinit var tasarim: FragmentYemeklerDetayBinding
    private lateinit var viewModel : YemeklerDetayFragmentViewModel
    var sayac : Int = 0
    var toplam : Int = 0
    var gelenSayi : Int = 0
    val user = FirebaseAuth.getInstance().currentUser
    val emailgelen = user?.email





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_yemekler_detay,container,false)
        tasarim.toolbarDetayBaslik = "WuuFood"
        tasarim.fragmentYemekDetay = this

        tasarim.kullaniciadi=(emailgelen.toString())

        val bundle: YemeklerDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemekArg
        tasarim.yemekNesnesi=gelenYemek


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Picasso.get().load(url).into(tasarim.imageViewDetayResim)
        gelenSayi=gelenYemek.yemek_fiyat

        tasarim.imageView.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_yemeklerDetayFragment_to_sepetFragment)

        }
        val callback = object : OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_yemeklerDetayFragment_to_yemeklerFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : YemeklerDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun sayacEkle(){
        sayac++
        if(sayac>0){
            toplam = sayac*gelenSayi
            tasarim.textViewToplamFiyat.setText(toplam.toString())
            tasarim.textSayac.setText(sayac.toString())
        }else if (sayac<0){
            sayac = 0
            toplam = 0
            tasarim.textSayac.setText(sayac.toString())
            tasarim.textViewToplamFiyat.setText(toplam.toString())
            }
        else if(sayac==0){
            toplam = 0
            tasarim.textSayac.setText(sayac.toString())
            tasarim.textViewToplamFiyat.setText(toplam.toString())}
    }
    fun sayacAzalt(){
        sayac--

        if(sayac>0){
            toplam = sayac*gelenSayi
            tasarim.textViewToplamFiyat.setText(toplam.toString())
            tasarim.textSayac.setText(sayac.toString())
        }else if (sayac<0){
            sayac = 0
            toplam = 0
            tasarim.textSayac.setText(sayac.toString())
            tasarim.textViewToplamFiyat.setText(toplam.toString())
        }
        else if(sayac==0){
            toplam = 0
            tasarim.textSayac.setText(sayac.toString())
            tasarim.textViewToplamFiyat.setText(toplam.toString())}

        }

    fun buttonSepeteYemekEkle( yemek_adi:String,
                               yemek_resim_adi:String,
                               yemek_fiyat:Int,
                               yemek_siparis_adet:Int,
                               kullanici_adi : String){

        viewModel.ekle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        Toast.makeText(requireContext(),"Sepetinize eklendi",Toast.LENGTH_SHORT).show()


    }




}


