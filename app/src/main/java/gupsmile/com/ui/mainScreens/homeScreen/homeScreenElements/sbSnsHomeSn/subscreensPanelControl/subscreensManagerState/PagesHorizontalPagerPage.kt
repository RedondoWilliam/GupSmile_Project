package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState

enum class PagesHorizontalPagerPage(
    val text: String
) {
    POST("Post"),
    SAVED("Gups"),
    MYREVIEWS("Historia"),
    TUGENTE("Tu gente"),
}

enum class CurrentPageHorizontalPager{
    POST,
    MYREVIEWS,
    SAVED,
    YOURPEOPLE,
    NONE
}

enum class StatePointerInputHomeScreen{
    ACTIVE,
    NOACTIVE
}
enum class StatePointerAction{
    ACTIVE,
    NOACTIVE
}