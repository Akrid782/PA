package com.diplom.pa.ui.fragments

import android.view.View
import com.diplom.pa.R
import com.diplom.pa.models.CommonModel
import com.diplom.pa.models.User
import com.diplom.pa.utility.*
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.toolbar_info.view.*

class SingleChatFragment(private val contact: CommonModel) :
    BaseFragment(R.layout.fragment_single_chat) {

    private lateinit var mListenerInfoToolbar: AppValueEventListener
    private lateinit var mReceivingUser: User
    private var mToolbarInfo: View = APP_ACTIVITY.mToolbar.toolbar_info
    private lateinit var mRefUser: DatabaseReference

    override fun onResume() {
        super.onResume()
        mToolbarInfo.visibility = View.VISIBLE
        mListenerInfoToolbar = AppValueEventListener {
            mReceivingUser = it.getUserModel()
            initInfoToolbar()
        }
        mRefUser = REF_DATABASE_ROOT.child(NODE_USERS).child(contact.id)
        mRefUser.addValueEventListener(mListenerInfoToolbar)
    }

    private fun initInfoToolbar() {
        if (mReceivingUser.fullname.isEmpty()) {
            mToolbarInfo.toolbar_contact_chat_status.text = contact.state
        } else mToolbarInfo.toolbar_contact_chat_status.text = mReceivingUser.state
        mToolbarInfo.toolbar_chat_image.downloadAndSetImage(mReceivingUser.photoUrl)
        mToolbarInfo.toolbar_contact_chat_fullname.text = mReceivingUser.fullname
    }

    override fun onPause() {
        super.onPause()
        mToolbarInfo.visibility = View.GONE
        mRefUser.removeEventListener(mListenerInfoToolbar)
    }
}