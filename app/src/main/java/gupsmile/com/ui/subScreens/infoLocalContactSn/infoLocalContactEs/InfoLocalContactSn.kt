package gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactEs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.ui.commonElements.DropDownMenuItemPersonalized
import gupsmile.com.ui.commonElements.TopAppBarPersonalized
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun InfoLocalContactSn(
    modifier: Modifier = Modifier,
    arrowBackTopBottom: () -> Unit,
    textNameUser: String,
    textPhoneUser:String,
    textEmailUser: String,
    textAddressUser: String,
    editContactActionBottom: () -> Unit,
    deleteContactActionBottom: () -> Unit,
    emailBottomBarActionBottom: () -> Unit,
    chatBottomBarActionBottom: () -> Unit,
    phoneBottomBarActionBottom:() -> Unit
){
    val columnStateAddContactScreen = rememberScrollState()

    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember{ mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(columnStateAddContactScreen)
                    .weight(0.8f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAppBarPersonalized(
                    arrowBackTopBottom = {arrowBackTopBottom()},
                    titleTopAppBar = R.string.empty_text,
                    bottomMenuActions = { expanded = !expanded }
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(130.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.image_add_contact),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = modifier.size(100.dp)
                    )
                }
                Spacer(modifier = modifier.height(20.dp))

                textCommonHomePageString(
                    stringResTextEntry = textNameUser,
                    maxLinesResParameter = 2 ,
                    lineHeightParameter = 25.sp,
                    fontSizeStyleParameter = 25.sp,
                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_semi_bold)),
                    colorStyleParameter = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = modifier.height(20.dp))

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 20.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 15.dp,
                                bottomEnd = 15.dp,
                                bottomStart = 15.dp
                            )
                        )
                        .background(MaterialTheme.colorScheme.tertiaryContainer)

                ) {
                    InformationContactItemInfo(
                        iconItem = R.drawable.phone_icon_info_contact,
                        contentIntoText = textPhoneUser,
                        describeInfoText = "Teléfono móvil",

                    )
                    InformationContactItemInfo(
                        iconItem = R.drawable.email_icon_info_contact,
                        contentIntoText = textEmailUser,
                        describeInfoText = "",
                    )
                    InformationContactItemInfo(
                        iconItem = R.drawable.addres_icon_add_contact,
                        contentIntoText = textAddressUser,
                        describeInfoText = "",
                    )
                }


            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomBarOptionsScn(
                    emailBottomBarActionBottom = emailBottomBarActionBottom,
                    chatBottomBarActionBottom = chatBottomBarActionBottom,
                    phoneBottomBarActionBottom = phoneBottomBarActionBottom
                )
            }
        }
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = modifier
                .padding(start = (15).dp, top =(15).dp)
                .offset(x = (150).dp, y = (50).dp)
        ) {
            DropdownMenu(
                offset = DpOffset.Zero,
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = modifier.background(MaterialTheme.colorScheme.secondaryContainer)
            )
            {
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.edit_icon,
                            nameItemMenu = R.string.text_options_menu_edit_contact
                        )
                    },
                    onClick = {editContactActionBottom()}
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.delete_icon,
                            nameItemMenu = R.string.text_options_menu_delete_contact
                        )
                    },
                    onClick = {deleteContactActionBottom()}
                )

            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun InfoLocalContactSnPreview(){
    GupsMileTheme {
        InfoLocalContactSn(
            arrowBackTopBottom = {},
            textNameUser = "Lucía Hernández",
            textPhoneUser = "+57 3142908556",
            textEmailUser = "lucía-hernandez@gmail.com",
            textAddressUser = "Calle 2 No. 3-56, Soracá -Boyacá",
            editContactActionBottom = {},
            deleteContactActionBottom = {},
            emailBottomBarActionBottom = {},
            chatBottomBarActionBottom = {},
            phoneBottomBarActionBottom = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InfoLocalContactSnDarkModePreview(){
    GupsMileTheme(
        darkTheme = true
    ) {
        InfoLocalContactSn(
            arrowBackTopBottom = {},
            textNameUser = "Lucía Hernández",
            textPhoneUser = "+57 3142908556",
            textEmailUser = "lucía-hernandez@gmail.com",
            textAddressUser = "Calle 2 No. 3-56, Soracá -Boyacá",
            editContactActionBottom = {},
            deleteContactActionBottom = {},
            emailBottomBarActionBottom = {},
            chatBottomBarActionBottom = {},
            phoneBottomBarActionBottom = {}
        )
    }
}