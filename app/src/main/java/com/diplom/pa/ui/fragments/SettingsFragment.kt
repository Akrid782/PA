package com.diplom.pa.ui.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.diplom.pa.R
import com.diplom.pa.activity.RegisterActivity
import com.diplom.pa.utility.*
import com.squareup.picasso.Picasso
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
        if (USER.bio.isNotEmpty()) settings_about.text = USER.bio
        settings_fullName.text = USER.fullname
        settings_number_phone.text = USER.phone
        settings_status.text = USER.status
        settings_userName.text = USER.username
        settings_btn_change_about.setOnClickListener { replaceFragment(ChangeBioFragment()) }
        settings_btn_change_userName.setOnClickListener { replaceFragment(ChangeUsernameFragment()) }
        settings_btn_change_photo.setOnClickListener { changePhotoUser() }
    }

    private fun changePhotoUser() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(APP_ACTIVITY, this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(
            R.menu.settings_action_menu,
            menu
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                APP_ACTIVITY.replaceActivity(RegisterActivity())
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
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(CURRENT_ID)
            path.putFile(uri).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    path.downloadUrl.addOnCompleteListener { task2 ->
                        if (task2.isSuccessful) {
                            val photoUrl = task2.result.toString()
                            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_ID)
                                .child(CHILD_PHOTO_URL).setValue(photoUrl)
                                .addOnCompleteListener { task3 ->
                                    if (task3.isSuccessful  ) {
                                        settings_profile_image.downloadAndSetImage(photoUrl)
                                        showToast("Данные обновленны")
                                    }
                                }
                        }
                    }
                }
            }
        }
    }
}