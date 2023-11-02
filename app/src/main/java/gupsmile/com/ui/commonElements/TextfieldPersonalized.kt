package gupsmile.com.ui.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePage
import gupsmile.com.R
import gupsmile.com.ui.theme.GupsMileTheme


@Composable
fun TextfieldPersonalized(
    modifier: Modifier = Modifier,
    textEntry: String,
    onTextChange: (String) -> Unit,
    focusRequester: FocusRequester,
    iconItem: Painter,
    textEntryDefault: Int
){

    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(start = 27.dp, end = 27.dp)
    ) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(30.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .height(44.dp)
                .padding(start = 10.dp, end = 16.dp)
                .width(320.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = modifier
                    .clip(CircleShape)
                    .size(35.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = iconItem,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = modifier.size(15.dp)
                )
            }
            BasicTextField(
                value = textEntry,
                onValueChange = {it:String -> onTextChange(it)}  ,
                textStyle = TextStyle(
                    MaterialTheme.colorScheme.onTertiary,
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_extra_light)),
                    textAlign = TextAlign.Start,
                    lineHeight = 19.sp,
                    lineHeightStyle  = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Bottom,
                        trim = LineHeightStyle.Trim.None
                    )
                ),
                modifier = modifier
                    .fillMaxWidth(0.97f)
                    .padding(start = 4.dp)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true,
                enabled = true
            )

        }
        if(textEntry == ""){
            textCommonHomePage(
                stringResTextEntry = textEntryDefault,
                maxLinesResParameter = 1,
                lineHeightParameter = 19.sp,
                fontSizeStyleParameter = 19.sp,
                fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_extra_light)) ,
                colorStyleParameter = MaterialTheme.colorScheme.onTertiary,
                modifier = modifier
                    .offset(x = 57.dp, y = 10.dp)
            )
        }


    }
}
@Composable
@Preview(showBackground = true)
fun TextfieldPersonalizedPreview(
) {

    GupsMileTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            TextfieldPersonalized(
                textEntry = "",
                onTextChange= {},
                focusRequester = FocusRequester(),
                iconItem =  painterResource(id = R.drawable.contact_new_contact),
                textEntryDefault = R.string.name_new_contact
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TextfieldPersonalizedDarkModePreview(
) {

    GupsMileTheme(
        darkTheme = true
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            TextfieldPersonalized(
                textEntry = "",
                onTextChange= {},
                focusRequester = FocusRequester(),
                iconItem =  painterResource(id = R.drawable.contact_new_contact),
                textEntryDefault = R.string.name_new_contact
            )
        }
    }
}