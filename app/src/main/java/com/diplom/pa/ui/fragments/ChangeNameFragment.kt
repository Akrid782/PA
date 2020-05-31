package com.diplom.pa.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.diplom.pa.MainActivity
import com.diplom.pa.R
import com.diplom.pa.utility.*
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeNameFragment : Fragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.setting_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> changeName()
        }
        return true
    }

    private fun changeName() {
        val firstName = settings_change_firstName.text.toString()
        val lastName = settings_change_lastName.text.toString()
        if (firstName.isEmpty()) {
            showToast("Имя не может быть пустым")
        } else {
            val fullName = "$firstName $lastName"
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_FULLNAME)
                .setValue(fullName).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast("Данные обновленны")
                        USER.fullname = fullName
                        fragmentManager?.popBackStack()
                    }
                }
        }

    }

}
