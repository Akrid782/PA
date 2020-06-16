package com.diplom.pa.ui.fragments

import com.diplom.pa.R
import com.diplom.pa.utility.*
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
            changeUsername()
        }
    }

    private fun changeUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mUsername).setValue(CURRENT_ID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername()
                }
            }
    }

    private fun updateCurrentUsername() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_ID).child(CHILD_USERNAME).setValue(mUsername)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("Данные обновленны")
                    deleteOldUsername()
                }
            }
    }

    private fun deleteOldUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USERModel.username).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    USERModel.username = mUsername
                    fragmentManager?.popBackStack()
                }
            }
    }

}
