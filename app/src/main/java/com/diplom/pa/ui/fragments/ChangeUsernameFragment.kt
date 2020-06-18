package com.diplom.pa.ui.fragments

import com.diplom.pa.R
import com.diplom.pa.database.USERModel
import com.diplom.pa.database.changeUsername
import com.diplom.pa.utility.showToast
import kotlinx.android.synthetic.main.fragment_change_username.*
import java.util.*

class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_username) {

    private lateinit var mUsername: String

    override fun onResume() {
        super.onResume()
        settings_change_userName.setText(USERModel.username)
    }

    override fun change() {
        mUsername = settings_change_userName.text.toString().toLowerCase(Locale.getDefault())
        if (mUsername.isEmpty()) {
            showToast("Поле пустное")
        } else {
            changeUsername(mUsername)
        }
    }

}
