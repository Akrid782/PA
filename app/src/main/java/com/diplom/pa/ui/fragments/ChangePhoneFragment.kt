package com.diplom.pa.ui.fragments

import com.diplom.pa.R
import com.diplom.pa.database.USERModel
import com.diplom.pa.database.setPhoneToDatabase
import kotlinx.android.synthetic.main.fragment_change_phone.*
import kotlinx.android.synthetic.main.fragment_change_username.*
import kotlinx.android.synthetic.main.fragment_enter.*

class ChangePhoneFragment : BaseChangeFragment(R.layout.fragment_change_phone) {

    override fun onResume() {
        super.onResume()
        settings_change_phone.setText(USERModel.phone)
        settings_change_phone.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) settings_change_phone_body.elevation = 12F
            else settings_change_phone_body.elevation = 0F
        }
    }

    override fun change() {
        val phone = settings_change_phone.text.toString()
        setPhoneToDatabase(phone)
    }

}
