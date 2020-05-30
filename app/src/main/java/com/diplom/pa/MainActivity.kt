package com.diplom.pa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.diplom.pa.activity.RegisterActivity
import com.diplom.pa.databinding.ActivityMainBinding
import com.diplom.pa.ui.fragments.WorkFragment
import com.diplom.pa.ui.`object`.AppDrawer
import com.diplom.pa.utility.replaceActivity
import com.diplom.pa.utility.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
    }

    private fun initFunc() {
        if (false) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(WorkFragment())
        }else{
            replaceActivity(RegisterActivity())
        }
    }
}
