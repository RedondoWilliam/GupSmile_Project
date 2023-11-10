package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeSnMyReviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeCardContentItem.homeCardContentElements.HomeCardContentItem
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun MyReviewsSbSn(
    modifier: Modifier = Modifier,
    textUser: String,
    onTextChange: (String) -> Unit,
    onDoneClicked: () -> Unit,
    listReviewsScn: @Composable () -> Unit,
    onConfirmActionsBottom: () -> Unit,
    onDimissBottom: () -> Unit,
) {
    val scroll = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(10.dp)
            .fillMaxHeight(),
        verticalArrangement =  Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddNewReviewScn(
            textUser = textUser,
            onTextChange = onTextChange,
            onDoneClicked = onDoneClicked,
            onConfirmActionsBottom = onConfirmActionsBottom,
            onDimissBottom = onDimissBottom
        )
        Spacer(modifier = modifier.height(10.dp))
        listReviewsScn()
    }
}



@Composable
@Preview(showBackground = true)
fun MyReviewsSbSnPreview(){
    GupsMileTheme {
        MyReviewsSbSn(
            textUser = "",
            onTextChange = {},
            onDoneClicked = {},
            listReviewsScn = {
                HomeCardContentItem(
                    nameUser = "Lucia Hernández",
                    timePost = "hace 30 minutos" ,
                    reactionsLikes = "1.2k",
                    reactionsComments = "145",
                    addContentMedia = {},
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
                    addContentMedia = {},
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
                    addContentMedia = {},
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
            },
            onDimissBottom = {},
            onConfirmActionsBottom = {}
        )
    }
}