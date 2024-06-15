package gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.ui.commonElements.CircleShapeBottomPd
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.commonElements.TopAppBarPersonalized
import gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactEs.InformationContactItemInfo
import gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactEs.InformationContactScn
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun ProfileSn(
    modifier: Modifier = Modifier,
    profilePhoto: @Composable () -> Unit,
    nameUser : String,
    emailBottomAction: () -> Unit,
    chatBottomAction: () -> Unit,
    phoneBottomAction: () -> Unit,
    informationContact: @Composable () -> Unit,
    bottomMenuActions: () -> Unit
){

    val columnState = rememberScrollState()

    Box {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(columnState),

            ) {
            TopAppBarPersonalized(
                arrowBackTopBottom = {},
                titleTopAppBar = R.string.chat_title,
                bottomMenuActions = {bottomMenuActions()}
            )
            Box(
                modifier = modifier
                    .height(160.dp)
                    .padding(15.dp),
                contentAlignment = Alignment.BottomEnd
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary)
                ) {
                   profilePhoto()
                }

                FloatingBottonDesignFixed(
                    imageIcon = R.drawable.camera,
                    sizeIcon = 21.dp,
                )

            }
            Row(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                textCommonHomePageString(
                    stringResTextEntry = nameUser,
                    maxLinesResParameter = 5,
                    lineHeightParameter = 22.sp ,
                    fontSizeStyleParameter = 22.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp, top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircleShapeBottomPd(
                    iconItem = R.drawable.email_icon_info_contact,
                    bottomActionsItem = {emailBottomAction()},
                    sizeIcon = 21.dp,
                    sizecontainerIcon = 42.dp,
                    containerColorIcon = MaterialTheme.colorScheme.primary,
                    colorIcon = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = modifier.width(20.dp))
                CircleShapeBottomPd(
                    iconItem = R.drawable.chat_icon_info_contact,
                    bottomActionsItem = {chatBottomAction()},
                    sizeIcon = 21.dp,
                    sizecontainerIcon = 42.dp,
                    containerColorIcon = MaterialTheme.colorScheme.primary,
                    colorIcon = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = modifier.width(20.dp))
                CircleShapeBottomPd(
                    iconItem = R.drawable.phone_icon_info_contact,
                    bottomActionsItem = {phoneBottomAction()},
                    sizeIcon = 21.dp,
                    sizecontainerIcon = 42.dp,
                    containerColorIcon = MaterialTheme.colorScheme.primary,
                    colorIcon = MaterialTheme.colorScheme.onPrimary
                )
            }
            informationContact()
            

        }

    }
}

@Composable
@Preview(showBackground = true)
fun ProfileSnPreview(){
    GupsMileTheme {
        ProfileSn(
            bottomMenuActions = {},
            profilePhoto = { /*TODO*/ },
            nameUser = "William Redondo" ,
            emailBottomAction = { /*TODO*/ },
            chatBottomAction = { /*TODO*/ },
            phoneBottomAction = { /*TODO*/ },
            informationContact = {
                InformationContactScn(
                    informationContactItems = {
                        InformationContactItemInfo(
                            iconItem = R.drawable.phone_icon_info_contact,
                            contentIntoText = "+57 3142908556",
                            describeInfoText = "Teléfono móvil",
                        )
                        InformationContactItemInfo(
                            iconItem = R.drawable.email_icon_info_contact,
                            contentIntoText = "lucía-hernandez@gmail.com",
                            describeInfoText = "",
                        )
                        InformationContactItemInfo(
                            iconItem = R.drawable.addres_icon_add_contact,
                            contentIntoText = "Calle 2 No. 3-56, Soracá -Boyacá",
                            describeInfoText = "",
                        )
                    }


                )
                InformationContactScn(
                    informationContactItems = {
                        InformationContactItemInfo(
                            iconItem = R.drawable.phone_icon_info_contact,
                            contentIntoText = "+57 3142908556",
                            describeInfoText = "Teléfono móvil",
                        )
                        InformationContactItemInfo(
                            iconItem = R.drawable.email_icon_info_contact,
                            contentIntoText = "lucía-hernandez@gmail.com",
                            describeInfoText = "",
                        )
                        InformationContactItemInfo(
                            iconItem = R.drawable.addres_icon_add_contact,
                            contentIntoText = "Calle 2 No. 3-56, Soracá -Boyacá",
                            describeInfoText = "",
                        )
                    }


                )
                InformationContactScn(
                    informationContactItems = {
                        InformationContactItemInfo(
                            iconItem = R.drawable.phone_icon_info_contact,
                            contentIntoText = "+57 3142908556",
                            describeInfoText = "Teléfono móvil",
                        )
                        InformationContactItemInfo(
                            iconItem = R.drawable.email_icon_info_contact,
                            contentIntoText = "lucía-hernandez@gmail.com",
                            describeInfoText = "",
                        )
                        InformationContactItemInfo(
                            iconItem = R.drawable.addres_icon_add_contact,
                            contentIntoText = "Calle 2 No. 3-56, Soracá -Boyacá",
                            describeInfoText = "",
                        )
                    }


                )
                InformationContactScn(
                    informationContactItems = {
                        InformationContactItemInfo(
                            iconItem = R.drawable.phone_icon_info_contact,
                            contentIntoText = "+57 3142908556",
                            describeInfoText = "Teléfono móvil",
                        )
                        InformationContactItemInfo(
                            iconItem = R.drawable.email_icon_info_contact,
                            contentIntoText = "lucía-hernandez@gmail.com",
                            describeInfoText = "",
                        )
                        InformationContactItemInfo(
                            iconItem = R.drawable.addres_icon_add_contact,
                            contentIntoText = "Calle 2 No. 3-56, Soracá -Boyacá",
                            describeInfoText = "",
                        )
                    }


                )
            }
        )
    }
}