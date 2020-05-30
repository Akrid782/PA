package com.diplom.pa.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import com.diplom.pa.R
import com.diplom.pa.ui.fragments.BaseFragment

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(
            R.menu.settings_action_menu,
            menu
        )
    }
}