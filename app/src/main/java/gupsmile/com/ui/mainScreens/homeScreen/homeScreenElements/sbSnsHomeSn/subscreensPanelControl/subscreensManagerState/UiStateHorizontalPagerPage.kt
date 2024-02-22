package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState


data class UiStateHorizontalPagerPage(
    var stateHorizontalPager: PagesHorizontalPagerPage = PagesHorizontalPagerPage.POST,
    var namePage: Int = 1,
    var currentPage: CurrentPageHorizontalPager = CurrentPageHorizontalPager.NONE,
    var statePointerInputHomeScreen: StatePointerInputHomeScreen = StatePointerInputHomeScreen.NOACTIVE,
    var statePointerAction: StatePointerAction = StatePointerAction.NOACTIVE,
    var stateImage: Boolean = false,
    var stateNewGup: Boolean = false,
    var stateUpdateGup: Boolean = false,
    var pauseGlobalUi: Boolean = false
)
