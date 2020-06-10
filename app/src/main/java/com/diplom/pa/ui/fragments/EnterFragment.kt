package com.diplom.pa.ui.fragments

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.diplom.pa.MainActivity
import com.diplom.pa.R
import com.diplom.pa.activity.RegisterActivity
import com.diplom.pa.utility.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_enter.*

class EnterFragment : Fragment(R.layout.fragment_enter) {

    private lateinit var mEmail: String
    private lateinit var mPassword: String

    override fun onStart() {
        super.onStart()
        AUTH = FirebaseAuth.getInstance()
        register_btn_enter.setOnClickListener { sendCode() }
        register_text_enter_email.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) register_text_enter_email_body.cardElevation = 12F
            else register_text_enter_email_body.cardElevation = 0F
        }
        register_text_enter_password.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) register_text_enter_password_body.cardElevation = 12F
            else register_text_enter_password_body.cardElevation = 0F
        }
        register_btn_enter.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) register_btn_enter.elevation = 12F
            else register_btn_enter.elevation = 0F
        }
    }


    private fun sendCode() {
        if (register_text_enter_email.text.toString()
                .isEmpty() && register_text_enter_password.text.toString().isEmpty()
        ) {
            showToast("Введите логин и пароль")
        } else {
            authUser()
        }
    }

    private fun authUser() {
        mEmail = register_text_enter_email.text.toString()
        mPassword = register_text_enter_password.text.toString()
        AUTH.signInWithEmailAndPassword(mEmail, mPassword)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    CURRENT_ID = AUTH.currentUser?.uid.toString()
                    initUser{
                        val uid = AUTH.currentUser?.uid.toString()
                        val username = USER.fullname
                        val phone = USER.phone
                        val dateMap = mutableMapOf<String, Any>()

                        dateMap[CHILD_ID] = uid
                        dateMap[CHILD_EMAIL] = mEmail
                        dateMap[CHILD_USERNAME] = username

                        REF_DATABASE_ROOT.child(NODE_PHONES).child(phone).setValue(uid)
                            .addOnFailureListener {}
                            .addOnSuccessListener {
                                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                                    .addOnSuccessListener {
                                        showToast("Добро пожаловать")
                                        (activity as RegisterActivity).replaceActivity(MainActivity())
                                    }
                            }
                    }
                } else showToast(task.exception?.message.toString())
            }
    }
}
