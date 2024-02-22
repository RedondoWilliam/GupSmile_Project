package gupsmile.com.ui.settingScreens.addNewResourceSn

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.commonElements.BottomItemDesignFixed
import gupsmile.com.ui.commonElements.CircleShapeBottomPd
import gupsmile.com.ui.commonElements.TextFieldPdThree
import gupsmile.com.ui.commonElements.TopAppBarPersonalized
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun AddNewResourceSn(
    modifier: Modifier = Modifier,
    titleHistoryText: String,
    titleHistoryOnTextChange: (String) -> Unit,
    contentHistoryText: String,
    contentHistoryOnTextChange: (String) -> Unit,
    takePhotoActionBottom: () -> Unit,
    doneBottomAction: () -> Unit,
    showNewPhoto: @Composable () -> Unit,
    arrowBackTopBottom: () -> Unit
){
    val columnState = rememberScrollState()
    Box(
        modifier = modifier
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(columnState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            TopAppBarPersonalized(
                arrowBackTopBottom = {arrowBackTopBottom()},
                titleTopAppBar = R.string.title_sn_addnewhistory
            )
            Spacer(modifier = modifier.height(15.dp))
            Row (
                modifier = modifier
                    .padding(start = 20.dp)
            ){
                IconEditPd()
            }
            Spacer(modifier = modifier.height(8.dp))
            TextFieldPdThree(
                textUser = titleHistoryText,
                ontextchage = titleHistoryOnTextChange ,
                onDoneClicked  = {} ,
                focusRequester = FocusRequester(),
                textOnTextfield = R.string.title_history_scn,
                backgroundTextfield = MaterialTheme.colorScheme.background,
            )
            Divider(
                modifier = modifier
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
            showNewPhoto()
            Spacer(modifier = modifier.height(15.dp))
            Row (
                modifier = modifier
                    .padding(start = 20.dp)
            ){
                IconEditPd()
            }
            Spacer(modifier = modifier.height(8.dp))
            TextFieldPdThree(
                textUser = contentHistoryText,
                ontextchage = contentHistoryOnTextChange ,
                onDoneClicked  = {} ,
                focusRequester = FocusRequester(),
                textOnTextfield =  R.string.content_history_scn,
                backgroundTextfield = MaterialTheme.colorScheme.background,
                minHeigt = 274.dp

            )
            Divider(
                modifier = modifier
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
            Spacer(modifier = modifier.height(15.dp))

            AddResourceOpcionsScn(
                resourceIcon =  R.drawable.add_new_image_video_icon,
                titleOptionText = R.string.add_new_resource_history_scn,
                addResourceBottomActions = {}
            )
            Spacer(modifier = modifier.height(10.dp))
            AddResourceOpcionsScn(
                resourceIcon =  R.drawable.camera_icon,
                titleOptionText = R.string.add_new_photo_hostory_scn,
                addResourceBottomActions = {takePhotoActionBottom()}
            )
            Spacer(modifier = modifier.height(15.dp))
            Row(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                BottomBarOptionsAddNewHistory(
                    titleSbScn = R.string.post_hisrtory_scn,
                    nameIcon = R.drawable.open_eye_icon
                )
                BottomBarOptionsAddNewHistory(
                    titleSbScn = R.string.save_history_scn,
                    nameIcon = R.drawable.padlock_icon
                )
                BottomBarOptionsAddNewHistory(
                    titleSbScn = R.string.share_history_scn,
                    nameIcon = R.drawable.share_two
                )
            }
            Spacer(modifier = modifier.height(15.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BottomItemDesignFixed(
                    bottomActions = {doneBottomAction()},
                    padddingStart = 5.dp,
                    paddingEnd = 5.dp,
                    widthFixed = 276.dp ,
                    textBottonName = R.string.title_bottom_post_history,
                    sizeFontBottom = 16.sp,
                )
            }
        }
    }
}

@Composable
fun AddResourceOpcionsScn(
    modifier: Modifier = Modifier,
    resourceIcon: Int,
    titleOptionText: Int,
    addResourceBottomActions: () -> Unit

){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .clickable { addResourceBottomActions() }
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        IconEditPd(dirIcon =resourceIcon)
        Spacer(modifier = modifier.width(15.dp))
        textCommonHomePage(
            stringResTextEntry = titleOptionText,
            maxLinesResParameter = 2,
            lineHeightParameter = 17.sp,
            fontSizeStyleParameter = 17.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
            colorStyleParameter = MaterialTheme.colorScheme.onBackground
        )
    }
}


@Composable
fun BottomBarOptionsAddNewHistory(
    modifier: Modifier = Modifier,
    titleSbScn: Int,
    nameIcon: Int,
){
    Column(
        modifier = modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircleShapeBottomPd(
            bottomActionsItem = { /*TODO*/ },
            iconItem = nameIcon
        )
        Spacer(modifier = modifier.height(20.dp))
        textCommonHomePage(
            stringResTextEntry = titleSbScn,
            maxLinesResParameter = 1,
            lineHeightParameter = 14.sp,
            fontSizeStyleParameter = 14.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
            colorStyleParameter = MaterialTheme.colorScheme.onBackground
        )
    }
}



@Composable
fun IconEditPd(
    modifier: Modifier = Modifier,
    dirIcon: Int = R.drawable.edit_icon
){
    Box(
        modifier = modifier
            .size(29.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(id = dirIcon),
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(17.dp)
        )
    }
}



@Composable
@Preview(showBackground = true)
fun AddNewResourceSnPreview(){
    GupsMileTheme {
        AddNewResourceSn(
            titleHistoryText = "",
            titleHistoryOnTextChange = {},
            contentHistoryText = "",
            contentHistoryOnTextChange = {} ,
            takePhotoActionBottom = { /*TODO*/ },
            doneBottomAction = {},
            showNewPhoto = {},
            arrowBackTopBottom = {}
        )
    }
}
@Composable
@Preview(showBackground = true)
fun AddNewResourceSnDarkModePreview(){
    GupsMileTheme(
        darkTheme = true
    ) {
        AddNewResourceSn(
            titleHistoryText = "",
            titleHistoryOnTextChange = {},
            contentHistoryText = "",
            contentHistoryOnTextChange = {} ,
            takePhotoActionBottom = { /*TODO*/ },
            doneBottomAction = {},
            showNewPhoto = {},
            arrowBackTopBottom = {}
        )
    }
}