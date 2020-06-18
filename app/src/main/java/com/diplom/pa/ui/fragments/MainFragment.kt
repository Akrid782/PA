package com.diplom.pa.ui.fragments

import androidx.fragment.app.Fragment
import com.diplom.pa.R
import com.diplom.pa.utility.APP_ACTIVITY

class MainFragment : Fragment(R.layout.fragment_work) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Расписание работ"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
    }

}
