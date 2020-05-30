package com.diplom.pa.ui.fragments

import android.app.Activity
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
        enter_btn.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (enter_email.text.toString().isEmpty() && enter_password.text.toString().isEmpty()) {
            showToast("Введите логин и пароль")
        } else {
            authUser()
        }
    }

    private fun authUser() {
        mEmail = enter_email.text.toString()
        mPassword = enter_password.text.toString()
        AUTH.signInWithEmailAndPassword(mEmail, mPassword)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    val uid = AUTH.currentUser?.uid.toString()
                    val dateMap = mutableMapOf<String, Any>()
                    dateMap[CHILD_ID] = uid
                    dateMap[CHILD_EMAIL] = mEmail
                    dateMap[CHILD_USERNAME] = "Иванов Николай"

                    REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                showToast("Добро пожаловать")
                                (activity as RegisterActivity).replaceActivity(MainActivity())
                            } else showToast(it.exception?.message.toString())
                        }
                } else showToast(task.exception?.message.toString())
            }
    }
}
