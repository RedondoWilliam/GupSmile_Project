package gupsmile.com.ui.subScreens.ChatScreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme
import kotlin.math.roundToInt




@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BubbleChat(
    modifier: Modifier = Modifier,
    message: String = "",
    onChatClicked: () -> Unit = {},
    onLongPress: () -> Unit = {},
    backgroundMessage: Color = MaterialTheme.colorScheme.primaryContainer,
    timeMessage: String = "12:23"
) {

    var linesMessages by remember { mutableIntStateOf(0) }
    val widthMessage = measureTextWidth(message).value.roundToInt()

    val configuration = LocalConfiguration.current
    val bubbleMaxWidth = remember(configuration) {
        configuration.screenWidthDp.dp
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp, end = 14.dp, start = 14.dp),
        horizontalAlignment = Alignment.End,
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topEnd = 12.dp,
                        topStart = 12.dp,
                        bottomEnd = 2.dp,
                        bottomStart = 12.dp
                    )
                )
                .background(backgroundMessage)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = { onLongPress() },
                        onTap = { onChatClicked() }
                    )
                }
                .widthIn(max = bubbleMaxWidth*0.80f)

        ) {

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                PersonalizedTextMessage(
                    modifier = Modifier,
                    message = message,
                    linesMessages = linesMessages,
                    onLineCountChanged = { linesMessages = it }
                )

                if (linesMessages ==1 && widthMessage <  (0.45f*bubbleMaxWidth.value).roundToInt()) { // 🔑 puedes ajustar este threshold
                    TimeMessage(
                        timeMessage = timeMessage
                    )
                }
            }
            if (linesMessages >= 2 || widthMessage >=  0.45f*bubbleMaxWidth.value.roundToInt()) {
                TimeMessage(
                    timeMessage = timeMessage
                )
            }
        }
    }
}

@Composable
fun PersonalizedTextMessage(
    modifier: Modifier,
    message: String,
    linesMessages: Int,
    onLineCountChanged: (Int) -> Unit){
    Text(
        text = cleanMessage(message),

        maxLines = 1000,
        style = LocalTextStyle.current.merge(
            TextStyle(
                lineHeight = 20.sp,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                fontSize = 17.sp,
                lineHeightStyle = LineHeightStyle(
                    alignment =  LineHeightStyle.Alignment.Bottom,
                    trim = LineHeightStyle.Trim.None
                ),
                fontFamily = FontFamily(Font(R.font.roboto_regular)) ,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            ),

            ),
        overflow =  TextOverflow.Ellipsis,
        textAlign =  TextAlign.Start,
        modifier = Modifier
            .padding(
                top = 6.dp,
                bottom = if (linesMessages >1) 0.dp else 6.dp,
                start = 8.dp,
                end = 6.dp
            ),
        onTextLayout = { textLayoutResult ->
            onLineCountChanged(textLayoutResult.lineCount)
        }
    )
}

@Composable
fun TimeMessage(modifier: Modifier = Modifier, timeMessage: String){
    Row(
        modifier = Modifier
            .padding( end = 3.dp)
    ) {
        Text(
            text = timeMessage,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
        Icon(
            painter = painterResource(id = R.drawable.duble_check),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
            modifier = Modifier.size(18.dp).padding(start = 2.dp)
        )
    }
}


@Composable
fun measureTextWidth(text: String): Dp {
    val density = LocalDensity.current
    val textMeasure = rememberTextMeasurer()
    
    val result = textMeasure.measure(text = AnnotatedString(text))

    val widthPx = result.size.width
    return with(density) { widthPx.toDp() }
}

fun cleanMessage(input: String): String {
    return input
        .trim()
        .replace(Regex("\n+"), "\n")
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
fun BubbleChatPreview(){
    GupsMileTheme {
        BubbleChat(
            message = "mucho gusto amigos!"
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
fun BubbleChatPreviewTwo(){
    GupsMileTheme {
        BubbleChat(
            message = "Esto es una prueba de texto largo, mucho gusto amigos!"
        )
    }
}




