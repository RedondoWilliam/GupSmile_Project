package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeScreenSaved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun HomeSnSavedSbSn(
    modifier: Modifier = Modifier,
    galleryImages: @Composable () -> Unit
){
    
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement =  Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            galleryImages()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeSnMySavedSbSnPreview(){
    GupsMileTheme {
        HomeSnSavedSbSn(
            galleryImages = {
                Text(text = "esta es la pantalla de recursos.")
            }
        )
    }
}