package com.example.wuufood.fragments

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.wuufood.R
import com.example.wuufood.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SignUpFragment : Fragment() {

    private lateinit var tasarim: FragmentSignUpBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        tasarim = FragmentSignUpBinding.inflate(inflater, container, false)

        tasarim.textViewZatenUyemisiniz.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.kayittanGirise)


        }


        tasarim.buttonSignUp.setOnClickListener {

            when {
                TextUtils.isEmpty(
                    tasarim.editTextSingUpEmail.text.toString().trim { it <= ' ' }) -> {

                    Toast.makeText(
                        requireContext(),
                        "Lütfen E-mail girin.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(
                    tasarim.editTextSignUpPassword.text.toString().trim { it <= ' ' }) -> {

                    Toast.makeText(
                        requireContext(),
                        "Lütfen şifrenizi girin.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String =
                        tasarim.editTextSingUpEmail.text.toString().trim { it <= ' ' }
                    val password: String =
                        tasarim.editTextSignUpPassword.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener
                            { task ->
                                if (task.isSuccessful) {

                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        requireContext(),
                                        "Kayıt işlemi başarıyla gerçekleşti.",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()



                                    Navigation.findNavController(it).navigate(R.id.kayittanGirise)
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }

                            }


                        )
                }

            }

        }




        return tasarim.root
    }


}