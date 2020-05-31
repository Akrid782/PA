package com.diplom.pa.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.diplom.pa.MainActivity
import com.diplom.pa.R
import com.diplom.pa.activity.RegisterActivity
import com.diplom.pa.utility.AUTH
import com.diplom.pa.utility.USER
import com.diplom.pa.utility.replaceActivity
import com.diplom.pa.utility.replaceFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        if (USER.bio.isNotEmpty()) settings_about.text = USER.bio
        settings_fullName.text = USER.fullname
        settings_number_phone.text = USER.phone
        settings_status.text = USER.status
        settings_userName.text = USER.username
        settings_btn_change_about.setOnClickListener { replaceFragment(ChangeBioFragment()) }
        settings_btn_change_userName.setOnClickListener { replaceFragment(ChangeUsernameFragment()) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(
            R.menu.settings_action_menu,
            menu
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> replaceFragment(ChangeNameFragment())
        }
        return true
    }
}