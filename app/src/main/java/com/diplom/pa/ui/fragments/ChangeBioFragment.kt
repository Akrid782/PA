package com.diplom.pa.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.diplom.pa.MainActivity
import com.diplom.pa.R
import com.diplom.pa.utility.*
import kotlinx.android.synthetic.main.fragment_change_bio.*


class ChangeBioFragment : BaseFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        settings_change_bio.setText(USER.bio)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.setting_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> changeBio()
        }
        return true
    }

    private fun changeBio() {
        val bio = settings_change_bio.text.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_BIO)
            .setValue(bio).addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("Данные обновленны")
                    USER.bio = bio
                    fragmentManager?.popBackStack()
                }
            }
    }

}
