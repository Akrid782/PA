package com.diplom.pa.ui.fragments

import com.diplom.pa.R
import com.diplom.pa.database.USERModel
import com.diplom.pa.database.setFullNameToDatabase
import com.diplom.pa.utility.showToast
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()
        initUsername()
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
