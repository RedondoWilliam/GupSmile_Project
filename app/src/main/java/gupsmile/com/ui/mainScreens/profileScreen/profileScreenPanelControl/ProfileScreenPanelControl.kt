package gupsmile.com.ui.mainScreens.profileScreen.profileScreenPanelControl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AnalitycsManager
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.ui.commonElements.DropDownMenuItemPersonalized
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.commonElements.LogOutDialogMenu
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileNavigationUpSections.profileNavigationUpSectionsPanelControl.HomeProfileNavigationUpSectionsPanelControl
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.nameToolsEditProfile.NameToosEditProfileScreen
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.photoProfile.PhotoProfileScreenSection
//import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.photoProfile.PhotoProfileSectionPanelControl
//import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.photoProfile.ProfilePerfilPhoto
import gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.ProfileSbsSnPlCl.ProfileSubscreensPanelControl
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.theme.GupsMileTheme
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateCurrentUser
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.StateLoginAsVisitor
import gupsmile.com.ui.viewModelPanelControl.viewModelAuthentication.ViewModelAuthentication

@Composable
fun ProfileScreenPanelControl(
    modifier: Modifier = Modifier,
    analytics: AnalitycsManager?,
    auth: AuthManager?,
    navController: NavHostController = rememberNavController(),
    viewModelAuthentication: ViewModelAuthentication?
){
    analytics?.logScreenView(screenName = RoutesMainScreens.ProfileScreen.route)


    val authenticationUiState = viewModelAuthentication?.uiState?.collectAsState()?.value

    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember{ mutableStateOf(false) }



    val user = auth?.getCurrentUser()

    val profileImage =
        if(user?.photoUrl != null){
            painterResource(id = R.drawable.profile_image)
        }else{
            painterResource(id = R.drawable.profile_image)
        }

    val onLogOutConfirmed: () -> Unit = {
        viewModelAuthentication?.signOutCurrentUser()
        viewModelAuthentication?.updateStateCurrentUser(newValue = StateCurrentUser.NULE)
        if(authenticationUiState?.stateLoginAsVisitor == StateLoginAsVisitor.SUCCESS){
            viewModelAuthentication.resetStateLoginAsVisitor()
        }
        showDialog = false
        navController.navigate(RoutesMainScreens.LoginScreen.route){
            popUpTo(RoutesMainScreens.ProfileScreen.route){
                this.inclusive = true
            }
        }
    }

    Box {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
        ) {
            PhotoProfileScreenSection(
                profileImage =  profileImage
            )
            NameToosEditProfileScreen(
                nameUser = if(!user?.email.isNullOrEmpty())"${user?.email}" else "Anonimo"
            )
            HomeProfileNavigationUpSectionsPanelControl()
            ProfileSubscreensPanelControl()
        }
        FloatingBottonDesignFixed(
            imageIcon = R.drawable.menu,
            coordinateX = 15.dp,
            coordinateY = 20.dp,
            sizeIcon = 35.dp,
            onclickBottomActions = {
                expanded = !expanded
            }
        )

        Box(
           contentAlignment = Alignment.TopStart,
           modifier = modifier
               .padding(start = 15.dp, top = 70.dp)
        ) {
            DropdownMenu(
                offset = DpOffset.Zero,
                expanded = expanded,
                onDismissRequest = { expanded = false })
            {
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.suggest_app,
                            nameItemMenu = R.string.suggest_app
                        )
                    },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.your_circle,
                            nameItemMenu = R.string.your_people
                        )
                    },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.family,
                            nameItemMenu = R.string.family_item
                        )
                    },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.activity,
                            nameItemMenu = R.string.activity_item
                        )
                    },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.shares,
                            nameItemMenu = R.string.shares_item
                        )
                    },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.setting_menu,
                            nameItemMenu = R.string.settings_items
                        )
                    },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.resource_private,
                            nameItemMenu = R.string.private_item
                        )
                    },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.saved_menu,
                            nameItemMenu = R.string.saved_items
                        )
                    },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.update,
                            nameItemMenu = R.string.update_item
                        )
                    },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = {
                        DropDownMenuItemPersonalized(
                            imageIcon = R.drawable.cahnge_account,
                            nameItemMenu = R.string.change_account
                        )
                    },
                    onClick = {}
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
                    }
                )
            }


            if (showDialog) {
                LogOutDialogMenu(
                    onDismiss = {
                        showDialog = false
                    },
                    onConfirmLogOut = {
                        onLogOutConfirmed()
                    },
                    titleAlertDialogMenu = R.string.titleAlertDialogMenu,
                    confirmBottomText = R.string.confirmBottomText,
                    dimmissBottomText = R.string.dimmissBottomText
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
            viewModelAuthentication = null
        )
    }
}