package gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileNavigationUpSections.profileNavigationUpSectionsPanelControl

import androidx.annotation.StringRes
import gupsmile.com.R

object StorageProfileNavigationUpOptions{
    val allValuesStorageProfileNavigationUpOptions = listOf(
        ProfileNavigationUpOptions(
            nameSubSection = R.string.contact_information,
            nameSubSectionId = "INFORMATION"
        ),
        ProfileNavigationUpOptions(
            nameSubSection = R.string.reviews,
            nameSubSectionId = "REVIEWS"
        ),
        ProfileNavigationUpOptions(
            nameSubSection = R.string.photos,
            nameSubSectionId = "PHOTOS"
        ),
        ProfileNavigationUpOptions(
            nameSubSection = R.string.videos,
            nameSubSectionId =  "VIDEOS"
        ),
    )
}

data class ProfileNavigationUpOptions(
        @StringRes val nameSubSection: Int,
        val nameSubSectionId: String
    )
