package gupsmile.com.ui.subScreens.ChatScreen

import android.content.res.Configuration
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun TextBarChat(
    modifier: Modifier = Modifier,
    textUser: String = "",
    ontextchage: (String) -> Unit = {},
    onDoneClicked: () -> Unit = {},
    focusRequester: FocusRequester,
    enabled: Boolean = true,
    onClickEmojiActions: () -> Unit = {},
    focusStateInputBar: (Boolean) -> Unit = {},
    hasFocus: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){
    Surface(
        tonalElevation = 4.dp,
        shadowElevation = 1.dp,
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp),
        color = MaterialTheme.colorScheme.surfaceVariant

    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextFieldBar(
                textUser = textUser,
                ontextchage = ontextchage ,
                onDoneClicked  = onDoneClicked ,
                focusRequester = focusRequester,
                textOnTextfield = R.string.message_chat,
                backgroundTextfield = Color.Unspecified,
                enabled = enabled,
                onClickEmojiActions = onClickEmojiActions,
                focusStateInputBar = focusStateInputBar,
                hasFocus = hasFocus,
                interactionSource = interactionSource
            )


        }
    }
}

@Composable
private fun LifecycleEffect(
    onResume: () -> Unit = {},
    onPause: () -> Unit = {}
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val listener = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                onResume()
            }

            override fun onPause(owner: LifecycleOwner) {
                onPause()
            }

        }
        lifecycle.addObserver(listener)
        onDispose {
            lifecycle.removeObserver(listener)
        }
    }
}


@Composable
fun TextFieldBar(
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
    onClickEmojiActions: () -> Unit = {},
    focusStateInputBar: (Boolean) -> Unit = {},
    hasFocus: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
){


//    var hasFocus by rememberSaveable  { mutableStateOf(false) }


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
          //  .wrapContentSize()
    ) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(5.dp))
                .background(backgroundTextfield)
                .heightIn(min = 50.dp, max = 120.dp)
                .fillMaxWidth()
                ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .size(38.dp)
                    .clickable { onDoneClicked() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.emoji_vector),
                    contentDescription = "Search Icon",
                    tint = colorIcons,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {onClickEmojiActions()}
                )
            }

            Box(
                modifier = modifier
                 //   .padding(start = 10.dp, end = 16.dp, bottom = 10.dp, top= 10.dp)
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
                        .fillMaxWidth(0.7f)
                        .background(backgroundTextfield)
                        .onFocusChanged {
                            focusStateInputBar(it.isFocused)
                        }
                        .focusRequester(focusRequester)
                    ,
                    interactionSource = interactionSource,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                    ),
                    keyboardActions = KeyboardActions(
//                        onDone = {
//                            onDoneClicked()
//                        }
                    ),
                    enabled = enabled,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.outline),
                    decorationBox = { innerTextField ->

                        Box (
                            modifier = Modifier,
                            contentAlignment = Alignment.CenterStart
                        ) {
                            innerTextField()
                            if(!hasFocus){
                                Row {
                                    Spacer(modifier = Modifier
                                        .height(20.dp)
                                        .width(2.dp)
                                        .background(
                                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alphaAnim)
                                        ))
                                }
                            }
                            if(textUser == ""){
                                textCommonHomePage(
                                    stringResTextEntry = textOnTextfield,
                                    maxLinesResParameter = 1,
                                    lineHeightParameter = 18.sp,
                                    fontSizeStyleParameter = 18.sp ,
                                    fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_regular)),
                                    colorStyleParameter = MaterialTheme.colorScheme.onSurfaceVariant,
                                )

                            }
                        }


                    }
                )

            }


            Box(
                modifier = modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .size(38.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.attach_icon),
                    contentDescription = "Search Icon",
                    tint = colorIcons,
                    modifier = Modifier
                        .size(30.dp)
                )
            }

            Box(
                modifier = modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .size(38.dp)
                    .clickable { onDoneClicked() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sent_icon),
                    contentDescription = "Search Icon",
                    tint = colorIcons,
                    modifier = Modifier
                        .size(30.dp)
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
fun TextFieldBarPreview(
) {

    val focusRequester = remember { FocusRequester() }
    GupsMileTheme {

        Row(
            modifier = Modifier
                .fillMaxWidth()
              //  .wrapContentSize()
        ){
            TextFieldBar(
                textUser = "",
                ontextchage = {} ,
                onDoneClicked  = {} ,
                focusRequester = focusRequester,
                textOnTextfield = R.string.message_chat,
                backgroundTextfield = MaterialTheme.colorScheme.tertiaryContainer
            )
        }

    }
}
