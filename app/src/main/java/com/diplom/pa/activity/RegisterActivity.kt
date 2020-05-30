package com.diplom.pa.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diplom.pa.databinding.ActivityRegisterBinding
import com.diplom.pa.ui.fragments.EnterFragment
import com.diplom.pa.utility.initFirebase
import com.diplom.pa.utility.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        replaceFragment(EnterFragment(), false)
    }
}
