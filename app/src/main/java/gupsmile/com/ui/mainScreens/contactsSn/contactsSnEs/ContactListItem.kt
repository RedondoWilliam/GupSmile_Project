package gupsmile.com.ui.mainScreens.contactsSn.contactsSnEs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.model.Contact
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import gupsmile.com.ui.theme.GupsMileTheme
import gupsmile.com.ui.viewModelPanelControl.viewModelContacts.ViewModelContacts

@Composable
fun ContactListItem(
    modifier: Modifier = Modifier,
    bottomActionsProfileImage: () -> Unit,
    profileImage: Painter,
    contact: Contact,
    navController: NavHostController?
){


    val viewModelContacts : ViewModelContacts = viewModel()
    val contactsUiState = viewModelContacts.uiState.collectAsState().value

    Row(
        modifier = modifier
            .padding(start = 20.dp, end = 15.dp, top = 2.5.dp, bottom = 2.5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(
               15.dp
            ))
            .height(55.dp)
            .clickable {
                navController?.navigate(route = RoutesMainScreens.InfoLocalContactPlCl.route + "/${contact.key}")
            }

        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = modifier.width(15.dp))
        Image(
            modifier = modifier
                .size(35.dp)
                .clip(CircleShape)
                .clickable { bottomActionsProfileImage() },
            painter = profileImage,
            contentDescription = "description",
            contentScale = ContentScale.Crop,
            )
        Spacer(modifier = modifier.width(15.dp))
        textCommonHomePageString(
            stringResTextEntry = contact.name + " " +  contact.lastName ,
            maxLinesResParameter = 1,
            lineHeightParameter = 17.sp,
            fontSizeStyleParameter = 17.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = MaterialTheme.colorScheme.onTertiaryContainer
        )

    }
}

@Composable
@Preview(showBackground = true)
fun ContactListItemPreview(){
    GupsMileTheme {
        val contact: Contact = Contact(name = "William")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            ContactListItem(
                bottomActionsProfileImage = { /*TODO*/ },
                profileImage = painterResource(id = R.drawable.selfie),
                contact = contact,
                navController = null
            )
        }
    }
}