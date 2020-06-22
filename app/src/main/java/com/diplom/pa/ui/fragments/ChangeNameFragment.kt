package com.diplom.pa.ui.fragments

import com.diplom.pa.R
import com.diplom.pa.database.USERModel
import com.diplom.pa.database.setFullNameToDatabase
import com.diplom.pa.utility.showToast
import kotlinx.android.synthetic.main.fragment_change_name.*
import kotlinx.android.synthetic.main.fragment_change_phone.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()
        initUsername()
        settings_change_firstName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) settings_change_firstName_body.elevation = 12F
            else settings_change_firstName_body.elevation = 0F
        }
        settings_change_lastName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) settings_change_lastName_body.elevation = 12F
            else settings_change_lastName_body.elevation = 0F
        }
    }

    private fun initUsername() {
        val fullNameList = USERModel.fullname.split(" ")
        settings_change_firstName.setText(fullNameList[0])
        if (fullNameList.size > 1) settings_change_lastName.setText(fullNameList[1])
    }

    override fun change() {
        val firstName = settings_change_firstName.text.toString()
        val lastName = settings_change_lastName.text.toString()
        if (firstName.isEmpty()) {
            showToast("Имя не может быть пустым")
        } else {
            val fullName = "$firstName $lastName"
            setFullNameToDatabase(fullName)
        }
    }

}
