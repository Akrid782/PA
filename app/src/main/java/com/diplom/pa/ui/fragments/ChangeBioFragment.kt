package com.diplom.pa.ui.fragments

import com.diplom.pa.R
import com.diplom.pa.utility.*
import kotlinx.android.synthetic.main.fragment_change_bio.*


class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_change_bio.setText(USERModel.bio)
    }

    override fun change() {
        val bio = settings_change_bio.text.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_ID).child(CHILD_BIO)
            .setValue(bio).addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("Данные обновленны")
                    USERModel.bio = bio
                    fragmentManager?.popBackStack()
                }
            }
    }

}
