package gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.ProfileSbsSnPlCl.profileSubscreensManagerState

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ViewModelProfileHorizontalPagerPage: ViewModel() {

    private val _uiState = MutableStateFlow(UiStateProfileHorizontalPagerPage())
    val uiState: StateFlow<UiStateProfileHorizontalPagerPage> = _uiState


    private fun updateStateHorizontalPager(page: ProfilePagesHorizontalPagerPage){
        _uiState.update {
            it.copy(
                stateHorizontalPager = page
            )
        }
    }

    fun updateNamePage(newValue: Int){
        _uiState.update {
            it.copy(
                namePage = newValue
            )
        }
        asignedPageProfileNavigation()
    }

    private fun asignedPageProfileNavigation(){
        when(uiState.value.namePage){
            0 -> updateStateHorizontalPager(page = ProfilePagesHorizontalPagerPage.INFORMATION)
            1 -> updateStateHorizontalPager(page = ProfilePagesHorizontalPagerPage.REVIEWS)
            2 -> updateStateHorizontalPager(page = ProfilePagesHorizontalPagerPage.PHOTOS)
            3 -> updateStateHorizontalPager(page = ProfilePagesHorizontalPagerPage.VIDEOS)
        }
    }

}