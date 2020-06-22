package com.diplom.pa.ui.fragments

import com.diplom.pa.R
import com.diplom.pa.database.USERModel
import com.diplom.pa.database.setBioToDatabase
import kotlinx.android.synthetic.main.fragment_change_bio.*
import kotlinx.android.synthetic.main.fragment_change_phone.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_change_bio.setText(USERModel.bio)
        settings_change_bio.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) settings_change_bio_body.elevation = 12F
            else settings_change_bio_body.elevation = 0F
        }
    }

    override fun change() {
        val bio = settings_change_bio.text.toString()
        setBioToDatabase(bio)
    }

}
