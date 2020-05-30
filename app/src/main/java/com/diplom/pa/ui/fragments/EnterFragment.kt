package com.diplom.pa.ui.fragments

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.diplom.pa.MainActivity
import com.diplom.pa.R
import com.diplom.pa.utility.showToast
import kotlinx.android.synthetic.main.fragment_enter.*

class EnterFragment : Fragment(R.layout.fragment_enter) {

    override fun onStart() {
        super.onStart()
        register_btn.setOnClickListener { sendCode() }
    }

    private fun sendCode () {
        if(username.text.toString().isEmpty() && password.text.toString().isEmpty()) {
            showToast("Введите логин и пароль")
        }else{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
