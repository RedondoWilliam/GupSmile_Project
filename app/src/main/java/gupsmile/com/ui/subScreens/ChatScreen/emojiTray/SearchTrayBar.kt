package gupsmile.com.ui.subScreens.ChatScreen.emojiTray

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.subScreens.ChatScreen.ChatScreenMode
import gupsmile.com.ui.subScreens.ChatScreen.EmojiSearchMode.Active
import gupsmile.com.ui.subScreens.ChatScreen.SectionEmojiTray
import gupsmile.com.ui.subScreens.ChatScreen.UiStateScreen
import gupsmile.com.ui.subScreens.ChatScreen.ViewModelKeyboardController
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun SearchTrayBar(
    modifier: Modifier = Modifier,
    textUser: String = "",
    ontextchage: (String) -> Unit = {},
    onDoneClicked: () -> Unit = {},
    focusRequester: FocusRequester,
    enabled: Boolean = true,
    focusStateInputBar: (Boolean) -> Unit = {},
    screenUiState: UiStateScreen = UiStateScreen()
){

    Surface(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp),
        color = MaterialTheme.colorScheme.background

    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextFieldTrayBar(
                textUser = textUser,
                ontextchage = ontextchage ,
                onDoneClicked  = onDoneClicked ,
                focusRequester = focusRequester,
                textOnTextfield = R.string.message_chat,
                backgroundTextfield = MaterialTheme.colorScheme.background,
                enabled = enabled,
                focusStateInputBar = focusStateInputBar,
                screenUiState = screenUiState
            )


        }
    }
}

@Composable
fun TextFieldTrayBar(
    modifier: Modifier = Modifier,
    textUser: String,
    ontextchage: (String) -> Unit,
    onDoneClicked: () -> Unit,
    focusRequester: FocusRequester,
    enabled: Boolean = true,
    @StringRes textOnTextfield: Int,
    backgroundTextfield: Color,
    viewModelKeyboardController: ViewModelKeyboardController = hiltViewModel(),
    colorIcons: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    focusStateInputBar: (Boolean) -> Unit = {},
    screenUiState: UiStateScreen = UiStateScreen()
){


    var hasFocus by rememberSaveable  { mutableStateOf(false) }


    val alphaAnim by rememberInfiniteTransition(label = "Cursor")
        .animateFloat(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 530),
                repeatMode = RepeatMode.Reverse
            ),
            label = "Alpha"
        )



    Box(
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .height(45.dp)
                .fillMaxWidth(0.95f)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = modifier

            ){
                BasicTextField(
                    value = textUser,
                    onValueChange = {it:String ->
                        ontextchage(it)
                    },
                    textStyle = TextStyle(
                        MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_medium))
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .background(backgroundTextfield)
                        .onFocusChanged {
                            focusStateInputBar(it.isFocused)
                        }
                        .focusRequester(focusRequester)

                    ,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                    ),
                    enabled = enabled,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.outline),
                    decorationBox = { innerTextField ->

                        Box (
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            innerTextField()
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Box(
                                    modifier = modifier
                                        .padding(start = 20.dp)
                                        .clip(CircleShape)
                                        .size(25.dp)
                                        .clickable { onDoneClicked() },
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            id =  when(val mode = screenUiState.mode){
                                                is ChatScreenMode.EmojiTray -> {
                                                    when(mode.sectionMode){
                                                        SectionEmojiTray.EMOJI_SECTION -> R.drawable.emojimenuicon
                                                        SectionEmojiTray.DYNAMIC_EMOJI_SECTION ->R.drawable.dinamicemojiicon
                                                        SectionEmojiTray.GIF_SECTION -> R.drawable.stickersicon
                                                        SectionEmojiTray.STICKER_SECTION -> R.drawable.gificon
                                                    }
                                                }
                                                else -> R.drawable.search_two
                                            }
                                        ),
                                        contentDescription = "Search Icon",
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                        modifier = Modifier
                                            .size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }


                    }
                )

            }



        }

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
fun TextFieldTrayBarPreview(
) {

    GupsMileTheme {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
        ){
          SearchTrayBar(
            textUser = "",
            ontextchage = {} ,
            onDoneClicked  = {} ,
            focusRequester = FocusRequester(),
        )
        }


    }
}
