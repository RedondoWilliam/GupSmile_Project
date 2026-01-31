package gupsmile.com.ui.subScreens.ChatScreen.proofEmojiTray

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun EmojiTrayProof(modifier: Modifier = Modifier){

    var stateEmojiTray by remember{ mutableStateOf(false) }
    val lazyListState = rememberLazyListState()

    val imePadding = WindowInsets.ime.asPaddingValues()

    // Optional: amount to move screen upward
    val trayHeight = 300.dp


    val offsetY by animateDpAsState(
        targetValue = if (stateEmojiTray) (-trayHeight) else 0.dp,
        label = "",
        animationSpec = tween(
            easing = LinearOutSlowInEasing
        )
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .animateContentSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.7f))
            .navigationBarsPadding()
            .statusBarsPadding(),
        contentAlignment = Alignment.BottomCenter

    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Bottom

        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .offset(y = offsetY)
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)),
                state = lazyListState,
                reverseLayout = true
            ) {
                items(20){index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ){
                        Text(
                            text = "This is the element number: ${index + 1}",
                            Modifier.clickable{stateEmojiTray = !stateEmojiTray}
                        )
                    }
                }

            }


        }
        AnimatedVisibility(
                visible =stateEmojiTray,
                enter = expandVertically(
                    animationSpec = tween(easing = LinearOutSlowInEasing)
                ),
                exit = shrinkVertically(
                    animationSpec = tween(easing = LinearOutSlowInEasing)
                )
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .height(300.dp)
                ) {
                    Text(text = "Hello this is the Emoji Tray!")
                }
            }

    }
}

@Composable
@Preview(showBackground = true)
fun EmojiTrayProofPreview(){
    GupsMileTheme(){
        EmojiTrayProof()
    }
}