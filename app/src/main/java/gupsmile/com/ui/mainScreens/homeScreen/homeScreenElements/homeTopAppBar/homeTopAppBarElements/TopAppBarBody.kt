package com.ensayo.example.ui.topAppBarHomePage.topBarElements

import android.icu.util.UniversalTimeScale
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.commonElements.LogOutDialogMenu
import gupsmile.com.ui.theme.GupsMileTheme


/**
 * Esta es una prueba para un cuadro de dialogo
 *
 * @param message El mensaje a mostrar en el cuerpo de diólogo.
 * @param onConfirm La acción a ejecutar cuando se confirme el cuadro de diólogo
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarBody(
    onSearchClicked: () -> Unit,
    scroll: TopAppBarScrollBehavior?,
    navigationHomeProfile: () -> Unit,
    onLogOutConfirmed: () -> Unit,
    expandedMenuOptions: () -> Unit,
    profileImage: Painter

    ){
    DefaultAppBar(
        scroll = scroll,
        onSearchClicked = onSearchClicked,
        navigationHomeProfile = navigationHomeProfile,
        onLogOutConfirmed = onLogOutConfirmed,
        expandedMenuOptions = expandedMenuOptions,
        profileImage = profileImage

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    modifier: Modifier = Modifier,
    onSearchClicked: () ->Unit = {},
    scroll: TopAppBarScrollBehavior?,
    navigationHomeProfile: () -> Unit,
    onLogOutConfirmed: () -> Unit,
    expandedMenuOptions: () -> Unit,
    profileImage: Painter
){


    TopAppBar(
        navigationIcon = {

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor =MaterialTheme.colorScheme.background
        ),
        title = {
            textCommonHomePage(
                stringResTextEntry = R.string.app_name,
                maxLinesResParameter = 1,
                lineHeightParameter = 27.sp,
                fontSizeStyleParameter = 27.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_bold)) ,
                colorStyleParameter = MaterialTheme.colorScheme.onBackground,
                modifier = modifier
            )

        },
        actions = {

            ProfileImageHome(
                modifier = Modifier
                    .padding(end = 10.dp),
                sizeImage = 44.dp,
                navigationHomeProfile =navigationHomeProfile,
                profileImage = profileImage
                )
        },
        scrollBehavior = scroll,
    )



}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun DefaultAppBarPreview(){
    GupsMileTheme{
        DefaultAppBar(
            scroll = null,
            navigationHomeProfile = {},
            onLogOutConfirmed = {},
            expandedMenuOptions = {},
            profileImage = painterResource(id = R.drawable.profile_image)
        )
    }
}
