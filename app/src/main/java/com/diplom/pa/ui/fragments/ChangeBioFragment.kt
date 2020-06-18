package com.diplom.pa.ui.fragments

import com.diplom.pa.R
import com.diplom.pa.database.USERModel
import com.diplom.pa.database.setBioToDatabase
import kotlinx.android.synthetic.main.fragment_change_bio.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_change_bio.setText(USERModel.bio)
    }

    override fun change() {
        val bio = settings_change_bio.text.toString()
        setBioToDatabase(bio)
    }

}
