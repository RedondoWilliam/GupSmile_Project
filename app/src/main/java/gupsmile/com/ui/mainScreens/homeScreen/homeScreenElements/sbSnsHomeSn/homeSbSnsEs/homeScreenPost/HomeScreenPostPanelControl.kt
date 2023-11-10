package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeScreenPost

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeCardContentItem.homeCardContentElements.HomeCardContentItem
//import gupsmile.com.ui.screens.homeScreen.homeScreenElements.homeCardContentItem.homeCardContentElements.HomeCardContentItem
import gupsmile.com.ui.theme.GupsMileTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPostPanelConrtrol(
    modifier: Modifier = Modifier,
    scrollBehavior : TopAppBarScrollBehavior,
    columnState : ScrollState = rememberScrollState(),
){
    val scroll = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection)
            .verticalScroll(columnState)
    ) {
        HomeCardContentItem(
            nameUser = "Lucia Hernández",
            timePost = "hace 30 minutos" ,
            reactionsLikes = "1.2k",
            reactionsComments = "145",
            addContentMedia = {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.selfie),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth

                )
            },
            descriptionPost = {
                textCommonHomePageString(
                    stringResTextEntry = "Son unos pendejos, pero siguen siendo mis empleados. Saludos desde la oficina" ,
                    maxLinesResParameter = 100 ,
                    lineHeightParameter = 14.sp ,
                    fontSizeStyleParameter = 14.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground
                )
            }
        )

        HomeCardContentItem(
            nameUser = "Lucia Hernández",
            timePost = "hace 30 minutos" ,
            reactionsLikes = "1.2k",
            reactionsComments = "145",
            addContentMedia = {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id =R.drawable.selfie_dos),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth

                )
            },
            descriptionPost = {
                textCommonHomePageString(
                    stringResTextEntry = "Son unos pendejos, pero siguen siendo mis empleados. Saludos desde la oficina" ,
                    maxLinesResParameter = 100 ,
                    lineHeightParameter = 14.sp ,
                    fontSizeStyleParameter = 14.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun HomeScreenMyReviewsPreview(){
    GupsMileTheme {
        val topAppBarState = rememberTopAppBarState()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
            state = topAppBarState,
            canScroll = {true},
            snapAnimationSpec = spring(stiffness = Spring.StiffnessVeryLow),
            flingAnimationSpec = rememberSplineBasedDecay()

        )
        HomeScreenPostPanelConrtrol(
            scrollBehavior = scrollBehavior
        )
    }
}
