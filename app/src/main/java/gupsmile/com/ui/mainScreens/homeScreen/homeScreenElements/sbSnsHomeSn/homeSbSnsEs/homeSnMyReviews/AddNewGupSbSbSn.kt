package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeSnMyReviews

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.commonElements.CircleShapeBottomPd
import gupsmile.com.ui.commonElements.TextFieldPdThree
import gupsmile.com.ui.settingScreens.addNewResourceSn.IconEditPd
import gupsmile.com.ui.theme.GupsMileTheme


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddNewGupSn(
    modifier: Modifier = Modifier,
    contentHistoryText: String,
    contentHistoryOnTextChange: (String) -> Unit,
    doneBottomAction: () -> Unit,
    arrowBackTopBottom: () -> Unit,
    openTopMenuOptions: () -> Unit,
    @StringRes titleActionGup: Int,
    @StringRes titleDoneActionGup: Int
){

    val focusRequester = remember{FocusRequester()}
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.barr_menu),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = modifier
                .width(60.dp)
                .height(5.dp)
        )

        Spacer(modifier = modifier.height(30.dp))

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    textCommonHomePage(
                        stringResTextEntry = titleActionGup,
                        maxLinesResParameter = 1,
                        lineHeightParameter = 20.sp,
                        fontSizeStyleParameter = 20.sp,
                        fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                        colorStyleParameter = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = modifier.height(25.dp))
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_down_icon),
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {}
                    )
                    Spacer(modifier = modifier.width(25.dp))
                    CircleShapeBottomPd(
                        bottomActionsItem = { /*TODO*/ },
                        iconItem = R.drawable.open_eye_icon,
                        containerColorIcon = MaterialTheme.colorScheme.primary,
                        colorIcon = MaterialTheme.colorScheme.onTertiary,
                        sizeIcon = 20.dp,
                        sizecontainerIcon = 40.dp
                    )
                    Spacer(modifier = modifier.width(25.dp))
                    CircleShapeBottomPd(
                        bottomActionsItem = { /*TODO*/ },
                        iconItem = R.drawable.share_two,
                        containerColorIcon = MaterialTheme.colorScheme.primary,
                        colorIcon = MaterialTheme.colorScheme.onPrimary,
                        sizeIcon = 20.dp,
                        sizecontainerIcon = 40.dp
                    )
                    Spacer(modifier = modifier.width(25.dp))
                    CircleShapeBottomPd(
                        bottomActionsItem = { /*TODO*/ },
                        iconItem = R.drawable.padlock_icon,
                        containerColorIcon = MaterialTheme.colorScheme.primary,
                        colorIcon = MaterialTheme.colorScheme.onPrimary,
                        sizeIcon = 20.dp,
                        sizecontainerIcon = 40.dp
                    )
                }
            }
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircleShapeBottomPd(
                    bottomActionsItem = {
                        doneBottomAction()
                                        },
                    iconItem = R.drawable.sent_icon,
                    containerColorIcon = MaterialTheme.colorScheme.primary,
                    colorIcon = MaterialTheme.colorScheme.onPrimary,
                    sizeIcon = 30.dp,
                    sizecontainerIcon = 60.dp
                )
                Spacer(modifier = modifier.height(15.dp))
                textCommonHomePage(
                    stringResTextEntry = titleDoneActionGup,
                    maxLinesResParameter = 1,
                    lineHeightParameter = 17.sp,
                    fontSizeStyleParameter = 17.sp ,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground
                )
            }


        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .clickable {
                    focusRequester.requestFocus()
                    keyboardController?.show()
                },
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start

        ) {
            Spacer(modifier = modifier.height(15.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
                    .background(MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconEditPd()
            }
            Spacer(modifier = modifier.height(8.dp))
            TextFieldPdThree(
                textUser = contentHistoryText,
                ontextchage = contentHistoryOnTextChange ,
                onDoneClicked  = {focusManager.clearFocus()} ,
                focusRequester = focusRequester,
                textOnTextfield =  R.string.title_content_addnewgup,
                backgroundTextfield = MaterialTheme.colorScheme.background,
                minHeigt = 300.dp
            )
        }

    }

}

@Composable
@Preview(showBackground = true)
fun AddNewResourceSnPreview(){
    GupsMileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Unspecified),
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            AddNewGupSn(
                contentHistoryText = "",
                contentHistoryOnTextChange = {} ,
                doneBottomAction = {},
                arrowBackTopBottom = {},
                openTopMenuOptions = {},
                titleActionGup = R.string.title_sn_addnewgup,
                titleDoneActionGup = R.string.action_update_gup
            )
        }
    }
}
@Composable
@Preview(showBackground = true)
fun AddNewResourceSnDarkModePreview(){
    GupsMileTheme(
        darkTheme = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Unspecified),
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            AddNewGupSn(
                contentHistoryText = "",
                contentHistoryOnTextChange = {} ,
                doneBottomAction = {},
                arrowBackTopBottom = {},
                openTopMenuOptions = {},
                titleActionGup = R.string.title_sn_addnewgup,
                titleDoneActionGup = R.string.title_bottom_post_history
            )
        }
    }
}
