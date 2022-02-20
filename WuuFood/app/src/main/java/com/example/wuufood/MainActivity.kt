package com.example.wuufood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.wuufood.databinding.ActivityMainBinding
import com.example.wuufood.fragments.SepetFragment
import com.example.wuufood.fragments.YemeklerFragment
import com.example.wuufood.model.Yemekler
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var  tasarim : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tasarim.root)
        val nav_host_fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(tasarim.botomNavigationView,nav_host_fragment.navController)
        val navController = this.findNavController(R.id.fragmentContainerView)
        navController.addOnDestinationChangedListener{_,nd:NavDestination,_->
            if (nd.id==R.id.yemeklerFragment||nd.id==R.id.sepetFragment||nd.id==R.id.profilFragment){
                tasarim.botomNavigationView.visibility= View.VISIBLE
            }else{

                tasarim.botomNavigationView.visibility= View.INVISIBLE

            }


        }



    }



}