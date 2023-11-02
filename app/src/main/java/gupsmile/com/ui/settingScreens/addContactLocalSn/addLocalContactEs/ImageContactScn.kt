package gupsmile.com.ui.settingScreens.addContactLocalSn.addLocalContactEs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.commonElements.FloatingBottonDesignFixed
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun ImageContactScn(
    modifier: Modifier = Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.image_add_contact),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = modifier.size(100.dp)
        )
        FloatingBottonDesignFixed(
            imageIcon =  R.drawable.camera,
            coordinateX = 35.dp,
            coordinateY = 35.dp,
            sizeIcon = 20.dp,
            onclickBottomActions = {}
        )

    }
}

@Composable
@Preview(showBackground = true)
fun ImageContactScnPreview(){
    GupsMileTheme {
        ImageContactScn()
    }
}