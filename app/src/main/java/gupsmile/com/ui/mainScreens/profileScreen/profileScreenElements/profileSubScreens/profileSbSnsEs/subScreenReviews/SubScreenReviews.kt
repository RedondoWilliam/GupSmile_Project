package gupsmile.com.ui.mainScreens.profileScreen.profileScreenElements.profileSubScreens.profileSbSnsEs.subScreenReviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun SubScreenReviews(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "esta es la pantalla de reseñas.")
    }
}

@Composable
@Preview(showBackground = true)
fun SubScreenReviewsPreview(){
    GupsMileTheme {
        SubScreenReviews()
    }
}