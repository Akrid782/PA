package com.diplom.pa.ui.fragments.register

import android.app.Activity
import androidx.fragment.app.Fragment
import com.diplom.pa.R
import com.diplom.pa.database.*
import com.diplom.pa.utility.AppValueEventListener
import com.diplom.pa.utility.restartActivity
import com.diplom.pa.utility.showToast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_enter.*

class EnterFragment : Fragment(R.layout.fragment_enter) {

    private lateinit var mEmail: String
    private lateinit var mPassword: String

    override fun onStart() {
        super.onStart()
        AUTH = FirebaseAuth.getInstance()
        register_btn_enter.setOnClickListener { sendCode() }
        register_text_enter_email.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) register_text_enter_email_body.cardElevation = 12F
            else register_text_enter_email_body.cardElevation = 0F
        }
        register_text_enter_password.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) register_text_enter_password_body.cardElevation = 12F
            else register_text_enter_password_body.cardElevation = 0F
        }
        register_btn_enter.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) register_btn_enter.elevation = 12F
            else register_btn_enter.elevation = 0F
        }
    }


    private fun sendCode() {
        if (register_text_enter_email.text.toString().isEmpty() &&
            register_text_enter_password.text.toString().isEmpty()
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
                    initUser {
                        val phone = USERModel.phone
                        val dateMap = mutableMapOf<String, Any>()

                        dateMap[CHILD_ID] = CURRENT_ID
                        dateMap[CHILD_EMAIL] = mEmail

                        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_ID)
                            .addListenerForSingleValueEvent(AppValueEventListener {

                                if (!it.hasChild(CHILD_USERNAME)) {
                                    dateMap[CHILD_USERNAME] = CURRENT_ID
                                }

                                REF_DATABASE_ROOT.child(NODE_PHONES).child(phone)
                                    .setValue(CURRENT_ID)
                                    .addOnFailureListener {}
                                    .addOnSuccessListener {
                                        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_ID)
                                            .updateChildren(dateMap)
                                            .addOnSuccessListener {
                                                showToast("Добро пожаловать")
                                                restartActivity()
                                            }
                                    }
                            })

                    }
                } else showToast(task.exception?.message.toString())
            }
    }
}
