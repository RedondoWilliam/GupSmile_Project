package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ViewModelHorizontalPagerPage: ViewModel() {

    private val _uiState = MutableStateFlow(UiStateHorizontalPagerPage())
    val uiState: StateFlow<UiStateHorizontalPagerPage> = _uiState


    private fun updateStateHorizontalPager(page: PagesHorizontalPagerPage){
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
        asignedPageTopNavigation()
    }


    private fun asignedPageTopNavigation(){
        when(uiState.value.namePage){
            0 -> updateStateHorizontalPager(page = PagesHorizontalPagerPage.POST)
            1 -> updateStateHorizontalPager(page = PagesHorizontalPagerPage.MYREVIEWS)
            2 -> updateStateHorizontalPager(page = PagesHorizontalPagerPage.SAVED)
            3 -> updateStateHorizontalPager(page = PagesHorizontalPagerPage.TUGENTE)
        }
    }

    /**
     * Actualiza la subpantalla que se mostrará inicialmente en el  horizontalPager que controla las subpantallas en HomeScreen,
     * @param newValue nuevo valor inicial en el horizontalpager. Es de tipo InitialPageHorizontalPager,
     * admite los elementos: POST, MYREVIEWS, SAVED y YOURPEOPLE.
     * */
    fun updateCurrentPage(newValue: CurrentPageHorizontalPager){
        _uiState.update {
            it.copy(
                currentPage = newValue
            )
        }
    }

     fun updateStateImage(newValue: Boolean){
        _uiState.update {
            it.copy(
                stateImage = newValue
            )
        }
    }

    fun updateStateNewGup(newValue: Boolean){
        _uiState.update {
            it.copy(
                stateNewGup = newValue,
            )
        }
    }

    fun updateStateUpdateGup(
        newValue:Boolean
    ){
        _uiState.update {
            it.copy(
                stateUpdateGup = newValue
            )
        }
    }

    fun updatePauseGlobalStateUi(newValue: Boolean){
        _uiState.update {
            it.copy(
                pauseGlobalUi = newValue
            )
        }
    }

}