package com.diplom.pa.ui.fragments

import com.diplom.pa.R
import com.diplom.pa.utility.APP_ACTIVITY

class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Контакты"
    }

}