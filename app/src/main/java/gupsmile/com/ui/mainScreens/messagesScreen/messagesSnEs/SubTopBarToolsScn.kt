package gupsmile.com.ui.mainScreens.messagesScreen.messagesSnEs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun SubTopBarToolsScn(
    modifier: Modifier = Modifier,
    bottomContactActions: () -> Unit
){
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 23.dp, start = 17.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .width(150.dp)

            ) {

                Icon(
                    painter = painterResource(id = R.drawable.filter_messages),
                    contentDescription = "Search Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(27.dp)

                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        .clip(CircleShape)
                        .size(29.dp)
                        .background(Color.White)
                        .clickable { bottomContactActions() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.contact_messages),
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = modifier
                            .size(27.dp)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.setting),
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = modifier
                        .size(27.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.search_two),
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(25.dp)
                )
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun SubTopBarToolsScnPreview(){
    GupsMileTheme {
        SubTopBarToolsScn(
            bottomContactActions = {}
        )
    }
}