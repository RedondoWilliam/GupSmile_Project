package gupsmile.com.ui.subScreens.ChatScreen.emojiTray.sections.emojiSection

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun NavigationBarEmojis(modifier: Modifier = Modifier){


    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(4.dp)
        ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id =R.drawable.emoji_vector),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = { /*TODO*/ }
                ),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = Modifier.height(6.dp))
        Icon(
            painter = painterResource(id =R.drawable.emoji_vector),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = { /*TODO*/ }
                ),
            tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Icon(
            painter = painterResource(id =R.drawable.emoji_vector),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = { /*TODO*/ }
                ),
            tint =MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Icon(
            painter = painterResource(id =R.drawable.emoji_vector),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = { /*TODO*/ }
                ),
            tint =MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Icon(
            painter = painterResource(id =R.drawable.emoji_vector),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = { /*TODO*/ }
                ),
            tint =MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Icon(
            painter = painterResource(id =R.drawable.emoji_vector),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = { /*TODO*/ }
                ),
            tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(6.dp))
        Icon(
            painter = painterResource(id =R.drawable.emoji_vector),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = { /*TODO*/ }
                ),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = Modifier.height(6.dp))
        Icon(
            painter = painterResource(id =R.drawable.emoji_vector),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = { /*TODO*/ }
                ),
            tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Icon(
            painter = painterResource(id =R.drawable.emoji_vector),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    onClick = { /*TODO*/ }
                ),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )

    }

}



@Preview(
    showBackground = true,
    name = "Date Separator",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    name = "Date Separator Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun NavigationBarEmojisPreview(){
    GupsMileTheme {
        NavigationBarEmojis()
    }
}
