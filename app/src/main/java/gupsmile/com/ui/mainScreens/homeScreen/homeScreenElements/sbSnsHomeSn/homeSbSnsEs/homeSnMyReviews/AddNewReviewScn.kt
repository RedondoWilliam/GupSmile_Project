package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeSnMyReviews

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.commonElements.PersonalizedBottomActions
import gupsmile.com.ui.commonElements.PrincipalIconEmergentMenuItem
import gupsmile.com.ui.commonElements.TextFieldPdTwo
import gupsmile.com.ui.theme.GupsMileTheme







@Composable
fun AddNewGupAccessScn(
    modifier: Modifier = Modifier,
    bottomActions: () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(25.dp))
            .clickable { bottomActions() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Spacer(modifier = modifier.width(16.dp))
            Box(
                modifier = modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .size(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_new_review_icon),
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .size(18.dp)
                )
            }
            Spacer(modifier = modifier.width(16.dp))
            textCommonHomePage(
                stringResTextEntry = R.string.title_scn_add_new_review,
                maxLinesResParameter = 2,
                lineHeightParameter = 15.sp,
                fontSizeStyleParameter = 15.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                colorStyleParameter = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = modifier.width(16.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddNewGupAccessScnPreview(){
    GupsMileTheme {
        AddNewGupAccessScn(
            bottomActions = {}
        )
    }
}









@Composable
fun AddNewReviewScn(
    modifier: Modifier = Modifier,
    textUser: String,
    onTextChange: (String) -> Unit,
    onDoneClicked: () -> Unit,
    onConfirmActionsBottom: () -> Unit,
    onDimissBottom: () -> Unit,
){
    var expanded by remember { mutableStateOf(false) }
    var focusRequester: FocusRequester = remember{ FocusRequester() }
    val focusManager = LocalFocusManager.current

    BackHandler {
        focusManager.clearFocus()
        expanded = false
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(top = 10.dp, bottom = 15.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
            .onFocusChanged {
                if (it.isFocused) expanded = !expanded
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add_new_review_icon),
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .size(18.dp)
        )
        Spacer(modifier = modifier.height(16.dp))
        textCommonHomePage(
            stringResTextEntry = R.string.title_scn_add_new_review,
            maxLinesResParameter = 4,
            lineHeightParameter = 14.sp,
            fontSizeStyleParameter = 14.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Spacer(modifier = modifier.height(16.dp))
        TextFieldPdTwo(
            textUser = textUser,
            ontextchage = onTextChange ,
            onDoneClicked  = {
                focusManager.clearFocus()
                onDoneClicked()
                expanded = false
            } ,
            focusRequester = focusRequester,
            textOnTextfield = R.string.title_text_field_add_new_review,
            backgroundTextfield = MaterialTheme.colorScheme.tertiaryContainer,
        )


        if (expanded) {
            Spacer(modifier = modifier.height(16.dp))
            Row(
                modifier = modifier
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SectionOptionsPostReview(
                    titleOptiontext = R.string.visibility_option_post_review,
                    titleOptionIcon = R.drawable.open_eye_icon
                )
                SectionOptionsPostReview(
                    titleOptiontext = R.string.coments_option_post_review,
                    titleOptionIcon = R.drawable.coment_fill_icon
                )
                SectionOptionsPostReview(
                    titleOptiontext = R.string.share_option_post_review,
                    titleOptionIcon = R.drawable.share_two
                )
            }
            Row(
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                PersonalizedBottomActions(
                    actionsBottomText = R.string.dimiss_post_new_review_title_bottom,
                    onConfirmActions = {
                        onDimissBottom()
                        focusManager.clearFocus()
                                       },
                    sizeFontTitleBottom = 14.sp,
                    stileFontTitleBottom = FontFamily(Font(R.font.raleway_regular))
                )
                Spacer(modifier = modifier.width(20.dp))
                PersonalizedBottomActions(
                    actionsBottomText = R.string.post_new_review_title_bottom,
                    onConfirmActions = {
                        onConfirmActionsBottom()
                        focusManager.clearFocus()
                    }
                )
            }
        }

    }
}

@Composable
fun SectionOptionsPostReview(
    modifier: Modifier = Modifier,
    @StringRes titleOptiontext: Int,
    @DrawableRes titleOptionIcon: Int
){
    Column(
        modifier = modifier
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrincipalIconEmergentMenuItem(drawableIcon = titleOptionIcon)
        Spacer(modifier = modifier.height(7.dp))
        textCommonHomePage(
            stringResTextEntry = titleOptiontext,
            maxLinesResParameter = 1,
            lineHeightParameter = 14.sp,
            fontSizeStyleParameter = 14.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter =  MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AddNewReviewScnPreview(){
    GupsMileTheme {
       Column(
           modifier = Modifier
               .fillMaxWidth()
               .padding(10.dp)
       ) {
           AddNewReviewScn(
               textUser = "",
               onTextChange = {},
               onDoneClicked = {},
               onConfirmActionsBottom = {},
               onDimissBottom =  {}
           )
       }
    }
}