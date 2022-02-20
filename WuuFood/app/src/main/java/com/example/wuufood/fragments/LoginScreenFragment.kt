package com.example.wuufood.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.wuufood.R
import com.example.wuufood.databinding.FragmentLoginScreenBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.tasks.OnCompleteListener


class LoginScreenFragment : Fragment() {
    private lateinit var tasarim: FragmentLoginScreenBinding


    private companion object{
        private const val  RC_SIGN_IN = 100
        private const val  TAG = "GOOGLE_SIGN_IN_TAG"
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = FragmentLoginScreenBinding.inflate(inflater, container, false)

        tasarim.textViewUyeDegilmisiniz.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.giristenKayita)
        }


        tasarim.buttonLogin.setOnClickListener {

            when {
                TextUtils.isEmpty(
                    tasarim.editTextLoginEmail.text.toString().trim { it <= ' ' }) -> {

                    Toast.makeText(
                        requireContext(),
                        "Lütfen E-mail girin.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(
                    tasarim.editTextLoginPassword.text.toString().trim { it <= ' ' }) -> {

                    Toast.makeText(
                        requireContext(),
                        "Lütfen şifrenizi girin.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String =
                        tasarim.editTextLoginEmail.text.toString().trim { it <= ' ' }
                    val password: String =
                        tasarim.editTextLoginPassword.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener
                            { task ->
                                if (task.isSuccessful) {

                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        requireContext(),
                                        "Giriş Başarıyla Yapıldı",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val user = FirebaseAuth.getInstance().currentUser
                                    val names = user?.displayName
                                    val emailgelen = user?.email
                                    val photoUrl = user?.photoUrl
                                    val emailVerified = user?.isEmailVerified
                                    val uid = user?.uid

                                    Navigation.findNavController(it).navigate(R.id.giristenAnasayfa)
                                } else {
                                    Toast.makeText(
                                        requireContext(), task.exception!!.message.toString(),
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


