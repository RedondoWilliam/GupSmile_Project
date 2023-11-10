package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeTopAppBar.homeTopAppBarPanelControl

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ensayo.example.ui.topAppBarHomePage.topBarElements.TopAppBarBody
import gupsmile.com.R
import gupsmile.com.data.firebaseManager.AuthManager
import gupsmile.com.ui.navigationApp.RoutesMainScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBarPanelControl(
    modifier: Modifier = Modifier,
    scroll: TopAppBarScrollBehavior?,
    navController: NavHostController = rememberNavController(),
    expandedMenuOptions: () -> Unit,
    auth: AuthManager?
){
    val user = auth?.getCurrentUser()

    val profileImage =
        if(user?.photoUrl != null){
            painterResource(id = R.drawable.selfie)
        }else{
            painterResource(id = R.drawable.profile_image)
        }

    TopAppBarBody(
        onSearchClicked = {  },
        scroll = scroll,
        navigationHomeProfile = {
            navController.navigate(RoutesMainScreens.ProfileScreen.route)
        },
        onLogOutConfirmed = {},
        expandedMenuOptions = expandedMenuOptions,
        profileImage = profileImage,
        profilePhoto = {
            if(user?.photoUrl != null){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user?.photoUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "image",
                    placeholder = painterResource(id = R.drawable.image_add_contact),
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier
                        .clip(CircleShape)
                        .size(38.dp)
                )

            }else{
                Icon(
                    painter = painterResource(id = R.drawable.image_add_contact),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = modifier.size(38.dp)
                )
            }
        }
    )
}