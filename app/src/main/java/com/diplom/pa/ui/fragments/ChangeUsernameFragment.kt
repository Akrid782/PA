package com.diplom.pa.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.diplom.pa.MainActivity
import com.diplom.pa.R
import com.diplom.pa.utility.*
import kotlinx.android.synthetic.main.fragment_change_username.*
import java.util.*

class ChangeUsernameFragment : BaseFragment(R.layout.fragment_change_username) {

    private lateinit var mUsername: String

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        settings_change_userName.setText(USER.username)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.setting_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> change()
        }
        return true
    }

    private fun change() {
        mUsername = settings_change_userName.text.toString().toLowerCase(Locale.getDefault())
        if (mUsername.isEmpty()) {
            showToast("Поле пустное")
        } else {
            changeUsername()
        }
    }

    private fun changeUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mUsername).setValue(UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername()
                }
            }
    }

    private fun updateCurrentUsername() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_USERNAME).setValue(mUsername)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("Данные обновленны")
                    deleteOldUsername()
                }
            }
    }

    private fun deleteOldUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    USER.username = mUsername
                    fragmentManager?.popBackStack()
                }
            }
    }

}
