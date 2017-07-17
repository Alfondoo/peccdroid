package com.javialonso.peccdroid.presentation.view.contract

import com.javialonso.peccdroid.data.entity.ProfileEntity

interface ProfileView {
    fun updateProfileCard(profileEntity: ProfileEntity)

    fun showLoader()
    fun hideLoader()
}