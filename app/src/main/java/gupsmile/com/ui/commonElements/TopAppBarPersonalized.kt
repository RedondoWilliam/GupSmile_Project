package gupsmile.com.ui.commonElements

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun TopAppBarPersonalized(
    modifier: Modifier = Modifier,
    arrowBackTopBottom: () -> Unit,
    @StringRes titleTopAppBar: Int,
    bottomMenuActions: () -> Unit = {}
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        FloatingBottonDesignFixed(
            imageIcon = R.drawable.arrowback,
            coordinateX = 0.dp,
            coordinateY = 0.dp,
            sizeIcon = 20.dp,
            onclickBottomActions = {
                arrowBackTopBottom()
            }
        )

        textCommonHomePage(
            stringResTextEntry = titleTopAppBar,
            maxLinesResParameter = 1,
            lineHeightParameter = 22.sp,
            fontSizeStyleParameter = 22.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
            colorStyleParameter = MaterialTheme.colorScheme.onBackground,
            modifier = modifier
                .padding(start = 25.dp)
                .weight(0.3f)

        )
        Icon(
            painter = painterResource(id = R.drawable.menu_icon),
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(25.dp)
                .clickable { bottomMenuActions() }


        )
    }
}


@Composable
@Preview(showBackground = true)
fun TopAppBarPersonalizedPreview(){
    GupsMileTheme {
        TopAppBarPersonalized(
            arrowBackTopBottom = {},
            titleTopAppBar = R.string.add_contact_screen_title
        )
    }
}