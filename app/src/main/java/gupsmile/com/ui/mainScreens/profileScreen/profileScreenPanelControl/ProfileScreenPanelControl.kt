package gupsmile.com.ui.mainScreens.profileScreen.profileScreenPanelControl

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.data.temporalConfig.StateChangesOnListReviews
import gupsmile.com.data.temporalConfig.ViewModelGetReviews
import gupsmile.com.ui.commonElements.DialogOptionsPersonalized
import gupsmile.com.ui.commonElements.DropDownMenuItemPersonalized
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.commonElements.LogOutDialogMenu
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.ProfileSn
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactEs.InformationContactItemInfo
import gupsmile.com.ui.subScreens.infoLocalContactSn.infoLocalContactEs.InformationContactScn
import gupsmile.com.ui.theme.GupsMileTheme
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateCurrentUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginAsVisitor
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginWithGoogle
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication
import kotlinx.coroutines.launch

@Composable
fun ProfileScreenPanelControl(
    modifier: Modifier = Modifier,
    analytics: AnalitycsManager?,
    auth: AuthManager?,
    navController: NavHostController = rememberNavController(),
    viewModelAuthentication: ViewModelAuthentication?,
    viewModelGetReviews: ViewModelGetReviews?
){
    analytics?.logScreenView(screenName = RoutesMainScreens.ProfileScreen.route)

    val scope = rememberCoroutineScope()

    val authenticationUiState = viewModelAuthentication?.uiState?.collectAsState()?.value

    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember{ mutableStateOf(false) }



    val user = auth?.getCurrentUser()


    val onLogOutConfirmed: () -> Unit = {
        scope.launch {
            viewModelAuthentication?.updateStateLoginWithGoogle(
                newValue = StateLoginWithGoogle.UNSPECIFIED
            )
            viewModelAuthentication?.signOutCurrentUser()
            viewModelAuthentication?.updateStateCurrentUser(newValue = StateCurrentUser.NULE)
            viewModelGetReviews?.updateNotes(result = mutableListOf())
            viewModelAuthentication?.getCurrentUser()?.reload()
            viewModelGetReviews?.getCurrentUser()?.reload()
            viewModelGetReviews?.updateStateChangesOnListReviews(
                StateChangesOnListReviews.LOGOUTCURRENTUSER)
            if(authenticationUiState?.stateLoginAsVisitor == StateLoginAsVisitor.SUCCESS
                ){
                viewModelAuthentication.resetStateLoginAsVisitor()
            }
        }
        showDialog = false
        navController.navigate(RoutesMainScreens.LoginScreen.route){
            popUpTo(RoutesMainScreens.ProfileScreen.route){
                this.inclusive = true
            }
        }

    }

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = modifier
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
        ProfileSn(
            profilePhoto = {
                if(user?.photoUrl != null){
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(user.photoUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "image",
                        placeholder = painterResource(id = R.drawable.image_add_contact),
                        contentScale = ContentScale.FillBounds,
                        modifier = modifier
                            .clip(CircleShape)
                            .size(130.dp)
                    )

                }else{
                    Icon(
                        painter = painterResource(id = R.drawable.image_add_contact),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = modifier.size(130.dp)
                    )
                }
            },
            nameUser = "William Redondo" ,
            emailBottomAction = { /*TODO*/ },
            chatBottomAction = { /*TODO*/ },
            phoneBottomAction = { /*TODO*/ },
            bottomMenuActions = {expanded = !expanded},
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
            }
        )
        Box(
            modifier = modifier
                .padding(end = 45.dp, top = 60.dp)
        ) {
            DropdownMenu(
                offset = DpOffset.Zero,
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = modifier
                    .background(MaterialTheme.colorScheme.tertiaryContainer))
            {
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.suggest_app,
                            nameItemMenu = R.string.suggest_app,
                        )
                    },
                    onClick = {},
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.your_circle,
                            nameItemMenu = R.string.your_people
                        )
                    },
                    onClick = {},
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.family,
                            nameItemMenu = R.string.family_item
                        )
                    },
                    onClick = {},
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.activity,
                            nameItemMenu = R.string.activity_item
                        )
                    },
                    onClick = {},
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.shares,
                            nameItemMenu = R.string.shares_item
                        )
                    },
                    onClick = {},
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.setting_menu,
                            nameItemMenu = R.string.settings_items
                        )
                    },
                    onClick = {},
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.resource_private,
                            nameItemMenu = R.string.private_item
                        )
                    },
                    onClick = {},
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.saved_menu,
                            nameItemMenu = R.string.saved_items
                        )
                    },
                    onClick = {},
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.update,
                            nameItemMenu = R.string.update_item
                        )
                    },
                    onClick = {},
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.cahnge_account,
                            nameItemMenu = R.string.change_account
                        )
                    },
                    onClick = {},
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.log_out,
                            nameItemMenu = R.string.log_out_item
                        )
                    },
                    onClick = {
                        showDialog = true
                        expanded = false
                    },
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )
            }


            if (showDialog) {
                DialogOptionsPersonalized(
                    onDismiss = {  showDialog = false },
                    onConfirmActions = {
                        onLogOutConfirmed()
                        showDialog = false
                                       },
                    textCancelBottom = R.string.text_action_bottom_cancel,
                    textConfirmBottom = R.string.text_action_bottom_aceptar,
                    textInfoDialogMenu = R.string.titleAlertDialogMenu,
                    iconDialogMenu = R.drawable.warning_icon
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPanelControlPreview(){
    GupsMileTheme {
        ProfileScreenPanelControl(
            analytics = null,
            auth = null,
            viewModelAuthentication = null,
            viewModelGetReviews = null
        )
    }
}