package gupsmile.com.ui.subScreens.ChatScreen.emojiTray

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import gupsmile.com.ui.theme.GupsMileTheme

@Composable
fun mainImeScreen(
    modifier: Modifier = Modifier
){

    var onclickHere by remember { mutableStateOf(false) }
    var textUser by remember { mutableStateOf("") }


    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        bottomBar = {

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .navigationBarsPadding(),
                contentAlignment = Alignment.BottomCenter

            ) {
                Column(
                                   modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = textUser,
                        onValueChange = {textUser = it},
                        modifier = modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .background(MaterialTheme.colorScheme.secondary)
                        //.imePadding()
//                            .windowInsetsPadding(
//                                WindowInsets.ime.only(WindowInsetsSides.Bottom)
//                                )
                        //  .imePadding(),

                    )
                    if(!onclickHere){
                        Spacer(
                            modifier = Modifier
                                .windowInsetsPadding(WindowInsets.ime)
                                .fillMaxWidth()
                            // No le ponemos altura, el ime padding se encarga
                        )
                    }
                    if(onclickHere){
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondary)

                        ){
                        }
                        TextField(
                            value = textUser,
                            onValueChange = {textUser = it},
                            modifier = modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .background(MaterialTheme.colorScheme.secondary)
//                                .imePadding(),
                        )
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .heightIn(min = 302.dp, max = 400.dp)

                        ) {
//                            item{
//                                TextField(
//                                    value = textUser,
//                                    onValueChange = {textUser = it},
//                                    modifier = modifier
//                                        .fillMaxWidth()
//                                        .height(70.dp)
//                                        .background(MaterialTheme.colorScheme.secondary),
//                                )
//                            }

                            items(10) { index ->
                                Text(
                                    text = "Elemento ${index + 1}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                )
                            }

                        }

                    }


            }
            }
        }
    ) {padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.BottomCenter

        ) {
            Column(
            ) {
                Box(
                    modifier
                        .height(60.dp)
                        .width(80.dp)
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable(
                            onClick = { onclickHere = !onclickHere }
                        ),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(
                        text = "onclick here!",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }


        }
    }



}



@Composable
@Preview(showBackground = true)
fun mainImeScreenPreview(){
    GupsMileTheme {
        mainImeScreen()
    }
}
