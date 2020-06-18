package com.diplom.pa.ui.fragments

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.diplom.pa.R
import com.diplom.pa.database.*
import com.diplom.pa.utility.*
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        if (USERModel.bio.isNotEmpty()) settings_about.text = USERModel.bio
        settings_fullName.text = USERModel.fullname
        settings_number_phone.text = USERModel.phone
        settings_status.text = USERModel.state
        settings_userName.text = USERModel.username
        settings_btn_change_about.setOnClickListener { replaceFragment(ChangeBioFragment()) }
        settings_btn_change_userName.setOnClickListener { replaceFragment(ChangeUsernameFragment()) }
        settings_btn_change_number_phone.setOnClickListener { replaceFragment(ChangePhoneFragment()) }
        settings_btn_change_photo.setOnClickListener { changePhotoUser() }
        settings_profile_image.downloadAndSetImage(USERModel.photoUrl)
    }

    private fun changePhotoUser() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(APP_ACTIVITY, this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AppStates.updateState(AppStates.OFFLINE)
                AUTH.signOut()
                restartActivity()
            }
            R.id.settings_menu_change_name -> replaceFragment(ChangeNameFragment())
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK && data != null
        ) {
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE).child(CURRENT_ID)

            putImageToStorage(uri, path) {
                getUrlFromStorage(path) {
                    putUrlToDatabase(it) {
                        settings_profile_image.downloadAndSetImage(it)
                        showToast("Данные обновленны")
                        USERModel.photoUrl = it
                        APP_ACTIVITY.mAppDrawer.updateHeader()
                    }
                }
            }
        }
    }
}