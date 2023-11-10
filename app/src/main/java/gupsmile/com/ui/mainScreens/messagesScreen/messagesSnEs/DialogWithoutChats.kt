package gupsmile.com.ui.mainScreens.messagesScreen.messagesSnEs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
fun DialogWithoutChats(
    modifier: Modifier = Modifier,
    contentTextDialog: Int
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(30.dp)
            .background(MaterialTheme.colorScheme.background)
        ,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.without_contacts_icon),
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = modifier
                .size(140.dp)
        )
        Spacer(modifier = modifier.height(20.dp))
        textCommonHomePage(
            stringResTextEntry = contentTextDialog,
            maxLinesResParameter = 2,
            lineHeightParameter = 14.sp,
            fontSizeStyleParameter = 14.sp,
            fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_extra_light)) ,
            colorStyleParameter =MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = modifier.height(20.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun DialogWithoutChatsPreview(){
    GupsMileTheme {
       Column (
           modifier = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ){
           DialogWithoutChats(
               contentTextDialog = R.string.dialog_without_contacts_messages_screen
           )
       }
    }
}