package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeNavigationUpSections.homeNavigationUpSectionPanelControl

import androidx.annotation.StringRes
import gupsmile.com.R




object StorageNavigationUpOptions{
    val allValuesStorageNavigationUpOptions: List<NavigationUpOptions> = listOf(
        NavigationUpOptions(
            nameSubSection = R.string.post,
            nameSubSectionId = "POST"
        ),
        NavigationUpOptions(
            nameSubSection = R.string.my_reviews,
            nameSubSectionId = "MYREVIEWS"
        ),
        NavigationUpOptions(
            nameSubSection = R.string.resources,
            nameSubSectionId = "SAVED"
        ),
        NavigationUpOptions(
            nameSubSection = R.string.your_people,
            nameSubSectionId =  "TUGENTE"
        ),
    )
}

data class NavigationUpOptions(
        @StringRes val nameSubSection: Int,
        val nameSubSectionId: String
    )
