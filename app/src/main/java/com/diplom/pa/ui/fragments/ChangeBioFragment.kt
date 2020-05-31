package com.diplom.pa.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.diplom.pa.MainActivity
import com.diplom.pa.R
import com.diplom.pa.utility.*
import kotlinx.android.synthetic.main.fragment_change_bio.*


class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_change_bio.setText(USER.bio)
    }

    override fun change() {
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
