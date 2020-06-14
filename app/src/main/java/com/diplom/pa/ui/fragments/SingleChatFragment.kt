package com.diplom.pa.ui.fragments

import com.diplom.pa.R
import com.diplom.pa.models.CommonModel
import com.diplom.pa.utility.APP_ACTIVITY
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*

class SingleChatFragment(contact: CommonModel) : BaseFragment(R.layout.fragment_single_chat) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.mToolbar.toolbar_info.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        APP_ACTIVITY.mToolbar.toolbar_info.visibility = View.GONE
    }
}